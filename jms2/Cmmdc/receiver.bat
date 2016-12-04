set JMS=d:\JavaApp\MessageQueue5.0\mq\lib
java -cp .;%JMS%\jms.jar;%JMS%\imq.jar MsgClientReceiver abc ABC
pause
