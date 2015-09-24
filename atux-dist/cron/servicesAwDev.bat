@echo off
call mvn -DawDev -f ..\..\pom.xml clean install
call mvn -DawDevEnv assembly:assembly

call copyto100.bat

