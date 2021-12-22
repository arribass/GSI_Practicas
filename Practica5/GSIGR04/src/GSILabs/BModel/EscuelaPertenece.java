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
import java.util.Objects;
import javax.xml.bind.JAXBException;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Arribas
 */
public class EscuelaPertenece implements XMLRepresentable, Serializable {
    
    private String nombre;
    private String nombreDirector;
    private String nombreEdificioSede;

    public EscuelaPertenece(String nombre, String nombreDirector, String nombreEdificioSede){
        this.nombre = nombre;
        this.nombreDirector = nombreDirector;
        this.nombreEdificioSede = nombreEdificioSede;
    }
    
    public EscuelaPertenece(String stringXML) throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(EscuelaPertenece.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(stringXML);
        EscuelaPertenece direccion = (EscuelaPertenece) unmarshaller.unmarshal(reader);
        this.nombre = direccion.getNombre();
        this.nombreDirector = direccion.getNombreDirector();
        this.nombreEdificioSede = direccion.getNombreEdificioSede(); 
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreDirector() {
        return nombreDirector;
    }

    public String getNombreEdificioSede() {
        return nombreEdificioSede;
    }

    
    //Sobre escritura de metodos equals y toString.
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EscuelaPertenece other = (EscuelaPertenece) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.nombreDirector, other.nombreDirector)) {
            return false;
        }
        if (!Objects.equals(this.nombreEdificioSede, other.nombreEdificioSede)) {
            return false;
        }
        return true;
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
