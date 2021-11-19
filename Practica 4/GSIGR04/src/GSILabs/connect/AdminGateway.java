/*
 * This document is part of the lab material for the subject:
 * Gestion de Sistemas de Informacion
 * to be released at the
 * Universidad Publica de Navarra
 * during the first semester of the Academic Year 2016-2017
 */

package GSILabs.connect;

import GSILabs.BModel.Local;
import GSILabs.BModel.Review;
import GSILabs.BModel.Cliente;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
*	This interface represents a remote gateway to access in read-write mode 
 * 		information about the events in the system.
 * @author carlos.lopez
 * @version 1.0 (12/08/2016)
 */
public interface AdminGateway extends Remote,LocalFinder{
    
    /**
     * Elimina un local del sistema
     * @param l Local a eliminar
     * @return  True sí y sólo si se pudo completar la tarea
     * @throws RemoteException If some error happens in the remove invocation.
     */
   public boolean eliminaLocal(Local l) throws RemoteException;
   
   /**
    * Elimina todas la reviews (y contestaciones asociadas) de un local
    * @param l Local cuyas reviews deben eliminarse
    * @return True sí y sólo si se pudo completar la tarea
     * @throws RemoteException If some error happens in the remove invocation.
    */
   
   public boolean eliminaReviewsDeLocal(Local l) throws RemoteException;
   
   /**
    * Elimina una review determinada del sistema
    * @param r Review a eliminar
    * @return True sí y sólo si se pudo completar la tarea
     * @throws RemoteException If some error happens in the remove invocation.
    */
   public boolean eliminaReview(Review r) throws RemoteException;
   
   /**
    * Elimina todas la reviews de un cliente determinado
    * @param c Cliente cuyas reviews deben ser eliminadas
    * @return Número de reviews eliminadas, o -1 si el cliente no existe.
     * @throws RemoteException If some error happens in the remove invocation.
    */
   public int eliminaReviewsDeUsuario(Cliente c) throws RemoteException;
   
   /**
    * Crea un usuario falso al que se le añade una review falsa con la puntuación
    *  determinada en el segunda argumento.
    * @param l Local al que añadir la review falsa
    * @param puntuacion puntuación de la review
    * @return True sí y sólo si se pudo completar la tarea
     * @throws RemoteException If some error happens in the remove invocation.
    */
   public boolean insertaReviewFalsa(Local l, Integer puntuacion) throws RemoteException;
   
    
}
