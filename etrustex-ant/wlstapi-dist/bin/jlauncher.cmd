@ECHO OFF
SETLOCAL
			
set WSCRIPTS_HOME=%~dp0..
REM set up WL_HOME, the root directory of your WebLogic installation
if NOT EXIST %WSCRIPTS_HOME%\bin goto wsScriptseNotSet

REM set up WL_HOME, the root directory of your WebLogic installation
if %WL_HOME%x == x goto wlhomeNotSet

@ echo DEBUG REPORT ................ > debug.txt

CALL "%WL_HOME%\server\bin\setWLSEnv.cmd" >>debug.txt 2>>&1

@ echo.%LOG%
REM SET CLASSPATH=%CLASSPATH%;%WSCRIPTS_HOME%/bin;%WL_HOME%\common\lib\config.jar;%WL_HOME%\common\eval\pointbase\lib\pbembedded51.jar;%WL_HOME%\common\eval\pointbase\lib\pbtools51.jar;%WL_HOME%\common\eval\pointbase\lib\pbclient51.jar
SET CLASSPATH=%CLASSPATH%;%WSCRIPTS_HOME%/bin;%WSCRIPTS_HOME%/lib/jpythonEU.jar
REM Service bus detection
if exist %WL_HOME%\..\license.bea (
find /i "Service Bus" %WL_HOME%\..\license.bea >>debug.txt 2>>&1
if %errorlevel% == 0 set CLASSPATH=%CLASSPATH%;%WL_HOME%\servicebus\lib\sb-public.jar;%WL_HOME%\servicebus\lib\sb-internal.jar;%WL_HOME%\servicebus\lib\sb-common.jar;%WL_HOME%\servicebus\lib\uddi_library.jar;%WL_HOME%\servicebus\lib\uddi_client_v3.jar
)
REM Service bus 3.0
REM TODO FAire un test sur la chaine
if %errorlevel% == 0 set CLASSPATH=%CLASSPATH%;%WL_HOME%\..\alsb_3.0\lib\sb-kernel-api.jar;%WL_HOME%\..\modules\com.bea.common.configfwk_1.1.0.0.jar;%WL_HOME%\common\eval\pointbase\lib\pbclient51.jar;%WL_HOME%\common\eval\pointbase\lib\pbembedded51.jar

REM OSB 10gR3
if exist %WL_HOME%\..\license.bea (
find /i "Oracle Service Bus" %WL_HOME%\..\registry.xml >>debug.txt 2>>&1
if %errorlevel% == 0 (
set CLASSPATH=%CLASSPATH%;%WL_HOME%\..\osb_10.3\lib\sb-kernel-api.jar;%WL_HOME%\..\modules\com.bea.common.configfwk_1.2.0.0.jar;%WL_HOME%\common\eval\pointbase\lib\pbclient57.jar;%WL_HOME%\common\eval\pointbase\lib\pbembedded57.jar
)
)

REM OSB 10.3.1.0
find /i "Oracle Service Bus" %WL_HOME%\..\registry.xml >>debug.txt 2>>&1
if %errorlevel% == 0 (
set CLASSPATH=%CLASSPATH%;%WL_HOME%\..\modules\com.bea.common.configfwk_1.2.1.0.jar
)

REM find /i "release=""10.0" %WL_HOME%\..\license.bea >>debug.txt 2>>&1
find /i "<component name=""WebLogic Server"" version=""10.0" %WL_HOME%\..\registry.xml >>debug.txt 2>>&1
if %errorlevel% == 0 goto isVersion10

find /i "version=""10.3.0.0" %WL_HOME%\..\registry.xml >>debug.txt 2>>&1
if %errorlevel% == 0 goto isVersion10.3

find /i "version=""10.3.1.0" %WL_HOME%\..\registry.xml >>debug.txt 2>>&1
if %errorlevel% == 0 goto isVersion10.3.1

find /i "version=""10.3.2.0" %WL_HOME%\..\registry.xml >>debug.txt 2>>&1
if %errorlevel% == 0 goto isVersion10.3.2

REM find /i "version=""10.3.3.0" %WL_HOME%\..\registry.xml >>debug.txt 2>>&1
find /i "<component name=""WebLogic Server"" version=""10.3.3.0" %WL_HOME%\..\registry.xml >>debug.txt 2>>&1
if %errorlevel% == 0 goto isVersion10.3.3

