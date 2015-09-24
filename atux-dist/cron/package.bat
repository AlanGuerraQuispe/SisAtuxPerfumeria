setlocal
REM SET JAVA_HOME=C:\ProgFile\jdk1.6.0_12
call mvn -Dmaven.test.skip=true -DawDev -U clean package
pause