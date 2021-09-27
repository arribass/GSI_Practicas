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
    static List<Reserva> Reservas = new ArrayList<>();
    
    @Override
    /**
     * {@inheritDoc}
     */
    public boolean nuevoUsuario(Usuario u) {
        // Agrega el usuario de entrada en la lista de usuarios
        if(!existeNick(u.getNick())){
            Usuarios.add(u);
            return true;
        }else{
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
        //agrega una review a la lista, antes mira para cada elemento de la lista si coinciden la fecha y el local con el elemento de la entrada, y en funcion de si se da ese caso o no agrega la review
        Boolean flag = true;
        for (int i=0;i<Reviews.size();i++) {
            if(Reviews.get(i).getLocal() == r.getLocal() && Reviews.get(i).getFecha() == r.getFecha()){
                flag = false;
            }
        }
        if (flag){
            try {
                Reviews.add(r);
                System.out.println("Review introducida\n");
                return true;
            }catch (Exception e) {
                System.out.println("Error introduciendo review\n");
                return false;
            }
        }else{
            System.out.println("Una review ha sido introducida en esa fecha y en ese local anteriormente\n");
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
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean nuevaContestacion(Contestacion c, Review r) {
        //Busca una review, y si existe agrega una contestacion    
        if (Reviews.stream().anyMatch(item -> r.equals(item))){
            return Contestaciones.add(c);
        }else{
            return false;
        }
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean tieneContestacion(Review r) {
        //Busca en la lista de contestaciones la review de entrada, y devuelve si existe o no una contestación con dicha review como atributo
        return Contestaciones.stream().anyMatch(item -> r.equals(item.getReview()));
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Contestacion obtenerContestacion(Review r) {
        //Recorre el array de contestaciones buscando la review de entrada, y si la encuentra devuelve la contestacion
        for (int i=0;i<Contestaciones.size();i++) {
            if(Contestaciones.get(i).getReview().equals(r)){
                return Contestaciones.get(i);
            }
        }
        System.out.println("No existe ninguna contestacion para esa review.");
        return null;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean eliminaContestacion(Contestacion c) {
        //Busca la contestacion en la lista de contestaciones y la elimina
        try{
            Contestaciones.remove(c);
            System.out.print("Contestacion eliminada");
            return true;
        }
        catch(NullPointerException e){
            System.out.print("La contestacion no existe");
            return false;
        }
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
        
        System.out.print("Local actualizado\n");
        return true;
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
        //Recorro el array de reviews e incluyo en la lista de items las reviews que tengan ese local
        for (int i=0;i<Reviews.size();i++) {
            if(Reviews.get(i).getLocal().equals(l)){
                rev[rev.length] = Reviews.get(i);
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
        
        //Comienzo comparando la fecha actual con la fecha de entrada, en caso de que sea igual o mayor continuo
        //Ahora miro si la fecha actual es igual a la fecha de entrada, si es igual miro si la hora de entrada es posterior a la hora actual, en caso contrario paro
        //Si las horas son correctas paso a mirar en las listas de usuarios y locales si existen el reservable y el cliente, en caso afirmativo de ambos creo la reserva y la agrego a la lista
        //Si la hora de entrada es mayor a la hora actual, repito el comentario anterior
        //Si alguna de las condiciones no se cumple se devuelve false y no se realiza la operacion
        
        if (ld.compareTo(LocalDate.now())>= 0){
            if (ld.compareTo(LocalDate.now())== 0){
                if(lt.compareTo(LocalTime.now()) > 0 ){
                    //ver si el cliente existe y si el local existe
                    if(Usuarios.stream().anyMatch(item -> c.equals(item))){
                        if(Locales.stream().anyMatch(item -> r.equals(item))){
                        Reserva res = new Reserva(c, r, ld, lt, 0);
                        Reservas.add(res);
                        return true;
                        }else{
                            System.out.println("El local no existe\n");
                            return false;
                        }
                    }else{
                        System.out.println("El cliente no existe\n");
                        return false;
                    }
                }else{
                    System.out.println("Estás reservando para la fecha de hoy con una hora anterior a la actual\n");
                    return false;
                }
            }else{
                if(Usuarios.stream().anyMatch(item -> c.equals(item))){
                    if(Locales.stream().anyMatch(item -> r.equals(item))){
                        Reserva res = new Reserva(c, r, ld, lt, 0);
                        Reservas.add(res);
                        return true;
                    }else{
                        System.out.println("El local no existe\n");
                        return false;
                    }
                }else{
                    System.out.println("El cliente no existe\n");
                    return false;
                }
            }
        }else{
            System.out.println("La fecha es anterior a la fecha de hoy\n");
            return false;
        }
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Reserva[] obtenerReservas(Cliente c) {
        Reserva[] res = null;
        //Recorro el array de reservas e incluyo en la lista de items las reservas que tengan ese cliente
        for (int i=0;i<Reservas.size();i++) {
            if(Reservas.get(i).getCliente().equals(c)){
                res[res.length] = Reservas.get(i);
            }
        }
        return res;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Reserva[] obtenerReservas(Reservable r) {
        Reserva[] res = null;
        //Recorro el array de reservas e incluyo en la lista de items las reservas que tengan ese reservable
        for (int i=0;i<Reservas.size();i++) {
            if(Reservas.get(i).getReservable().equals(r)){
                res[res.length] = Reservas.get(i);
            }
        }
        return res;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Reserva[] obtenerReservas(LocalDate ld) {
        Reserva[] res = null;
        //Recorro el array de reservas e incluyo en la lista de items las reservas que tengan esa fecha
        for (int i=0;i<Reservas.size();i++) {
            if(Reservas.get(i).getFecha().equals(ld)){
                res[res.length] = Reservas.get(i);
            }
        }
        return res;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean eliminarReserva(Reserva r) {
        //Busca la reserva de entrada en la lista y lo elimina, si la reserva introducida es valida/existe
        try{
            Reservas.remove(r);
            System.out.print("Reserva eliminada");
            return true;
        }
        catch(NullPointerException e){
            System.out.print("La reserva no existe");
            return false;
        }
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
        
        //Comenzamos mirando si la review existe, si no existe acabamos
        //Si la review existe buscamos en la lista de contestaciones aquellas que vengan de esa review y las borramos
        
        int cont = 0;
        
        if(Contestaciones.stream().anyMatch(item -> r.equals(item.getReview()))){
            for(int i=0; i<Contestaciones.size(); i++){
                if (Contestaciones.get(i).getReview().equals(r)){
                    Contestaciones.remove(Contestaciones.get(i));
                    cont ++;
                }
            }
            System.out.println("Se han eliminado " + cont + " contestaciones de la review indicada.\n");
            return true;
        }else{
            System.out.println("La review introducida no existe.\n");
            return false;
        }
    }
    
}
