/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.Misc;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.jopendocument.dom.spreadsheet.Sheet;

/**
 *
 * @author Asier
 */
public class SSTest01 {
    
    public static void main(String[] args) throws IOException {

        SpreadSheet s = SpreadSheet.create(1, 20, 20);
        
        //First sheet
        Sheet sheet = s.getSheet(0);

        final int[][] array = new int[4][6];
        Random random = new Random();
        for(int i = 0; i<4; i++){
            for(int j = 0; j<6; j++){
                int randInt = random.nextInt(100);
                array[i][j] = (int) randInt;
                System.out.println(array[i][j]);
            }
        }
        
        for(int i = 0; i<4; i++){
            for(int j = 0; j<6; j++){
            
                sheet.setValueAt(array[i][j], j, i); //(Value,x,y)
            }
        }
        
        final File file = new File("test01.ods");
        s.saveAs(file);
        
        OOUtils.open(file);
    }
}
