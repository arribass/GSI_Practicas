/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author LENOVO-arribass
 */
public class Local {
    //private static final Set<String> nombres = new HashSet<String>();

    private String nombre;
    private Direccion direccion;
    private String descripcion;
    
    private final int maxDescripcion = 300;

    public Local(String nombre, Direccion direccion, String descripcion) {
        this.nombre = nombre;
        this.direccion = direccion;
        if (descripcion.length() > maxDescripcion){
            descripcion = descripcion.substring(0, maxDescripcion);
        }
        this.descripcion = descripcion;
    }
    
    public Direccion getDireccion(){
        return this.direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    
}
