/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;
import GSILabs.BSystem.XMLRepresentable;
import GSILabs.persistence.XMLParsingException;
import java.io.File;
import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.JAXBException;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
/**
 *
 * @author Arribas
 */
public class Examen  implements XMLRepresentable, Serializable{
    private int puntuacionMaxima;
    private String preguntas;
    private Date fecha;
    private Curso curso; 
    private Alumno alumno;

    public Examen(int puntuacionMax, String preguntas, Date fecha, Curso curso, Alumno alumno){
        this.puntuacionMaxima = puntuacionMax;
        this.preguntas = preguntas;
        this.fecha = fecha;
        this.curso = curso;
        this.alumno = alumno;
    }

    public Examen(String stringXML) throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Examen.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(stringXML);
        Examen review = (Examen) unmarshaller.unmarshal(reader);
        
        this.puntuacionMaxima = review.getPuntuacionMaxima();
        this.preguntas = review.getPreguntas();
        this.fecha = review.getFecha();
        this.curso = review.getCurso();

        
    }
    
    public Date getFecha(){
    return this.fecha;
    }

    public Curso getCurso() {
        return curso;
    }

    public String getPreguntas() {
        return preguntas;
    }

    public int getPuntuacionMaxima() {
        return puntuacionMaxima;
    }

    public Alumno getAlumno() {
        return alumno;
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
 