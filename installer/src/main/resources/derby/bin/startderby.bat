@echo off

rem
rem YesCart. Start db and tomcat
rem @author Igor Azarny (iazarny@yahoo.com)

echo derby starting
echo Please wait and do not close any window
ping 1.1.1.1 -n 1 -w 2000 > NUL
set DERBY_HOME=%~dp0..
cd %~dp0
set LOCALCLASSPATH=%DERBY_HOME%/lib/derby.jar;%DERBY_HOME%/lib/derbynet.jar;%DERBY_HOME%/lib/derbyclient.jar;%DERBY_HOME%/lib/derbytools.jar

start %~dp0\startNetworkServer.bat
ping 1.1.1.1 -n 1 -w 5000 > NUL

echo derby started
echo create initial database ...

call java -classpath "%LOCALCLASSPATH%" -Dderby.system.home=%DERBY_HOME% -Dij.outfile=%DERBY_HOME%\derbyinit.log -Dderby.ui.codeset=UTF8 org.apache.derby.tools.ij dbinit.sql

echo Ok

exit 0
