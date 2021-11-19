/*
 * This document is part of the lab material for the subject:
 * Gestion de Sistemas de Informacion
 * to be released at the
 * Universidad Publica de Navarra
 * during the first semester of the Academic Year 2015-2016
 */
 
 package GSILabs.BSystem;

import java.io.File;
import GSILabs.persistence.XMLParsingException;

/**
 * This interface embodies the ability of a a class of objects to represent itself
 * 	using XML. 
 * Note that it refers, in no manner, to its ability to create instances from such a representation.
 *
 * @author carlos.lopez
 * @version 1.0 (06/08/2013)    
 */
public interface XMLRepresentable {
    
    public String toXML() throws XMLParsingException;
    
    public boolean saveToXML(File f) throws XMLParsingException;
    
    public boolean saveToXML(String filePath) throws XMLParsingException;
    
}