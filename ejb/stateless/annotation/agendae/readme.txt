Derby utilizeaza portul 2527.

server.bat:
set JAVA_HOME=c:\Program Files\Java\jdk1.8.0_66
set DERBY_HOME=e:\JavaApp\db-derby-10.12.1.1-bin
%DERBY_HOME%\bin\startNetworkServer.bat -h 0.0.0.0 -noSecurityManager -p 2527
