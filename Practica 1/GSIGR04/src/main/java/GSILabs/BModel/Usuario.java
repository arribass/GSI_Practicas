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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author LENOVO-arribass
 */
public class Usuario {
    private static final AtomicInteger id = new AtomicInteger();

    private String nick;
    private String password;
    private Date fechaNacimiento;
    private final int EDAD_MINIMA  = 14;

    public Usuario(String nick, String password, Date fechaNacimiento) {
        Usuario.id.addAndGet(1);
        
        this.nick = nick;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
    }
    /*Getters and Setters*/
    public AtomicInteger getId() {
        return id;
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
    
    /**/

    /**
     *
     * @param u
     * @return 
     */

     public boolean validarUsuario(Usuario u){
        return validarLongitudNick(u.getNick());
    }
    public boolean validarLongitudNick(String nick){
        return nick.length() > 3;
    }
    public boolean validarLongitudNick(String nick,int longitud){
        return true;
    }
    private boolean edadValida() {
        long fechaActual = new java.util.Date().getTime();
        long diffInMillies = Math.abs(fechaNacimiento.getTime() - fechaActual);
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        Period period = Period.between(fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
        period.getYears();
        System.out.println("Edad: " + period.getYears());
        
        return period.getYears() > EDAD_MINIMA;
    }
    
    @Override
    public String toString() {
        return "ID:" + getId() + "\nNick: " + getNick() + "\nFecha nacimiento: " + getFechaNacimiento().toString() +"\n";
    }
}
