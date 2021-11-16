/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import GSILabs.BSystem.XMLRepresentable;
import GSILabs.persistence.XMLParsingException;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.xml.bind.JAXBException;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Arribas
 */
public class Reserva  implements XMLRepresentable{
    
    private Cliente cliente;
    private Reservable local;
    private LocalDate fecha;
    private LocalTime hora;
    private float descuento;

    public Reserva(Cliente cliente, Reservable reservable, LocalDate fecha, LocalTime hora, float descuento) {
        this.cliente = cliente;
        this.local = local;
        this.fecha = fecha;
        this.hora = hora;
        //Si tengo un 25% de descuento calcularia 0.75 que es por lo que tengo que multiplicar el precio para que me salga un 25 de descuento.
        //Ejemplo: vale 10 euros -> 10 * 0.75 = 7.5
        this.descuento = 1 - (descuento/100); 
    }
    
    public Reserva(String stringXML)throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Reserva.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(stringXML);
        Reserva reserva = (Reserva) unmarshaller.unmarshal(reader);
        this.cliente = reserva.getCliente();
        this.local = reserva.getLocal();
        this.fecha = reserva.getFecha();
        this.hora = reserva.getHora();
        //Si tengo un 25% de descuento calcularia 0.75 que es por lo que tengo que multiplicar el precio para que me salga un 25 de descuento.
        //Ejemplo: vale 10 euros -> 10 * 0.75 = 7.5
        this.descuento = 1 - (reserva.getDescuento()/100);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Reservable getReservable() {
        return local;
    }

    public void setReservable(Reservable local) {
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

    public Reservable getLocal() {
        return local;
    }

    @Override
    public String toXML() {
        try{
            
            throw new XMLParsingException("Error XML");
            
        }catch(XMLParsingException e){
            System.out.println("Error al convertir a XML");
        }
        return null;
    }

    @Override
    public boolean saveToXML(File f) {
        try{
            
            throw new XMLParsingException("Error XML");
            
        }catch(XMLParsingException e){
            System.out.println("Error al guardar en XML");
        }
        return false;
    }

    @Override
    public boolean saveToXML(String filePath) {
        try{
            
            throw new XMLParsingException("Error XML");
            
        }catch(XMLParsingException e){
            System.out.println("Error al guardar en XML");
        }
        return false;
    }
    
    
}
