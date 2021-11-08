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
public class Review implements XMLPrepresentable{
    private int valoracion;
    private String comentario;
    private Date fecha;
    private Local local; //añadir de alguna manera a que local pertenece

    public Review(int valoracion, String comentario, Date fecha, Local local){
        this.valoracion = valoracion;
        this.comentario = comentario;
        this.fecha = fecha;
        this.local = local;
    }

    public Date getFecha(){
    return this.fecha;
    }

    public Local getLocal() {
        return local;
    }

    public String getComentario() {
        return comentario;
    }

    
}
 