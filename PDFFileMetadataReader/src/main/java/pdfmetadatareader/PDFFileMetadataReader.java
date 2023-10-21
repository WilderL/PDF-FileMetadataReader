package pdfmetadatareader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFFileMetadataReader {

    public static void main(String[] args) {
        String folderPath = "ruta/a/la/carpeta"; 
        List<PDFFile> pdfFiles = new ArrayList<>();
        PDFFileFinder pdfFileFinder = new PDFFileFinder();
        try {
            pdfFiles = pdfFileFinder.findPdfFiles(folderPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
