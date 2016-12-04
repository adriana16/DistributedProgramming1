keytool -import -alias server_pub -file server_pub.crt -keystore client.jks -storepass ClientJKS

keytool -list -keystore client.jks -storepass ClientJKS
