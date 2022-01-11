/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BSystem;

import GSILabs.BModel.*;
import GSILabs.connect.*;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/*
 *
 * @author Arribas
 */
public class UniSystem implements LeisureOffice, ODSPersistente, XMLRepresentable, AdminGateway, ClientGateway{
    static List<Persona> Personas = new ArrayList<>();
    static List<Curso> Cursos = new ArrayList<>();
    static List<Examen> Examenes = new ArrayList<>();
    static List<Nota> Notas = new ArrayList<>();
    static List<Matricula> Matriculas = new ArrayList<>();

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean nuevoUsuario(Persona u) { //OK
        // Agrega el usuario de entrada en la lista de usuarios
        if(Personas.stream().anyMatch(item -> u.equals(item))){
            System.out.println("El usuario ya existe.\n");
            return false;
        }else{
            if(!existeNick(u.getNIA())){
                Personas.add(u);
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
    public boolean eliminaUsuario(Persona u) { //OK
        //Busca el usuario de entrada en la lista y lo elimina, si el usuario introducido es valido
        try{
            Personas.remove(u);
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
    public boolean modificaUsuario(Persona u, Persona nuevoU) { //OK
        //Busca el usuario de la entrada, lo borra e introduce el usuario de entrada modificado, si lo encuentra
        //nuevoU.usuarioValido(nick, password, fechaNacimiento, 0);
        try{
            Personas.remove(u);
            System.out.print("Usuario modificado");
            Personas.add(u);
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
        return Personas.stream().anyMatch(item -> nick.equals(item.getNIA()));
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Persona obtenerUsuario(String nick) { //OK
        //Busca y devuelve un usuario de la lista de usuarios
        //Recorre la lista comparando, si llega al final sin encontrar nada devuelve null
        //Boolean usuarioEncontrado = false;
        for (int i = 0; i<Personas.size(); i++){
            if(Personas.get(i).getNIA() == nick){
                return Personas.get(i);
            }
        }
        
        return null;
        /*
        Usuario userFound = Personas.stream()
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
    public boolean nuevaReview(Examen r) { //OK
        //agrega una review a la lista, antes mira para cada elemento de la lista si coinciden la fecha y el local con el elemento de la entrada, y en funcion de si se da ese caso o no agrega la review
        Boolean flag = true;
        for (int i=0;i<Examenes.size();i++) {
            if(Examenes.get(i).getCurso() == r.getCurso() && Examenes.get(i).getFecha() == r.getFecha()){
                flag = false;
            }
        }
        if (flag){
            try {
                Examenes.add(r);
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
    public boolean eliminaExamen(Examen r) throws RemoteException{
        //elimina una review de la lista si esta existe
        try{
            Examenes.remove(r);
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
    public boolean existeExamen(Persona u, Curso l, LocalDate ld) {
        // Primero busca si un usuario existe, si existe busca un local y una fecha en el array de reviews, si ambas existen confirma la busqueda
        Boolean aaa = Personas.stream().anyMatch(item -> u.equals(item));
        if (aaa == true){
            Boolean bbb = Examenes.stream().anyMatch(item -> l.equals(item.getCurso()));
            Boolean ccc = Examenes.stream().anyMatch(item -> ld.equals(item.getFecha()));
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
    public boolean nuevaNota(Nota c, Examen r) {
        //Busca una review, y si existe agrega una contestacion    
        if (Examenes.stream().anyMatch(item -> r.equals(item))){
            return Notas.add(c);
        }else{
            return false;
        }
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean tieneContestacion(Examen r) {
        //Busca en la lista de contestaciones la review de entrada, y devuelve si existe o no una contestación con dicha review como atributo
        return Notas.stream().anyMatch(item -> r.equals(item.getExamen()));
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Nota obtenerContestacion(Examen r) {
        //Recorre el array de contestaciones buscando la review de entrada, y si la encuentra devuelve la contestacion
        for (int i=0;i<Notas.size();i++) {
            if(Notas.get(i).getExamen().equals(r)){
                return Notas.get(i);
            }
        }
        System.out.println("No existe ninguna contestacion para esa review.");
        return null;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean eliminaContestacion(Nota c) {
        //Busca la contestacion en la lista de contestaciones y la elimina
        try{
            Notas.remove(c);
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
    public boolean nuevoCurso(Curso l) { //OK
        Scanner sc = new Scanner(System.in);
        //Primero consulto al usuario si lo que desea eliminar es un bar, restaurante o pub.
             System.out.println("De que tipo quieres que sea el curos: 1-Grado  2-Master 3-Doctorado "); 
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
        for (int i=0;i<Cursos.size();i++) {      
            if(Cursos.get(i).getEscuelaPertenece().equals(l.getEscuelaPertenece())){
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
                    Master b = new Master(l.getNombre(),l.getEscuelaPertenece(),l.getContenidos());
                    Cursos.add(b);
                    System.out.println("******Grado añadido*******");
                    break;
                case 2:
                    Grado r = new Grado(l.getNombre(),l.getEscuelaPertenece(),l.getContenidos());
                    Cursos.add(r);
                    System.out.println("******Master añadido*******");
                    break;
                case 3:
                    Doctorado p = new Doctorado(l.getNombre(),l.getEscuelaPertenece(),l.getContenidos());
                    Cursos.add(p);
                    System.out.println("******Doctorado añadido*******");
                    break;
            } 
            return true;
        } else{
            //Si ya habia un local en esa dirección lo notifico al usuario.
            System.out.println("Ya existe un curos en esa direccion.");
            return false;
        }  
             }
    }

    @Override
    /**
     * {@inheritDoc}
     */

    public boolean eliminarCurso(Curso l) {//OK
        //Recibo un objeto de tipo local, hago un try intentando eliminarlo del
        //arraylist locales, si no esta saltará una excepcion que recojo con el catch e indico que no existe.
        try{
            Cursos.remove(l);
            System.out.print("Ya no existen cursos en la dirección introducida");
            return true;
        }
        catch(NullPointerException e){
            System.out.print("El curso no existe");
            return false;
        }
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Curso obtenerCurso(EscuelaPertenece d) {//OK
        //Recibo como parametro una dirección (sabemos que existe un único local en una misma dirección)
        //Recorro el arraylist que almacena los locales y cuando encuentro un local en esa dirección lo devuelvo.
        //En caso de no haber ninguno recogo la excepcion y digo al usuario que no hay ningun local.
        try{
            Curso localFound = Cursos.stream()
                                .filter(item -> item.getEscuelaPertenece().equals(d))
                                .collect(Collectors.toList()).get(0);
            return localFound;
        }catch(IndexOutOfBoundsException e){
            System.out.println("No existe ningún curso en la dirección que buscas");
            return null;
        }
        
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean asociarCurso(Curso l, Propietario p) {
        //Busco en el array de locales el local de entrada y agrego el propietario; el método se encargará de decidir si se puede agregar el propietario o no
        for (int i=0;i<Cursos.size();i++) {
            if(Cursos.get(i).getEscuelaPertenece().equals(l.getEscuelaPertenece())){
                return Cursos.get(i).anadirPropietario(p);
            }
        }
        return false;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean desasociarCurso(Curso l, Propietario p) {
        
        //Vemos si el local existe, si no existe hemos acabado
        //Si el local existe, sacamos sus propietarios y buscamos el propietario de entrada, si no existe lo indicamos y acabamos, si sí existe lo borramos
        //Si al durante el bucle for se borra el propietario lo indicamos y acabamos, ya que no puede haber dos propietarios iguales
        //en caso contrario sabemos que podemos indicar que el propietario de entrada no era un propietario del local indicado
        
        if(Cursos.stream().anyMatch(item -> l.equals(item))){
            Persona[] propietarios = l.getDocentes();
            
            for (int i = 0; i<propietarios.length; i++){
                if(propietarios[i] == p){
                    propietarios[i] = null;
                    System.out.println("Se ha borrado el propietario indicado.\n");
                    return true;
                }
            }
            System.out.println("El propietario indicado no formaba parte de los propietarios asociados al curso.\n");
            return false;
        }else{
            System.out.println("El curso no existe.\n");
            return false;
        }

    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean actualizarCurso(Curso viejoL, Curso nuevoL) { //OK
        try{
        Cursos.stream().filter(item -> item.getEscuelaPertenece().equals(viejoL.getEscuelaPertenece()))
                        .collect(Collectors.toList()).get(0).setContenidos(nuevoL.getContenidos());
        
        Cursos.stream().filter(item -> item.getEscuelaPertenece().equals(viejoL.getEscuelaPertenece()))
                        .collect(Collectors.toList()).get(0).setEscuelaPertenece(nuevoL.getEscuelaPertenece());
        
        Cursos.stream().filter(item -> item.getEscuelaPertenece().equals(viejoL.getEscuelaPertenece()))
                        .collect(Collectors.toList()).get(0).setNombre(nuevoL.getNombre());
        
        System.out.print("Curso actualizado\n");
        return true;
        }catch(Exception ex){
            System.out.print("No ha sido posible actualizar el curso\n");
            return false;
        }
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Examen[] verExamenes(Curso l) {
        Examen[] rev = null;
        //Recorro el array de reviews e incluyo en la lista de items las reviews que tengan ese local
        for (int i=0;i<Examenes.size();i++) {
            if(Examenes.get(i).getCurso().equals(l)){
                rev[rev.length] = Examenes.get(i);
                //rev.add(Examenes.get(i));
                //return Examenes.get(i);
            }
        }
        return rev;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean nuevaMatricula(Alumno c, Matriculable r, LocalDate ld, LocalTime lt) {
        
        //Comienzo comparando la fecha actual con la fecha de entrada, en caso de que sea igual o mayor continuo
        //Ahora miro si la fecha actual es igual a la fecha de entrada, si es igual miro si la hora de entrada es posterior a la hora actual, en caso contrario paro
        //Si las horas son correctas paso a mirar en las listas de usuarios y locales si existen el reservable y el cliente, en caso afirmativo de ambos creo la reserva y la agrego a la lista
        //Si la hora de entrada es mayor a la hora actual, repito el comentario anterior
        //Si alguna de las condiciones no se cumple se devuelve false y no se realiza la operacion
        
        if (ld.compareTo(LocalDate.now())>= 0){
            if (ld.compareTo(LocalDate.now())== 0){
                if(lt.compareTo(LocalTime.now()) > 0 ){
                    //ver si el cliente existe y si el local existe
                    if(Personas.stream().anyMatch(item -> c.equals(item))){
                        if(Cursos.stream().anyMatch(item -> r.equals(item))){
                        Matricula res = new Matricula(c, r, ld, lt, 0);
                        Matriculas.add(res);
                        return true;
                        }else{
                            System.out.println("El curso no existe\n");
                            return false;
                        }
                    }else{
                        System.out.println("El alumno no existe\n");
                        return false;
                    }
                }else{
                    System.out.println("Estás matriculado para la fecha de hoy con una hora anterior a la actual\n");
                    return false;
                }
            }else{
                if(Personas.stream().anyMatch(item -> c.equals(item))){
                    if(Cursos.stream().anyMatch(item -> r.equals(item))){
                        Matricula res = new Matricula(c, r, ld, lt, 0);
                        Matriculas.add(res);
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
    public Matricula[] obtenerMatriculas(Alumno c) {
        Matricula[] res = null;
        //Recorro el array de reservas e incluyo en la lista de items las reservas que tengan ese cliente
        for (int i=0;i<Matriculas.size();i++) {
            if(Matriculas.get(i).getAlumno().equals(c)){
                res[res.length] = Matriculas.get(i);
            }
        }
        return res;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Matricula[] obtenerMatriculas(Matriculable r) {
        Matricula[] res = null;
        //Recorro el array de reservas e incluyo en la lista de items las reservas que tengan ese reservable
        for (int i=0;i<Matriculas.size();i++) {
            if(Matriculas.get(i).getReservable().equals(r)){
                res[res.length] = Matriculas.get(i);
            }
        }
        return res;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Matricula[] obtenerMatriculas(LocalDate ld) {
        Matricula[] res = null;
        //Recorro el array de reservas e incluyo en la lista de items las reservas que tengan esa fecha
        for (int i=0;i<Matriculas.size();i++) {
            if(Matriculas.get(i).getFecha().equals(ld)){
                res[res.length] = Matriculas.get(i);
            }
        }
        return res;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean eliminarMatricula(Matricula r) {
        //Busca la reserva de entrada en la lista y lo elimina, si la reserva introducida es valida/existe
        try{
            Matriculas.remove(r);
            System.out.print("Matricula eliminada");
            return true;
        }
        catch(NullPointerException e){
            System.out.print("La matricula no existe");
            return false;
        }
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Curso[] listarCursos(String ciudad, String provincia) { //OK
        Curso LocalesDE[] = new Curso[Cursos.size()];
        //Recorro el arraylist de los locales y los que están en la provincia y ciudad indicadas los añado al array
        int contador = 0;
        for (int i=0;i<Cursos.size();i++) {
            if(Cursos.get(i).getEscuelaPertenece().getNombre().equals(ciudad) && Cursos.get(i).getEscuelaPertenece().getNombreDirector().equals(provincia)){
                LocalesDE[contador] = Cursos.get(i);
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
    public Master[] listarMasteres(String ciudad, String provincia) { //OK
        Curso LocalesDE[] = new Curso[Cursos.size()];
        EscuelaPertenece d = new EscuelaPertenece("upna","paco paco","aulario");
        Master bar = new Master("aux",d,"bonito");
        //Recorro el arraylist de los locales y los que están en la provincia y ciudad indicadas los añado al array
        int contador = 0;
        for (int i=0;i<Cursos.size();i++) {
            if(Cursos.get(i).getEscuelaPertenece().getNombre().equals(ciudad) && Cursos.get(i).getEscuelaPertenece().getNombreDirector().equals(provincia) && Cursos.get(i).getClass().equals(bar.getClass())){
                LocalesDE[contador] = Cursos.get(i);
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
    public Grado[] listarGrados(String ciudad, String provincia) {//OK
        Curso LocalesDE[] = new Curso[Cursos.size()];
        EscuelaPertenece d = new EscuelaPertenece("upna","paco paco","aulario");
        Grado restaurante = new Grado("aux",d,"bonito");
        //Recorro el arraylist de los locales y los que están en la provincia y ciudad indicadas los añado al array
        int contador = 0;
        for (int i=0;i<Cursos.size();i++) {
            if(Cursos.get(i).getEscuelaPertenece().getNombre().equals(ciudad) && Cursos.get(i).getEscuelaPertenece().getNombreDirector().equals(provincia) && Cursos.get(i).getClass().equals(restaurante.getClass())){
                LocalesDE[contador] = Cursos.get(i);
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
    public Doctorado[] listarDoctorados(String ciudad, String provincia) {//OK
        Curso LocalesDE[] = new Curso[Cursos.size()];
        EscuelaPertenece d = new EscuelaPertenece("upna","paco paco","aulario");
        Doctorado pub = new Doctorado("aux",d,"bonito");
        //Recorro el arraylist de los locales y los que están en la provincia y ciudad indicadas los añado al array
        int contador = 0;
        for (int i=0;i<Cursos.size();i++) {
            if(Cursos.get(i).getEscuelaPertenece().getNombre().equals(ciudad) && Cursos.get(i).getEscuelaPertenece().getNombreDirector().equals(provincia) && Cursos.get(i).getClass().equals(pub.getClass())){
                LocalesDE[contador] = Cursos.get(i);
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
    public boolean eliminaNota(Examen r) {
        
        //Comenzamos mirando si la review existe, si no existe acabamos
        //Si la review existe buscamos en la lista de contestaciones aquellas que vengan de esa review y las borramos
        
        int cont = 0;
        
        if(Notas.stream().anyMatch(item -> r.equals(item.getExamen()))){
            for(int i=0; i<Notas.size(); i++){
                if (Notas.get(i).getExamen().equals(r)){
                    Notas.remove(Notas.get(i));
                    cont ++;
                }
            }
            System.out.println("Se han eliminado " + cont + " contestaciones de la nota indicada.\n");
            return true;
        }else{
            System.out.println("La nota introducida no existe.\n");
            return false;
        }
    }
    
    /* Funciones implementadas para realizar la practica 2*/
   
    /**
     * {@inheritDoc}
     */
    public List <Curso> listarTodosMasteres() { //OK
        
        List<Curso> CursosMasteres = new ArrayList<>();
        EscuelaPertenece d = new EscuelaPertenece("upna","paco paco","aulario");
        Master master = new Master("aux",d,"bonito");
        //Recorro el arraylist de los locales y los que están en la provincia y ciudad indicadas los añado al array
        int contador = 0;
        for (int i=0;i<Cursos.size();i++) {
            if(Cursos.get(i).getClass().equals(master.getClass())){
               
                CursosMasteres.add(Cursos.get(i));
                contador++;
            }            
        }
        
        
        return CursosMasteres;
    }
    /**
     * {@inheritDoc}
     */
    public List <Curso> listarTodosGrados() { //OK
        
        List<Curso> CursosGrados = new ArrayList<>();
        EscuelaPertenece d = new EscuelaPertenece("upna","paco paco","aulario");
        Grado grado = new Grado("aux",d,"bonito");
        //Recorro el arraylist de los locales y los que están en la provincia y ciudad indicadas los añado al array
        int contador = 0;
        for (int i=0;i<Cursos.size();i++) {
            if(Cursos.get(i).getClass().equals(grado.getClass())){
               
                CursosGrados.add(Cursos.get(i));
                contador++;
            }            
        }
        return CursosGrados;
    }
    
    
    /**
     * {@inheritDoc}
     */
    public List <Curso> listarTodosDoctorados() { //OK
        
        List<Curso> CursosDoctorados = new ArrayList<>();
        EscuelaPertenece d = new EscuelaPertenece("upna","paco paco","aulario");
        Doctorado doctorado = new Doctorado("aux",d,"bonito");
        //Recorro el arraylist de los locales y los que están en la provincia y ciudad indicadas los añado al array
        int contador = 0;
        for (int i=0;i<Cursos.size();i++) {
            if(Cursos.get(i).getClass().equals(doctorado.getClass())){
               
                CursosDoctorados.add(Cursos.get(i));
                contador++;
            }            
        }
        return CursosDoctorados;
    }
    
    /**
     * Función que importa una tabla con registros de bares de un fichero .ods
     * 
     * {@inheritDoc}
     */
    public int importarMasteres(File f) throws IOException{
    
        return 0;
    
    }
    
     /**
     * Función que importa una tabla con registros de Pubs de un fichero .ods
     * 
     * {@inheritDoc}
     */
    public int importarDoctorados(File f) throws IOException{
    
        return 0;
    
    }
    
    @Override
    public String toXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveToXML(File f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveToXML(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean loadFromFile(File f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveToFile(File f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean loadXMLFile(File f){
        try {
            // create an instance of `JAXBContext`
            JAXBContext context = JAXBContext.newInstance(UniSystem.class);

            // create an instance of `Unmarshaller`
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // convert XML file to `BusinessSystem` object
            UniSystem bs = (UniSystem) unmarshaller.unmarshal(f);

            // print BusinessSystem object
            System.out.println(bs);
            return true;

        } catch (JAXBException ex) {
            ex.printStackTrace();
            return false;
        }
    
    }

    @Override
    public boolean eliminaCurso(Curso l) throws RemoteException { //copia de eliminaRLocal, ya comprobado que funciona
        try{
            Cursos.remove(l);
            System.out.print("Ya no existen locales en la dirección introducida\n");
            return true;
        }
        catch(NullPointerException e){
            System.out.print("El local no existe\n");
            return false;
        }
    }

    @Override
    public boolean eliminaExamenesDeCurso(Curso l) throws RemoteException {
        Boolean flag = false;
        for (int i=0;i<Examenes.size();i++) {
            if(Examenes.get(i).getCurso() == l){
                try {
                    Examenes.remove(i);
                    System.out.println("examen eliminada\n");
                    flag = true;
                }catch (Exception e) {
                    System.out.println("Error eliminando el examen\n");
                    return false;
                 }
            }
        }
        
         if(flag == false){
            return false;
        }else{
             return true;
         }
    }
    

    @Override
    public int eliminaExamenesDeAlumno(Alumno c) throws RemoteException {
        int count = 0;
        for (int i=0;i<Examenes.size();i++) {
            if(Examenes.get(i).getAlumno() == c){
                try {
                    Examenes.remove(i);
                    System.out.println("Examen eliminado\n");
                    count++;
                }catch (Exception e) {
                    System.out.println("Error eliminando examen\n");
                    return -1;
                 }
            }
        }
        
        return count;
    }

    @Override
    public boolean insertaExamenFalso(Curso l, Integer puntuacion) throws RemoteException {

        Date hoy = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Alumno c = new Alumno("james876", "12345", new GregorianCalendar(1997, Calendar.APRIL, 23).getTime(), 999);
        Examen r = new Examen(puntuacion, "Nice", hoy, l, c);
        try{
            if (Cursos.contains(l) == true){
                Examenes.add(r);
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            System.out.println("No existe ningún curso en la dirección que buscas");
            return false;
        }
    }

    @Override
    public Curso getCurso(String name) throws RemoteException { //copia de obtenerCurso, cambiando direccion por nombre
        try{
            Curso cursoFound = Cursos.stream()
                                .filter(item -> item.getNombre().equals(name))
                                .collect(Collectors.toList()).get(0);
            return cursoFound;
        }catch(IndexOutOfBoundsException e){
            System.out.println("No existe ningún curso en la dirección que buscas");
            return null;
        }
    }

    @Override
    public Curso[] getCursos(String name) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertaExamen(Examen r) throws RemoteException { //copia de nuevareview
        Boolean flag = true;
        for (int i=0;i<Examenes.size();i++) {
            if(Examenes.get(i).getCurso() == r.getCurso() && Examenes.get(i).getFecha() == r.getFecha()){
                flag = false;
            }
        }
        if (flag){
            try {
                Examenes.add(r);
                System.out.print("Examen introducido\n");
                return true;
            }catch (Exception e) {
                System.out.print("Error introduciendo examen\n");
                return false;
            }
        }else{
            System.out.print("Una examen ha sido introducida en esa fecha y en ese curso anteriormente\n");
            return false;
        }

    }

    @Override
    public boolean quitaExamen(Examen r) throws RemoteException {
       Boolean flag = false;
        for (int i=0;i<Examenes.size();i++) {
            if(Examenes.get(i).getCurso() == r.getCurso() && Examenes.get(i).getFecha() == r.getFecha()){
                try {
                    Examenes.remove(r);
                    System.out.print("Examen eliminado\n");
                    flag = true;
                    return true;
                }catch (Exception e) {
                    System.out.print("Error eliminando examen\n");
                    return false;
                 }
            }
        }
        
         if(flag == false){
            return false;
        }else{
             return true;
         }
    }
    

    @Override
    public Master mejorMaster(String ciudad) throws RemoteException{
    
        return null;
    
    } 

    @Override
    public Grado[] mejoresGrados(String ciudad, Integer num) throws RemoteException {
        Grado[] arr = new Grado[num];
        List<Curso> LocalesRestaurantes = new ArrayList<>();
        EscuelaPertenece d = new EscuelaPertenece("upna","paco paco","aulario");
        Grado restaurante = new Grado("aux",d,"bonito");
        //Recorro el arraylist de los locales y los que están en la provincia y ciudad indicadas los añado al array
        int contador = 0;
        for (int i=0;i<Cursos.size();i++) {
            if(Cursos.get(i).getClass().equals(restaurante.getClass())){
             
                LocalesRestaurantes.add(Cursos.get(i));
                contador++;
            }            
        }
        return arr;
    }
}
