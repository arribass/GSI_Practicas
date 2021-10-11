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
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.jopendocument.dom.spreadsheet.Sheet;

/**
 *
 * @author Pablo
 */
public class SSTest02 {
    
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
        
        for(int i = 5; i<9; i++){
            for(int j = 3; j<9; j++){
            
                sheet.setValueAt(array[i-5][j-3], j, i); //(Value,x,y)
                if (array[i-5][j-3] >= 10)
                    sheet.getCellAt(j, i).setBackgroundColor(Color.blue);
                else
                    sheet.getCellAt(j, i).setBackgroundColor(Color.red);
            }
        }
        
        final File file = new File("test02.ods");
        s.saveAs(file);
        
        OOUtils.open(file);
    }
}
