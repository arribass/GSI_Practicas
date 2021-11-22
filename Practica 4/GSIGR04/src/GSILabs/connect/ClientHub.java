/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.connect;

import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Review;
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
public class ClientHub {
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
            System.out.println("¿Qué puerto quieres utilizar? (1100)");
            int puerto = Integer.parseInt(br.readLine());
            
            System.out.println("¿Cuál es el tag del objeto? (CLIENT)");
            String tag = sc.nextLine();
            //Instanciar el registro RMI
            Registry registro=LocateRegistry.getRegistry(direccion, puerto);	
            //Instanciar un objeto de la clase del servidor
            ClientGateway cg = (ClientGateway) registro.lookup(tag);
            //Uso del servicio
            Direccion d = new Direccion("Haro", "La Rioja", "Avenida mayor", "3");
            Local l = new Local("Casa Paco", d, "Bar elegante en el centro.");
            Date fecha = new Date();
            Review r = new Review(6, "Muy limpio y bonito.", fecha, l);
            //Comprobación insertarReview
            cg.insertaReview(r);
            
            //Comprobación quitarReview
            cg.quitaReview(r);
            
            //Comprobación mejorBar
            //cg.mejorBar("ciudad");
            /*Como en ClienteGateway.java no está declarada la función añadir local
              no se puede comprobar la función mejorar bar ya que no podemos insertar 
              locales tipo bares.*/
            
            //Comprobación mejoresRestaurantes
            /*Misma situación*/
            
            
            
            System.out.println("Todo correcto");
        }catch (Exception e)
        {
            System.err.println("Excepción en el ClientHub:");
            e.printStackTrace();
        }
    }
}
