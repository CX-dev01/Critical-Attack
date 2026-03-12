@echo off

set "JAVA_HOME=C:\Program Files\Java\jdk-25.0.2"
set "PATH=%JAVA_HOME%\bin;%PATH%"

echo ===============================
echo Creating EXE...
echo ===============================

jpackage ^
--name CriticalAttack ^
--input "out\artifacts\CriticalAttack_jar" ^
--main-jar CriticalAttack.jar ^
--main-class com.cx.Main ^
--type app-image

echo ===============================
echo DONE!
pause