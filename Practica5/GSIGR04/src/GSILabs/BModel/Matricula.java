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
public class Matricula  implements XMLRepresentable{
    
    private Alumno alumno;
    private Matriculable curso;
    private LocalDate fecha;
    private LocalTime hora;
    private float sobreCosteRepeticion;

    public Matricula(Alumno cliente, Matriculable reservable, LocalDate fecha, LocalTime hora, float descuento) {
        this.alumno = cliente;
        this.curso = curso;
        this.fecha = fecha;
        this.hora = hora;
        this.sobreCosteRepeticion = 1 + (descuento/100); 
    }
    
    public Matricula(String stringXML)throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Matricula.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(stringXML);
        Matricula matricula = (Matricula) unmarshaller.unmarshal(reader);
        this.alumno = matricula.getAlumno();
        this.curso = matricula.getCurso();
        this.fecha = matricula.getFecha();
        this.hora = matricula.getHora();
        //Si tengo un 25% de descuento calcularia 0.75 que es por lo que tengo que multiplicar el precio para que me salga un 25 de descuento.
        //Ejemplo: vale 10 euros -> 10 * 0.75 = 7.5
        this.sobreCosteRepeticion = 1 + (matricula.getSobreCosteRepeticion()/100);
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Matriculable getReservable() {
        return curso;
    }

    public void setReservable(Matriculable local) {
        this.curso = local;
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

    public float getSobreCosteRepeticion() {
        return sobreCosteRepeticion;
    }

    public void setSobreCosteRepeticion(float sobreCosteRepeticion) {
        this.sobreCosteRepeticion = sobreCosteRepeticion;
    }

    public Matriculable getCurso() {
        return curso;
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
