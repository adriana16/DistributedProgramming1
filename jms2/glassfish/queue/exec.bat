set GLASSFISH_HOME=d:\JavaApp\glassfish-4.1\glassfish4
set PATH=%GLASSFISH_HOME%\glassfish\bin;%GLASSFISH_HOME%\bin;%PATH%
start appclient -client sender\sender.jar 
start appclient -client syncrcv\syncrcv.jar 
pause
start appclient -client asyncrcv\asyncrcv.jar 
start appclient -client sender\sender.jar 
rem -targetserver localhost:3700

