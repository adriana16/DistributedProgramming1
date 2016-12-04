#!/bin/bash
GLASSFISH_HOME=/home/mk/JavaApp/glassfish4
#PATH=$GLASSFISH_HOME/bin:$PATH
export GLASSFISH_HOME
$GLASSFISH_HOME/bin/asadmin start-domain domain1
 

