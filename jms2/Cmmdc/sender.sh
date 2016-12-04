#!/bin/bash
JMS=/home/mk/JavaApp/glassfish4/mq/lib
classpath=./:$JMS/jms.jar:$JMS/imq.jar;
# export classpath
java -cp $classpath MsgClientSender $1 $2
