/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.connect;

import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Review;
import static GSILabs.connect.ClientHub.sc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author sergi
 */
public class AdminHub {
    static Scanner sc = new Scanner(System.in);
    public static void main(String args[])
    {
        try	
        {
            //1. pedir direcciopn ip
            
            System.out.println("¿A qué dirección te quieres conectar? (localhost)");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String direccion;
            try {
                direccion = br.readLine();
            } catch (IOException ioe) {
            System.out.println("Exception when reading : "+ioe.getMessage());
                direccion="localhost";
            }
            
            //2. pedir puerto
            System.out.println("¿Qué puerto quieres utilizar? (1099)");
            int puerto = Integer.parseInt(br.readLine());
            
            System.out.println("¿Cuál es el tag del objeto? (ADMIN)");
            String tag = sc.nextLine();
            //Instanciar el registro RMI
            Registry registro=LocateRegistry.getRegistry(direccion, puerto);	
            //Instanciar un objeto de la clase del servidor
            AdminGateway ag=(AdminGateway) registro.lookup(tag);	
            //Uso del servicio
            Direccion d = new Direccion("Haro", "La Rioja", "Avenida mayor", "3");
            Local l = new Local("Casa Paco", d, "Bar elegante en el centro.");
            ag.eliminaLocal(l);
            System.out.println("Fin");
            
        }catch (Exception e){
            System.err.println("Excepción en el AdminHub:");
            e.printStackTrace();
        }
    }
}
