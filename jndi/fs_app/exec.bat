set classpath=.;fscontext-4.2.jar

javac Lookup.java
java -Djava.naming.factory.initial=com.sun.jndi.fscontext.RefFSContextFactory Lookup
