/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;
import GSILabs.BSystem.XMLRepresentable;
import GSILabs.persistence.XMLParsingException;
import java.io.File;
import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.JAXBException;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
/**
 *
 * @author Arribas
 */
public class Review  implements XMLRepresentable, Serializable{
    private int valoracion;
    private String comentario;
    private Date fecha;
    private Local local; //añadir de alguna manera a que local pertenece
    private Cliente cliente;

    public Review(int valoracion, String comentario, Date fecha, Local local, Cliente cliente){
        this.valoracion = valoracion;
        this.comentario = comentario;
        this.fecha = fecha;
        this.local = local;
        this.cliente = cliente;
    }

    public Review(String stringXML) throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Review.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(stringXML);
        Review review = (Review) unmarshaller.unmarshal(reader);
        
        this.valoracion = review.getValoracion();
        this.comentario = review.getComentario();
        this.fecha = review.getFecha();
        this.local = review.getLocal();

        
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

    public int getValoracion() {
        return valoracion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toXML() {
        try{
            
            throw new XMLParsingException("Error XML");
            
        }catch(XMLParsingException e){
            System.out.println("Error al convertir a XML");
        }
        return null;
    }

    @Override
    public boolean saveToXML(File f) {
        try{
            
            throw new XMLParsingException("Error XML");
            
        }catch(XMLParsingException e){
            System.out.println("Error al guardar en XML");
        }
        return false;
    }

    @Override
    public boolean saveToXML(String filePath) {
        try{
            
            throw new XMLParsingException("Error XML");
            
        }catch(XMLParsingException e){
            System.out.println("Error al guardar en XML");
        }
        return false;
    }

    
}
 