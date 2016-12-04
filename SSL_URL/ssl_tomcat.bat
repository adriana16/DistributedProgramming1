rem Generarea semnaturii
keytool -genkey -alias tomcat -keyalg RSA -keystore tomcatKeystore -dname "cn=XYZ, ou=cs, o=unitbv, l=brasov, c=RO" -keypass 1q2w3e -storepass 1q2w3e
