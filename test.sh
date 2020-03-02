#!/bin/bash

set -x

tmpfile=$(mktemp /tmp/out.XXXXXX)
java -jar ./target/verify*.jar "./tests/assets/tiny.apk" > "$tmpfile" 2>&1 || true
if ! grep -q 'java.lang.Exception' "$tmpfile"; then
  exit 1
fi
java -jar ./target/verify*.jar "./tests/assets/tiny.s.apk" || exit 1
