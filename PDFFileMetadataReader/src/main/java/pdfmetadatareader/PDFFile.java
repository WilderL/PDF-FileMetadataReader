package pdfmetadatareader;

import java.io.Serializable;

/**
 * Clase que representa la información de un archivo PDF.
 */
public class PDFFile implements Serializable{
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
    private String anotaciones;

    /**
     * Constructor de la clase PDFFile que inicializa los atributos con la información del archivo PDF.
     *
     * @param tamanoArchivo El tamaño del archivo en bytes.
     * @param tamanoPagina El tamaño de la página del PDF.
     * @param numeroPaginas El número de páginas en el PDF.
     * @param titulo El título del PDF.
     * @param autor El autor del PDF.
     * @param nombreArchivo El nombre del archivo PDF.
     * @param asunto El asunto del PDF.
     * @param palabrasClave Las palabras clave del PDF.
     * @param tipoArchivoPDF El tipo de archivo PDF.
     * @param versionPDF La versión del PDF.
     * @param aplicacionCreacion La aplicación que creó el PDF.
     * @param listaImagenes Una lista de imágenes en el PDF.
     * @param listaFuentes Una lista de fuentes en el PDF.
     * @param fechaCreacion La fecha de creación del PDF.
     * @param herramientasCreacion Las herramientas utilizadas en la creación del PDF.
     * @param resumen Un resumen del contenido del PDF.
     */
    public PDFFile(double tamanoArchivo, String tamanoPagina, int numeroPaginas, String titulo, 
            String autor, String nombreArchivo, String asunto, String palabrasClave, String tipoArchivoPDF, 
            String versionPDF, String aplicacionCreacion, String listaImagenes, String listaFuentes, 
            String fechaCreacion, String herramientasCreacion, String resumen) {    
        this.tamanoArchivo = tamanoArchivo;
        this.tamanoPagina = tamanoPagina;
        this.numeroPaginas = numeroPaginas;
        this.titulo = titulo;
        this.autor = autor;
        this.nombreArchivo = nombreArchivo;
        this.asunto = asunto;
        this.palabrasClave = palabrasClave;
        this.tipoArchivoPDF = tipoArchivoPDF;
        this.versionPDF = versionPDF;
        this.aplicacionCreacion = aplicacionCreacion;
        this.listaImagenes = listaImagenes;
        this.listaFuentes = listaFuentes;
        this.fechaCreacion = fechaCreacion;
        this.herramientasCreacion = herramientasCreacion;
        this.resumen = resumen;
        this.anotaciones = "No hay anotaciones";
    }
/**
     * Obtiene el tamaño del archivo en bytes.
     *
     * @return El tamaño del archivo en bytes.
     */
    public double getTamanoArchivo() {
        return tamanoArchivo;
    }

    /**
     * Establece el tamaño del archivo en bytes.
     *
     * @param tamanoArchivo El tamaño del archivo en bytes a establecer.
     */
    public void setTamanoArchivo(double tamanoArchivo) {
        this.tamanoArchivo = tamanoArchivo;
    }

    /**
     * Obtiene el tamaño de la página del PDF.
     *
     * @return El tamaño de la página del PDF.
     */
    public String getTamanoPagina() {
        return tamanoPagina;
    }

    /**
     * Establece el tamaño de la página del PDF.
     *
     * @param tamanoPagina El tamaño de la página del PDF a establecer.
     */
    public void setTamanoPagina(String tamanoPagina) {
        this.tamanoPagina = tamanoPagina;
    }

    /**
     * Obtiene el número de páginas en el PDF.
     *
     * @return El número de páginas en el PDF.
     */
    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    /**
     * Establece el número de páginas en el PDF.
     *
     * @param numeroPaginas El número de páginas en el PDF a establecer.
     */
    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    /**
     * Obtiene el título del PDF.
     *
     * @return El título del PDF.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del PDF.
     *
     * @param titulo El título del PDF a establecer.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene el autor del PDF.
     *
     * @return El autor del PDF.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Establece el autor del PDF.
     *
     * @param autor El autor del PDF a establecer.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * Obtiene el nombre del archivo PDF.
     *
     * @return El nombre del archivo PDF.
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * Establece el nombre del archivo PDF.
     *
     * @param nombreArchivo El nombre del archivo PDF a establecer.
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Obtiene el asunto del PDF.
     *
     * @return El asunto del PDF.
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * Establece el asunto del PDF.
     *
     * @param asunto El asunto del PDF a establecer.
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * Obtiene las palabras clave del PDF.
     *
     * @return Las palabras clave del PDF.
     */
    public String getPalabrasClave() {
        return palabrasClave;
    }

