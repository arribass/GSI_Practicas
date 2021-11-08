package GSILabs.persistence;
import java.io.File;

/**
 *  This interface imposes basic read/write abilities on Open Document Format
 *      spreadsheets.
 * @author carlos.lopez
 */
public interface ODSPersistente {
    
    /**
     * This file loads from a file the instances needed to populate a business model.
     * The method must close the file after finishing, and return a true value
     * if and only if:
     *      a) The file existed, was accessible, and could be closed after analysis,
     *      b) The contents of the file did follow the expected format, AND
     *      c) The system could read the information from the file with no error or interruption.
     * @param f  File from which the info is to be read.
     * @return A result of the process according to the rules above.
     */
    public boolean loadFromFile(File f);

    
    /**
     * This file loads from a file the instances needed to populate a business model.
     * The resulting file must be according to the pre-specified rules,
     * so that any file f written by this method should be readable by means of the
     * execution of loadFromFile(f).
     * @param f The destination file.
     * @return true if the information could be safely stored.
     */
    public boolean saveToFile(File f);
    
}
