javac Cmmdcfx.java
jar cmfv myManifest.mf cmmdc0.jar *.class 
rem Generarea semnaturii
keytool -genkey -keystore myKeystore -alias myself -dname "cn=XYZ, ou=cs, o=unitbv, l=brasov, c=RO" -keypass abc123 -storepass 123abc
keytool -selfcert -alias myself -keystore myKeystore -keypass abc123 -storepass 123abc
pause
rem Certificarea resurselor
jarsigner -keystore myKeystore -signedjar cmmdc.jar -keypass abc123 -storepass 123abc cmmdc0.jar myself
jarsigner -verify cmmdc.jar
