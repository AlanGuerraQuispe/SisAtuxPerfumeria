@echo off
REM $Id:  $

REM Setup the APP environment
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM APP_HOME assumes that your batch file is in APP_HOME\bin.
REM If your batch file is in APP_HOME change the next line
REM to 'SET APP_HOME=%~dp0'
SET APP_HOME=%~dp0..
SET APP_LIB=%APP_HOME%\lib
SET APP_CLASSPATH=%APP_HOME%\conf

FOR %%j IN ("%APP_LIB%\*.properties") DO CALL cpappend.bat %%j
FOR %%i IN ("%APP_LIB%\*.jar") DO CALL cpappend.bat %%i

@echo "%APP_CLASSPATH%"

"%JAVA_HOME%"\bin\java -cp %APP_CLASSPATH% com.atux.cron.component.ManagerService
pause