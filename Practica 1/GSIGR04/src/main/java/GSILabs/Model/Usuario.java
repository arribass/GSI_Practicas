/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.Model;

import java.util.Date;
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
    private Date fechaNacimiento;

    public Usuario(String nombre, String password, Date fechaNacimiento) throws Exception{
        if (nombre.length() < 3)
            throw new IllegalArgumentException("Nombre demasiado corto");
        if (!nombres.add(nombre))
            throw new Exception("Ya existe el usuario");
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public static Set<String> getNombres() {
        return nombres;
    }
    public String getNombre() {
        return nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNombre(String nombre) {
        this.setNombre(nombre);
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
