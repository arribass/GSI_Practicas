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
public class Direccion implements XMLRepresentable, Serializable {
    
    private String localidad;
    private String provincia;
    private String calle;
    private String numero;

    public Direccion(String Localidad, String Provincia, String Calle, String Numero) {
        this.localidad = Localidad;
        this.provincia = Provincia;
        this.calle = Calle;
        this.numero = Numero;
    }
    
    public Direccion(String stringXML) throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Direccion.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(stringXML);
        Direccion direccion = (Direccion) unmarshaller.unmarshal(reader);
        this.localidad = direccion.getLocalidad();
        this.provincia = direccion.getProvincia();
        this.calle = direccion.getCalle();
        this.numero = direccion.getNumero();        
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
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
        final Direccion other = (Direccion) obj;
        if (!Objects.equals(this.localidad, other.localidad)) {
            return false;
        }
        if (!Objects.equals(this.provincia, other.provincia)) {
            return false;
        }
        if (!Objects.equals(this.calle, other.calle)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
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
