/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO-arribass
 */
public class Main {
    static List<Usuario> Usuarios = new ArrayList<>();
    static List<Local> Locales = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int choice;
        do {
            System.out.println("1.Registrar Usuario \n2.Borrar usuario\n Exit ");
            System.out.println("Enter your Choice : ");

            choice = sc.nextInt();
            switch (choice) {
                case 1: 
                    //Registrar usuario (Tipo sin definir)
                    registrarUsuario();
                    break;
                case 2:
                    //Registrar un local (Tipo sin definir)
                    registrarLocal();
                case 3: 
                    System.out.println("EXIT");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Accion no permitida");
            }
        } while ( choice > 2 || choice < 1 );
        System.out.println(Usuarios.size() > 0 ? Usuarios.toString() : "La lista esta vacía");
    }
    
    public static boolean registrarUsuario(){
        String nombreUsuario;
        boolean registerOk = false;
                
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre de usuario: ");  
        nombreUsuario = sc.nextLine();      

        System.out.println("Introduce la contraseña: "); 
        String passwordUsuario = sc.nextLine();

        System.out.println("Introduce la fecha de nacimiento (dd/MM/yyyy): "); 
        String edadUsuario = sc.nextLine();
        Date fechaNacimientoUsuario = null;
        try {
            fechaNacimientoUsuario = new SimpleDateFormat("dd/MM/yyyy").parse(edadUsuario);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        Usuario u;
        try {
            u = new Usuario(nombreUsuario,passwordUsuario,fechaNacimientoUsuario);
            Usuarios.add(u);
            System.out.println("Usuario registrado correctamente!");
            registerOk = true;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return registerOk;
    }

    private static void registrarLocal() {
        
        //Pido al usuario el nombre del nuevo local.
        Scanner sc = new Scanner(System.in);
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
        Locales.add(l); 
    }
}