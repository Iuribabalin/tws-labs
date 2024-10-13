@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@rem ##########################################################################
@rem
@rem  console-client startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
@rem This is normally unused
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and CONSOLE_CLIENT_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if %ERRORLEVEL% equ 0 goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\console-client-1.0-SNAPSHOT.jar;%APP_HOME%\lib\jaxws-api-2.3.1.jar;%APP_HOME%\lib\jaxws-tools-2.3.3.jar;%APP_HOME%\lib\jaxws-rt-2.3.3.jar;%APP_HOME%\lib\jackson-databind-2.0.1.jar;%APP_HOME%\lib\jaxb-api-2.3.1.jar;%APP_HOME%\lib\javax.xml.soap-api-1.4.0.jar;%APP_HOME%\lib\javax.annotation-api-1.3.2.jar;%APP_HOME%\lib\jakarta.xml.ws-api-2.3.3.jar;%APP_HOME%\lib\jaxb-jxc-2.3.3.jar;%APP_HOME%\lib\jaxb-xjc-2.3.3.jar;%APP_HOME%\lib\jaxb-impl-2.3.3.jar;%APP_HOME%\lib\jakarta.xml.bind-api-2.3.3.jar;%APP_HOME%\lib\saaj-impl-1.5.2.jar;%APP_HOME%\lib\jakarta.xml.soap-api-1.4.2.jar;%APP_HOME%\lib\jakarta.annotation-api-1.3.5.jar;%APP_HOME%\lib\jakarta.jws-api-2.1.0.jar;%APP_HOME%\lib\policy-2.7.10.jar;%APP_HOME%\lib\gmbal-4.0.1.jar;%APP_HOME%\lib\streambuffer-1.5.9.jar;%APP_HOME%\lib\stax-ex-1.8.3.jar;%APP_HOME%\lib\mimepull-1.9.13.jar;%APP_HOME%\lib\FastInfoset-1.2.18.jar;%APP_HOME%\lib\ha-api-3.1.12.jar;%APP_HOME%\lib\woodstox-core-5.1.0.jar;%APP_HOME%\lib\stax2-api-4.1.jar;%APP_HOME%\lib\jakarta.activation-api-1.2.2.jar;%APP_HOME%\lib\jackson-annotations-2.0.1.jar;%APP_HOME%\lib\jackson-core-2.0.1.jar;%APP_HOME%\lib\javax.activation-api-1.2.0.jar;%APP_HOME%\lib\management-api-3.2.2.jar;%APP_HOME%\lib\pfl-tf-4.1.0.jar;%APP_HOME%\lib\pfl-basic-4.1.0.jar;%APP_HOME%\lib\jakarta.activation-1.2.2.jar


@rem Execute console-client
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %CONSOLE_CLIENT_OPTS%  -classpath "%CLASSPATH%" com.example.Main %*

:end
@rem End local scope for the variables with windows NT shell
if %ERRORLEVEL% equ 0 goto mainEnd

:fail
rem Set variable CONSOLE_CLIENT_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
set EXIT_CODE=%ERRORLEVEL%
if %EXIT_CODE% equ 0 set EXIT_CODE=1
if not ""=="%CONSOLE_CLIENT_EXIT_CONSOLE%" exit %EXIT_CODE%
exit /b %EXIT_CODE%

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
