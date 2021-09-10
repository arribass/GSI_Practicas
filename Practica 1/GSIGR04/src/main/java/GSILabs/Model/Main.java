/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO-arribass
 */
public class Main {
    public static void main(String[] args) {
        List<Usuario> Usuarios = new ArrayList<>();
        
        for (int i = 0; i < 3; i++) {
            Scanner sc= new Scanner(System.in);
            System.out.println("Introduce el nombre de usuario: ");  
            String nombreUsuario = sc.nextLine();        
            System.out.println("Introduce la contraseña: "); 
            String passwordUsuario = sc.nextLine();
            Usuario u;
            try {
                u = new Usuario(nombreUsuario,passwordUsuario);
                Usuarios.add(u);
            } catch (Exception ex) {
                System.out.println("El usuario ya existe");
            }
        }
        System.out.println(Usuarios.toString());
    }
}