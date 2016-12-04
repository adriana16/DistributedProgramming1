keytool -import -alias client_pub -file client_pub.crt -keystore server.jks -storepass ServerJKS

keytool -list -keystore server.jks -storepass ServerJKS