    /**
     * Establece las palabras clave del PDF.
     *
     * @param palabrasClave Las palabras clave del PDF a establecer.
     */
    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    /**
     * Obtiene el tipo de archivo PDF.
     *
     * @return El tipo de archivo PDF.
     */
    public String getTipoArchivoPDF() {
        return tipoArchivoPDF;
    }

    /**
     * Establece el tipo de archivo PDF.
     *
     * @param tipoArchivoPDF El tipo de archivo PDF a establecer.
     */
    public void setTipoArchivoPDF(String tipoArchivoPDF) {
        this.tipoArchivoPDF = tipoArchivoPDF;
    }

    /**
     * Obtiene la versión del PDF.
     *
     * @return La versión del PDF.
     */
    public String getVersionPDF() {
        return versionPDF;
    }

    /**
     * Establece la versión del PDF.
     *
     * @param versionPDF La versión del PDF a establecer.
     */
    public void setVersionPDF(String versionPDF) {
        this.versionPDF = versionPDF;
    }

    /**
     * Obtiene la aplicación que creó el PDF.
     *
     * @return La aplicación que creó el PDF.
     */
    public String getAplicacionCreacion() {
        return aplicacionCreacion;
    }

    /**
     * Establece la aplicación que creó el PDF.
     *
     * @param aplicacionCreacion La aplicación que creó el PDF a establecer.
     */
    public void setAplicacionCreacion(String aplicacionCreacion) {
        this.aplicacionCreacion = aplicacionCreacion;
    }

    /**
     * Obtiene una lista de imágenes en el PDF.
     *
     * @return Una lista de imágenes en el PDF.
     */
    public String getListaImagenes() {
        return listaImagenes;
    }

    /**
     * Establece una lista de imágenes en el PDF.
     *
     * @param listaImagenes Una lista de imágenes en el PDF a establecer.
     */
    public void setListaImagenes(String listaImagenes) {
        this.listaImagenes = listaImagenes;
    }

    /**
     * Obtiene una lista de fuentes en el PDF.
     *
     * @return Una lista de fuentes en el PDF.
     */
    public String getListaFuentes() {
        return listaFuentes;
    }

    /**
     * Establece una lista de fuentes en el PDF.
     *
     * @param listaFuentes Una lista de fuentes en el PDF a establecer.
     */
    public void setListaFuentes(String listaFuentes) {
        this.listaFuentes = listaFuentes;
    }

    /**
     * Obtiene la fecha de creación del PDF.
     *
     * @return La fecha de creación del PDF.
     */
    public String getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de creación del PDF.
     *
     * @param fechaCreacion La fecha de creación del PDF a establecer.
     */
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene las herramientas utilizadas en la creación del PDF.
     *
     * @return Las herramientas utilizadas en la creación del PDF.
     */
    public String getHerramientasCreacion() {
        return herramientasCreacion;
    }

    /**
     * Establece las herramientas utilizadas en la creación del PDF.
     *
     * @param herramientasCreacion Las herramientas utilizadas en la creación del PDF a establecer.
     */
    public void setHerramientasCreacion(String herramientasCreacion) {
        this.herramientasCreacion = herramientasCreacion;
    }

    /**
     * Obtiene un resumen del contenido del PDF.
     *
     * @return Un resumen del contenido del PDF.
     */
    public String getResumen() {
        return resumen;
    }

    /**
     * Establece un resumen del contenido del PDF.
     *
     * @param resumen Un resumen del contenido del PDF a establecer.
     */
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    /**
     * Obtiene la información de las anotaciones en el PDF.
     *
     * @return Las anotaciones en el PDF.
     */
    public String getAnotaciones() {
        return anotaciones;
    }

    /**
     * Establece las anotaciones en el PDF.
     *
     * @param anotaciones Las anotaciones a establecer en el PDF.
     */
    public void setAnotaciones(String anotaciones) {
        this.anotaciones = anotaciones;
    }
}
