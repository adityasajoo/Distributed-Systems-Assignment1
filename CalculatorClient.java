package Assignment1;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(2001);
            Calculator calculator = (Calculator) registry.lookup("Calculator");

            String clientId = UUID.randomUUID().toString();
            System.out.println("Client ID: " + clientId);

            Scanner scanner = new Scanner(System.in);

            System.out.println("------ MENU ------");
            System.out.println("1. Push value");
            System.out.println("2. Pop");
            System.out.println("3. Check if stack is empty");
            System.out.println("4. Delay Pop");
            System.out.println("5. Perform Minimum operation");
            System.out.println("6. Perform Maximum operation");
            System.out.println("7. Perform LCM operation");
            System.out.println("8. Perform GCD operation");
            System.out.println("9. Exit");

            while (true) {
                System.out.print("\nEnter your choice (1-9): ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter an value to push: ");
                        int value = scanner.nextInt();
                        calculator.pushValue(value, clientId);
                        System.out.println("Value " + value + " pushed to stack.");
                        break;

                    case 2:
                        try {
                            int poppedValue = calculator.pop(clientId);
                            System.out.println("Popped value: " + poppedValue);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 3:
                        boolean isEmpty = calculator.isEmpty(clientId);
                        if (isEmpty) {
                            System.out.println("Stack is empty!");
                        } else {
                            System.out.println("Stack is not empty!");
                        }
                        break;

                    case 4:
                        System.out.print("Enter delay in milliseconds: ");
                        int delay = scanner.nextInt();
                        try {
                            int delayedValue = calculator.delayPop(delay, clientId);
                            System.out.println("Delayed pop result: " + delayedValue);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }

                    case 5:
                        calculator.pushOperation("min", clientId);
                        System.out.println("Min operation performed successfully.");
                        break;

                    case 6:
                        calculator.pushOperation("max", clientId);
                        System.out.println("Max operation performed successfully.");
                        break;

                    case 7:
                        calculator.pushOperation("lcm", clientId);
                        System.out.println("LCM operation performed successfully.");
                        break;

                    case 8:
                        calculator.pushOperation("gcd", clientId);
                        System.out.println("GCD operation performed successfully.");
                        break;

                    case 9:
                        System.out.println("Exiting the calculator.");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                }
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

}