#!/bin/bash
mvn package
mkdir -p ./dist
cp ./target/verify-0.0.1-SNAPSHOT.jar ./dist/verify.jar
zip -d ./dist/verify.jar META-INF*

cd ./verify_jar/
zip ./../dist/verify.jar ./META-INF/MANIFEST.MF