package Assignment1.src;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2001);
            Calculator calculator = (Calculator) registry.lookup("Calculator");

    
            calculator.pushValue(10);
            calculator.pushValue(5);
            calculator.pushOperation("max");
            int result = calculator.pop();
            System.out.println("Max of 10 and 5: " + result);


        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}