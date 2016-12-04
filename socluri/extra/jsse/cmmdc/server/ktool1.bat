keytool -genkey -alias server_full -keypass ServerKey -keystore server.jks -storepass ServerJKS -dname "cn=SE_server, ou=cs, o=unitbv, l=brasov, c=RO"

keytool -export -alias server_full -file server_pub.crt -keystore server.jks -storepass ServerJKS

rem Send "server_pub.crt" to Client

rem Receive "client_pub.crt

