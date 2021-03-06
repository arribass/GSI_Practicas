/*
 * CLASE: Usuario
 */
package GSILabs.BModel;

import GSILabs.BSystem.XMLRepresentable;
import GSILabs.persistence.XMLParsingException;
import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.Serializable;
import org.mozilla.javascript.JavaScriptException;
        
/**
 *
 * @author LENOVO-arribass
 */
public class Persona {
    private static final AtomicInteger id = new AtomicInteger();

    private String NIA;
    private String nombre;
    private Date fechaNacimiento;
    private String centroUltimosEstudiosAnteriores;
   
    private final int EDAD_MINIMA  = 18;
    private final int LONGITUD_MINIMA_NICK = 3;

    

    public Persona(String NIA, String nombre, Date fechaNacimiento, int centroUltimosEstudiosAnteriores) {
        try {
            usuarioValido(NIA,nombre,fechaNacimiento,centroUltimosEstudiosAnteriores);
            this.NIA = NIA;
            this.nombre = nombre;
            this.fechaNacimiento = fechaNacimiento;
            this.centroUltimosEstudiosAnteriores = centroUltimosEstudiosAnteriores == 1?"Cliente":"Propietario";
            Persona.id.addAndGet(1);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Datos de persona invalidos" + e.toString());
        }
    }
    
    public Persona(String stringXML) throws JAXBException{
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Persona.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(stringXML);
            Persona usuario = (Persona) unmarshaller.unmarshal(reader);

            this.NIA = usuario.getNIA();
            this.nombre = usuario.getNombre();
            this.fechaNacimiento = usuario.getFechaNacimiento();
            this.centroUltimosEstudiosAnteriores = usuario.getCentroUltimosEstudiosAnteriores();
            Persona.id.addAndGet(1);
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Datos de persona invalidos" + e.toString());
        }
    }
    
    
    /*Getters and Setters*/
    public AtomicInteger getId() {
        return id;
    }
    
    public String getNIA() {
        return NIA;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNIA(String NIA) {
        this.NIA = NIA;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public String getCentroUltimosEstudiosAnteriores() {
        return centroUltimosEstudiosAnteriores;
    }

    public void setCentroUltimosEstudiosAnteriores(String centroUltimosEstudiosAnteriores) {
        this.centroUltimosEstudiosAnteriores = centroUltimosEstudiosAnteriores;
    }
    /*METODOS*/
    
    /**
     * Comprueba que la longitud del nick es valida
     * @param nick
     * @return 
     */
    public boolean longitudNickValida(String nick){
        return nick.length() > LONGITUD_MINIMA_NICK;
    }
    /**
     * Desc: Calcula la edad actual en base a la fecha recibida como parametro
     * @param fechaNacimiento1
     * @return 
     */
    private int calculaEdad(Date fechaNacimiento){
        long fechaActual = new java.util.Date().getTime();
        long diffInMillies = Math.abs(fechaNacimiento.getTime() - fechaActual);
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        Period period = Period.between(fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
        
        return period.getYears();
    }
    /**
     * Desc: Recibe una edad y devuelve la edad actual del usuario en el instante en el que se calcula
     * @return true si la edad es valida
     */
    private boolean edadValida(Date fechaNacimiento) {        
        return calculaEdad(fechaNacimiento) > EDAD_MINIMA;
    }
    /**
     * Desc: Comprueba que el tipo de usuario es valido
     * 1 - Cliente
     * 2 - Propietario
     * @param perfil
     * @return 
     */
    private boolean perfilValido(int perfil) {
        return perfil == 1 || perfil == 2;
    }
    
    /**
     * Desc: Obtienes unos datos de usuario y comprueba si son validos para registrar
     * Comprueba que el nick, la edad y el tipo de usuario es valido
     * @param NIA
     * @param nombre
     * @param fechaNacimiento
     * @param perfil
     */
    public void usuarioValido(String NIA, String nombre, Date fechaNacimiento, int perfil) {
        if(!longitudNickValida(NIA))
            throw new IllegalArgumentException("NIA demasiado corto");
        if(!edadValida(fechaNacimiento))
            throw new IllegalArgumentException("Edad minima 18");
        if(!perfilValido(perfil))
            throw new IllegalArgumentException("El indice de perfil debe ser 1(Alumno) o 2(Profesor)");  
    }
        
    @Override
    /**
     * Desc: Devuelve los datos de usuario en el siguiente formato
     * ID: {id} 
     * Nick: {nick}
     * Fecha nacimiento: {fechaNacimiento}
     * return @param String que contiene la informacion publica de usuario
     */
    public String toString() {
        return "ID:" + getId() + "\nNIA: " + getNIA() + "\nFecha nacimiento: " + getFechaNacimiento().toString() +"\n";
    }

  
  
}
