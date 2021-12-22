/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;
import GSILabs.BSystem.XMLRepresentable;
import GSILabs.persistence.XMLParsingException;
import java.io.File;
import java.util.Date;

import javax.xml.bind.JAXBException;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
/**
 *
 * @author Arribas
 */
public class Nota implements XMLRepresentable {
    private Examen review;
    private String comentario;
    private Date fecha;

    public Nota(Examen review, String comentario, Date fecha){
        this.review = review;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public Nota(String stringXML) throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Nota.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(stringXML);
        Nota contestacion = (Nota) unmarshaller.unmarshal(reader);
        
        this.review = contestacion.getReview();
        this.comentario = contestacion.getComentario();
        this.fecha = contestacion.getFecha();
    }
    
    public Examen getReview() {
        return review;
    }

    public String getComentario() {
        return comentario;
    }

    public Date getFecha() {
        return fecha;
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
