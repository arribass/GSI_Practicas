/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BSystem;

import GSILabs.BModel.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/*
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
    public boolean nuevoUsuario(Usuario u) { //OK
        // Agrega el usuario de entrada en la lista de usuarios
        if(Usuarios.stream().anyMatch(item -> u.equals(item))){
            System.out.println("El usuario ya existe.\n");
            return false;
        }else{
            if(!existeNick(u.getNick())){
                Usuarios.add(u);
                return true;
            }else{
                System.out.println("El nick de este usuario esta en uso por otro usuario existente.\n");
                return false;
            }
        }
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean eliminaUsuario(Usuario u) { //OK
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
    public boolean modificaUsuario(Usuario u, Usuario nuevoU) { //OK
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
    public boolean existeNick(String nick) { //OK
        //Busca en la lista de usuarios si el nick de entrada existe o no
        return Usuarios.stream().anyMatch(item -> nick.equals(item.getNick()));
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Usuario obtenerUsuario(String nick) { //OK
        //Busca y devuelve un usuario de la lista de usuarios
        //Recorre la lista comparando, si llega al final sin encontrar nada devuelve null
        //Boolean usuarioEncontrado = false;
        for (int i = 0; i<Usuarios.size(); i++){
            if(Usuarios.get(i).getNick() == nick){
                return Usuarios.get(i);
            }
        }
        
        return null;
        /*
        Usuario userFound = Usuarios.stream()
                                .filter(item -> item.getNick().equals(nick))
                                .collect(Collectors.toList()).get(0);
        if (userFound == null){
            return null;
        }else{
            return userFound;
        }*/
    }
    
    @Override
    /**
     * {@inheritDoc}
     */
    public boolean nuevaReview(Review r) { //OK
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
        //Primero consulto al usuario si lo que desea eliminar es un bar, restaurante o pub.
             System.out.println("De que tipo quieres que sea el local: 1-Bar 2-Restaurante 3-Pub"); 
             int tipo = 4; //variable auxiliar
             //realizo un try-catch del tipo por si el usuario introduce valores incorrectos, como pueden ser en lugar de
             //un entero un caracter o una expresión del estilo "4.-", o cualquier otra que no sea un entero.
             try{
                 tipo = sc.nextInt();
             }catch(InputMismatchException e){
                 System.out.println("Caracter invalido");
             }
             //Compruebo que ya sabiendo que es un entero correcto, este esté en los valores posibles (1, 2 o 3)
             // y si no es así no añado el local.
             if(tipo != 1 && tipo != 2 && tipo!= 3){
                 System.out.println("No existe el tipo deseado, no se ha podido insertar el local, vuelva a intentarlo");
                 return false;
             }else{
                
       //Miro a ver si hay ya un local en esa dirección, ya que en caso afirmativo
       // no podría añadir otro más.
        boolean existeDireccion = false;
        for (int i=0;i<Locales.size();i++) {      
            if(Locales.get(i).getDireccion().equals(l.getDireccion())){
                existeDireccion = true;
                break;
            }
        }
        
        //En caso de estar la dirección libre procedo a añadir el local en esta
        //en lugar de añadir un objeto de tipo local añado un objeto del tipo que haya 
        //dictaminado el usuario anteriormente.
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
            //Si ya habia un local en esa dirección lo notifico al usuario.
            System.out.println("Ya existe un local en esa direccion.");
            return false;
        }  
             }
    }

    @Override
    /**
     * {@inheritDoc}
     */

    public boolean eliminarLocal(Local l) {//OK
        //Recibo un objeto de tipo local, hago un try intentando eliminarlo del
        //arraylist locales, si no esta saltará una excepcion que recojo con el catch e indico que no existe.
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
    /**
     * {@inheritDoc}
     */
    public Local obtenerLocal(Direccion d) {//OK
        //Recibo como parametro una dirección (sabemos que existe un único local en una misma dirección)
        //Recorro el arraylist que almacena los locales y cuando encuentro un local en esa dirección lo devuelvo.
        //En caso de no haber ninguno recogo la excepcion y digo al usuario que no hay ningun local.
        try{
            Local localFound = Locales.stream()
                                .filter(item -> item.getDireccion().equals(d))
                                .collect(Collectors.toList()).get(0);
            return localFound;
        }catch(IndexOutOfBoundsException e){
            System.out.println("No existe ningún local en la dirección que buscas");
            return null;
        }
        
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
        
        //Vemos si el local existe, si no existe hemos acabado
        //Si el local existe, sacamos sus propietarios y buscamos el propietario de entrada, si no existe lo indicamos y acabamos, si sí existe lo borramos
        //Si al durante el bucle for se borra el propietario lo indicamos y acabamos, ya que no puede haber dos propietarios iguales
        //en caso contrario sabemos que podemos indicar que el propietario de entrada no era un propietario del local indicado
        
        if(Locales.stream().anyMatch(item -> l.equals(item))){
            Usuario[] propietarios = l.getPropietarios();
            
            for (int i = 0; i<propietarios.length; i++){
                if(propietarios[i] == p){
                    propietarios[i] = null;
                    System.out.println("Se ha borrado el propietario indicado.\n");
                    return true;
                }
            }
            System.out.println("El propietario indicado no formaba parte de los propietarios asociados al local.\n");
            return false;
        }else{
            System.out.println("El local no existe.\n");
            return false;
        }

    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean actualizarLocal(Local viejoL, Local nuevoL) { //OK
        try{
        Locales.stream().filter(item -> item.getDireccion().equals(viejoL.getDireccion()))
                        .collect(Collectors.toList()).get(0).setDescripcion(nuevoL.getDescripcion());
        
        Locales.stream().filter(item -> item.getDireccion().equals(viejoL.getDireccion()))
                        .collect(Collectors.toList()).get(0).setDireccion(nuevoL.getDireccion());
        
        Locales.stream().filter(item -> item.getDireccion().equals(viejoL.getDireccion()))
                        .collect(Collectors.toList()).get(0).setNombre(nuevoL.getNombre());
        
        System.out.print("Local actualizado\n");
        return true;
        }catch(Exception ex){
            System.out.print("No ha sido posible actualizar el local\n");
            return false;
        }
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
    public Local[] listarLocales(String ciudad, String provincia) { //OK
        Local LocalesDE[] = new Local[Locales.size()];
        //Recorro el arraylist de los locales y los que están en la provincia y ciudad indicadas los añado al array
        int contador = 0;
        for (int i=0;i<Locales.size();i++) {
            if(Locales.get(i).getDireccion().getLocalidad().equals(ciudad) && Locales.get(i).getDireccion().getProvincia().equals(provincia)){
                LocalesDE[contador] = Locales.get(i);
                contador++;
            }            
        }
        for (int i=0;i<contador;i++) {
         System.out.println("- " + LocalesDE[i].getNombre() + ".\n");
        }
        
        return LocalesDE;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Bar[] listarBares(String ciudad, String provincia) { //OK
        Local LocalesDE[] = new Local[Locales.size()];
        Direccion d = new Direccion("haro","larioja","alemania","1");
        Bar bar = new Bar("aux",d,"bonito");
        //Recorro el arraylist de los locales y los que están en la provincia y ciudad indicadas los añado al array
        int contador = 0;
        for (int i=0;i<Locales.size();i++) {
            if(Locales.get(i).getDireccion().getLocalidad().equals(ciudad) && Locales.get(i).getDireccion().getProvincia().equals(provincia) && Locales.get(i).getClass().equals(bar.getClass())){
                LocalesDE[contador] = Locales.get(i);
                contador++;
            }            
        }
        for (int i=0;i<contador;i++) {
         System.out.println("- " + LocalesDE[i].getNombre() + ".\n");
        }
        
        return null;
    }
    
    
    
 
    @Override
    /**
     * {@inheritDoc}
     */
    public Restaurante[] listarRestaurantes(String ciudad, String provincia) {//OK
        Local LocalesDE[] = new Local[Locales.size()];
        Direccion d = new Direccion("haro","larioja","alemania","1");
        Restaurante restaurante = new Restaurante("aux",d,"bonito");
        //Recorro el arraylist de los locales y los que están en la provincia y ciudad indicadas los añado al array
        int contador = 0;
        for (int i=0;i<Locales.size();i++) {
            if(Locales.get(i).getDireccion().getLocalidad().equals(ciudad) && Locales.get(i).getDireccion().getProvincia().equals(provincia) && Locales.get(i).getClass().equals(restaurante.getClass())){
                LocalesDE[contador] = Locales.get(i);
                contador++;
            }            
        }
        for (int i=0;i<contador;i++) {
         System.out.println("- " + LocalesDE[i].getNombre() + ".\n");
        }
        
        return null;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Pub[] listarPubs(String ciudad, String provincia) {//OK
        Local LocalesDE[] = new Local[Locales.size()];
        Direccion d = new Direccion("haro","larioja","alemania","1");
        Pub pub = new Pub("aux",d,"bonito");
        //Recorro el arraylist de los locales y los que están en la provincia y ciudad indicadas los añado al array
        int contador = 0;
        for (int i=0;i<Locales.size();i++) {
            if(Locales.get(i).getDireccion().getLocalidad().equals(ciudad) && Locales.get(i).getDireccion().getProvincia().equals(provincia) && Locales.get(i).getClass().equals(pub.getClass())){
                LocalesDE[contador] = Locales.get(i);
                contador++;
            }            
        }
        for (int i=0;i<contador;i++) {
         System.out.println("- " + LocalesDE[i].getNombre() + ".\n");
        }
        
        return null;
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
    
    /* Funciones implementadas para realizar la practica 2*/
   
    /**
     * {@inheritDoc}
     */
    public List <Local> listarTodosBares() { //OK
        
        List<Local> LocalesBares = new ArrayList<>();
        Direccion d = new Direccion("haro","larioja","alemania","1");
        Bar bar = new Bar("aux",d,"bonito");
        //Recorro el arraylist de los locales y los que están en la provincia y ciudad indicadas los añado al array
        int contador = 0;
        for (int i=0;i<Locales.size();i++) {
            if(Locales.get(i).getClass().equals(bar.getClass())){
               
                LocalesBares.add(Locales.get(i));
                contador++;
            }            
        }
        
        
        return LocalesBares;
    }
    /**
     * {@inheritDoc}
     */
    public List <Local> listarTodosRestaurantes() { //OK
        
        List<Local> LocalesRestaurantes = new ArrayList<>();
        Direccion d = new Direccion("haro","larioja","alemania","1");
        Restaurante restaurante = new Restaurante("aux",d,"bonito");
        //Recorro el arraylist de los locales y los que están en la provincia y ciudad indicadas los añado al array
        int contador = 0;
        for (int i=0;i<Locales.size();i++) {
            if(Locales.get(i).getClass().equals(restaurante.getClass())){
               
                LocalesRestaurantes.add(Locales.get(i));
                contador++;
            }            
        }
        return LocalesRestaurantes;
    }
    
    
    /**
     * {@inheritDoc}
     */
    public List <Local> listarTodosPubs() { //OK
        
        List<Local> LocalesPubs = new ArrayList<>();
        Direccion d = new Direccion("haro","larioja","alemania","1");
        Pub pub = new Pub("aux",d,"bonito");
        //Recorro el arraylist de los locales y los que están en la provincia y ciudad indicadas los añado al array
        int contador = 0;
        for (int i=0;i<Locales.size();i++) {
            if(Locales.get(i).getClass().equals(pub.getClass())){
               
                LocalesPubs.add(Locales.get(i));
                contador++;
            }            
        }
        return LocalesPubs;
    }
    
    /**
     * Función que importa una tabla con registros de bares de un fichero .ods
     * 
     * {@inheritDoc}
     */
    public void importarBares(File f) throws IOException{
        OOUtils.open(f);
         SpreadSheet s = SpreadSheet.createFromFile(f);
        //First sheet
        Sheet sheet = s.getSheet(0);
        int numeroFilas = sheet.getRowCount();
        String[] tokens ;
        
        System.out.println("El numero de filas es: "+ numeroFilas);
        String nombreBar, direccion, localidad,provincia;
        for (int i = 0; i < numeroFilas; i++) {
           nombreBar =  (String) sheet.getCellAt(0 , i).getValue();//nombre del bar
           direccion = (String)  sheet.getCellAt(1 , i).getValue();//direccion
           tokens = direccion.split(" ");
           
           localidad =  (String) sheet.getCellAt(2 , i).getValue();//localidad
           provincia = (String) sheet.getCellAt(3 , i).getValue();//provincia
           
            System.out.println("Linea: " + i + " nombre bar: " + nombreBar
                                + " calle: " +tokens[0] + " " + tokens[1]
                                + " Numero: " + tokens[2] + " localidad: " + localidad
                                + " provincia:" + provincia);
            //Generamos la direccion
            Direccion d = new Direccion(localidad,provincia,tokens[0] + " " + tokens[1],tokens[2]);
            //Generamos el local
            Local l = new Local(nombreBar,d,"");
            //Generamos local tipo bar
            Bar b = new Bar(l.getNombre(),l.getDireccion(),l.getDescripcion());
            //Lo añadimos a los locales
            Locales.add(b);
                   
        }
        
        System.out.println("Fin de la importación");
    }
}
