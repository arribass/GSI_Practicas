/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BTesting;

import GSILabs.BModel.Main;
import GSILabs.BModel.Usuario;
import GSILabs.BSystem.BusinessSystem;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1.Registrar Usuario\n Exit ");
            System.out.println("Enter your Choice : ");

            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    String nombreUsuario;
                    boolean registerOk = false;
                    System.out.println("Introduce el nombre de usuario: ");  
                    nombreUsuario = sc.nextLine();      

                    System.out.println("Introduce la contraseña: "); 
                    String passwordUsuario = sc.nextLine();

                    System.out.println("Introduce la fecha de nacimiento (dd/MM/yyyy): "); 
                    String edadUsuario = sc.nextLine();
                    Date fechaNacimientoUsuario;
                    
                    try {
                        fechaNacimientoUsuario = new SimpleDateFormat("dd/MM/yyyy").parse(edadUsuario);
                        Usuario u = new Usuario(nombreUsuario, passwordUsuario, fechaNacimientoUsuario);
                        bs.nuevoUsuario(u);
                    } catch (IllegalArgumentException | ParseException ex) {
                        System.out.println(ex.toString());
                    }

                    break;
                default:
                    //EXIT
                    System.out.println("Accion no permitida");
                    System.exit(0);
                    break;  
            }
        } while ( choice > 2 || choice < 1 );
    }
}
