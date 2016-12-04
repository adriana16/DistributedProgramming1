#!/bin/bash
cp=./:fscontext-4.2.jar

javac Lookup.java
java -cp $cp -Djava.naming.factory.initial=com.sun.jndi.fscontext.RefFSContextFactory Lookup
