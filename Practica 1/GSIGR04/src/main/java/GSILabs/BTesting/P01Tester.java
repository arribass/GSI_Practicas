/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BTesting;

import GSILabs.BModel.Usuario;
import GSILabs.BSystem.BusinessSystem;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            System.out.println("1.Registrar Usuario\n"
                                + "2.Buscar usuario por nick"
                                + "\n Exit ");
            System.out.println("Enter your Choice : ");

            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
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
                        Usuario u = new Usuario(nombreUsuario, passwordUsuario, fechaNacimientoUsuario);
                        if (bs.nuevoUsuario(u)){
                            System.out.println("Usuario registrado correctamente!");
                            System.out.println(u.toString());
                        }else{
                            System.out.println("Usuario NO registrado!");
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
                default:
                    //EXIT
                    System.out.println("Accion no permitida");
                    System.exit(0);
                    break;  
            }
        } while ( choice < 3 && choice > 0 );
        System.out.println("Ultimo Choice: " + choice);
    }
}
