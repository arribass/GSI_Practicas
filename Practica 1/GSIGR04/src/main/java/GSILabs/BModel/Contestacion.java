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
public class Contestacion {
    private String comentario;
    private Date fecha;

    public Contestacion(String comentario, Date fecha){
        this.comentario = comentario;
        this.fecha = fecha;
    }
}
