package pdfmetadatareader;

import java.io.IOException;
import java.util.List;

import gui.Main_window;
import gui.EncontrarRuta;
import javax.swing.SwingUtilities;

public class PDFFileMetadataReader {

    public static void main(String[] args) {

        // Ejecuta el código de la interfaz de usuario en el hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            EncontrarRuta path = new EncontrarRuta();
            PDFFileFinder pdfFileFinder = new PDFFileFinder();
            Main_window mw;

            if (!pdfFileFinder.pdfFileListExist()) {
                try {
                    path.comprobarExistencia();
                    String folderPath = path.find();      
                    List<PDFFile> pdfFiles = pdfFileFinder.findPdfFiles(folderPath);
                    pdfFileFinder.savePDFFileList(pdfFiles);
                    mw = new Main_window(pdfFiles);
                    mw.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                List<PDFFile> pdfFiles = pdfFileFinder.loadPDFFileList();
                mw = new Main_window(pdfFiles);
                mw.setVisible(true);
            }
        });
    }
}
