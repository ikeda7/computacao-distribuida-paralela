call javac *.java
call jar cvf compute.jar Compute.class Task.class
call javac -cp compute.jar Executer.java
call javac -cp compute.jar Soma.java

pause