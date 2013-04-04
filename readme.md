`verify.jar` will check to ensure the APK is signed with Appium's debug cert.

```java
$ java -jar verify.jar no_cert.apk
Exception in thread "main" java.lang.Exception: No cert. APK is not signed.
	at v.Verify.verify(Verify.java:52)
	at v.Verify.main(Verify.java:59)
```

```bash
$ java -jar verify.jar valid_cert.apk
```

```java
$ java -jar verify.jar invalid_cert.apk
Exception in thread "main" java.lang.Exception: Invalid cert.
	at v.Verify.verify(Verify.java:44)
```