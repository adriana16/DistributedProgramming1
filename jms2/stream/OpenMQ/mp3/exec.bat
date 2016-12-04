set JMS=e:\JavaApp\MessageQueue5.1.1\mq\lib
set classpath=.;%JMS%\jms.jar;%JMS%\imq.jar;jl1.0.1.jar
javac *.java
java MsgPS
