@echo off
setlocal
title Start Energy API and Angular UI

set "ROOT_DIR=%~dp0"
set "ANGULAR_DIR=%ROOT_DIR%energy-ui"
set "JAVA_HOME=C:\Program Files\Java\jdk-22"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo.
echo ==========================================================
echo Starting Energy Data Management System
echo ==========================================================
echo.

if not exist "%JAVA_HOME%\bin\java.exe" (
    echo ERROR: Java was not found at:
    echo %JAVA_HOME%
    echo.
    pause
    exit /b 1
)

where npm.cmd >nul 2>&1
if errorlevel 1 (
    echo ERROR: npm.cmd was not found in PATH.
    echo Install Node.js or check your PATH.
    echo.
    pause
    exit /b 1
)

if "%ENERGY_DB_USERNAME%"=="" (
    set "ENERGY_DB_USERNAME=ENERGY_DEMO"
)

if "%ENERGY_DB_PASSWORD%"=="" (
    echo.
    set /p ENERGY_DB_PASSWORD=Enter Oracle password for %ENERGY_DB_USERNAME%: 
)

if not exist "%ANGULAR_DIR%\package.json" (
    echo ERROR: Angular project was not found at:
    echo %ANGULAR_DIR%
    echo.
    pause
    exit /b 1
)

echo Checking Oracle services...
net session >nul 2>&1
if %errorlevel%==0 (
    echo Running as Administrator. Starting Oracle services if needed...
    net start OracleServiceXE >nul 2>&1
    net start OracleOraDB21Home1TNSListener >nul 2>&1
) else (
    echo Not running as Administrator.
    echo If Spring Boot cannot connect to Oracle, run this file as Administrator
    echo or start OracleServiceXE and OracleOraDB21Home1TNSListener manually.
)
echo.

echo Checking ports 8081 and 4200...
powershell -NoProfile -ExecutionPolicy Bypass -Command "Get-NetTCPConnection -LocalPort 8081,4200 -State Listen -ErrorAction SilentlyContinue | Select-Object LocalPort,OwningProcess"
echo.

echo Starting Spring Boot API on http://localhost:8081 ...
start "Energy API - Spring Boot" cmd /k "cd /d ""%ROOT_DIR%"" && mvnw.cmd spring-boot:run"

echo Waiting before starting Angular...
timeout /t 8 /nobreak >nul

echo Starting Angular UI on http://localhost:4200 ...
start "Energy UI - Angular" cmd /k "cd /d ""%ANGULAR_DIR%"" && npm start"

echo.
echo Waiting for services to start...
timeout /t 20 /nobreak >nul

echo.
echo Opening local URLs...
start "" "http://localhost:8081/api/health"
start "" "http://localhost:4200/"

echo.
echo ==========================================================
echo Done.
echo Keep the two opened command windows running.
echo.
echo Backend: http://localhost:8081/api/health
echo Angular: http://localhost:4200/
echo ==========================================================
echo.
pause
endlocal
