package Assignment1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {

    /**
     * Push value into the stack
     * 
     * @param val      - Value to push into the stack
     * @param clientId - ID of the requested client
     * @throws RemoteException
     */
    void pushValue(int val, String clientId) throws RemoteException;

    /**
     * Push and perform operation on the stack. All the values of the stack will be
     * popped and the requested operation will be performed.
     * 
     * @param operator - Operation to be performed : min/max/gcd/lcm
     * @param clientId - ID of the requested client
     * @throws RemoteException
     */
    void pushOperation(String operator, String clientId) throws RemoteException;

    /**
     * Method Pops and returns the top stack
     * 
     * @param clientId - - ID of the requested client
     * @return
     * @throws RemoteException
     */
    int pop(String clientId) throws RemoteException;

    /**
     * Check if the stack of requested client is empty
     * 
     * @param clientId
     * @return
     * @throws RemoteException
     */
    boolean isEmpty(String clientId) throws RemoteException;

    /**
     * Method waits for the given milliseconds, then pops and returns the top stack
     * element.
     * 
     * @param millis
     * @param clientId
     * @return
     * @throws RemoteException
     */
    int delayPop(int millis, String clientId) throws RemoteException;

}