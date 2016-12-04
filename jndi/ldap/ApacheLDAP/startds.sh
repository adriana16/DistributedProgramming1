#!/bin/bash
APACHEDS_HOME=/home/mk/JavaApp/apacheds-2.0.0-M23/bin
cd $APACHEDS_HOME
# Parametrul cerut in comanda urmatoare va fi start / stop
./apacheds.sh $1
