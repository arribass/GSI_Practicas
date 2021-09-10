/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.Model;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author LENOVO-arribass
 */
public class Usuario {
    private static final Set<String> nombres = new HashSet<String>();

    private String nombre;
    private String password;

    public Usuario(String nombre, String password) throws Exception{
        if (!nombres.add(nombre))
             throw new Exception();
        this.nombre = nombre;
        this.password = password;
    }
    
    public static Set<String> getNombres() {
        return nombres;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }  

    @Override
    public String toString() {
        return getNombre() + " " + getPassword();
    }
    
    
}
