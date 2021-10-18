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
public class SSTest03 {
    
    public static void main(String[] args) throws IOException {
        
        //Inicializo la matriz de 4x6 donde vamos a guardar los datos
        final Object[][] array = new Object[4][6];
        //Creo la instancia del objteo File con el nombre dicho en el documento
        final File file = new File("test02.ods");
        //Creo el documento a partir del test02.ods
        SpreadSheet s = SpreadSheet.createFromFile(file);
        //First sheet
        Sheet sheet = s.getSheet(0);
        
        //Creo un bucle anidado para completar la matriz a partir de los datos del .ods
        for(int i = 5; i<9; i++){
            for(int j = 3; j<9; j++){
                //Índices del array mediante hard-coding porque en el ods la matriz está desplazada
                array[i-5][j-3] = sheet.getCellAt(j, i).getValue();
            }
        }
        
        //Creo un bucle anidado para printear la matriz obtenida de los datos del ods
        //y comprobar que está bien
        for(int i=0; i<4; i++){
            for(int j = 0; j<6; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
