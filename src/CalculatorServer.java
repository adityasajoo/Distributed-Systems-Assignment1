package Assignment1.src;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServer {
    public static void main(String[] args) {
        try {
            Calculator calculator = new CalculatorImplementation();
            Registry registry = LocateRegistry.createRegistry(2001);
            registry.rebind("Calculator", calculator);
            System.out.println("Calculator Server is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}