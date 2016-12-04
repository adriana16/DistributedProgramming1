set GLASSFISH_HOME=e:\JavaApp\glassfish4
del %GLASSFISH_HOME%\glassfish\domains\domain1\logs\server.log
set PATH=%GLASSFISH_HOME%\bin;%PATH%
set JAVA_HOME=c:\Progra~1\Java\jdk1.8.0_66
asadmin start-domain domain1 
rem asadmin start-domain --verbose

rem asadmin  start-domain  --domaindir e:\gfish domain2
