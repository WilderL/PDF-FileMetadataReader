
package gui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EncontrarRuta {
    public String find(){
        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
                    String Path = fileChooser.getSelectedFile().getAbsolutePath();
                    escribir(Path);
                    System.out.println("Archivo de ruta: " + Path);
                    JOptionPane.showMessageDialog(null, "Se ha aguardado la ruta", "Ok", JOptionPane.INFORMATION_MESSAGE);
                    return Path;
                } else {
                    System.out.println("No se ha seleccionado.");
                    return "";
                }  
    }
    public void escribir(String ruta) {
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("folderPath.txt",true));
            writer.println(ruta);
            writer.close();
        }catch (IOException e){
            
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean comprobarExistencia(){
        File file = new File("folderPath.txt");
        
        if (file.exists()){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "No se ha seleccionado una ruta, porfavor, selecciona una", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }       
    }
    
    public void delete(){
        try (PrintWriter writer = new PrintWriter(new FileWriter("folderPath.txt"))) {
            // Al abrir el archivo en modo de escritura, el contenido existente se eliminará
            System.out.println("Se ha vaciado el archivo para nuevas rutas");
        } catch (IOException e) {
            System.err.println("Error al intentar eliminar el contenido del archivo: " + e.getMessage());
        }
    }
}
