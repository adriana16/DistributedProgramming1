#!/bin/bash
JMS=/home/mk/JavaApp/glassfish4/mq/lib
classpath=./:$JMS/jms.jar:$JMS/imq.jar;
javac -cp $classpath *.java
java -cp $classpath MsgPS
