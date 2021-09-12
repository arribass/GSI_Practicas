/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        
        Scanner sc= new Scanner(System.in);
        
        do {            
            System.out.println("Introduce el nombre de usuario: ");  
            nombreUsuario = sc.nextLine();      
            if("exit".equals(nombreUsuario))
                break;
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
            } catch (Exception e) {
                System.out.println("Error: "+e.getMessage());
            }
        } while (true);
        
        System.out.println("La lista de los " + Usuarios.size() + " usuarios es: "+ Usuario.getNombres());
        System.out.println("Lista again: " + Usuarios.toString());
    }
}