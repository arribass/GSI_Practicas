/*
 * This document is part of the lab material for the subject:
 * Gestion de Sistemas de Informacion
 * to be released at the
 * Universidad Publica de Navarra
 * during the first semester of the Academic Year 2016-2017
 */

package GSILabs.connect;


import GSILabs.BModel.Review;
import GSILabs.BModel.Restaurante;
import GSILabs.BModel.Bar;
import java.rmi.Remote;
import java.rmi.RemoteException;


/**
*	This interface represents a remote gateway to access in read-write mode 
 * 		information about the clients in the system.
 * @author carlos.lopez
 * @version 1.0 (12/08/2016)
 */
public interface ClientGateway extends Remote, LocalFinder{
    
    
        /**
     * Incorpora una nueva review al sistema, en caso de que sus datos (Usuario, 
     *  Local) sean correctos y no haya otra introducida para la misma fecha.
     * @param r La review a introducir al sistema.
     * @return True si y solo si la operacion fue completada.
     * @throws RemoteException If some error happens in the remove invocation.
     */
    public boolean insertaReview(Review r) throws RemoteException;
    
    /**
     * Elimina una review del sistema, siempre y cuando exista y no tenga una 
     * contestacion asociada.
     * @param r Review a eliminar
     * @return True si y solo si la operacion fue completada.
     * @throws RemoteException If some error happens in the remove invocation.
     */
    public boolean quitaReview(Review r) throws RemoteException;
	
	/**
     * Devuelve el mejor bar de la cuidad que coincida con el nombre.
     * @param ciudad nombre de la cuidad
     * @return El bar con mejor puntuación media de la cuidad
     * @throws RemoteException If some error happens in the remove invocation.
     */
    public Bar mejorBar(String ciudad) throws RemoteException;
	
	
	/**
     * Devuelve los num restaurantes de la cuidad con mejores puntuaciones.
     * @param ciudad nombre de la cuidad
     * @return Una tabla con Num restaurantes, conteniendo nulls en caso de que no existan tantos.
     * @throws RemoteException If some error happens in the remove invocation.
     */
    public Restaurante[] mejoresRestaurantes(String ciudad,Integer num)  throws RemoteException;

    
}
