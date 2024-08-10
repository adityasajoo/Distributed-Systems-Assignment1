package Assignment1.src;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Stack;

public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {
    private Stack<Integer> stack;

    public CalculatorImplementation() throws RemoteException {
        super();
        stack = new Stack<>();
    }

    private int findMin(ArrayList<Integer> values) {
        int min = Integer.MAX_VALUE;
        for (int value : values) {
            if (value < min)
                min = value;
        }
        return min;
    }

    private int findMax(ArrayList<Integer> values) {
        int max = Integer.MIN_VALUE;
        for (int value : values) {
            if (value > max)
                max = value;
        }
        return max;
    }

    private int findLCM(ArrayList<Integer> values) {
        int result = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            result = lcm(result, values.get(i));
        }
        return result;
    }

    private int findGCD(ArrayList<Integer> values) {
        int result = values.get(0);
        for (int i = 1; i < values.size(); i++) {
            result = gcd(result, values.get(i));
        }
        return result;
    }

    private int lcm(int a, int b) {
        return Math.abs(a * b) / gcd(a, b);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    @Override
    public void pushValue(int val) throws RemoteException {
        stack.push(val);
    }

    @Override
    public void pushOperation(String operator) throws RemoteException {
        if (stack.isEmpty()) {
            throw new RemoteException("Cannot perform operation on an empty stack");
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
    public int pop() throws RemoteException {
        if (!stack.isEmpty()) {
            return stack.pop();
        }
        throw new RemoteException("Stack is empty");
    }

    @Override
    public boolean isEmpty() throws RemoteException {
        return stack.isEmpty();

    }

    @Override
    public int delayPop(int millis) throws RemoteException {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RemoteException("Interrupted during delay", e);
        }
        return pop();
    }
}
