set GLASSFISH_HOME=d:\JavaApp\glassfish4
start %GLASSFISH_HOME%\glassfish\bin\appclient -client dist\cmmdc_sender.jar 
start %GLASSFISH_HOME%\glassfish\bin\appclient -client dist\cmmdc_receiver.jar 