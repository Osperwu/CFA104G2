@echo off



set jdkhome=C:\Program Files\Java\jdk1.8.0_221\bin
set cp=*/Java-workspace/Lotto/WebContent/WEB-INF/lib/*




%jdkhome%/java -classpath %cp% bat.CreateExcel
pause