title INSTALL
setlocal

call bajarServicesQa.bat

echo DIR_BIN=%DIR_BIN%
echo DIR_CONF=%DIR_CONF%
echo DIR_LIB=%DIR_LIB%

echo DIR_A_COPIAR_BIN=%DIR_A_COPIAR_BIN%
echo DIR_A_COPIAR_CONF=%DIR_A_COPIAR_CONF%
echo DIR_A_COPIAR_LIB=%DIR_A_COPIAR_LIB%

set _INSTALL_DIR_BIN=%DIR_BIN%
REM set _INSTALL_DIR_BIN=%DIR_BIN%
set _INSTALL_DIR_CONF=%DIR_CONF%
REM set _INSTALL_DIR_CONF=%DIR_CONF%
set _INSTALL_DIR_LIB=%DIR_LIB%
REM set _INSTALL_DIR_LIB=%DIR_LIB%

echo %_INSTALL_DIR_BIN%
echo %_INSTALL_DIR_CONF%
echo %_INSTALL_DIR_LIB%

REM copy /y .%DIR_A_COPIAR_BIN% %_INSTALL_DIR_BIN%

copy /y .%DIR_A_COPIAR_BIN% %_INSTALL_DIR_BIN%

REM copy /y .%DIR_A_COPIAR_CONF% %_INSTALL_DIR_CONF%

copy /y .%DIR_A_COPIAR_CONF% %_INSTALL_DIR_CONF%

REM copy /y .%DIR_A_COPIAR_LIB% %_INSTALL_DIR_LIB%

copy /y .%DIR_A_COPIAR_LIB% %_INSTALL_DIR_LIB%

call subirServicesQa.bat