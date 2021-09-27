/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BSystem;


import GSILabs.BModel.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Arribas
 */
public class BusinessSystem implements LeisureOffice{
    static List<Usuario> Usuarios = new ArrayList<>();
    static List<Local> Locales = new ArrayList<>();
    static List<Review> Reviews = new ArrayList<>();
    static List<Contestacion> Contestaciones = new ArrayList<>();
    
    @Override
    /**
     * {@inheritDoc}
     */
    public boolean nuevoUsuario(Usuario u) {
    //agrega el usuario de entrada en la lista de usuarios
        try {
            Usuarios.add(u);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    /**
     * {@inheritDoc}
     */
    public boolean eliminaUsuario(Usuario u) {
        //Busca el usuario de entrada en la lista y lo elimina, si el usuario introducido es valido
        try{
            Usuarios.remove(u);
            System.out.print("Usuario eliminado");
            return true;
        }
        catch(NullPointerException e){
            System.out.print("El usuario no existe");
            return false;
        }
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean modificaUsuario(Usuario u, Usuario nuevoU) {
        //Busca el usuario de la entrada, lo borra e introduce el usuario de entrada modificado, si lo encuentra
        //nuevoU.usuarioValido(nick, password, fechaNacimiento, 0);
        try{
            Usuarios.remove(u);
            System.out.print("Usuario modificado");
            Usuarios.add(u);
            return true;
        }
        catch(NullPointerException e){
            System.out.print("El usuario no existe");
            return false;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean existeNick(String nick) {
    //Busca en la lista de usuarios si el nick de entrada existe o no
        return Usuarios.stream().anyMatch(item -> nick.equals(item.getNick()));
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Usuario obtenerUsuario(String nick) {
        //Busca y devuelve un usuario de la lista de usuarios
        Usuario userFound = Usuarios.stream()
                                .filter(item -> item.getNick().equals(nick))
                                .collect(Collectors.toList()).get(0);
        return userFound;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean nuevaReview(Review r) {
    //agrega una review a la lista
        try {
            Reviews.add(r);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean eliminaReview(Review r) {
    //elimina una review de la lista si esta existe
        try{
            Reviews.remove(r);
            return true;
        }catch(NullPointerException e){
            System.out.print("La review no existe");
            return false;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean existeRewiew(Usuario u, Local l, LocalDate ld) {
        // Primero busca si un usuario existe, si existe busca un local y una fecha en el array de reviews, si ambas existen confirma la busqueda
        Boolean aaa = Usuarios.stream().anyMatch(item -> u.equals(item));
        if (aaa == true){
            Boolean bbb = Reviews.stream().anyMatch(item -> l.equals(item.getLocal()));
            Boolean ccc = Reviews.stream().anyMatch(item -> ld.equals(item.getFecha()));
            if (bbb == ccc == true){
                System.out.println("El usuario ha escrito una review de ese local en esa fecha\n");
                return true;
            }else{
                System.out.println("El usuario no ha escrito reviews para ese local en esa fecha\n");
                return false;
            }
            
        }else{
            System.out.println("El usuario no existe\n");
            return false;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean nuevaContestacion(Contestacion c, Review r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean tieneContestacion(Review r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Contestacion obtenerContestacion(Review r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean eliminaContestacion(Contestacion c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean nuevoLocal(Local l) { //OK
        Scanner sc = new Scanner(System.in);
        //Introducir si quiere crear un bar un pub o un restaurante y abajo instanciar ese tipo en funcion de esa variable
             System.out.println("De que tipo quieres que sea el local: 1-Bar 2-Restaurante 3-Pub"); 
             int tipo = sc.nextInt();
             while(tipo != 1 && tipo != 2 && tipo!= 3){
                System.out.println("caracter invalido repite la eleccion"); 
                tipo = sc.nextInt();
             }
       
        boolean existeDireccion = false;
        for (int i=0;i<Locales.size();i++) {      
            if(Locales.get(i).getDireccion().equals(l.getDireccion())){
                existeDireccion = true;
                break;
            }
        }
        if(!existeDireccion){
            switch (tipo) {
                case 1:
                    Bar b = new Bar(l.getNombre(),l.getDireccion(),l.getDescripcion());
                    Locales.add(b);
                    System.out.println("******Bar añadido*******");
                    break;
                case 2:
                    Restaurante r = new Restaurante(l.getNombre(),l.getDireccion(),l.getDescripcion());
                    Locales.add(r);
                    System.out.println("******Restaurante añadido*******");
                    break;
                case 3:
                    Pub p = new Pub(l.getNombre(),l.getDireccion(),l.getDescripcion());
                    Locales.add(p);
                    System.out.println("******Pub añadido*******");
                    break;
            } 
            return true;
        } else{
            System.out.println("Ya existe un local en esa direccion.");
            return true;
        }        
    }

    @Override
    /**
     * {@inheritDoc}
     */

    public boolean eliminarLocal(Local l) {//OK
        try{
            Locales.remove(l);
            System.out.print("Local eliminado");
            return true;
        }
        catch(NullPointerException e){
            System.out.print("El local no existe");
            return false;
        }
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Local obtenerLocal(Direccion d) {//OK
        Local localFound = Locales.stream()
                                .filter(item -> item.getDireccion().equals(d))
                                .collect(Collectors.toList()).get(0);
        return localFound;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean asociarLocal(Local l, Propietario p) {
        //Busco en el array de locales el local de entrada y agrego el propietario; el método se encargará de decidir si se puede agregar el propietario o no
        for (int i=0;i<Locales.size();i++) {
            if(Locales.get(i).getDireccion().equals(l.getDireccion())){
                return Locales.get(i).anadirPropietario(p);
            }
        }
        return false;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean desasociarLocal(Local l, Propietario p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean actualizarLocal(Local viejoL, Local nuevoL) {
        Locales.stream().filter(item -> item.getDireccion().equals(viejoL.getDireccion()))
                        .collect(Collectors.toList()).get(0).setDescripcion(nuevoL.getDescripcion());
        
        Locales.stream().filter(item -> item.getDireccion().equals(viejoL.getDireccion()))
                        .collect(Collectors.toList()).get(0).setDireccion(nuevoL.getDireccion());
        
        Locales.stream().filter(item -> item.getDireccion().equals(viejoL.getDireccion()))
                        .collect(Collectors.toList()).get(0).setNombre(nuevoL.getNombre());
        /*
>>>>>>> Stashed changes
        for (int i=0;i<Locales.size();i++) {
            if(Locales.get(i).getDireccion().equals(viejoL.getDescripcion())){
                Locales.get(i).setDescripcion(nuevoL.getDescripcion());
                Locales.get(i).setDireccion(nuevoL.getDireccion());
                Locales.get(i).setNombre(nuevoL.getNombre());
                return true;
            }
        }
        System.out.println("No existe el local a modificar.");
        return false;*/
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Review[] verReviews(Local l) {
        Review[] rev = null;
        int a = 0;
        //Recorro el array de reviews e incluyo en la lista de items las reviews que tengan ese local
        for (int i=0;i<Reviews.size();i++) {
            if(Reviews.get(i).getLocal().equals(l)){
                rev[a] = Reviews.get(i);
                a = a+1;
                //rev.add(Reviews.get(i));
                //return Reviews.get(i);
            }
        }
        return rev;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean nuevaReserva(Cliente c, Reservable r, LocalDate ld, LocalTime lt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Reserva[] obtenerReservas(Cliente c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Reserva[] obtenerReservas(Reservable r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Reserva[] obtenerReservas(LocalDate ld) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean eliminarReserva(Reserva r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Local[] listarLocales(String ciudad, String provincia) {
        Local[] LocalesDE = null;
        //Recorro el arraylist de los locales y los que están en la provincia y ciudad indicadas los añado al array
        for (int i=0;i<Locales.size();i++) {
            if(Locales.get(i).getDireccion().getLocalidad() == ciudad && Locales.get(i).getDireccion().getProvincia() == provincia){
                LocalesDE[LocalesDE.length] = Locales.get(i);
            }
        }
        
        return LocalesDE;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Bar[] listarBares(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Restaurante[] listarRestaurantes(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Pub[] listarPubs(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean eliminaContestacion(Review r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
