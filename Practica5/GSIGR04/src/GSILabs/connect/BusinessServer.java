/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.connect;

import GSILabs.BSystem.UniSystem;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author sergi
 */
public class BusinessServer {
    
    private static int RMI_PORT_ADMIN=1099;
    private static int RMI_PORT_CLIENT=1100;
    
    
    public static void main(String[] args) throws RemoteException{
        AdminGateway ag = new UniSystem();
        ClientGateway cg = new UniSystem();
        AdminGateway stubAG;
        ClientGateway stubCG;
        Registry registroAG, registroCG;
        stubAG = (AdminGateway) UnicastRemoteObject.exportObject(ag,9998);
        stubCG = (ClientGateway) UnicastRemoteObject.exportObject(cg,0);
        
        try{
            LocateRegistry.createRegistry(1099);
            System.out.println("Created register for ADMIN");
            LocateRegistry.createRegistry(1100);
            System.out.println("Created register for CLIENT");
            registroAG = LocateRegistry.getRegistry(RMI_PORT_ADMIN);
            System.out.println("Got register for ADMNIN");
            registroAG.rebind("ADMIN", stubAG);
            System.out.println("Stub rebind for ADMIN done");
            registroCG = LocateRegistry.getRegistry(RMI_PORT_CLIENT);
            System.out.println("Got register for CLIENT");
            registroCG.rebind("CLIENT", stubCG);
            System.out.println("Stub rebind for CLIENT done");
        }catch (RemoteException re){
             System.out.println("RMI Error in publishing the stub: "+re.getMessage());
        }
    }
}
