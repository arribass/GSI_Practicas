/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
    public static void main(String[] args) {
        List<Usuario> Usuarios = new ArrayList<>();
        String nombreUsuario;
        
        do {            
            Scanner sc= new Scanner(System.in);
            System.out.println("Introduce el nombre de usuario: ");  
            nombreUsuario = sc.nextLine();      
            if("exit".equals(nombreUsuario))
                break;
            System.out.println("Introduce la contraseña: "); 
            String passwordUsuario = sc.nextLine();
            Usuario u;
            try {
                u = new Usuario(nombreUsuario,passwordUsuario,Calendar.getInstance().getTime());
                Usuarios.add(u);
            } catch (Exception e) {
                System.out.println("Error: "+e.getMessage());
            }
        } while (true);
        
        System.out.println("La lista de los " + Usuarios.size() + " usuarios es: "+ Usuario.getNombres());
    }
}