set JAVA_HOME=c:\Program Files\Java\jdk1.8.0_77
set GRADLE_HOME=e:\JavaApp\gradle-2.12
set codebase=e:/mk/distr/gradle/rmi/cmmdc/server/build/classes/main/
%GRADLE_HOME%\bin\gradle.bat clean assemble run -Djava.rmi.server.codebase=file://%codebase% -Dexec.args="localhost 1099"
