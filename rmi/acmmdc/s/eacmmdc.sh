#!/bin/bash
cale=/home/mk/DISTR1/rmi/acmmdc
export CLASSPATH=$cale/s/public/classes/cmmdc.jar:$cale/s/public/classes
java -Djava.rmi.server.codebase=file:$cale/s/public/classes/ -Dmyactivation.impl.codebase=file:$cale/s/public/classes/ -Dmyactivation.name=CmmdcServer -Dmyactivation.file="" -Dmyactivation.policy=group.policy acmmdc.Setup acmmdc.CmmdcActivabil

