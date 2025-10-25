#!/usr/bin/env bash
set -euo pipefail

# --- CONFIG ---
README="README.md"
SECTION_HEADER="## 📂 Contents"
TMPFILE=$(mktemp)

# --- FUNCTIONS ---

generate_tree() {
  echo ""
  # Find second-level directories (like ep1/einstufungstest-k2)
  find . -mindepth 2 -maxdepth 2 -type d ! -path '*/.*' | sort | while read -r dir; do
    repo_url="https://github.com/${GITHUB_REPOSITORY}/tree/${GITHUB_REF_NAME}/${dir#./}"
    echo "- [\`${dir#./}\`](${repo_url})"
  done
  echo ""
}

# --- MAIN LOGIC ---

if ! grep -q "^${SECTION_HEADER}" "$README"; then
  echo "❌ ERROR: '${SECTION_HEADER}' not found in README.md"
  exit 1
fi

# Split README into three parts:
#   1. Before the section header
#   2. The section header itself
#   3. Everything after the section (until next '## ' or EOF)
awk -v header="$SECTION_HEADER" '
  BEGIN { in_section=0 }
  {
    if ($0 == header) {
      print $0 > "section_header.tmp"
      in_section=1
      next
    }

    if (in_section && $0 ~ /^## /) {
      print $0 > "after_section.tmp"
      in_section=0
      next
    }

    if (in_section) {
      next  # skip lines inside the section
    }

    if (in_section == 0 && !($0 ~ /^## / && FNR == 1)) {
      if (!in_section) print $0 > "before_section.tmp"
    }
  }
  END {
    if (in_section) print "" > "after_section.tmp"
  }
' "$README"

# Rebuild README
cat before_section.tmp > "$TMPFILE"
cat section_header.tmp >> "$TMPFILE"
generate_tree >> "$TMPFILE"
cat after_section.tmp >> "$TMPFILE"

mv "$TMPFILE" "$README"
rm -f before_section.tmp after_section.tmp section_header.tmp

echo "✅ README.md updated — only the '${SECTION_HEADER}' section replaced."
