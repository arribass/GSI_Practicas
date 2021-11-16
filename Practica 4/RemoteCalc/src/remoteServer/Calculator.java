package remoteServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *  This inteface encapsulates the public behaviour of a Calculator.
 * @author carlos.lopez
 */
public interface Calculator extends Remote {

    /**
     * This operation sums two integers.
     *
     * @param a First integer
     * @param b Second integer
     * @return The sum of both
     * @throws java.rmi.RemoteException if somethig goes wrong in the connection
     */
    public Integer sum(Integer a, Integer b) throws RemoteException;

    /**
     * This operation substracts the second integer from the first one.
     * @param a First integer
     * @param b Second integer
     * @return The first integer minus the second one.
     * @throws java.rmi.RemoteException if somethig goes wrong in the connection
     */
    public Integer substract(Integer a, Integer b) throws RemoteException;

    /**
     * This operation multiplies two integers.
     * @param a First integer
     * @param b Second integer
     * @return The product of both integers
     * @throws java.rmi.RemoteException if somethig goes wrong in the connection
     */
    public Integer multiply(Integer a, Integer b) throws RemoteException;

    /**
     * Find the roots of a polynomial of n grade, 
     * n being the length of the
     * array coefficients minus 1.
     *
     * @param coefficients Vector of the coefficients of the polynomial
     * @return An array of reals with the polynomials
     * @throws java.rmi.RemoteException if somethig goes wrong in the connection
     */
    public Float[] findRoots(Integer[] coefficients) throws RemoteException;

}



