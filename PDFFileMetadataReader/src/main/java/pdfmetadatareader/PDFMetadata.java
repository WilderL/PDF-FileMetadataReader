
package pdfmetadatareader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;


public class PDFMetadata {
    /**
    * Obtiene el tamaño de un archivo PDF en megabytes (MB).
    *
    * @param pdfFilePath La ruta del archivo PDF del que se desea obtener el tamaño.
    * @return El tamaño del archivo PDF en megabytes (MB).
    */
   public static double getPDFSizeInMB(String pdfFilePath) {
       File pdfFile = new File(pdfFilePath);

       // Obtén el tamaño del archivo PDF en bytes
       long fileSizeBytes = pdfFile.length();

       // Convierte los bytes a megabytes (1 MB = 1024 * 1024 bytes)
       double fileSizeMB = (double) fileSizeBytes / (1024 * 1024);

       return fileSizeMB;
   }
   
    /**
  * Obtiene la información de tamaño de página de un archivo PDF.
  *
  * @param pdfFilePath La ruta del archivo PDF del que se desea obtener la información de tamaño de página.
  * @return Una lista de cadenas de texto que contienen el ancho y el alto de cada página del PDF.
  * @throws IOException Si ocurre un error al cargar el archivo PDF.
  */
   public List<String> getPageSizesFromPDF(String pdfFilePath) throws IOException {
        List<String> pageSizesInInches = new ArrayList<>();
        File pdfFile = new File(pdfFilePath);
    
        try (PDDocument document = PDDocument.load(pdfFile)) {
            for (PDPage page : document.getPages()) {
                PDRectangle pageSize = page.getMediaBox();
                float widthInPoints = (float) pageSize.getWidth();
                float heightInPoints = (float) pageSize.getHeight();

                // Convertir de puntos a pulgadas (1 pulgada = 72 puntos)
                float widthInInches = widthInPoints / 72.0f;
                float heightInInches = heightInPoints / 72.0f;

                String sizeInfo = "Ancho: " + widthInInches + " pulgadas, Alto: " + heightInInches + " pulgadas";
                pageSizesInInches.add(sizeInfo);
            }
        }

        return pageSizesInInches;
   }
   
   /**
    * Obtiene el número de páginas de un archivo PDF.
    *
    * @param pdfFilePath La ruta del archivo PDF del que se desea obtener el número de páginas.
    * @return El número de páginas en el archivo PDF.
    * @throws IOException Si ocurre un error al cargar el archivo PDF.
    */
   public static int getNumberOfPagesInPDF(String pdfFilePath) throws IOException {
       File pdfFile = new File(pdfFilePath);
       try (PDDocument document = PDDocument.load(pdfFile)) {
           return document.getNumberOfPages();
       }
   }
   
   /**
    * Obtiene el título de un archivo PDF.
    *
    * @param pdfFilePath La ruta del archivo PDF del que se desea obtener el título.
    * @return El título del archivo PDF o una cadena vacía si no se encuentra ningún título.
    * @throws IOException Si ocurre un error al cargar el archivo PDF.
    */
   public static String getPDFTitle(String pdfFilePath) throws IOException {
       File pdfFile = new File(pdfFilePath);

       try (PDDocument document = PDDocument.load(pdfFile)) {
           String title = document.getDocumentInformation().getTitle();
           return title != null ? title : "";
       }
   }
   
   /**
    * Obtiene el nombre de un archivo PDF sin la extensión ni la ruta.
    *
    * @param pdfFilePath La ruta del archivo PDF del que se desea obtener el nombre.
    * @return El nombre del archivo PDF sin la extensión ni la ruta.
    */
   public static String getPDFFileName(String pdfFilePath) {
       File pdfFile = new File(pdfFilePath);
       String fileName = pdfFile.getName();

       int lastDotIndex = fileName.lastIndexOf('.');
       if (lastDotIndex > 0) {
           return fileName.substring(0, lastDotIndex);
       } else {
           return fileName;
       }
   }
   
   /**
    * Obtiene la extensión de un archivo PDF.
    *
    * @param pdfFilePath La ruta del archivo PDF del que se desea obtener la extensión.
    * @return La extensión del archivo PDF (por ejemplo, "pdf").
    */
   public static String getPDFFileExtension(String pdfFilePath) {
       File pdfFile = new File(pdfFilePath);
       String fileName = pdfFile.getName();

       int lastDotIndex = fileName.lastIndexOf('.');
       if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
           return fileName.substring(lastDotIndex + 1);
       } else {
           return ""; // No se encontró una extensión válida.
       }
   }
}
