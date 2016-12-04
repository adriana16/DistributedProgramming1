set JAVA_HOME=c:\Program Files\Java\jdk1.8.0_77
set GRADLE_HOME=e:\JavaApp\gradle-2.12
%GRADLE_HOME%\bin\gradle.bat clean assemble run -Dexec.args="-ORBInitialPort 1050 -ORBInitialHost localhost"
