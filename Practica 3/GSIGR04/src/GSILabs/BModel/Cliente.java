/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import GSILabs.BSystem.XMLRepresentable;
import java.io.File;
import java.util.Date;

/**
 *
 * @author LENOVO-arribass
 */
public class Cliente extends Usuario implements XMLRepresentable{

    public Cliente(String nick, String password, Date fechaNacimiento, int perfil) {
        super(nick, password, fechaNacimiento, perfil);
    }

    @Override
    public String toXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveToXML(File f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveToXML(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
