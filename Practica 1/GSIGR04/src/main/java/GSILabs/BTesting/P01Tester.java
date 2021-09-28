/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BTesting;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Review;
import GSILabs.BModel.Reserva;
import GSILabs.BModel.Reservable;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Pub;
import GSILabs.BModel.Restaurante;
import GSILabs.BModel.Usuario;
import GSILabs.BSystem.BusinessSystem;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arribas
 */
public class P01Tester {
               
    public static void main(String[] args) {
        // Instanciamos una clase BusinessSystem
        BusinessSystem bs = new BusinessSystem();
        String passwordUsuario;
        String nombreUsuario;
        
        //Menú principal, el usuario elige que quiere hacer.
        Scanner sc = new Scanner(System.in);
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
                                + "20. Comprobacion de ejercicio 4 apartado S5) \n"
                                + "21. Comprobación de ejercicio 4 apartado S6) \n"
                                + "15. Comprobación de ejercicio 4 apartado S4) \n"
                                + "16. Comprobacion del ejercicio 4 apartado S5)\n");
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
                        Usuario u = new Usuario(nombreUsuario, passwordUsuario, fechaNacimientoUsuario,1);
                        if (bs.nuevoUsuario(u)){
                            System.out.println("Usuario registrado correctamente!");
                            System.out.println(u.toString());
                        }else{
                            System.out.println("El usuario " + u.getNick()+ " ya existe!");
                        }
                    } catch (IllegalArgumentException | ParseException ex) {
                        System.out.println(ex.toString());
                    }

                    break;
                case 2: //Buscar usuario por nick
                    //Obtener usuario
                    System.out.println("¿Que usuario quieres buscar?: ");  
                    nombreUsuario = sc.nextLine(); 
                    Usuario u = bs.obtenerUsuario(nombreUsuario);
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
                    Direccion d = new Direccion(localidad,provincia,calle,numero);

                    //Pido la descripcion del local al usuario.
                    System.out.println("Introduce la descripción del local: "); 
                    String descripcionLocal = sc.nextLine();
                    Local l = new Local(nombreLocal,d,descripcionLocal);
                    //añado el nuevo local
                    bs.nuevoLocal(l);
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
                    System.out.println("Vas a introducir la dirección del local al que quieres añadir comentario\n");         
                    System.out.println("Introduce la localidad: "); 
                    String loc = sc.nextLine();         
                    System.out.println("Introduce la provincia: "); 
                    String prov = sc.nextLine();       
                    System.out.println("Introduce la calle: "); 
                    String cal = sc.nextLine();       
                    System.out.println("Introduce el numero: "); 
                    String num = sc.nextLine();
                    //Genero la dirección.
                    Direccion dir = new Direccion(loc,prov,cal,num);
                    Local local = bs.obtenerLocal(dir);
                    Review r = new Review(valint,comentario,fecha,local);
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
                    Direccion dB = new Direccion(localidadB,provinciaB,calleB,numeroB);
                    //Cojo el local de esa dirección
                    Local localEliminar = bs.obtenerLocal(dB);
                    //elimino el local
                    bs.eliminarLocal(localEliminar);                    
                    break;
                case 6: //Listar locales
                    System.out.println("Introduce la provincia deseada "); 
                    String provinciaD = sc.nextLine();
                    System.out.println("Introduce la ciudad deseada "); 
                    String ciudadD = sc.nextLine();
                    System.out.println("Listado de locales en: "+ ciudadD + ", "+ provinciaD +"\n");   
                    Local localeslist[] = bs.listarLocales(ciudadD, provinciaD);                                        
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
                    Direccion dc = new Direccion(localidadc,provinciac,callec,numeroc);
                    Local local1 = bs.obtenerLocal(dc);
                    
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
                    Direccion dd = new Direccion(localidadc,provinciac,callec,numeroc);
                    Local localviejo = bs.obtenerLocal(dd);
                    
                    //Variables auxiliares.
                    String nuevaDescripcion = null;
                    Direccion nuevaDireccion = null;
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
                                nuevaDireccion = localviejo.getDireccion();
                            }catch(NullPointerException e){
                              System.out.println("");  
                            }                            
                            break;
                        case 2:
                            System.out.println("Introduce el nombre del local: "); 
                            nuevoNombre = sc2.nextLine();
                            try{
                                nuevaDescripcion = localviejo.getDescripcion();
                                nuevaDireccion = localviejo.getDireccion();
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
                            nuevaDireccion = new Direccion(localidadc,provinciac,callec,numeroc);
                            try{
                               nuevaDescripcion = localviejo.getDescripcion();
                                nuevoNombre = localviejo.getNombre(); 
                            }catch(NullPointerException e){
                              System.out.println("");  
                            }                           
                            break;
                        default:
                            nuevaDescripcion = localviejo.getDescripcion();
                            nuevoNombre = localviejo.getNombre();
                            nuevaDireccion = localviejo.getDireccion();
                            System.out.println("Seleccion no válida\n");
                            break;
                    }   
                    try{
                        bs.actualizarLocal(localviejo, new Local(nuevoNombre,nuevaDireccion,nuevaDescripcion));
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
                    Local localeslist2[] = bs.listarBares(ciudadD, provinciaD);                                        
                    break;
                case 10: //Listar solo restaurantes
                    System.out.println("Introduce la provincia deseada "); 
                    provinciaD = sc.nextLine();
                    System.out.println("Introduce la ciudad deseada "); 
                    ciudadD = sc.nextLine();
                    System.out.println("Listado de restaurantes en: "+ ciudadD + ", "+ provinciaD +"\n");   
                    Local localeslist3[] = bs.listarRestaurantes(ciudadD, provinciaD);                                        
                    break;
                case 11: //Listar solo pubs
                    System.out.println("Introduce la provincia deseada "); 
                    provinciaD = sc.nextLine();
                    System.out.println("Introduce la ciudad deseada "); 
                    ciudadD = sc.nextLine();
                    System.out.println("Listado de pubs en: "+ ciudadD + ", "+ provinciaD +"\n");   
                    Local localeslist4[] = bs.listarPubs(ciudadD, provinciaD);                                        
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
                    Direccion d1 = new Direccion(localidad1,provincia1,calle1,numero1);

                    //Pido la descripcion del local al usuario.
                    System.out.println("Introduce la descripción del local: \n"); 
                    String descripcionLocal2 = sc.nextLine();
                    Local l2 = new Local(nombreLocal1,d1,descripcionLocal2);
                    //añado el nuevo local
                    bs.nuevoLocal(l2);
                    
                    //Creamos la primera review con la fecha y el local introducidos
                    Review rev1 = new Review(5, "aaaaaaaaa",date, l2);
                    System.out.println("Primera review introducida.\n");
                            
                    //agregamos la primera review
                    bs.nuevaReview(rev1);

                    //Creamos la segunda review con la misma fecha y el mismo local
                    
                    Review rev2 = new Review(3, "bbbbbbbbb",date, l2);
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
                    Direccion d13 = new Direccion(localidad13,provincia13,calle13,numero13);

                    //Pido la descripcion del local al usuario.
                    System.out.println("Introduce la descripción del local: \n"); 
                    String descripcionLocal23 = sc.nextLine();
                    Local l23 = new Local(nombreLocal13,d13,descripcionLocal23);
                    //añado el nuevo local
                    bs.nuevoLocal(l23);
                    
                    //Una vez agregado el local vamos a agregar los tres propietarios
                    
                    String fechas = new String("13/11/1999");
                    
                
                    try {
                        
                        
                        fechaNacimientoUsuario = new SimpleDateFormat("dd/MM/yyyy").parse(fechas);
                        Usuario pro1 = new Usuario("Aaaaa", "aaaaa", fechaNacimientoUsuario,1);
                        Usuario pro2 = new Usuario("Bbbbb", "bbbbb", fechaNacimientoUsuario,1);
                        Usuario pro3 = new Usuario("Ccccc", "ccccc", fechaNacimientoUsuario,1);
                    
                        l23.anadirPropietario(pro1);
                        l23.anadirPropietario(pro2);
                        l23.anadirPropietario(pro3);
                        
                        System.out.println("Tres propietarios agregados\n");
                        
                        System.out.println("Probamos a agregar un cuarto propietario\n");
                        Usuario pro4 = new Usuario("Ddddd", "ddddd", fechaNacimientoUsuario,1);
                        
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
                    Review rev2345 = null; //Comentar esta linea para realizar la comprobacion
                    try {
                        Date fechaContestacion = new SimpleDateFormat("dd/MM/yyyy").parse(fechas11);
                        //Simplemente vamos a crear una contestacion en cuyo constructor no va a haber una review válida ya que simplemente no la hemos creado
                        //Para ello vamos a capturar el error que nos va a lanzar el compilador e indicar el porque
                        try{
                            Contestacion contestacion = new Contestacion(rev2345, "comentario", fechaContestacion);
                        }catch(RuntimeException e){
                            System.out.println("Se ha intentado crear una contestacion a una review que no existe");
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(P01Tester.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case 15:
                    Direccion dr = new Direccion("Pamplona", "Navarra", "Tajonar", "4");
                    Local locaux = new Local("BarNistelroy", dr, "bonito");
                    
                    bs.nuevoLocal(locaux);
                    
                    bs.eliminarLocal(locaux);
                    
                    Bar bar = new Bar("BarNistelroy2", dr, "bonito");
                    bs.nuevoLocal(bar);
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
                        Usuario u123 = new Usuario(nombreUsuario, passwordUsuario, fechaNacimientoUsuario123,1);
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

                case 21:
                    
                    String fechaNacimiento123344 = new String("20/11/1997");
                    Reservable localAux = null; //Comentar esta linea para realizar la comprobacion

                    //Simplemente vamos a crear una contestacion en cuyo constructor no va a haber una review válida ya que simplemente no la hemos creado
                    //Para ello vamos a capturar el error que nos va a lanzar el compilador e indicar el porque
                    
                    //Creamos un cliente
                     try {
                        fechaNacimientoUsuario = new SimpleDateFormat("dd/MM/yyyy").parse(fechaNacimiento123344);
                        Cliente cliente = new Cliente("Aaaaa", "aaaaa", fechaNacimientoUsuario,1);
                        
                        try{
                            Reserva reserva = new Reserva(cliente, localAux, LocalDate.now(), LocalTime.MAX, 1);
                        //Contestacion contestacion = new Contestacion(rev2345, "comentario", fechaContestacion);
                        }catch(RuntimeException e){
                            System.out.println("Se ha intentado crear una reserva a un local que no existe");
                        }

                    }catch(ParseException ex) {
                       Logger.getLogger(P01Tester.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    break;
                case 17:
                    dr = new Direccion("Pamplona", "Navarra", "Tajonar", "4");
                    locaux = new Local("BarNistelroy", dr, "bonito");                    
                    bs.nuevoLocal(locaux);                    
                    Local locaux2 = new Local("BarNistelroy2", dr, "bonito");
                    bs.nuevoLocal(locaux2);
                    break;
                /*case 7: 
                    System.out.println("Nombre del propietario"); 
                    String prop = sc.nextLine();
                    Usuario p = bs.obtenerUsuario(prop);
                    bs.asociarLocal(l, p);
                    break;*/
                default:
                    //EXIT
                    System.out.println("Accion no permitida");
                    System.out.println("Cerrando programa...");
                    System.exit(0);
                    break;  
            }
        } while ( choice != 0 );
    }

    private static void nuevaReview(Review r){
        //TODO: comprobar que no haya otra review para esa fecha y ese local
//        Reviews.add(r);//Hay que añadir en businessSystem no en el tester CAMBIAR
    }
}
