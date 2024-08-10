To compile and run the program, follow the steps below :

1. Open terminal and go to the program's directory.

2. Run the following command to compile the code : 

    javac -d out *.java
    
    This command will compile all the java files and generate its executable file in the folder 'out'

3. Now run the following command to start the server : 
    
     java -classpath out Assignment1.CalculatorServer

    This will start the server

4. Open a new terminal and run the following to start the client : 

     java -classpath out Assignment1.CalculatorClient

    You can start multiple clients in similar way.