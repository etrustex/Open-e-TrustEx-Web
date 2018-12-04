@echo off

set CLASSPATH=%CLASSPATH%;./../lib/bcprov-jdk15on-1.55.jar
set CLASSPATH=%CLASSPATH%;./../lib/etx-wacc-applet-${project.version}.jar;./../lib/etrustex-functional-test-${project.version}.jar
set CLASSPATH=%CLASSPATH%;./../lib/etx-wacc-node-api-${project.version}.jar;./../lib/etx-wacc-model-${project.version}.jar;./../lib/etx-wacc-security-${project.version}.jar;./../lib/etx-wacc-utils-${project.version}.jar
set CLASSPATH=%CLASSPATH%;./../lib/bcpkix-jdk15on-1.55.jar;./../lib/xades4j-1.3.0.jar;./../lib/guice-4.1.0.jar;./../lib/guice-multibindings-4.1.0.jar
set CLASSPATH=%CLASSPATH%;./../lib/xmlsec-2.0.7.jar;./../lib/commons-logging-1.2.jar;./../lib/commons-codec-1.10.jar;./../lib/log4j-1.2.17.jar

java -cp "%CLASSPATH%" -Dlog4j.configuration=file:../resources/log4j.properties eu.europa.ec.etrustex.tools.EncryptPassword

if errorlevel 0 (
   echo Successfully encrypted the password in the file encrypted.txt
)