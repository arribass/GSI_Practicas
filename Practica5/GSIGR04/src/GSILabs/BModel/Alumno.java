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
import java.io.Serializable;
import javax.xml.bind.JAXBException;

/**
 *
 * @author LENOVO-arribass
 */
public class Alumno extends Persona implements XMLRepresentable, Serializable{

    public Alumno(String NIA, String nombre, Date fechaNacimiento, int centroUltimosEstudiosAnteriores) {
        super(NIA, nombre, fechaNacimiento, centroUltimosEstudiosAnteriores);
    }
    
    public Alumno(String stringXML) throws JAXBException{
        super(stringXML);
        
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
