/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.connect;

import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Review;
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
            System.out.println("¿A qué dirección te quieres conectar?");
            String direccion = sc.nextLine();
            System.out.println("¿Qué puerto quieres utilizar?");
            int puerto = sc.nextInt();
            System.out.println("¿Cuál es el tag del objeto?");
            String tag = sc.nextLine();
            //Instanciar el registro RMI
            Registry registro=LocateRegistry.getRegistry(puerto);	
            //Instanciar un objeto de la clase del servidor
            ClientGateway cg=(ClientGateway) registro.lookup(tag);	
            //Uso del servicio
            Direccion d = new Direccion("Haro", "La Rioja", "Avenida mayor", "3");
            Local l = new Local("Casa Paco", d, "Bar elegante en el centro.");
            Date fecha = new Date();
            Review r = new Review(6, "Muy limpio y bonito.", fecha, l);
            cg.insertaReview(r);
            
        }catch (Exception e)
        {
            System.err.println("Excepción en el ClientHub:");
            e.printStackTrace();
        }
    }
}
