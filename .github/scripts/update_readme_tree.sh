#!/usr/bin/env bash
set -euo pipefail

# --- CONFIG ---
README="README.md"
SECTION_START="## 📂 Contents"
TMPFILE=$(mktemp)

# --- FUNCTIONS ---

# Generate markdown tree of 2nd-level project folders
generate_tree() {
  echo ""
  echo "📦 **Projects**"
  echo ""

  # Find second-level directories (like ep1/einstufungstest-k2)
  find . -mindepth 2 -maxdepth 2 -type d ! -path '*/.*' | sort | while read -r dir; do
    # Create markdown link to GitHub folder
    repo_url="https://github.com/${GITHUB_REPOSITORY}/tree/${GITHUB_REF_NAME}/${dir#./}"
    echo "- [\`${dir#./}\`](${repo_url})"
  done
  echo ""
}

# --- MAIN LOGIC ---
# Extract everything before the section start
awk -v marker="$SECTION_START" '
  $0 == marker { exit } { print }
' "$README" > "$TMPFILE"

# Add the section header
echo "$SECTION_START" >> "$TMPFILE"
echo "" >> "$TMPFILE"

# Append generated tree
generate_tree >> "$TMPFILE"

# Write back to README
mv "$TMPFILE" "$README"

echo "✅ README.md updated with new folder tree."
