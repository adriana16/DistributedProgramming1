set ActiveMQVersion=5.12.1
set JMSactiveMQ=e:\JavaApp\apache-activemq-%ActiveMQVersion%
set JMSopenMQ=e:\JavaApp\MessageQueue5.1.1\mq\lib
set classpath=.;%JMSopenMQ%\jms.jar;%JMSopenMQ%\imq.jar;%JMSactiveMQ%\activemq-all-%ActiveMQVersion%.jar
echo %classpath%
