set PATH=e:\JavaApp\apache-maven-3.3.9\bin;%PATH%
set JAVA_HOME=c:\Program Files\Java\jdk1.8.0_102

mvn -e exec:java -Dexec.mainClass="cmmdc.server.CmmdcImpl" 
