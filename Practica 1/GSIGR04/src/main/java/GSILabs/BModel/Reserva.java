/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Arribas
 */
public class Reserva {
    
    private Cliente cliente;
    private Reservable local;
    private LocalDate fecha;
    private LocalTime hora;
    private float descuento;

    public Reserva(Cliente cliente, Reservable local, LocalDate fecha, LocalTime hora, float descuento) {
        this.cliente = cliente;
        this.local = local;
        this.fecha = fecha;
        this.hora = hora;
        //Si tengo un 25% de descuento calcularia 0.75 que es por lo que tengo que multiplicar el precio para que me salga un 25 de descuento.
        //Ejemplo: vale 10 euros -> 10 * 0.75 = 7.5
        this.descuento = 1 - (descuento/100); 
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Reservable getLocal() {
        return local;
    }

    public void setLocal(Reservable local) {
        this.local = local;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }
    
    
}
