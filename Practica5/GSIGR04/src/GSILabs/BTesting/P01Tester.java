/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BTesting;

import GSILabs.BModel.Master;
import GSILabs.BModel.Examen;
import GSILabs.BModel.Matricula;
import GSILabs.BModel.EscuelaPertenece;
import GSILabs.BModel.Curso;
import GSILabs.BModel.Nota;
import GSILabs.BModel.Alumno;
import GSILabs.BModel.Doctorado;
import GSILabs.BModel.Grado;
import GSILabs.BModel.Persona;
import GSILabs.BSystem.UniSystem;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import GSILabs.BModel.Matriculable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author Arribas
 */
public class P01Tester {
               
    public static void main(String[] args) throws IOException, ParseException {
        // Instanciamos una clase BusinessSystem
        UniSystem bs = new UniSystem();
        String passwordUsuario;
        String nombreUsuario;
        String psw;
        Alumno c;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca su nombre de usuario: ");
        nombreUsuario = sc.nextLine();
        passwordUsuario = searchCsvUser(nombreUsuario);
        
        if (passwordUsuario == null){
            System.out.println("El usuario no existe. El programa finalizará ahora mismo...");
            System.exit(1);
        }
        System.out.printf("Introduzca la contraseña para el usuario %s: ", nombreUsuario);
        psw  = sc.nextLine();
        
        if (!psw.equals(passwordUsuario)){
            System.out.println("La contraseña del usuario es incorrecta. El programa finalizará ahora mismo...");
            System.exit(1);
        }
        System.out.println("Ha iniciado sesión con éxito");
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        BufferedReader br = new BufferedReader(new FileReader("alumnos.csv"));
        String line;
        while ( (line = br.readLine()) != null ) {
            String[] values = line.split(",");
            String[] nia = nombreUsuario.split(".");
            if(values[0].equals(nia[1])) {
                Date date = format.parse(values[2]);
                //Si encuentra un match de usuario, recoge la contraseña de éste
                c = new Alumno(nia[1], values[1], date, Integer.parseInt(values[3]));
                break;
            }
        }
        
        br.close();
        //Menú principal, el usuario elige que quiere hacer.
        int choice=999;
        do {
            System.out.println("0. Salir\n"
                                + "1. Registrar Usuario\n"
                                + "2. Buscar usuario por nick\n"
                                + "3. Registrar local\n"
                                + "4. Añadir Review\n"
                                + "5. Eliminar local\n"
                                + "6. Listar locales por provincia y ciudad\n"
                                + "7. Obtener local\n"
                                + "8. Modificar local\n"
                                + "9. Listar bares por provincia y ciudad\n"
                                + "10. Listar restaruantes por provincia y ciudad\n"
                                + "11. Listar pubs por provincia y ciudad\n"                    
                                + "12. Comprobación de ejercicio 4 apartado S10)\n"
                                + "13. Comprobación de ejercicio 4 apartado S9) \n"
                                + "14. Comprobación de ejercicio 4 apartado S8) \n"
                                + "15. Comprobación de ejercicio 4 apartado S4) \n"
                                + "16. Comprobacion de ejercicio 4 apartado S5) \n"
                                + "17. Comprobacion de ejercicio 4 apartado S3) \n"
                                + "18. Comprobacion de ejercicio 4 apartado S1) \n"
                                + "19. Comprobación de ejercicio 4 apartado S6) \n"
                                + "20. Comprobacion de ejercicio 4 apartado S2) \n"
                                + "21. Comprobacion de ejercicio 6 practica 2   \n"
                                + "22. Comprobacion de ejercicio extra e2.1     \n");

 
            System.out.print("¿Qué deseas hacer? : \n");
            //Recogo su elección y si no es válida se lo notifico.
            try{
                choice = sc.nextInt();
            }catch(Exception e){
                System.out.println("Caracter inválido");
            }
            //En función de lo que haya elegido hacemos una acción u otra.
            sc.nextLine();
            switch (choice) {
                case 0: //cerrar programa
                    System.out.println("Cerrando programa...");
                    break;
                case 1:  //Añadir usuario                
                    boolean registerOk = false;
                    System.out.println("Introduce el nombre de usuario: ");  
                    nombreUsuario = sc.nextLine();      

                    System.out.println("Introduce la contraseña: "); 
                    passwordUsuario = sc.nextLine();

//                    System.out.println("Introduce la fecha de nacimiento (dd/MM/yyyy): "); 
//                    String edadUsuario = sc.nextLine();
                    Date fechaNacimientoUsuario;
                    
                    try {
                        fechaNacimientoUsuario = new SimpleDateFormat("dd/MM/yyyy").parse("13/11/1999");
                        Persona u = new Persona(nombreUsuario, passwordUsuario, fechaNacimientoUsuario,1);
                        if (bs.nuevoUsuario(u)){
                            System.out.println("Usuario registrado correctamente!");
                            System.out.println(u.toString());
                        }else{
                            System.out.println("El usuario " + u.getNIA()+ " ya existe!");
                        }
                    } catch (IllegalArgumentException | ParseException ex) {
                        System.out.println(ex.toString());
                    }

                    break;
                case 2: //Buscar usuario por nick
                    //Obtener usuario
                    System.out.println("¿Que usuario quieres buscar?: ");  
                    nombreUsuario = sc.nextLine(); 
                    Persona u = bs.obtenerUsuario(nombreUsuario);
                    if(u != null){
                        System.out.println("Usuario encontrado");
                        System.out.println(u.toString());
                    }else{
                        System.out.println("Usuario NO encontrado");
                    }
                    break;
                case 3: //Registrar local
                    //Pido al usuario el nombre del nuevo local.                    

                    System.out.println("Introduce el nombre del local: ");  
                    String nombreLocal = sc.nextLine(); 

                    //Pido al usuario los componentes de la direccion del local.        
                    System.out.println("Vas a introducir la dirección \n");         
                    System.out.println("Introduce la localidad: "); 
                    String localidad = sc.nextLine();         
                    System.out.println("Introduce la provincia: "); 
                    String provincia = sc.nextLine();       
                    System.out.println("Introduce la calle: "); 
                    String calle = sc.nextLine();       
                    System.out.println("Introduce el numero: "); 
                    String numero = sc.nextLine();
                    //Genero la dirección.
                    EscuelaPertenece d = new EscuelaPertenece(localidad,provincia,calle);

                    //Pido la descripcion del local al usuario.
                    System.out.println("Introduce la descripción del local: "); 
                    String descripcionLocal = sc.nextLine();
                    Curso l = new Curso(nombreLocal,d,descripcionLocal);
                    //añado el nuevo local
                    bs.nuevoCurso(l);
                    break;
                case 4://Añadir review
                    System.out.println("¿cual es tu puntuacion de 1 a 5?");
                    int valint;
                    do{
                        String valoracion = sc.nextLine();
                        valint = Integer.parseInt(valoracion);
                    }while(valint < 1 || valint > 5);
                    
                    System.out.println("Escribe a continuación tu comentario");
                    String comentario = sc.nextLine();

                    Date fecha = new Date(); //coge la fecha actual
                    System.out.println("Vas a introducir la escuela del curso al que quieres añadir examen\n");         
                    System.out.println("Introduce el nombre: "); 
                    String loc = sc.nextLine();         
                    System.out.println("Introduce el director: "); 
                    String prov = sc.nextLine();       
                    System.out.println("Introduce el edificio central: "); 
                    String cal = sc.nextLine();       
                    //Genero la escuela.
                    EscuelaPertenece dir = new EscuelaPertenece(loc,prov,cal);
                    Curso local = bs.obtenerCurso(dir);
                    Examen r = new Examen(valint,comentario,fecha,local, c);
                    bs.nuevaReview(r);
                    break;
                case 5: //Eliminar local
                    //Recogo la direccion del local que quiero eliminar para identificarlo
                    System.out.println("Introduce a continuación los datos del local a eliminar"); 
                    System.out.println("Introduce la localidad: "); 
                    String localidadB = sc.nextLine();         
                    System.out.println("Introduce la provincia: "); 
                    String provinciaB = sc.nextLine();       
                    System.out.println("Introduce la calle: "); 
                    String calleB = sc.nextLine();       
                    System.out.println("Introduce el numero: "); 
                    String numeroB = sc.nextLine();
                    //Genero la dirección.
                    EscuelaPertenece dB = new EscuelaPertenece(localidadB,provinciaB,calleB);
                    //Cojo el local de esa dirección
                    Curso localEliminar = bs.obtenerCurso(dB);
                    //elimino el local
                    bs.eliminarCurso(localEliminar);                    
                    break;
                case 6: //Listar locales
                    System.out.println("Introduce la provincia deseada "); 
                    String provinciaD = sc.nextLine();
                    System.out.println("Introduce la ciudad deseada "); 
                    String ciudadD = sc.nextLine();
                    System.out.println("Listado de locales en: "+ ciudadD + ", "+ provinciaD +"\n");   
                    Curso localeslist[] = bs.listarCursos(ciudadD, provinciaD);                                        
                    break;
                case 7: //Obtener Local
                    System.out.println("Introduce la localidad: "); 
                    String localidadc = sc.nextLine();         
                    System.out.println("Introduce la provincia: "); 
                    String provinciac = sc.nextLine();       
                    System.out.println("Introduce la calle: "); 
                    String callec = sc.nextLine();       
                    System.out.println("Introduce el numero: "); 
                    String numeroc = sc.nextLine();
                    //Genero la dirección.
                    EscuelaPertenece dc = new EscuelaPertenece(localidadc,provinciac,callec);
                    Curso local1 = bs.obtenerCurso(dc);
                    
                    if(local1 != null){
                        System.out.println("Local encontrado");
                        System.out.println(local1.toString());
                    }
                    break;
                case 8: //Modificar Local
                    //identifico el local que quiero modificar con su dirección que es única.
                    System.out.println("Datos del local a modificar: \n"); 
                    System.out.println("Introduce la localidad: "); 
                    localidadc = sc.nextLine();         
                    System.out.println("Introduce la provincia: "); 
                    provinciac = sc.nextLine();       
                    System.out.println("Introduce la calle: "); 
                    callec = sc.nextLine();       
                    System.out.println("Introduce el numero: "); 
                    numeroc = sc.nextLine();
                    //Genero la dirección.
                    EscuelaPertenece dd = new EscuelaPertenece(localidadc,provinciac,callec);
                    Curso localviejo = bs.obtenerCurso(dd);
                    
                    //Variables auxiliares.
                    String nuevaDescripcion = null;
                    EscuelaPertenece nuevaDireccion = null;
                    String nuevoNombre = null;
                    
                    int eleccion = 10;
                    //Escojo el atributo que deseo modificar.
                    System.out.println("¿Que deseas modificar?\n 1- descripcion\n 2-nombre \n 3-dirección");
                    try{
                        eleccion = sc.nextInt();
                    }catch(Exception e){
                        System.out.println("Caracter inváido");
                    }
                    
                    
                    Scanner sc2 = new Scanner(System.in);
                    switch(eleccion){
                        case 1:
                            System.out.println("Introduce la descripción del local: "); 
                            nuevaDescripcion = sc2.nextLine();
                            try{
                                nuevoNombre = localviejo.getNombre();
                                nuevaDireccion = localviejo.getEscuelaPertenece();
                            }catch(NullPointerException e){
                              System.out.println("");  
                            }                            
                            break;
                        case 2:
                            System.out.println("Introduce el nombre del local: "); 
                            nuevoNombre = sc2.nextLine();
                            try{
                                nuevaDescripcion = localviejo.getContenidos();
                                nuevaDireccion = localviejo.getEscuelaPertenece();
                            }catch(NullPointerException e){
                              System.out.println("");  
                            }
                            break;
                        case 3:
                            System.out.println("Introduce la localidad: "); 
                            localidadc = sc2.nextLine();         
                            System.out.println("Introduce la provincia: "); 
                            provinciac = sc2.nextLine();       
                            System.out.println("Introduce la calle: "); 
                            callec = sc2.nextLine();       
                            System.out.println("Introduce el numero: "); 
                            numeroc = sc2.nextLine();
                            nuevaDireccion = new EscuelaPertenece(localidadc,provinciac,callec);
                            try{
                               nuevaDescripcion = localviejo.getContenidos();
                                nuevoNombre = localviejo.getNombre(); 
                            }catch(NullPointerException e){
                              System.out.println("");  
                            }                           
                            break;
                        default:
                            nuevaDescripcion = localviejo.getContenidos();
                            nuevoNombre = localviejo.getNombre();
                            nuevaDireccion = localviejo.getEscuelaPertenece();
                            System.out.println("Seleccion no válida\n");
                            break;
                    }   
                    try{
                        bs.actualizarCurso(localviejo, new Curso(nuevoNombre,nuevaDireccion,nuevaDescripcion));
                    }catch(Exception e){
                        System.out.println("no se ha podido actualizar porque no existe un local en esa direccion");
                    }
                    break;
                case 9: //Listar solo bares
                    System.out.println("Introduce la provincia deseada "); 
                    provinciaD = sc.nextLine();
                    System.out.println("Introduce la ciudad deseada "); 
                    ciudadD = sc.nextLine();
                    System.out.println("Listado de bares en: "+ ciudadD + ", "+ provinciaD +"\n");   
                    Curso localeslist2[] = bs.listarMasteres(ciudadD, provinciaD);                                        
                    break;
                case 10: //Listar solo restaurantes
                    System.out.println("Introduce la provincia deseada "); 
                    provinciaD = sc.nextLine();
                    System.out.println("Introduce la ciudad deseada "); 
                    ciudadD = sc.nextLine();
                    System.out.println("Listado de restaurantes en: "+ ciudadD + ", "+ provinciaD +"\n");   
                    Curso localeslist3[] = bs.listarGrados(ciudadD, provinciaD);                                        
                    break;
                case 11: //Listar solo pubs
                    System.out.println("Introduce la provincia deseada "); 
                    provinciaD = sc.nextLine();
                    System.out.println("Introduce la ciudad deseada "); 
                    ciudadD = sc.nextLine();
                    System.out.println("Listado de pubs en: "+ ciudadD + ", "+ provinciaD +"\n");   
                    Curso localeslist4[] = bs.listarDoctorados(ciudadD, provinciaD);                                        
                    break;
                
                //Ejercicio 4 S10, no se pueden añadir dos reviews del mismo usuario, el mismo dia para el mismo local
                case 12:
                    //Creamos un local y la fecha actual
                    Date date = new Date();
                    //Pido al usuario el nombre del nuevo local.                    

                    System.out.println("Introduce el nombre del local: ");  
                    String nombreLocal1 = sc.nextLine(); 

                    //Pido al usuario los componentes de la direccion del local.        
                    System.out.println("Vas a introducir la dirección \n");         
                    System.out.println("Introduce la localidad: \n"); 
                    String localidad1 = sc.nextLine();         
                    System.out.println("Introduce la provincia: \n"); 
                    String provincia1 = sc.nextLine();       
                    System.out.println("Introduce la calle: \n"); 
                    String calle1 = sc.nextLine();       
                    System.out.println("Introduce el numero: \n"); 
                    String numero1 = sc.nextLine();
                    //Genero la dirección.
                    EscuelaPertenece d1 = new EscuelaPertenece(localidad1,provincia1,calle1);

                    //Pido la descripcion del local al usuario.
                    System.out.println("Introduce la descripción del local: \n"); 
                    String descripcionLocal2 = sc.nextLine();
                    Curso l2 = new Curso(nombreLocal1,d1,descripcionLocal2);
                    //añado el nuevo local
                    bs.nuevoCurso(l2);
                    //Creo cliente
        
                    //Creamos la primera review con la fecha y el local introducidos
                    Examen rev1 = new Examen(5, "aaaaaaaaa",date, l2, c);
                    System.out.println("Primera review introducida.\n");
                            
                    //agregamos la primera review
                    bs.nuevaReview(rev1);

                    //Creamos la segunda review con la misma fecha y el mismo local
                    
                    Examen rev2 = new Examen(3, "bbbbbbbbb",date, l2, c);
                    System.out.println("Intentamos agregar la segunda review.\n");
                    //Agregamos la segunda review
                    Boolean resultado = bs.nuevaReview(rev2);
                    
                    if(resultado == false){
                        System.out.println("Operación no completada: review del mismo local en la misma fecha ya existente.\n");
                    }
                    
                    break;
                
                case 13:
                    
                    
                    //Comenzamos agregando un local:
                    System.out.println("Introduce el nombre del local: ");  
                    String nombreLocal13 = sc.nextLine(); 

                    //Pido al usuario los componentes de la direccion del local.        
                    System.out.println("Vas a introducir la dirección \n");         
                    System.out.println("Introduce la localidad: \n"); 
                    String localidad13 = sc.nextLine();         
                    System.out.println("Introduce la provincia: \n"); 
                    String provincia13 = sc.nextLine();       
                    System.out.println("Introduce la calle: \n"); 
                    String calle13 = sc.nextLine();       
                    System.out.println("Introduce el numero: \n"); 
                    String numero13 = sc.nextLine();
                    //Genero la dirección.
                    EscuelaPertenece d13 = new EscuelaPertenece(localidad13,provincia13,calle13);

                    //Pido la descripcion del local al usuario.
                    System.out.println("Introduce la descripción del local: \n"); 
                    String descripcionLocal23 = sc.nextLine();
                    Curso l23 = new Curso(nombreLocal13,d13,descripcionLocal23);
                    //añado el nuevo local
                    bs.nuevoCurso(l23);
                    
                    //Una vez agregado el local vamos a agregar los tres propietarios
                    
                    String fechas = new String("13/11/1999");
                    
                
                    try {
                        
                        
                        fechaNacimientoUsuario = new SimpleDateFormat("dd/MM/yyyy").parse(fechas);
                        Persona pro1 = new Persona("Aaaaa", "aaaaa", fechaNacimientoUsuario,1);
                        Persona pro2 = new Persona("Bbbbb", "bbbbb", fechaNacimientoUsuario,1);
                        Persona pro3 = new Persona("Ccccc", "ccccc", fechaNacimientoUsuario,1);
                    
                        l23.anadirPropietario(pro1);
                        l23.anadirPropietario(pro2);
                        l23.anadirPropietario(pro3);
                        
                        System.out.println("Tres propietarios agregados\n");
                        
                        System.out.println("Probamos a agregar un cuarto propietario\n");
                        Persona pro4 = new Persona("Ddddd", "ddddd", fechaNacimientoUsuario,1);
                        
                        Boolean flag = l23.anadirPropietario(pro4);
                        
                        if(flag){
                            System.out.println("Cuarto propietario agregado correctamente\n");
                        }else{
                            System.out.println("Cuarto propietario no agregado, maximo tres propietarios\n");
                        }
                        
                    } catch (ParseException ex) {
                        Logger.getLogger(P01Tester.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                


                case 14:
                    String fechas11 = new String("20/11/2020");
                    Examen rev2345 = null; //Comentar esta linea para realizar la comprobacion
                    try {
                        Date fechaContestacion = new SimpleDateFormat("dd/MM/yyyy").parse(fechas11);
                        //Simplemente vamos a crear una contestacion en cuyo constructor no va a haber una review válida ya que simplemente no la hemos creado
                        //Para ello vamos a capturar el error que nos va a lanzar el compilador e indicar el porque
                        try{
                            Nota contestacion = new Nota(rev2345, 3, fechaContestacion);
                        }catch(RuntimeException e){
                            System.out.println("Se ha intentado crear una contestacion a una review que no existe");
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(P01Tester.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case 15:
                    //Creamos un local en la direccion dr
                    EscuelaPertenece dr = new EscuelaPertenece("upna","paco paco","aulario");
                    Curso locaux = new Curso("BarNistelroy", dr, "bonito");
                    
                    bs.nuevoCurso(locaux);
                    //borramos el local
                    bs.eliminarCurso(locaux);
                    //insertamos un nuevo bar en esa misma direccion y nos permite hacerlo
                    Master bar = new Master("BarNistelroy2", dr, "bonito");
                    bs.nuevoCurso(bar);
                break;
                case 16:
                    
                    //Para comprobar que no podemos introducir un usuario menor de 14 años simplemente vamos a crear un usuario con una fecha de nacimiento del 2019
                    System.out.println("Introduce el nombre de usuario: ");  
                    nombreUsuario = sc.nextLine();      

                    System.out.println("Introduce la contraseña: "); 
                    passwordUsuario = sc.nextLine();

//                    System.out.println("Introduce la fecha de nacimiento (dd/MM/yyyy): "); 
//                    String edadUsuario = sc.nextLine();
                    Date fechaNacimientoUsuario123;
                    
                    try {
                        fechaNacimientoUsuario123 = new SimpleDateFormat("dd/MM/yyyy").parse("20/09/2019");
                        Persona u123 = new Persona(nombreUsuario, passwordUsuario, fechaNacimientoUsuario123,1);
                        if (bs.nuevoUsuario(u123)){
                            System.out.println("Usuario registrado correctamente!");
                            System.out.println(u123.toString());
                        }else{
                            System.out.println("La fecha de nacimiento es invalida\n");
                        }
                    } catch (IllegalArgumentException | ParseException ex) {
                        System.out.println(ex.toString());

                    }
                    break;

                case 19:
                    
                    String fechaNacimiento123344 = new String("20/11/1997");
                    Matriculable localAux = null; //Comentar esta linea para realizar la comprobacion

                    //Simplemente vamos a crear una contestacion en cuyo constructor no va a haber una review válida ya que simplemente no la hemos creado
                    //Para ello vamos a capturar el error que nos va a lanzar el compilador e indicar el porque
                    
                    //Creamos un cliente
                     try {
                        fechaNacimientoUsuario = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacimiento123344);
                        Alumno cliente = new Alumno("Aaaaa", "aaaaa", fechaNacimientoUsuario,1);
                        
                        try{
                            Matricula reserva = new Matricula(cliente, localAux, LocalDate.now(), LocalTime.MAX, 1);
                        //Contestacion contestacion = new Contestacion(rev2345, "comentario", fechaContestacion);
                        }catch(RuntimeException e){
                            System.out.println("Se ha intentado crear una reserva a un local que no existe");
                        }

                    }catch(ParseException ex) {
                       Logger.getLogger(P01Tester.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    break;
                    
                    
                    
                case 20:
                    //Buscar un usuario que no existe con findClient devuelve null
                    
                    //creamos un nick que no esté en uso y analizamos lo que devuelve la funcion
                    String nickInvent = "Paquito";
                    
                   

                    Persona user1234567 =    bs.obtenerUsuario(nickInvent);

                    if (user1234567 == null){
                        System.out.println("El nick no tiene un usuario que le corresponda\n");
                    }else{
                        System.out.println("Usuario encontrado\n");
                    }
                    break;
                    
                    
                    
                case 17:
                    //inserto un local en la direccion dr
                    dr = new EscuelaPertenece("upna","paco paco","aulario");
                    locaux = new Curso("BarNistelroy", dr, "bonito");                    
                    bs.nuevoCurso(locaux);
                    //intento insertar un nuevo bar en esa misma direccion y me dice que no se puede.
                    Curso locaux2 = new Curso("BarNistelroy2", dr, "bonito");
                    bs.nuevoCurso(locaux2);
                    break;
                case 18:
                    //Creo un usuario y lo inserto
                    Date dat = new Date(95, 5,3);
                    Persona us = new Persona("Sergio", "1234", dat , 1);
                    bs.nuevoUsuario(us);
                    //lo busco con el nick que le he insertado al usuario y me lo busca
                    bs.obtenerUsuario("Sergio");
                    break;
                default:
                    //EXIT
                    System.out.println("Accion no permitida");
                    System.out.println("Cerrando programa...");
                    System.exit(0);
                    break; 
                    
                case 21:
                    //Importa datos de locales tipo bares de una hoja de calculo
                    final File fileBares = new File("test06.ods");
                    int exitosBares = bs.importarMasteres(fileBares);
                    System.out.println("Numero de bares añadidos con éxito: " + exitosBares);
                    //Local listaLocales[] = bs.listarCursos("Barcelona", "Barcelona");
                    break;
                case 22:
                     //Importa datos de locales tipo bares de una hoja de calculo
                    /*Aunque test06.ods es una hoja con bares se puede utilizar tambien para
                    comprobar el funcionamiento de la función importarDoctorados*/
                    final File filePubs = new File("test06.ods");
                    int exitosPubs = bs.importarDoctorados(filePubs);
                    System.out.println("Numero de Pubs añadidos con éxito: " + exitosPubs);
                    //Local listaLocales[] = bs.listarCursos("Barcelona", "Barcelona");
                    break;
                 
            }
        } while ( choice != 0 );
    }

    private static void nuevaReview(Examen r){
        //TODO: comprobar que no haya otra review para esa fecha y ese local
//        Reviews.add(r);//Hay que añadir en businessSystem no en el tester CAMBIAR
    }
    
    //Función para buscar en un csv
    public static String searchCsvUser(String searchString) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("usuarios.csv"));
        String line;
        //Inicializo la contraseña a null
        String psw = null;
        while ( (line = br.readLine()) != null ) {
            String[] values = line.split(",");
            if(values[1].equals(searchString)) {
                //Si encuentra un match de usuario, recoge la contraseña de éste
                psw = values[2];
                break;
            }
        }
        br.close();
        //Devuelvo la variable contraseña, en caso de haberla encontrado será el 
        //valor de ésta, si no, será null
        return psw;
    }
}
