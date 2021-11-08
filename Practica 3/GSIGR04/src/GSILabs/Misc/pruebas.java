/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.Misc;

import java.io.File;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 *
 * @author ASier
 */
public class pruebas {
    public static void main(String[] args) throws IOException {
        final Object[][] data = new Object[6][2];
        data[0] = new Object[] { "January", 1 };
        data[1] = new Object[] { "February", 3 };
        data[2] = new Object[] { "March", 8 };
        data[3] = new Object[] { "April", 10 };
        data[4] = new Object[] { "May", 15 };
        data[5] = new Object[] { "June", 18 };

        String[] columns = new String[] { "Month", "Temp" };

        TableModel model = new DefaultTableModel(data, columns);  

        // Save the data to an ODS file and open it.
        final File file = new File("temperature.ods");
        SpreadSheet.createEmpty(model).saveAs(file);

        OOUtils.open(file);
    }
}
