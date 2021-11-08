/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import java.util.Objects;

/**
 *
 * @author Arribas
 */
public class Direccion implements XMLPrepresentable{
    
    private String localidad;
    private String provincia;
    private String calle;
    private String numero;

    public Direccion(String Localidad, String Provincia, String Calle, String Numero) {
        this.localidad = Localidad;
        this.provincia = Provincia;
        this.calle = Calle;
        this.numero = Numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }
    
    //Sobre escritura de metodos equals y toString.
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Direccion other = (Direccion) obj;
        if (!Objects.equals(this.localidad, other.localidad)) {
            return false;
        }
        if (!Objects.equals(this.provincia, other.provincia)) {
            return false;
        }
        if (!Objects.equals(this.calle, other.calle)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Direccion{" + "Localidad=" + localidad + ", Provincia=" + provincia + ", Calle=" + calle + ", Numero=" + numero + '}';
    }
    
    
    
}
