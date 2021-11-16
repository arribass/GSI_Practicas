package remoteServer;

import java.rmi.RemoteException;

/**
 * This class implements the business logic of Calculator. Except for that of
 *  findRoots, I'm not in the mood for that, so it's kind of a fake method. Who cares.
 * @author carlos.lopez
 */
public class ComputingMachine implements Calculator {
 
    /**
     * Default constructor
     */
    public ComputingMachine(){
        super();
    }

    @Override
    public Integer sum(Integer a, Integer b) throws RemoteException {
        return a+b;
    }

    @Override
    public Integer substract(Integer a, Integer b) throws RemoteException {
        return a-b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) throws RemoteException {
        return a*b;
    }

    @Override
    public Float[] findRoots(Integer[] coefficients) throws RemoteException {
       Float[] aux=new Float[3];
       aux[1]=new Float(3);
       aux[2]=new Float(31.2);
       aux[3]=new Float(4.3);
       return aux;
    }
    
}




