/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GSILabs.Misc;

import GSILabs.BModel.Master;
import GSILabs.BModel.Examen;
import GSILabs.BModel.Matricula;
import GSILabs.BModel.EscuelaPertenece;
import GSILabs.BModel.Curso;
import GSILabs.BModel.Nota;
import GSILabs.BModel.Alumno;
import GSILabs.BModel.Doctorado;
import GSILabs.BModel.Grado;
import GSILabs.BModel.Persona;
import GSILabs.BSystem.UniSystem;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import GSILabs.BModel.Matriculable;

/**
 *
 * @author Jaime
 */
public class SSTest04 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // Instanciamos una clase BusinessSystem
        UniSystem bs = new UniSystem();
        String passwordUsuario;
        String nombreUsuario;
        
        
        Scanner sc = new Scanner(System.in);
        int choice=999;
        do {
             System.out.println("0. Salir\n"
                                + "1. Registrar Usuario\n"
                                + "2. Registrar local\n"
                                + "3. Guardar datos en un documento\n"                
                               );

            System.out.print("¿Qué deseas hacer? : \n");
            //Recogo su elección y si no es válida se lo notifico.
            try{
                choice = sc.nextInt();
            }catch(Exception e){
                System.out.println("Caracter inválido");
            }
            //En función de lo que haya elegido hacemos una acción u otra.
            sc.nextLine();
            switch (choice) {
                case 0: //cerrar programa
                    System.out.println("Cerrando programa...");
                    break;
                case 1:  //Añadir usuario                
                    boolean registerOk = false;
                    System.out.println("Introduce el nombre de usuario: ");  
                    nombreUsuario = sc.nextLine();      

                    System.out.println("Introduce la contraseña: "); 
                    passwordUsuario = sc.nextLine();

//                    System.out.println("Introduce la fecha de nacimiento (dd/MM/yyyy): "); 
//                    String edadUsuario = sc.nextLine();
                    Date fechaNacimientoUsuario;
                    
                    try {
                        fechaNacimientoUsuario = new SimpleDateFormat("dd/MM/yyyy").parse("13/11/1999");
                        Persona u = new Persona(nombreUsuario, passwordUsuario, fechaNacimientoUsuario,1);
                        if (bs.nuevoUsuario(u)){
                            System.out.println("Usuario registrado correctamente!");
                            System.out.println(u.toString());
                        }else{
                            System.out.println("El usuario " + u.getNIA()+ " ya existe!");
                        }
                    } catch (IllegalArgumentException | ParseException ex) {
                        System.out.println(ex.toString());
                    }

                    break;
                case 2: //Registrar local
                   //Pido al usuario el nombre del nuevo local.                    

                   System.out.println("Introduce el nombre del local: ");  
                   String nombreLocal = sc.nextLine(); 
                   
                   

                   //Pido al usuario los componentes de la direccion del local.        
                   System.out.println("Vas a introducir la dirección \n");         
                   System.out.println("Introduce la localidad: "); 
                   String localidad = sc.nextLine();         
                   System.out.println("Introduce la provincia: "); 
                   String provincia = sc.nextLine();       
                   System.out.println("Introduce la calle: "); 
                   String calle = sc.nextLine();       
                   System.out.println("Introduce el numero: "); 
                   String numero = sc.nextLine();
                   //Genero la dirección.
                   EscuelaPertenece d = new EscuelaPertenece(localidad,provincia,calle);

                   //Pido la descripcion del local al usuario.
                   System.out.println("Introduce la descripción del local: "); 
                   String descripcionLocal = sc.nextLine();
                   Curso l = new Curso(nombreLocal,d,descripcionLocal);
                   
                   
                   //Pido al usuario el nombre del propietarios                    

                   System.out.println("Introduce el nombre del Propietario: ");  
                   String nombrePro = sc.nextLine(); 
                   
                   Persona u = bs.obtenerUsuario(nombrePro);
                  
                   l.anadirPropietario(u);
                   bs.nuevoCurso(l);
                   break;

                case 3://guardar los datos de bar, restaurante y pub en un fichero .ods 
                    
                    SpreadSheet s = SpreadSheet.create(3, 20, 20);
                    //Hoja bares
                    Sheet hojaBares = s.getSheet(0);
                    hojaBares.setName("Bares");
                    //Listar todos los bares 
                    List<Curso> listaBares = new ArrayList<>();
                    listaBares = bs.listarTodosMasteres();
                    
                   
                    for (int i=0;i<listaBares.size();i++) {
                        Curso bar = listaBares.get(i);
                        
                        hojaBares.setValueAt(bar.getNombre(),0,i);
                        hojaBares.setValueAt(bar.getEscuelaPertenece(),1,i);
                        hojaBares.setValueAt(bar.getContenidos(),2,i);
                       // hojaBares.setValueAt(bar.getPropietarios()[0].getNick(),3,i);
                        
                    }       
                    
                    
                    //Hoja Restaurantes
                    Sheet hojaRestaurantes = s.getSheet(1);
                    hojaRestaurantes.setName("Restaurantes");
                    //Listar todos los Restaurantes
                    List<Curso> listaRestaurantes = new ArrayList<>();
                    listaRestaurantes = bs.listarTodosGrados();
                    
                    
                    for (int i=0;i<listaRestaurantes.size();i++) {
                        Curso restaurante = listaRestaurantes.get(i);
                        hojaRestaurantes.setValueAt(restaurante.getNombre(),0,i);                        
                        hojaRestaurantes.setValueAt(restaurante.getEscuelaPertenece(),1,i);
                        hojaRestaurantes.setValueAt(restaurante.getContenidos(),2,i);
                        hojaRestaurantes.setValueAt(restaurante.getDocentes(),3,i);
                    }
                    
                    //Hoja Restaurantes
                    Sheet hojaPubs = s.getSheet(2);
                    hojaPubs.setName("Pubs");
                    //Listar todos los Restaurantes
                    List<Curso> listaPubs = new ArrayList<>();
                    listaPubs = bs.listarTodosDoctorados();
                    
                    
                    for (int i=0;i<listaPubs.size();i++) {
                        Curso pub = listaPubs.get(i);
                        hojaPubs.setValueAt(pub.getNombre(),0,i);                        
                        hojaPubs.setValueAt(pub.getEscuelaPertenece(),1,i);
                        hojaPubs.setValueAt(pub.getContenidos(),2,i);
                        hojaPubs.setValueAt(pub.getDocentes(),3,i);
                    }
                    
                    final File file = new File("test04.ods");
                    s.saveAs(file);
        
                    OOUtils.open(file);
                    
                    break;
                    
            
            }
        }while ( choice != 0 );
        
        
    }
    
}
