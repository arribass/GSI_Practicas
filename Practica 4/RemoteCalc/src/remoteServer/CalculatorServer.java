package remoteServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *  This class is able to create an instance of Computing Maching and to publish it under the 
 *      interface Calculator.
 * @author carlos.lopez
 */
public class CalculatorServer {

    private static int RMI_PORT=1099;
    
    public static void main(String[] args) throws RemoteException  {
        
        // Step 1- creating a Security Manager
        //  Comment if having troubles in publishing the object
        //  "Comment" equals "Comment under your own responsability"
        /*if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }*/
        
        // Step 2- Create the object and make a publishable out of it.
        //  Note that it is stub what we can publish, not cm itself.
        ComputingMachine cm = new ComputingMachine();
        Calculator stub =(Calculator) UnicastRemoteObject.exportObject(cm,0);
        
        // Step 3- We create a registry at the desired port and we publish the object,
        //  which will be accessibe (in terms of the interface) under the tag
        //   "AwesomeCalculator"
        try{
            System.out.println("About to create the registry");
            Registry reg = LocateRegistry.createRegistry(RMI_PORT);
            System.out.println("Registry create");
            reg.rebind("AwesomeCalculator", stub);
            System.out.println("Stub rebind done");
        }catch(RemoteException re){
             System.out.println("RMI Error in publishing the stub: "+re.getMessage());
        }
        
        // Note that the object stays published (and accessible) when this method
        //  "finishes". They key in understanding this is that rebind properly 
        //  creates an execution thread (listening for connections).
    }
}
