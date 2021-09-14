/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import java.util.Date;

/**
 *
 * @author Arribas
 */
public class Propietario extends Usuario{
    
    public Propietario(String nombre, String password, Date fechaNacimiento) throws Exception {
        super(nombre, password, fechaNacimiento);
    }
    
}
