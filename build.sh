#!/usr/bin/env sh

cd native
cargo build --release "$@"
cd ..
./gradlew build
