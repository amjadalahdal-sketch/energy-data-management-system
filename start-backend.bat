@echo off
setlocal

set "JAVA_HOME=C:\Program Files\Java\jdk-22"
set "PATH=%JAVA_HOME%\bin;%PATH%"

cd /d "%~dp0"

if "%ENERGY_DB_USERNAME%"=="" (
    echo ENERGY_DB_USERNAME is not set.
    echo Create start-backend.local.bat or set the variable before running this script.
    exit /b 1
)

if "%ENERGY_DB_PASSWORD%"=="" (
    echo ENERGY_DB_PASSWORD is not set.
    echo Create start-backend.local.bat or set the variable before running this script.
    exit /b 1
)

echo Starting Energy API with JAVA_HOME=%JAVA_HOME%
call mvnw.cmd spring-boot:run

endlocal
