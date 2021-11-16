package remoteClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import remoteServer.Calculator;

/**
 *  This runnable class is able to connect to a remote Calculator and perform 
 *      mathematical operations in the server side.
 * @author carlos.lopez
 */

public class CalculatorClient {
    
    public static void main(String[] args) {
        
        // Step 1- Reading from the keyboard the address of the remote machine
        
        System.out.print("Please input the addess of the machine: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String remoteMachine;
        try {
            remoteMachine = br.readLine();
        } catch (IOException ioe) {
            System.out.println("Exception when reading : "+ioe.getMessage());
            remoteMachine="localhost";
        }
        
        
        try {
            // Step 2-  Connecting to the remote registry
            Registry registry = LocateRegistry.getRegistry(remoteMachine);
            // Step 3- Linking the remote object as if it was a local one
            Calculator comp = (Calculator) registry.lookup("AwesomeCalculator");
            // Step 4- Just using the object!
            System.out.println("5 plus 6 is "+comp.sum(5,6));
        } catch (RemoteException | NotBoundException ex) {
            System.out.println("Exception in connection : "+ex.getMessage());
        }
    }
}