/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import GSILabs.BSystem.XMLRepresentable;
import java.io.File;
import GSILabs.persistence.XMLParsingException;

/**
 *
 * @author Arribas
 */
public class Bar extends Local implements XMLRepresentable{
    
    private String Duenos[] = new String[3];
    
    public Bar(String nombre, Direccion direccion, String descripcion) {
        super(nombre, direccion, descripcion);
    }
    
    private void anadirDueno(String dueno){
        if(this.Duenos[0] == null){
            this.Duenos[0] = dueno;
        } else if(this.Duenos[1] == null){
            this.Duenos[1] = dueno;
        } else if(this.Duenos[2] == null){
            this.Duenos[2] = dueno;
        } else if(this.Duenos[2] != null){
            System.out.println("Este bar ya tiene 3 dueños.");
        }
    }

    @Override
    public String toXML() {
        try{
            
            throw new XMLParsingException("Error XML");
            
        }catch(XMLParsingException e){
            System.out.println("Error al convertir a XML");
        }
    }

    @Override
    public boolean saveToXML(File f) {
        try{
            
            throw new XMLParsingException("Error XML");
            
        }catch(XMLParsingException e){
            System.out.println("Error al guardar en XML");
        }
    }

    @Override
    public boolean saveToXML(String filePath) {
        try{
            
            throw new XMLParsingException("Error XML");
            
        }catch(XMLParsingException e){
            System.out.println("Error al guardar en XML");
        }
    }
    
}
