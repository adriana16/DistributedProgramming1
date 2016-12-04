rem Generarea semnaturii
keytool -genkey -alias tomcat -keyalg RSA -keystore \JavaApp\apache-tomcat-7.0.30\conf\tomcatKeystore -dname "cn=SE, ou=cs, o=unitbv, l=brasov, c=RO" -keypass 1q2w3e -storepass 1q2w3e
