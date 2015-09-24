@echo off

echo AWPROFILE=%AWPROFILE%

call mvn -D%AWPROFILE% clean
call mvn -D%AWPROFILE% -f ..\..\pom.xml clean install
call mvn -D%AWPROFILE%Env assembly:assembly

call copyto100.bat

