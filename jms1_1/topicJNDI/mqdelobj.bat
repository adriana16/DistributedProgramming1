set PATH=d:\JavaApp\MessageQueue4_5_2\mq\bin;%PATH%
imqobjmgr delete -t qf -l "ConnectionFactory" -j java.naming.provider.url=file:///d:/Temp	-j java.naming.factory.initial=com.sun.jndi.fscontext.RefFSContextFactory 
imqobjmgr delete -t tf -l "ConnectionFactory" -j java.naming.provider.url=file:///d:/Temp	-j java.naming.factory.initial=com.sun.jndi.fscontext.RefFSContextFactory 
imqobjmgr delete -t q -l "queue" -j java.naming.provider.url=file:///d:/Temp	-j java.naming.factory.initial=com.sun.jndi.fscontext.RefFSContextFactory 
imqobjmgr delete -t t -l "topic" -j java.naming.provider.url=file:///d:/Temp	-j java.naming.factory.initial=com.sun.jndi.fscontext.RefFSContextFactory 
