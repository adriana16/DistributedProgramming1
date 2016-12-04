rem Compilarea si arhivarea aplicatiei Java
set classpath=lib\mysql-connector-java-5.1.18-bin.jar
javac agendae\*.java
jar cmfv myManifest.mf agendae0.jar agendae\*.class
pause
rem Generarea semnaturii
keytool -genkey -keystore myKeystore -alias myself -dname "cn=SE, ou=cs, o=unitbv, l=brasov, c=RO" -keypass abc123 -storepass 123abc
keytool -selfcert -alias myself -keystore myKeystore -keypass abc123 -storepass 123abc
pause
rem Certificarea resurselor
copy lib\mysql-connector-java-5.1.18-bin.jar .
jarsigner -keystore myKeystore -signedjar agendae.jar -keypass abc123 -storepass 123abc agendae0.jar myself
jarsigner -keystore myKeystore -keypass abc123 -storepass 123abc mysql-connector-java-5.1.18-bin.jar myself
jarsigner -verify agendae.jar mysql-connector-java-5.1.18-bin.jar
