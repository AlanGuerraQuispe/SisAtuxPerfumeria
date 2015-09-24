@echo off
call mvn -f ..\..\pom.xml install
call mvn -Dmaven.test.skip=true clean package assembly:assembly
pause