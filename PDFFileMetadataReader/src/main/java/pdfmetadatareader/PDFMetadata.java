
package pdfmetadatareader;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;


/**
 * Clase que proporciona utilidades para la extracción de metadatos y análisis de archivos PDF.
 */
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

       // Convierte los bytes a megabytes (1 MB = 1024 * 1024 bytes) con al menos 4 decimales
       double fileSizeMB = (double) fileSizeBytes / (1024 * 1024);

       // Formatea el resultado para que tenga al menos 4 decimales
       DecimalFormat df = new DecimalFormat("#.####");
       String formattedFileSize = df.format(fileSizeMB);

       // Convierte el valor formateado nuevamente a double
       double fileSize = Double.parseDouble(formattedFileSize);

       return fileSize;
   }
   
   /**
     * Obtiene el autor de un archivo PDF.
     * Si no se encuentra un autor, se devuelve "No existe autor".
     *
     * @param filePath La ruta al archivo PDF del que se desea obtener el autor.
     * @return El autor del PDF o "No existe autor" si no se encuentra.
     */
    public static String getAuthorPDF(String filePath) {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDDocumentInformation info = document.getDocumentInformation();
            String author = info.getAuthor();

            if (author != null && !author.isEmpty()) {
                return author;
            } else {
                return "No existe autor";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al abrir el archivo PDF";
        }
    }
   
    /**
   * Obtiene el tamaño general promedio de todas las páginas de un archivo PDF.
   *
   * @param pdfFilePath La ruta del archivo PDF del que se desea obtener el tamaño general.
   * @return Una cadena de texto que representa el tamaño general promedio del PDF en pulgadas.
   * @throws IOException Si ocurre un error al cargar el archivo PDF.
   */
  public static String getPageSizesFromPDF(String pdfFilePath) throws IOException {
      File pdfFile = new File(pdfFilePath);
      float totalWidthInPoints = 0;
      float totalHeightInPoints = 0;
      int pageCount = 0;

      try (PDDocument document = PDDocument.load(pdfFile)) {
          for (PDPage page : document.getPages()) {
              PDRectangle pageSize = page.getMediaBox();
              totalWidthInPoints += pageSize.getWidth();
              totalHeightInPoints += pageSize.getHeight();
              pageCount++;
          }

          // Calcular el promedio de ancho y alto
          float averageWidthInPoints = totalWidthInPoints / pageCount;
          float averageHeightInPoints = totalHeightInPoints / pageCount;

          // Convertir de puntos a pulgadas (1 pulgada = 72 puntos)
          float averageWidthInInches = averageWidthInPoints / 72.0f;
          float averageHeightInInches = averageHeightInPoints / 72.0f;

          return averageWidthInInches + "x" + averageHeightInInches + " pulgadas";
      }
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
           return title != null ? title : "No tiene título";
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
     * Determina el tipo de un archivo PDF, incluyendo versiones específicas y estándares como PDF/A.
     *
     * @param pdfFilePath La ruta al archivo PDF que se va a analizar.
     * @return Una cadena que describe el tipo de PDF (por ejemplo, "PDF 1.7" o "PDF/A") o un mensaje de error en caso de problemas.
     */
    public static String getPDFType(String pdfFilePath) {
        try {
            float pdfVersion;
            boolean isPdfA;
            try ( // Cargar el archivo PDF
                    PDDocument document = PDDocument.load(new File(pdfFilePath))) {
                pdfVersion = document.getVersion();
                // Verificar si es PDF/A
                isPdfA = false;
                PDDocumentInformation info = document.getDocumentInformation();
                if (info != null) {
                    String conformance = info.getCustomMetadataValue("pdfaid:conformance");
                    if (conformance != null && conformance.toLowerCase().contains("a")) {
                        isPdfA = true;
                    }
                }
            }

            // Determinar el tipo de PDF
            if (isPdfA) {
                return "PDF/A";
            } else if (pdfVersion == 1.0f) {
                return "PDF 1.0";
            } else if (pdfVersion == 1.1f) {
                return "PDF 1.1";
            } else if (pdfVersion == 1.2f) {
                return "PDF 1.2";
            } else if (pdfVersion == 1.3f) {
                return "PDF 1.3";
            } else if (pdfVersion == 1.4f) {
                return "PDF 1.4";
            } else if (pdfVersion == 1.5f) {
                return "PDF 1.5";
            } else if (pdfVersion == 1.6f) {
                return "PDF 1.6 (PDF/X-1a)";
            } else if (pdfVersion == 1.7f) {
                return "PDF 1.7 (PDF/X-1a)";
            } else if (pdfVersion == 2.0f) {
                return "PDF 2.0";
            } else {
                return "Versión desconocida";
            }
        } catch (Exception e) {
            return "Error al procesar el archivo PDF: " + e.getMessage();
        }
    }
   
   /**
    * Obtiene el asunto de un archivo PDF.
    *
    * @param pdfFilePath La ruta del archivo PDF del que se desea obtener el asunto.
    * @return El asunto del archivo PDF o una cadena vacía si no se encuentra ningún asunto.
    * @throws IOException Si ocurre un error al cargar el archivo PDF.
    */
   public static String getPDFSubject(String pdfFilePath) throws IOException {
       File pdfFile = new File(pdfFilePath);

       try (PDDocument document = PDDocument.load(pdfFile)) {
           String subject = document.getDocumentInformation().getSubject();
           return subject != null ? subject : "No tiene asunto";
       }
   }
   
   /**
    * Obtiene las palabras clave de un archivo PDF.
    *
    * @param pdfFilePath La ruta del archivo PDF del que se desean obtener las palabras clave.
    * @return Las palabras clave del archivo PDF o una cadena vacía si no se encuentran palabras clave.
    * @throws IOException Si ocurre un error al cargar el archivo PDF.
    */
   public static String getPDFKeywords(String pdfFilePath) throws IOException {
       File pdfFile = new File(pdfFilePath);

       try (PDDocument document = PDDocument.load(pdfFile)) {
           String keywords = document.getDocumentInformation().getKeywords();
           return keywords != null ? keywords : "No tiene palabras claves";
       }
   }
   
   /**
    * Obtiene la versión de PDF de un archivo PDF.
    *
    * @param pdfFilePath La ruta del archivo PDF del que se desea obtener la versión de PDF.
    * @return La versión de PDF del archivo PDF o una cadena vacía si no se encuentra ninguna versión.
    * @throws IOException Si ocurre un error al cargar el archivo PDF.
    */
   public static String getPDFVersion(String pdfFilePath) throws IOException {
       File pdfFile = new File(pdfFilePath);

       try (PDDocument document = PDDocument.load(pdfFile)) {
           float pdfVersion = document.getDocument().getVersion();
           return String.valueOf(pdfVersion);
       }
   }
   
   /**
    * Obtiene la aplicación con la que fue creada un archivo PDF.
    *
    * @param pdfFilePath La ruta del archivo PDF del que se desea obtener la aplicación de creación.
    * @return La aplicación con la que fue creada el archivo PDF o una cadena vacía si no se encuentra información de la aplicación.
    * @throws IOException Si ocurre un error al cargar el archivo PDF.
    */
   public static String getPDFCreationApplication(String pdfFilePath) throws IOException {
       File pdfFile = new File(pdfFilePath);

       try (PDDocument document = PDDocument.load(pdfFile)) {
           String creator = document.getDocumentInformation().getCreator();
           return creator != null ? creator : "Aplicación desconocido";
       }
   }
   
     /**
    * Obtiene la fecha de creación de un archivo PDF.
    *
    * @param pdfFilePath La ruta al archivo PDF del cual deseas obtener la fecha de creación.
    * @return La fecha de creación del archivo PDF en formato de cadena o una cadena vacía si no se encuentra.
    */
   public static String getPDFCreationDate(String pdfFilePath) {
       try {
           File pdfFile = new File(pdfFilePath);
           Calendar fechaCreacion = null;

           try (PDDocument document = PDDocument.load(pdfFile)) {
               PDDocumentInformation info = document.getDocumentInformation();
               fechaCreacion = info.getCreationDate();
           }

           if (fechaCreacion != null) {
               SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
               return dateFormat.format(fechaCreacion.getTime()); // Obtén la fecha como un objeto Date y formatea eso.
           } else {
               return "No tiene fecha de creación";
           }
       } catch (IOException e) {
           e.printStackTrace();
           return "No tiene fecha de creación";
       }
   }

    /**
    * Obtiene la herramienta de creación del archivo PDF.
    *
    * @param pdfFilePath La ruta al archivo PDF del cual deseas obtener la herramienta de creación.
    * @return La herramienta de creación del archivo PDF o una cadena vacía si no se encuentra.
    */
   public static String getCreationToolPDF(String pdfFilePath) {
       try {
           File pdfFile = new File(pdfFilePath);
           String herramientaCreacion = null;

           try (PDDocument document = PDDocument.load(pdfFile)) {
               PDDocumentInformation info = document.getDocumentInformation();
               herramientaCreacion = info.getProducer();
           }

           if (herramientaCreacion != null && !herramientaCreacion.isEmpty()) {
               return herramientaCreacion;
           } else {
               return "Herramienta desconocida";
           }
       } catch (IOException e) {
           e.printStackTrace();
           return "Herramienta desconocida";
       }
   }
   
    /**
     * Obtiene el resumen de un archivo PDF.
     *
     * @param pdfFilePath La ruta al archivo PDF del que se desea extraer el resumen.
     * @return El resumen del PDF si se encuentra, o "No hay resumen" si no se encuentra.
     */
    public static String getSummaryFromPdf(String pdfFilePath) {
        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            PDFTextStripper textStripper = new PDFTextStripper();
            String pdfText = textStripper.getText(document);

            // Dividir el texto en líneas para facilitar el procesamiento
            String[] lines = pdfText.split(System.lineSeparator());

            boolean foundSummary = false;
            StringBuilder summary = new StringBuilder();

            for (String line : lines) {
                // Verifica si la línea contiene la palabra "Resumen" o "Summary"
                if (line.toLowerCase().contains("resumen") || line.toLowerCase().contains("summary")) {
                    foundSummary = true;
                    continue; // Salta la línea que contiene la palabra clave
                }

                // Si se encontró el resumen, agrega las líneas siguientes al resumen
                if (foundSummary) {
                    // Verifica si la línea está vacía (fin del resumen)
                    if (line.trim().isEmpty()) {
                        break;
                    }
                    summary.append(line).append(System.lineSeparator());
                }
            }

            if (summary.length() > 0) {
                return summary.toString().trim();
            } else {
                return "No hay resumen";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al procesar el archivo PDF: " + e.getMessage();
        }
    }
    
    /**
    * Obtiene fuentes de las imágenes en un archivo PDF.
    *
    * @param pdfFilePath La ruta al archivo PDF del que se desea extraer las fuentes.
    * @return Una lista de fuentes encontradas en el PDF o "No existen fuentes" si no se encuentran.
    */
   public static String getSourcesImagePdf(String pdfFilePath) {
       try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
           PDFTextStripper textStripper = new PDFTextStripper();
           String pdfText = textStripper.getText(document);

           String[] lines = pdfText.split(System.lineSeparator());

           StringBuilder sources = new StringBuilder();
           boolean foundSource = false;

           for (String line : lines) {
               if (line.toLowerCase().contains("fuente:")) {
                   foundSource = true;
                   sources.append(line).append(System.lineSeparator());
               } else if (foundSource) {
                   // Si ya se encontró una fuente, sigue agregando líneas hasta encontrar una en blanco
                   if (line.trim().isEmpty()) {
                       foundSource = false;
                   } else {
                       sources.append(line).append(System.lineSeparator());
                   }
               }
           }

           if (sources.length() > 0) {
               return sources.toString().trim();
           } else {
               return "No existen fuentes";
           }
       } catch (IOException e) {
           e.printStackTrace();
           return "Error al procesar el archivo PDF: " + e.getMessage();
       }
   }
    
    /**
    * Obtiene la cantidad de imágenes en un archivo PDF.
    *
    * @param pdfFilePath La ruta al archivo PDF del que se desea contar las imágenes.
    * @return El número de imágenes en el PDF o "No hay imágenes" si no se encuentran.
    */
   public static String getImagesInPdf(String pdfFilePath) {
       try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
           int imageCount = 0;

           for (PDPage page : document.getPages()) {
               PDResources resources = page.getResources();
               if (resources != null) {
                   for (COSName xObjectName : resources.getXObjectNames()) {
                       PDXObject xObject = resources.getXObject(xObjectName);
                       if (xObject instanceof PDImageXObject) {
                           imageCount++;
                       }
                   }
               }
           }

           if (imageCount > 0) {
               return "Número de imágenes: " + imageCount;
           } else {
               return "No hay imágenes";
           }
       } catch (IOException e) {
           e.printStackTrace();
           return "Error al procesar el archivo PDF: " + e.getMessage();
       }
   }
    
}

