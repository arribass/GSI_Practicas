/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.BModel;

import GSILabs.BSystem.XMLRepresentable;

/**
 *
 * @author Arribas
 */
public class Pub extends Local implements Reservable, XMLRepresentable{
    
    public Pub(String nombre, Direccion direccion, String descripcion) {
        super(nombre, direccion, descripcion);
    }
    
}
