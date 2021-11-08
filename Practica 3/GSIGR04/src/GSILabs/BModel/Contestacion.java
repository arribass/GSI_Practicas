/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;
import GSILabs.BSystem.XMLRepresentable;
import java.io.File;
import java.util.Date;

/**
 *
 * @author Arribas
 */
public class Contestacion implements XMLRepresentable {
    private Review review;
    private String comentario;
    private Date fecha;

    public Contestacion(Review review, String comentario, Date fecha){
        this.review = review;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public Review getReview() {
        return review;
    }

    public String getComentario() {
        return comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    @Override
    public String toXML() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveToXML(File f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean saveToXML(String filePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
