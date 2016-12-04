set GLASSFISH_HOME=d:\JavaApp\glassfish3
set PATH=%GLASSFISH_HOME%\glassfish\bin;%GLASSFISH_HOME%\bin%PATH%
start appclient -client subscriber\subscriber.jar -targetserver localhost:3700
start appclient -client publisher\publisher.jar -targetserver localhost:3700
