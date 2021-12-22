/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import GSILabs.BSystem.XMLRepresentable;
import java.io.File;
import GSILabs.persistence.XMLParsingException;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Arribas
 */
public class Master extends Curso implements XMLRepresentable{
    
    private String docentes[] = new String[3];
    
   
    
    public Master(String nombre, EscuelaPertenece escuelaPertenece, String contenidos) {
        super(nombre, escuelaPertenece, contenidos);
    }
    
    
    
    public Master(String stringXML) throws JAXBException{
        super(stringXML);
        
    }
    
    
    
    private void anadirDocente(String dueno){
        if(this.docentes[0] == null){
            this.docentes[0] = dueno;
        } else if(this.docentes[1] == null){
            this.docentes[1] = dueno;
        } else if(this.docentes[2] == null){
            this.docentes[2] = dueno;        
        } else if(this.docentes[2] != null){
            System.out.println("Este bar ya tiene 3 docentes, no se pueden añadir más.");
        }
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
