/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BTesting;

import GSILabs.BModel.Usuario;
import GSILabs.BSystem.BusinessSystem;
import java.util.Scanner;

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
            System.out.println("1.Registrar Usuario \n2.Borrar usuario\n Exit ");
            System.out.println("Enter your Choice : ");

            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    Usuario u = null;
                    bs.nuevoUsuario(u);
                    break;

                case 2:
                    //Registrar un local (Tipo sin definir)
                case 3: 
                    System.out.println("EXIT");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Accion no permitida");
            }
        } while ( choice > 2 || choice < 1 );
    }
}
