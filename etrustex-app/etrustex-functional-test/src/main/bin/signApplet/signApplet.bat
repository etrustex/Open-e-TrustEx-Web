@echo off
title Sign applet

if [%1] equ [] goto ParameterMissing

set fileToSign=%1
set destinationFolder=%~n1
set timeStamp=%date:~0,2%%date:~3,2%%date:~6,4%_%time:~0,2%%time:~3,2%%time:~6,2%
rem Insert zero instead of blank for times and dates between 1 and 9
set timeStamp=%timeStamp: =0%
set destinationEar=%destinationFolder%_signed_%timeStamp%.ear
set certAlias="le-46935ab3-7b75-4202-95d4-3e931af89c0e"
set eTokenDirectory="%JAVA_HOME%\bin\eToken.cfg"
set whiteList="ecas-desktop-4.19.2.jar;ecas-desktop-windows-4.19.2.jar"
powershell -Command $pword = read-host "Enter token password" -AsSecureString ; $BSTR=[System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($pword) ; [System.Runtime.InteropServices.Marshal]::PtrToStringAuto($BSTR) > .tmp.txt & set /p tokenPassword=<.tmp.txt & del .tmp.txt

echo Signing %fileToSign%
rem Unpacking ear
unzip -q %fileToSign% -d %destinationFolder%
unzip -q %destinationFolder%\etx-web-access.war -d %destinationFolder%\etx-web-access
del %destinationFolder%\etx-web-access.war

rem Iterate the folder \etx-web-access\applet to sign, verify and pack the jars
cd %destinationFolder%\etx-web-access\applet
for %%f in (*.jar) do (
    echo.
    echo Signing %%f

    ECHO.%whiteList% | findstr /C:"%%f">nul && (
    	zip -d %%f META-INF/*.SF META-INF/*.RSA META-INF/MANIFEST.MF
    )

    "%JAVA_HOME%"\bin\pack200 --repack %%f

    "%JAVA_HOME%"\bin\jarsigner -keystore NONE -storetype PKCS11 -tsa http://rfc3161timestamp.globalsign.com/advanced  -providerClass sun.security.pkcs11.SunPKCS11 -providerArg %eTokenDirectory% -storepass %tokenPassword% %%f %certAlias%
    if %ERRORLEVEL% NEQ 0 goto ErrorSigningJar

    "%JAVA_HOME%"\bin\jarsigner -verify %%f
    if %ERRORLEVEL% NEQ 0 goto ErrorSigningJar

    "%JAVA_HOME%"\bin\pack200 %%f.pack.gz %%f )

rem Packing ear
cd ..
zip -q -r ..\etx-web-access.war .
cd ..
rmdir etx-web-access /s /q
zip -q -r ../%destinationEar% .
cd ..
rmdir %destinationFolder% /s /q

echo.
echo %fileToSign% signed
goto :eof

:ParameterMissing
    echo Parameter fileToSign missing. The script must be called as follows: signApplet.bat [fileToSign]
    goto :eof

:ErrorSigningJar
    echo Error signing %fileToSign%
    cd ../../..
    rmdir %destinationFolder% /s /q