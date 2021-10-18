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
        for(int i = 5; i<9; i++){
            for(int j = 3; j<9; j++){
                //Inserto el valor en el documento
                //x e y están intercambiadas
                sheet.setValueAt(array[i-5][j-3], j, i); //(Value,x,y)
                if (array[i-5][j-3] >= 10)  //Si el valor del elemento es mayor o igual que 10
                    //Pinto la celda de azul
                    sheet.getCellAt(j, i).setBackgroundColor(Color.blue);
                else    //Si el valor del elemento es menor que 10
                    //Pinto la celda de rojo
                    sheet.getCellAt(j, i).setBackgroundColor(Color.red);
            }
        }
        
        //Creo la instancia del objteo File con el nombre dicho en el documento
        final File file = new File("test02.ods");
        //Guardo el documento como ese fichero
        s.saveAs(file);
        //Abro el documento para comprobarlo
        OOUtils.open(file);
    }
}
