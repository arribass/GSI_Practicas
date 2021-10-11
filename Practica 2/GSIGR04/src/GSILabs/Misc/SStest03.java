/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.Misc;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 *
 * @author Pablo
 */
public class SStest03 {
    
    public static void main(String[] args) throws IOException {
        
        final Object[][] array = new Object[4][6];
        final File file = new File("test02.ods");
        SpreadSheet s = SpreadSheet.createFromFile(file);
        //First sheet
        Sheet sheet = s.getSheet(0);
        
        for(int i = 5; i<9; i++){
            for(int j = 3; j<9; j++){
                
                array[i-5][j-3] = sheet.getCellAt(j, i).getValue();
            }
        }
        
        for(int i=0; i<4; i++){
            for(int j = 0; j<6; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
