
package pdfmetadatareader;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;


public class PDFMetadata {
    /**
     * Obtiene el tamaño de un archivo PDF en bytes.
     *
     * @param rutaArchivo Ruta al archivo PDF.
     * @return El tamaño del archivo PDF en bytes.
     * @throws IOException Si ocurre un error al acceder al archivo o al procesar el PDF.
     */
    public static long obtenerTamanoPDF(String rutaArchivo) throws IOException {
        File archivoPDF = new File(rutaArchivo);

        if (archivoPDF.exists()) {
            try (PDDocument document = PDDocument.load(archivoPDF)) {
                return archivoPDF.length(); // Devuelve el tamaño del archivo en bytes
            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        } else {
            throw new IOException("El archivo PDF no existe en la ruta especificada.");
        }
    }
}