find /i "<component name=""WebLogic Server"" version=""10.3.4.0" %WL_HOME%\..\registry.xml >>debug.txt 2>>&1
if %errorlevel% == 0 goto isVersion10.3.4
			
find /i "<component name=""WebLogic Server"" version=""10.3.5.0" %WL_HOME%\..\registry.xml >>debug.txt 2>>&1
if %errorlevel% == 0 goto isVersion10.3.5

find /i "<component name=""WebLogic Server"" version=""10.3.6.0" %WL_HOME%\..\registry.xml >>debug.txt 2>>&1
if %errorlevel% == 0 goto isVersion10.3.6

find /i "<component name=""WebLogic Server"" version=""12.1.1.0" %WL_HOME%\..\registry.xml >>debug.txt 2>>&1
if %errorlevel% == 0 goto isVersion12.1.1

find /i "<distribution status=""installed"" name=""WebLogic Server"" version=""12.1.2.0.0" %WL_HOME%\..\inventory\registry.xml >>debug.txt 2>>&1
if %errorlevel% == 0 goto isVersion12.1.2

find /i "<distribution status=""installed"" name=""WebLogic Server"" version=""12.1.3.0.0" %WL_HOME%\..\inventory\registry.xml >>debug.txt 2>>&1
if %errorlevel% == 0 goto isVersion12.1.3

:isVersion10
REM @ echo isVersion10
set CLASSPATH=%CLASSPATH%;%WL_HOME%\server\lib;%WL_HOME%\..\modules\com.bea.cie.config_5.1.3.0.jar
set JYTHON_OPTION=-Dprod.props.file=%WL_HOME%\.product.properties
goto buildLaunch

:isVersion10.3
REM @ echo isVersion103
set CLASSPATH=%CLASSPATH%;%WL_HOME%\server\lib;%WL_HOME%\..\modules\com.bea.cie.config_5.2.0.0.jar
set JYTHON_OPTION=-Dprod.props.file=%WL_HOME%\.product.properties
goto buildLaunch

:isVersion10.3.1
REM @ echo isVersion1031
set CLASSPATH=%CLASSPATH%;%WL_HOME%\server\lib;%WL_HOME%\..\modules\com.bea.cie.config_6.0.0.0.jar
set JYTHON_OPTION=-Dprod.props.file=%WL_HOME%\.product.properties
goto buildLaunch

:isVersion10.3.2
REM @ echo isVersion1032
set CLASSPATH=%CLASSPATH%;%WL_HOME%\server\lib;%WL_HOME%\..\modules\com.bea.cie.config_6.1.0.0.jar
set JYTHON_OPTION=-Dprod.props.file=%WL_HOME%\.product.properties
goto buildLaunch

:isVersion10.3.3
REM @ echo isVersion1033
REM set CLASSPATH=%CLASSPATH%;%WL_HOME%\server\lib;%WL_HOME%\..\modules\com.oracle.cie.config-wls_7.0.0.0.jar
REM set CLASSPATH=%WL_HOME%\server\lib;%WL_HOME%\..\modules\com.oracle.cie.config-wls_7.0.0.0.jar;%CLASSPATH%
REM set CLASSPATH=%WL_HOME%\server\lib;%CLASSPATH%
set CLASSPATH=%WL_HOME%/server/lib/Weblogic.jar;%WL_HOME%\server\lib;%CLASSPATH%
set JYTHON_OPTION=-Dprod.props.file=%WL_HOME%\.product.properties
goto buildLaunch
			
:isVersion10.3.4
REM @ echo isVersion1034
REM set CLASSPATH=%CLASSPATH%;%WL_HOME%\server\lib;%WL_HOME%\..\modules\com.oracle.cie.config-wls_7.1.0.0.jar
REM set CLASSPATH=%WL_HOME%\server\lib;%WL_HOME%\..\modules\com.oracle.cie.config-wls_7.1.0.0.jar;%CLASSPATH%
REM set CLASSPATH=%WL_HOME%\server\lib;%CLASSPATH%
set CLASSPATH=%WL_HOME%/server/lib/Weblogic.jar;%WL_HOME%\server\lib;%CLASSPATH%
set JYTHON_OPTION=-Dprod.props.file=%WL_HOME%\.product.properties
goto buildLaunch

