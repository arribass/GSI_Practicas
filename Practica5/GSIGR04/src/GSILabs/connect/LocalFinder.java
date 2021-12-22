/*
 * This document is part of the lab material for the subject:
 * Gestion de Sistemas de Informacion
 * to be released at the
 * Universidad Publica de Navarra
 * during the first semester of the Academic Year 2016-2017
 */

package GSILabs.connect;

import GSILabs.BModel.Local;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *	This interface represents a remote gateway to access in read mode 
 * 		information about the events in the system.
 * @author carlos.lopez
 * @version 1.0 (12/08/2016)
 */
public interface LocalFinder extends Remote {
   
    /**
     * Retrieves an Locals that matches EXACTLY (up to the case and/or spacing),
     *  the name.
     * @param name Name of the local
     * @return The local , or a null response
     * @throws RemoteException If some error happens in the remove invocation.
     */
    public Local getLocal(String name) throws RemoteException;
    
    /**
     * Retrieves all the Locals that match, either partial o totally (up to the case and/or spacing),
     *  the name given as argument.
     * @param name Complete or partial name of the ñpcañ
     * @return The list of local, that might eventually contain zero elements
     * @throws RemoteException If some error happens in the remove invocation.
     */
    public Local[] getLocals(String name) throws RemoteException;
    
    
}
