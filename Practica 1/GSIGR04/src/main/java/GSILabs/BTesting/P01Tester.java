/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BTesting;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Review;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Pub;
import GSILabs.BModel.Restaurante;
import GSILabs.BModel.Usuario;
import GSILabs.BSystem.BusinessSystem;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
        
        Scanner sc = new Scanner(System.in);
        int choice;
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
                                + "9. Listar bares por provincia y ciudad\n");
            System.out.print("¿Qué deseas hacer? : \n");

            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 0:
                    System.out.println("Cerrando programa...");
                    break;
                case 1:
                  
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
                case 2:
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
                case 3:
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
                case 4:
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
                case 5:
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
                case 6:
                    System.out.println("Introduce la provincia deseada "); 
                    String provinciaD = sc.nextLine();
                    System.out.println("Introduce la ciudad deseada "); 
                    String ciudadD = sc.nextLine();
                    System.out.println("Listado de locales en: "+ ciudadD + ", "+ provinciaD +"\n");   
                    Local localeslist[] = bs.listarLocales(ciudadD, provinciaD);                                        
                    break;
                case 7:
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
                case 8:
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
                    
                    String nuevaDescripcion = null;
                    Direccion nuevaDireccion = null;
                    String nuevoNombre = null;
                    
                    int eleccion = 10;
                    System.out.println("¿Que deseas modificar?\n 1- descripcion\n 2-nombre\n 3-direccion");
                    eleccion = sc.nextInt();
                    
                    switch(eleccion){
                        case 1:
                            System.out.println("Introduce la descripción del local: "); 
                            nuevaDescripcion = sc.nextLine();
                            nuevoNombre = localviejo.getNombre();
                            nuevaDireccion = localviejo.getDireccion();
                            break;
                        case 2:
                            System.out.println("Introduce el nombre del local: "); 
                            nuevoNombre = sc.nextLine();
                            nuevaDescripcion = localviejo.getDescripcion();
                            nuevaDireccion = localviejo.getDireccion();
                            break;
                        case 3:
                            System.out.println("Introduce la localidad: "); 
                            localidadc = sc.nextLine();         
                            System.out.println("Introduce la provincia: "); 
                            provinciac = sc.nextLine();       
                            System.out.println("Introduce la calle: "); 
                            callec = sc.nextLine();       
                            System.out.println("Introduce el numero: "); 
                            numeroc = sc.nextLine();
                            nuevaDireccion = new Direccion(localidadc,provinciac,callec,numeroc);
                            nuevaDescripcion = localviejo.getDescripcion();
                            nuevoNombre = localviejo.getNombre();
                            break;
                        default:
                            nuevaDescripcion = localviejo.getDescripcion();
                            nuevoNombre = localviejo.getNombre();
                            nuevaDireccion = localviejo.getDireccion();
                            System.out.println("Seleccion no válida\n");
                            break;
                    }   
                    bs.actualizarLocal(localviejo, new Local(nuevoNombre,nuevaDireccion,nuevaDescripcion));
                    break;
                case 9:
                    System.out.println("Introduce la provincia deseada "); 
                    provinciaD = sc.nextLine();
                    System.out.println("Introduce la ciudad deseada "); 
                    ciudadD = sc.nextLine();
                    System.out.println("Listado de bares en: "+ ciudadD + ", "+ provinciaD +"\n");   
                    Local localeslist2[] = bs.listarBares(ciudadD, provinciaD);                                        
                    break;
                case 10:
                    System.out.println("Introduce la provincia deseada "); 
                    provinciaD = sc.nextLine();
                    System.out.println("Introduce la ciudad deseada "); 
                    ciudadD = sc.nextLine();
                    System.out.println("Listado de restaurantes en: "+ ciudadD + ", "+ provinciaD +"\n");   
                    Local localeslist3[] = bs.listarRestaurantes(ciudadD, provinciaD);                                        
                    break;
                case 11:
                    System.out.println("Introduce la provincia deseada "); 
                    provinciaD = sc.nextLine();
                    System.out.println("Introduce la ciudad deseada "); 
                    ciudadD = sc.nextLine();
                    System.out.println("Listado de pubs en: "+ ciudadD + ", "+ provinciaD +"\n");   
                    Local localeslist4[] = bs.listarPubs(ciudadD, provinciaD);                                        
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
