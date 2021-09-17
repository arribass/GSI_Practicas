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
public class Review {
    private int valoracion;
    private String comentario;
    private Date fecha;

    public Review(int valoracion, String comentario, Date fecha){
        this.valoracion = valoracion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public Date getFecha(){
    return this.fecha;
    }

    
}
 