
package gui;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
                    
                } else {
                    System.out.println("No se ha seleccionado.");
                }  
    }
    public void escribir(String ruta) {
        try{
            FileOutputStream archivo = new FileOutputStream("Prueba.txt", true);
            try (DataOutputStream writer = new DataOutputStream(archivo)) {
                writer.writeUTF(ruta);
                System.out.println("Se ha creado");
            }
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
