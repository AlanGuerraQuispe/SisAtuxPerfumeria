REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Append to APP_CLASSPATH
REM
REM $Id: $
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET _TEMP=

REM Process the first argument
IF ""%1"" == """" GOTO end
SET _TEMP=%1
SHIFT

REM Process the remaining arguments.
REM Paths with spaces are handled here
:setArgs
IF ""%1"" == """" GOTO doneSetArgs
SET _TEMP=%_TEMP% %1
SHIFT
GOTO setArgs

REM Build the classpath
:doneSetArgs
SET APP_CLASSPATH=%APP_CLASSPATH%;"%_TEMP%"
GOTO end

:end