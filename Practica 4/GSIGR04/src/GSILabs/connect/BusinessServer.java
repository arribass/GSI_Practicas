/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.connect;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Reserva;
import GSILabs.BModel.Restaurante;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import GSILabs.BSystem.BusinessSystem;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author sergi
 */
public class BusinessServer implements AdminGateway,ClientGateway{
    //Variables para servidor
    AdminGateway ag = new BusinessSystem();
    ClientGateway cg = new BusinessSystem();
    AdminGateway stubAG;
    ClientGateway stubCG;
    Registry registroAG,registroCG;
    
    //Variables para SI
    static List<Usuario> Usuarios = new ArrayList<>();
    static List<Local> Locales = new ArrayList<>();
    static List<Review> Reviews = new ArrayList<>();
    static List<Contestacion> Contestaciones = new ArrayList<>();
    static List<Reserva> Reservas = new ArrayList<>();
    
    public BusinessServer() throws RemoteException {
        this.stubAG = (AdminGateway) UnicastRemoteObject.exportObject(ag,9998);
        this.stubCG = (ClientGateway) UnicastRemoteObject.exportObject(cg,9999);
        LocateRegistry.createRegistry(1099);
        LocateRegistry.createRegistry(1100);
        this.registroAG = LocateRegistry.getRegistry(1099);
        this.registroAG.rebind("ADMIN", stubAG);
        this.registroCG = LocateRegistry.getRegistry(1100);
        this.registroCG.rebind("CLIENT", stubCG);
    }   

    @Override
    public boolean eliminaLocal(Local l) throws RemoteException {
        try{
            Locales.remove(l);
            System.out.print("Ya no existen locales en la dirección introducida");
            return true;
        }
        catch(NullPointerException e){
            System.out.print("El local no existe");
            return false;
        }
    }

    @Override
    public boolean eliminaReviewsDeLocal(Local l) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminaReview(Review r) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminaReviewsDeUsuario(Cliente c) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertaReviewFalsa(Local l, Integer puntuacion) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Local getLocal(String name) throws RemoteException {
        try{
            Local localFound = Locales.stream()
                                .filter(item -> item.getNombre().equals(name))
                                .collect(Collectors.toList()).get(0);
            return localFound;
        }catch(IndexOutOfBoundsException e){
            System.out.println("No existe ningún local en la dirección que buscas");
            return null;
        }
    }

    @Override
    public Local[] getLocals(String name) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertaReview(Review r) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean quitaReview(Review r) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bar mejorBar(String ciudad) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Restaurante[] mejoresRestaurantes(String ciudad, Integer num) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
