/*
 * CLASE: Usuario
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
    private String perfil;
   
    private final int EDAD_MINIMA  = 14;
    private final int LONGITUD_MINIMA_NICK = 3;

    public Usuario(String nick, String password, Date fechaNacimiento, int perfil) {
        try {
            usuarioValido(nick,password,fechaNacimiento,perfil);
            this.nick = nick;
            this.password = password;
            this.fechaNacimiento = fechaNacimiento;
            this.perfil = perfil == 1?"Cliente":"Dueno";
            Usuario.id.addAndGet(1);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Parametros de usuario invalido");
        }
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
    
    /*METODOS*/
    
    /**
     * Comprueba que la longitud del nick es valida
     * @param nick
     * @return 
     */
    public boolean longitudNickValida(String nick){
        return nick.length() > LONGITUD_MINIMA_NICK;
    }
    
    /**
     * Desc: Recibe una edad y devuelve la edad actual del usuario en el instante en 
     * el que se calcula
     * @return 
     */
    private boolean edadValida(Date fechaNacimiento) {
        long fechaActual = new java.util.Date().getTime();
        long diffInMillies = Math.abs(fechaNacimiento.getTime() - fechaActual);
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        Period period = Period.between(fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now());
        period.getYears();
        System.out.println("Edad: " + period.getYears());
        
        return period.getYears() > EDAD_MINIMA;
    }
    /**
     * Desc: Obtienes unos datos de usuario y comprueba si son validos para registrar
     * @param nick
     * @param password1
     * @param fechaNacimiento1
     * @return 
     */
    private boolean usuarioValido(String nick, String password, Date fechaNacimiento, int perfil) {
        if(!longitudNickValida(nick))
            throw new IllegalArgumentException("Nick demasiado corto");
        if(!edadValida(fechaNacimiento))
            throw new IllegalArgumentException("Edad minima 14");
        if(!perfilValido(perfil))
            throw new IllegalArgumentException("El indice de perfil debe ser 1(Cliente) o 2(Dueno)");
        return true;    
    }
        
    @Override
    /**
     * Desc: Devuelve los datos de usuario en el siguiente formato
     * ID: {id} 
     * Nick: {nick}
     * Fecha nacimiento: {dateOfBirth}
     */
    public String toString() {
        return "ID:" + getId() + "\nNick: " + getNick() + "\nFecha nacimiento: " + getFechaNacimiento().toString() +"\n";
    }

    private boolean perfilValido(int perfil) {
        return perfil == 1 || perfil == 2;
    }
}
