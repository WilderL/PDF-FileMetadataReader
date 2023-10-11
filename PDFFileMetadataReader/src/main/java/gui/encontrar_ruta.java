
package gui;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class encontrar_ruta {
    public void find(){
        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
                    String Path = fileChooser.getSelectedFile().getAbsolutePath();
                    escribir(Path);
                    System.out.println("Archivo de ruta: " + Path);
                    JOptionPane.showMessageDialog(null, "Se ha aguardado la ruta", "Ok", JOptionPane.INFORMATION_MESSAGE);
                    
                } else {
                    System.out.println("No se ha seleccionado.");
                }  
    }
    public void escribir(String ruta) {
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("prueba.txt",true));
            writer.println(ruta);
            writer.close();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
