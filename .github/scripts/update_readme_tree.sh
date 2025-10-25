#!/usr/bin/env bash
# .github/scripts/update_readme_tree.sh
set -euo pipefail

README="README.md"
SECTION_HEADER="## 📂 Contents"
TMPFILE="$(mktemp)"
GITHUB_REPOSITORY="${GITHUB_REPOSITORY:-your-org/your-repo}"    # CI sets this; fallback for local runs
GITHUB_REF_NAME="${GITHUB_REF_NAME:-main}"                     # CI sets this; fallback to main

# Generate the markdown list of second-level directories
generate_tree() {
  # blank line after header for readability
  echo ""

  # find second-level dirs, ignore hidden dirs and vendor dirs
  # -mindepth 2 -maxdepth 2 ensures only second-level
  find . -mindepth 2 -maxdepth 2 -type d \
    ! -path '*/.*' \
    ! -path './node_modules*' \
    ! -path './.git*' \
    | sed 's|^\./||' \
    | sort \
    | while IFS= read -r dir; do
        # build link to the folder on GitHub
        # Ensure no leading ./ and URL-escape spaces (basic)
        safe_dir="$(printf '%s' "$dir" | sed 's/ /%20/g')"
        repo_url="https://github.com/${GITHUB_REPOSITORY}/tree/${GITHUB_REF_NAME}/${safe_dir}"
        echo "- [\`${dir}\`](${repo_url})"
      done

  echo ""
}

# Ensure section header exists
header_line="$(grep -n -m1 -xF "$SECTION_HEADER" "$README" 2>/dev/null || true)"
if [ -z "$header_line" ]; then
  echo "❌ ERROR: Section header '${SECTION_HEADER}' not found in ${README}."
  exit 1
fi

# grep -n returns "line:content"; extract line number
header_line_number="$(printf '%s' "$header_line" | cut -d: -f1)"

# Find the first '## ' heading AFTER the header line (next section start)
next_section_line_number="$(awk -v h="$header_line_number" 'NR>h && /^## / { print NR; exit }' "$README" || true)"

# Build new README in temporary file
# 1) everything up to and including the header
head -n "$header_line_number" "$README" > "$TMPFILE"

# 2) generated tree (replace the old content under the header)
generate_tree >> "$TMPFILE"

# 3) append the rest (from next_section_line_number to EOF) if it exists
if [ -n "$next_section_line_number" ]; then
  # append starting from that line (which is the next '## ' header)
  tail -n +"$next_section_line_number" "$README" >> "$TMPFILE"
fi

# Replace README atomically
mv "$TMPFILE" "$README"
chmod 644 "$README"

echo "✅ Updated '${SECTION_HEADER}' in ${README} (replaced exactly in-place)."
