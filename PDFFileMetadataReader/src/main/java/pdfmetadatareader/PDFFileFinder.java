
package pdfmetadatareader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase que busca archivos PDF en una carpeta y sus subcarpetas, almacenando información sobre ellos.
 */
public class PDFFileFinder {
    private double tamanoArchivo;
    private String tamanoPagina;
    private int numeroPaginas;
    private String titulo;
    private String autor;
    private String nombreArchivo;
    private String asunto;
    private String palabrasClave;
    private String tipoArchivoPDF;
    private String versionPDF;
    private String aplicacionCreacion;
    private String listaImagenes;
    private String listaFuentes;
    private String fechaCreacion;
    private String herramientasCreacion;
    private String resumen;
    private static final String OUTPUT_PATH = "pdfFileList.ser";
    
    /**
     * Método para buscar archivos PDF en una carpeta y sus subcarpetas.
     *
     * @param folderPath La ruta de la carpeta raíz a explorar.
     * @return Una lista de objetos PDFFile que contienen información sobre los archivos PDF encontrados.
     */
    public List<PDFFile> findPdfFiles(String folderPath) throws IOException {
        List<PDFFile> pdfFiles = new ArrayList<>();
        File folder = new File(folderPath);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // Si es una carpeta, llama de manera recursiva para explorarla.
                        pdfFiles.addAll(findPdfFiles(file.getAbsolutePath()));
                    } else {
                        // Si es un archivo, verifica si es un archivo PDF.
                        if (file.getName().toLowerCase().endsWith(".pdf")) {
                            String pdfFilePath = file.getAbsolutePath();
                             tamanoArchivo = PDFMetadata.getPDFSizeInMB(pdfFilePath);
                             tamanoPagina =  PDFMetadata.getPageSizesFromPDF(pdfFilePath);
                             numeroPaginas = PDFMetadata.getNumberOfPagesInPDF(pdfFilePath);
                             titulo = PDFMetadata.getPDFTitle(pdfFilePath);
                             autor = PDFMetadata.getAuthorPDF(pdfFilePath);
                             nombreArchivo = PDFMetadata.getPDFFileName(pdfFilePath);
                             asunto = PDFMetadata.getPDFSubject(pdfFilePath);
                             palabrasClave = PDFMetadata.getPDFKeywords(pdfFilePath);
                             tipoArchivoPDF = PDFMetadata.getPDFType(pdfFilePath);
                             versionPDF = PDFMetadata.getPDFVersion(pdfFilePath);
                             aplicacionCreacion = PDFMetadata.getPDFCreationApplication(pdfFilePath);
                             listaImagenes = PDFMetadata.getImagesInPdf(pdfFilePath);
                             listaFuentes = PDFMetadata.getSourcesImagePdf(pdfFilePath);
                             fechaCreacion = PDFMetadata.getPDFCreationDate(pdfFilePath);
                             herramientasCreacion = PDFMetadata.getCreationToolPDF(pdfFilePath);
                             resumen = PDFMetadata.getSummaryFromPdf(pdfFilePath);
                            
                            PDFFile pdfFile = new PDFFile(tamanoArchivo, tamanoPagina, numeroPaginas, titulo, 
                                    autor, nombreArchivo, asunto, palabrasClave, tipoArchivoPDF, versionPDF, 
                                    aplicacionCreacion, listaImagenes, listaFuentes, fechaCreacion, 
                                    herramientasCreacion, resumen);
                            pdfFiles.add(pdfFile);
                        }
                    }
                }
            }
        }

        return pdfFiles;
    }
    
    /**
     * Guarda una lista de objetos PDFFile en un archivo.
     *
     * @param pdfFileList Lista de objetos PDFFile que se desea guardar en el archivo.
     */
    public void savePDFFileList(List<PDFFile> pdfFileList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OUTPUT_PATH))) {
            oos.writeObject(pdfFileList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Carga una lista de objetos PDFFile desde un archivo.
     *
     * @return Una lista de objetos PDFFile cargada desde el archivo.
     */
    public List<PDFFile> loadPDFFileList() {
        List<PDFFile> pdfFiles = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(OUTPUT_PATH))) {
            pdfFiles = (List<PDFFile>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pdfFiles;
    }
    
    /**
    * Verifica si el archivo pdfFileList.ser existe.
    *
    * @return true si el archivo existe, false si no.
    */
   public boolean pdfFileListExist() {
       File file = new File(OUTPUT_PATH);
       return file.exists();
   }

   /**
     * Ordena la lista de archivos PDF por autor y devuelve una nueva lista (normal o inversa).
     *
     * @param pdfFileList La lista de archivos PDF a ordenar.
     * @return Una nueva lista ordenada por autor.
     */
    public List<PDFFile> sortByAuthor(List<PDFFile> pdfFileList) {
        if (isSortedByAuthor(pdfFileList)) {
            Collections.reverse(pdfFileList);
        } else {
            Collections.sort(pdfFileList, (pdf1, pdf2) -> pdf1.getAutor().compareTo(pdf2.getAutor()));
        }
        return new ArrayList<>(pdfFileList);
    }

    /**
     * Ordena la lista de archivos PDF por asunto y devuelve una nueva lista (normal o inversa).
     *
     * @param pdfFileList La lista de archivos PDF a ordenar.
     * @return Una nueva lista ordenada por asunto.
     */
    public List<PDFFile> sortBySubject(List<PDFFile> pdfFileList) {
        if (isSortedBySubject(pdfFileList)) {
            Collections.reverse(pdfFileList);
        } else {
            Collections.sort(pdfFileList, (pdf1, pdf2) -> pdf1.getAsunto().compareTo(pdf2.getAsunto()));
        }
        return new ArrayList<>(pdfFileList);
    }

    /**
     * Verifica si la lista está ordenada por autor.
     *
     * @param pdfList La lista de archivos PDF a verificar.
     * @return true si la lista está ordenada por autor, false en caso contrario.
     */
    private static boolean isSortedByAuthor(List<PDFFile> pdfFileList) {
        for (int i = 0; i < pdfFileList.size() - 1; i++) {
            if (pdfFileList.get(i).getAutor().compareTo(pdfFileList.get(i + 1).getAutor()) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica si la lista está ordenada por asunto.
     *
     * @param pdfList La lista de archivos PDF a verificar.
     * @return true si la lista está ordenada por asunto, false en caso contrario.
     */
    private static boolean isSortedBySubject(List<PDFFile> pdfFileList) {
        for (int i = 0; i < pdfFileList.size() - 1; i++) {
            if (pdfFileList.get(i).getAsunto().compareTo(pdfFileList.get(i + 1).getAsunto()) > 0) {
                return false;
            }
        }
        return true;
    }
}

