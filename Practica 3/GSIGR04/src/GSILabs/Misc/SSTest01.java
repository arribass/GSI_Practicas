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

        //Creo el documento con una hoja y de 20x20 (filasxcolumnas)
        SpreadSheet s = SpreadSheet.create(1, 20, 20);
        
        //First sheet
        Sheet sheet = s.getSheet(0);

        //Inicializo la matriz de 4x6
        final int[][] array = new int[4][6];
        //Creo instancia de Random()
        Random random = new Random();
        //Creo un bucle anidado para rellenar la matriz con números enteros aleatorios
        for(int i = 0; i<4; i++){
            for(int j = 0; j<6; j++){
                int randInt = random.nextInt(100);
                array[i][j] = (int) randInt;
                System.out.println(array[i][j]);
            }
        }
        
        //Creo un bucle anidado para completar el documento recorriendo la matriz creada
        for(int i = 0; i<4; i++){
            for(int j = 0; j<6; j++){
                //Inserto el valor en el documento
                //x e y están intercambiadas
                sheet.setValueAt(array[i][j], j, i); //(Value,x,y)
            }
        }
        
        //Creo la instancia del objteo File con el nombre dicho en el documento
        final File file = new File("test01.ods");
        //Guardo el documento como ese fichero
        s.saveAs(file);
        //Abro el documento para comprobarlo
        OOUtils.open(file);
    }
}
