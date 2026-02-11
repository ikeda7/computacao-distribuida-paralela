start java -cp ./;./compute.jar -Djava.rmi.server.codebase=file:./compute.jar -Djava.rmi.server.hostname=localhost Executer localhost 1099
pause
start rmiregistry
pause

start java Server
pause
call java Client
pause

