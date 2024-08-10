package Assignment1.src;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
    void pushValue(int val) throws RemoteException;

    void pushOperation(String Operator) throws RemoteException;

    int pop() throws RemoteException;

    boolean isEmpty() throws RemoteException;

    int delayPop(int millis) throws RemoteException;

}