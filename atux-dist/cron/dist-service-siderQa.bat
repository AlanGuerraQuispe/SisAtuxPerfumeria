@echo off
title Instalando Jars de Servicios siderQa
SETLOCAL


SET AWPROFILE=siderQa


SET DIR_MAIN=\\192.168.1.100\c\services-suministros
SET DIR_BIN=%DIR_MAIN%\bin
SET DIR_CONF=%DIR_MAIN%\conf
SET DIR_LIB=%DIR_MAIN%\lib

SET DIR_MAIN_A_COPIAR=\target\suministros-cron.dir
SET DIR_A_COPIAR_BIN=%DIR_MAIN_A_COPIAR%\bin
SET DIR_A_COPIAR_CONF=%DIR_MAIN_A_COPIAR%\conf
SET DIR_A_COPIAR_LIB=%DIR_MAIN_A_COPIAR%\lib


call servicesMvn.bat

pause