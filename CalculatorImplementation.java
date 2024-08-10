package Assignment1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {

    /**
     * Map of clients and its stack. Each client will have it's own stack
     */
    private Map<String, Stack<Integer>> clientStacks;

    /**
     * Constructor method to initialize the Map
     * 
     * @throws RemoteException
     */
    public CalculatorImplementation() throws RemoteException {
        super();
        clientStacks = new HashMap<>();
    }

    /**
     * Method to get the stack of the requested client
     * 
     * @param clientId - ID for the requested client
     * @return
     */
    private Stack<Integer> getClientStack(String clientId) {
        if (!clientStacks.containsKey(clientId))
            clientStacks.put(clientId, new Stack<>());
        return clientStacks.get(clientId);
    }

    @Override
    public void pushValue(int val, String clientId) throws RemoteException {
        getClientStack(clientId).push(val);
    }

    @Override
    public void pushOperation(String operator, String clientId) throws RemoteException {
        Stack<Integer> stack = getClientStack(clientId);
        if (stack.isEmpty()) {
            throw new RemoteException("Stack is empty");
        }

        ArrayList<Integer> values = new ArrayList<>();
        while (!stack.isEmpty()) {
            values.add(stack.pop());
        }

        int result;
        switch (operator.toLowerCase()) {
            case "min":
                result = findMin(values);
                break;
            case "max":
                result = findMax(values);
                break;
            case "lcm":
                result = findLCM(values);
                break;
            case "gcd":
                result = findGCD(values);
                break;
            default:
                throw new RemoteException("Invalid operator: " + operator);
        }

        stack.push(result);
    }

    @Override
    public int pop(String clientId) throws RemoteException {
        Stack<Integer> stack = getClientStack(clientId);
        if (!stack.isEmpty()) {
            return stack.pop();
        }
        throw new RemoteException("Stack is empty");
    }

    @Override
    public boolean isEmpty(String clientId) throws RemoteException {
        return getClientStack(clientId).isEmpty();
    }

    @Override
    public int delayPop(int millis, String clientId) throws RemoteException {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RemoteException("Interrupted during delay", e);
        }
        return pop(clientId);
    }

    /** Helper Functions */

    /**
     * Method to find the minimum value of the stack
     * 
     * @param values - All the popped elements of the stack
     * @return - Smallest value of the stack
     */
    private int findMin(ArrayList<Integer> values) {
        int min = Integer.MAX_VALUE;
        for (int value : values) {
            if (value < min)
                min = value;
        }
        return min;
    }

    /**
     * Method to find the minimum value of the stack
     * 
     * @param values - All the popped elements of the stack
     * @return - Largest value of the stack
     */
    private int findMax(ArrayList<Integer> values) {
        int max = Integer.MIN_VALUE;
        for (int value : values) {
            if (value > max)
                max = value;
        }
        return max;
    }

    /**
     * Method to find the LCM of the elements
     * 
     * @param values - All the popped elements of the stack
     * @return - LCM of all the elements
     */
    private int findLCM(ArrayList<Integer> values) {
        int result = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            result = lcm(result, values.get(i));
        }
        return result;
    }

    /**
     * Method to find the GCD of the elements
     * 
     * @param values - All the popped elements of the stack
     * @return - GCD of all the elements
     */
    private int findGCD(ArrayList<Integer> values) {
        int result = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            result = gcd(result, values.get(i));
        }
        return result;
    }

    /**
     * Find LCM of two numbers
     * 
     * @param num1 - First Number
     * @param num2 - Second Number
     * @return - LCM of the given two numbers
     */
    private int lcm(int num1, int num2) {
        return Math.abs(num1 * num2) / gcd(num1, num2);
    }

    /**
     * Find GCD of tww numbers
     * 
     * @param num1 - First Number
     * @param num2 - Second Number
     * @return - GCD of the given two numbers
     */
    private int gcd(int num1, int num2) {
        while (num2 != 0) {
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }
        return Math.abs(num1);
    }
}