:isVersion10.3.5
REM @ echo isVersion1035
REM set CLASSPATH=%CLASSPATH%;%WL_HOME%\server\lib;%WL_HOME%\..\modules\com.oracle.cie.config-wls_7.1.0.0.jar
REM set CLASSPATH=%WL_HOME%\server\lib;%WL_HOME%\..\modules\com.oracle.cie.config-wls_7.1.0.0.jar;%CLASSPATH%
REM set CLASSPATH=%WL_HOME%\server\lib;%CLASSPATH%
set CLASSPATH=%WL_HOME%/server/lib/Weblogic.jar;%WL_HOME%\server\lib;%CLASSPATH%
set JYTHON_OPTION=-Dprod.props.file=%WL_HOME%\.product.properties
goto buildLaunch

:isVersion10.3.6
REM @ echo isVersion1036
REM set CLASSPATH=%CLASSPATH%;%WL_HOME%\server\lib;%WL_HOME%\..\modules\com.oracle.cie.config-wls_7.1.0.0.jar
REM set CLASSPATH=%WL_HOME%\server\lib;%WL_HOME%\..\modules\com.oracle.cie.config-wls_7.1.0.0.jar;%CLASSPATH%
REM set CLASSPATH=%WL_HOME%\server\lib;%CLASSPATH%
set CLASSPATH=%WL_HOME%/server/lib/Weblogic.jar;%WL_HOME%\server\lib;%CLASSPATH%
set JYTHON_OPTION=-Dprod.props.file=%WL_HOME%\.product.properties
goto buildLaunch

:isVersion12.1.1
REM @ echo isVersion12.1.1
set CLASSPATH=%WL_HOME%/server/lib/Weblogic.jar;%WL_HOME%\server\lib;%CLASSPATH%
set JYTHON_OPTION=-Dprod.props.file=%WL_HOME%\.product.properties
goto buildLaunch

:isVersion12.1.2
REM @ echo isVersion12.1.2
set CLASSPATH=%WL_HOME%/server/lib/Weblogic.jar;%WL_HOME%\server\lib;%CLASSPATH%
set JYTHON_OPTION=-Dprod.props.file=%WL_HOME%\.product.properties
goto buildLaunch

:isVersion12.1.3
REM @ echo isVersion12.1.3
set CLASSPATH=%WL_HOME%/server/lib/Weblogic.jar;%WL_HOME%\server\lib;%CLASSPATH%
set JYTHON_OPTION=-Dprod.props.file=%WL_HOME%\.product.properties
goto buildLaunch

:buildLaunch		
REM set JYTHON_OPTION=%JYTHON_OPTION% -Dbea.home=%BEA_HOME% -Dweblogic.home=%WL_HOME% -Dpython.path=%WL_HOME%\common\wlst\modules;%WSCRIPTS_HOME%\API;%WL_HOME%\common\wlst\modules\jython-modules.jar\Lib; -Dpython.cachedir=%TEMP%\%USERNAME%\cachedir 
set JYTHON_OPTION=%JYTHON_OPTION% -Dpython.path=%WL_HOME%\common\wlst\modules;%WSCRIPTS_HOME%\wlst;%WL_HOME%\common\wlst\modules\jython-modules.jar\Lib;%WSCRIPTS_HOME%/lib/wlstscripts.jar -Dpython.cachedir=%TEMP%\%USERNAME%\cachedir 

REM @ echo CLASSPATH=%CLASSPATH%
REM @ echo JYTHON_OPTION=%JYTHON_OPTION%
REM @ echo JAVA_OPTION=%JAVA_OPTION%

REM Test arguments are correct
IF NOT EXIST "%1" (
echo "ERROR : Can't find python file %1"
GOTO end 
)

REM @ echo ------------------------------------------------------------------------------
REM @ echo %JAVA_HOME%\bin\java -Xmx512m %JAVA_OPTION% org.python.util.jython  %JYTHON_OPTION% %*
REM @ echo ------------------------------------------------------------------------------
@ %JAVA_HOME%\bin\java -Xmx512m %JAVA_OPTION% org.python.util.jython %JYTHON_OPTION% %*

ENDLOCAL
goto end

:wlhomeNotSet
@ echo The WL_HOME environment variable is not defined correctly
@ echo This environment variable is needed to run this program
@ echo NB: WL_HOME should point to a weblogic installation directory.
exit /b 1

:wsScriptseNotSet
@ echo The WSCRIPTS_HOME environment variable is not defined correctly
@ echo This environment variable is needed to run this program
@ echo NB: WSCRIPTS_HOME should point to a WLST API installation directory.
exit /b 1

:end