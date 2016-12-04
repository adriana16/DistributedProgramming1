keytool -genkey -alias client_full -keypass ClientKey -keystore client.jks -storepass ClientJKS -dname "cn=SE_client, ou=cs, o=unitbv, l=brasov, c=RO"

keytool -export -alias client_full -file client_pub.crt -keystore client.jks -storepass ClientJKS

rem Send "client_pub.crt" to server

rem Receive "server_pub.crt
