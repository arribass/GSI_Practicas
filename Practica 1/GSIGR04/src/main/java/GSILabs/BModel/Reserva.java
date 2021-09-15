/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import java.util.Date;

/**
 *
 * @author Arribas
 */
public class Reserva {
    private Date fecha;
    private String hora;
    private float descuento;
    
    public Reserva(Date fecha, String hora, float descuento){
        this.descuento = descuento;
        this.fecha = fecha;
        //Si tengo un 25% de descuento calcularia 0.85 que es por lo que tengo que multiplicar el precio para que me salga un 25 de descuento.
        //Ejemplo: vale 10 euros -> 10 * 0.85 = 8.5
        this.descuento = 1 - (descuento/100); 
    }
}
