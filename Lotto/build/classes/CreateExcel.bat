@echo off



set jdkhome=D:\Java-workspace\Lotto\src\bat\java\jdk1.8.0_221\bin
set cp=bat/lib/*




%jdkhome%/java -classpath %cp% src.output
pause