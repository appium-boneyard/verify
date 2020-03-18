`verify.jar` will check to ensure the APK is signed with Appium's debug cert.

## Development

Building:

```bash
./build.sh
```

Testing:

```bash
./test.sh
```

## Release

New releases are published to GitHub automatically by CI agent.
It is only necessary to push a new version tag to `master`:

```bash
git tag -a 1.0 -m 1.0
git push --tags origin master
git push --tags remote master
```

## Usage

```bash
$ java -jar verify.jar no_cert.apk
Exception in thread "main" java.lang.Exception: No cert. APK is not signed.
	at v.Verify.verify(Verify.java:52)
	at v.Verify.main(Verify.java:59)
```

```bash
$ java -jar verify.jar valid_cert.apk
```

```bash
$ java -jar verify.jar invalid_cert.apk
Exception in thread "main" java.lang.Exception: Invalid cert.
	at v.Verify.verify(Verify.java:44)
```
