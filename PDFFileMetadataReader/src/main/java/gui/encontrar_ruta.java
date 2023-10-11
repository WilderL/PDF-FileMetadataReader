
package gui;

import javax.swing.JFileChooser;

public class encontrar_ruta {
    public void find(){
        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
                    String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                    System.out.println("Selected file/folder: " + selectedFilePath);
                } else {
                    System.out.println("No file selected.");
                }  
    }
}
