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
    private EscuelaPertenece escuelaPertenece;
    private String contenidos;
    private Persona[] docentes;
    
    private final int maxLongitudContenidos = 600;

    public Curso(String nombre, EscuelaPertenece escuelaPertenece, String contenidos) {
        this.nombre = nombre;
        this.escuelaPertenece = escuelaPertenece;
        if (contenidos.length() > maxLongitudContenidos){
            contenidos = contenidos.substring(0, maxLongitudContenidos);
        }
        this.contenidos = contenidos;
        this.docentes = new Persona[3]; //3 profesores
    }
    
    public Curso(String stringXML) throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Curso.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(stringXML);
        Curso local = (Curso) unmarshaller.unmarshal(reader);
        this.nombre = local.getNombre();
        this.escuelaPertenece = local.getEscuelaPertenece();
        this.contenidos = local.getContenidos();
        this.docentes = local.getDocentes();
    }
    
    public EscuelaPertenece getEscuelaPertenece(){
        return this.escuelaPertenece;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContenidos() {
        return contenidos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEscuelaPertenece(EscuelaPertenece escuelaPertenece) {
        this.escuelaPertenece = escuelaPertenece;
    }

    public void setContenidos(String contenidos) {
        this.contenidos = contenidos;
    }

    public boolean anadirPropietario(Persona u){
        //si ya existen los 22 participantes indico que no se pueden añadir más y salgo
        if(docentes[0] != null && docentes[1] != null && docentes[2] != null){
            System.out.println("Máximo de decoentes(3) ya alcanzado");
            return false;
        }else{
            //En caso de que quede algún hueco de docente libre lo busco recorriendo
            // el array de 3 posiciones y en el primer hueco que encuentro meto al nuevo participante.
          for(int i=0;i<docentes.length;i++){
            if(docentes[i] == null){
                docentes[i] = u;
                return true;
            }
            }
            return false;  
        }
    }     

    public Persona[] getDocentes() {
        return docentes;
    }

    public void setDocentes(Persona[] docentes) {
        this.docentes = docentes;
    }

    @Override
    public String toString() {
        return "- Nombre-> " + nombre + "\n- escuela-> " + escuelaPertenece + "\n- contenidos-> " + contenidos;
        
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
