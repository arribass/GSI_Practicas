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
public class Propietario extends Usuario implements XMLPrepresentable{
    
    public Propietario(String nick, String password, Date fechaNacimiento, int perfil) {
        super(nick, password, fechaNacimiento, perfil);
    }
    

   
    
}
