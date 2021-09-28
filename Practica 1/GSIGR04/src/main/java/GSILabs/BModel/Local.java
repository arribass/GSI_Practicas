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
    private Usuario[] propietarios;
    
    private final int maxDescripcion = 300;

    public Local(String nombre, Direccion direccion, String descripcion) {
        this.nombre = nombre;
        this.direccion = direccion;
        if (descripcion.length() > maxDescripcion){
            descripcion = descripcion.substring(0, maxDescripcion);
        }
        this.descripcion = descripcion;
        this.propietarios = new Usuario[3];
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean anadirPropietario(Usuario u){
        if(propietarios[2] != null){
            return false;
        }else{
          for(int i=0;i<propietarios.length;i++){
            if(propietarios[i] == null){
                propietarios[i] = u;
                return true;
            }
            }
            return false;  
        }
    }     

    public Usuario[] getPropietarios() {
        return propietarios;
    }

    public void setPropietarios(Usuario[] propietarios) {
        this.propietarios = propietarios;
    }

    @Override
    public String toString() {
        return "- nombre= " + nombre + "\n- direccion= " + direccion + "\n- descripcion= " + descripcion;
        
    }
}
