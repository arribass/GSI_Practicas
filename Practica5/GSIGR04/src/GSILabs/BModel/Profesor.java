/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;


import java.util.Date;
import javax.xml.bind.JAXBException;

/**
 *
 * @author Jaime
 */
public class Profesor extends Persona {

    public Profesor(String NIA, String nombre, Date fechaNacimiento, int centroUltimosEstudiosAnteriores) {
        super(NIA, nombre, fechaNacimiento, centroUltimosEstudiosAnteriores);
    }
    
    public Profesor(String stringXML) throws JAXBException{
        super(stringXML);
        
    }

    
}
