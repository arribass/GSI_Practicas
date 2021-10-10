/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.Misc;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 *
 * @author Pablo
 */
public class SSTest02 {
    
    public static void main(String[] args) throws IOException {

        final Object[][] array = new Object[4][6];
        
        Random random = new Random();
        for(int i = 0; i<4; i++){
            for(int j = 0; j<6; j++){
                int randInt = random.nextInt(100);
                array[i][j] = (int) randInt;
                System.out.println(array[i][j]);
            }
        }
        
        
        
        
        //String[] columns = new String[] { "", "", "", "", "", "" };
        //TableModel model = new DefaultTableModel(array, columns);
        //final File file = new File("test01.ods");
        //SpreadSheet.createEmpty(model).saveAs(file);
        //OOUtils.open(file);
    }
}
