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
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author LENOVO-arribass
 */
public class Curso implements XMLRepresentable, Serializable{
    //private static final Set<String> nombres = new HashSet<String>();

    private String nombre;
    private Nombre direccion;
    private String descripcion;
    private Persona[] propietarios;
    
    private final int maxDescripcion = 300;

    public Curso(String nombre, Nombre direccion, String descripcion) {
        this.nombre = nombre;
        this.direccion = direccion;
        if (descripcion.length() > maxDescripcion){
            descripcion = descripcion.substring(0, maxDescripcion);
        }
        this.descripcion = descripcion;
        this.propietarios = new Persona[3];
    }
    
    public Curso(String stringXML) throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Curso.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(stringXML);
        Curso local = (Curso) unmarshaller.unmarshal(reader);
        this.nombre = local.getNombre();
        this.direccion = local.getDireccion();
        this.descripcion = local.getDescripcion();
        this.propietarios = local.getPropietarios();
    }
    
    public Nombre getDireccion(){
        return this.direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(Nombre direccion) {
        this.direccion = direccion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean anadirPropietario(Persona u){
        //si ya existen los 3 propietarios indico que no se pueden añadir más y salgo
        if(propietarios[0] != null && propietarios[1] != null && propietarios[2] != null){
            System.out.println("Máximo de propietarios (3) ya alcanzado");
            return false;
        }else{
            //En caso de que quede algún hueco de propietario libre lo busco recorriendo
            // el array de 3 posiciones y en el primer hueco que encuentro meto al nuevo propietario.
          for(int i=0;i<propietarios.length;i++){
            if(propietarios[i] == null){
                propietarios[i] = u;
                return true;
            }
            }
            return false;  
        }
    }     

    public Persona[] getPropietarios() {
        return propietarios;
    }

    public void setPropietarios(Persona[] propietarios) {
        this.propietarios = propietarios;
    }

    @Override
    public String toString() {
        return "- nombre= " + nombre + "\n- direccion= " + direccion + "\n- descripcion= " + descripcion;
        
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
