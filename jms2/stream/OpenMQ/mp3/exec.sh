#!/bin/bash
JMS=/home/mk/JavaApp/glassfish4/mq/lib
classpath=./:$JMS/jms.jar:$JMS/imq.jar:jl1.0.1.jar
javac -cp $classpath *.java
java -cp $classpath MsgPS
