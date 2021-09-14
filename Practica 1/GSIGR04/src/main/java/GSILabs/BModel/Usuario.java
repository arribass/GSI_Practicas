/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author LENOVO-arribass
 */
public class Usuario {
    private static final Set<String> nombres = new HashSet<String>();
    private AtomicInteger id;

    public AtomicInteger getId() {
        return id;
    }

    public void setId(AtomicInteger id) {
        this.id = id;
    }
    private String nick;
    private String password;
    private Date fechaNacimiento;

    public Usuario(String nombre, String password, Date fechaNacimiento) throws Exception{
        //Comprobamos la longitud del nombre y que esta disponible
        if (!nombreValido(nombre))
            throw new IllegalArgumentException("Nombre demasiado corto");         
        if (!nombres.add(nombre))
            throw new Exception("Ya existe el usuario");
        this.nick = nombre;
        
        this.password = password;
        
        //Comprobamos que el usuario tiene la edad suficiente
        if(!edadValida(fechaNacimiento))
            throw new Exception("Edad minima 14 años");
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public static Set<String> getNombres() {
        return nombres;
    }
    public String getNick() {
        return nick;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }  

    @Override
    public String toString() {
        return getNick() + " " + getPassword() + " " + getFechaNacimiento().toString();
    }

    private boolean edadValida(Date fechaNacimiento) {
        long fechaActual = new java.util.Date().getTime();
        long diffInMillies = Math.abs(fechaNacimiento.getTime() - fechaActual);
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        Period period = Period.between(fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
        period.getYears();
        System.out.println("Edad: " + period.getYears());
        
        return period.getYears() > 14;
    }

    private boolean nombreValido(String nombre) {
        return nombre.length() > 3;
    }
    
    
}
