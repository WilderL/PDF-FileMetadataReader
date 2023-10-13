package pdfmetadatareader;

import java.util.ArrayList;
import java.util.Date;


public class PDFFile {
    private long tamanoArchivo;
    private long tamanoPagina;
    private int numeroPaginas;
    private String titulo;
    private String asunto;
    private ArrayList<String> palabrasClave;
    private String tipoArchivoPDF;
    private String versionPDF;
    private String aplicacionCreacion;
    private ArrayList<String> listaImagenes;
    private ArrayList<String> listaFuentes;
    private Date fechaCreacion;
    private String herramientasCreacion;
    private String resumen;
    private String anotaciones;

    public PDFFile(long tamanoArchivo, long tamanoPagina, int numeroPaginas, String titulo, String asunto,
                        ArrayList<String> palabrasClave, String tipoArchivoPDF, String versionPDF, String aplicacionCreacion,
                        ArrayList<String> listaImagenes, ArrayList<String> listaFuentes, Date fechaCreacion, String herramientasCreacion,
                        String resumen) {
        this.tamanoArchivo = tamanoArchivo;
        this.tamanoPagina = tamanoPagina;
        this.numeroPaginas = numeroPaginas;
        this.titulo = titulo;
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
        this.anotaciones = null;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(String anotaciones) {
        this.anotaciones = anotaciones;
    }
    
    public long getTamanoPagina() {
        return tamanoPagina;
    }

    public void setTamanoPagina(long tamanoPagina) {
        this.tamanoPagina = tamanoPagina;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public ArrayList<String> getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(ArrayList<String> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public String getTipoArchivoPDF() {
        return tipoArchivoPDF;
    }

    public void setTipoArchivoPDF(String tipoArchivoPDF) {
        this.tipoArchivoPDF = tipoArchivoPDF;
    }

    public String getVersionPDF() {
        return versionPDF;
    }

    public void setVersionPDF(String versionPDF) {
        this.versionPDF = versionPDF;
    }

    public String getAplicacionCreacion() {
        return aplicacionCreacion;
    }

    public void setAplicacionCreacion(String aplicacionCreacion) {
        this.aplicacionCreacion = aplicacionCreacion;
    }

    public ArrayList<String> getListaImagenes() {
        return listaImagenes;
    }

    public void setListaImagenes(ArrayList<String> listaImagenes) {
        this.listaImagenes = listaImagenes;
    }

    public ArrayList<String> getListaFuentes() {
        return listaFuentes;
    }

    public void setListaFuentes(ArrayList<String> listaFuentes) {
        this.listaFuentes = listaFuentes;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getHerramientasCreacion() {
        return herramientasCreacion;
    }

    public void setHerramientasCreacion(String herramientasCreacion) {
        this.herramientasCreacion = herramientasCreacion;
    }
    
    public long getTamanoArchivo() {
        return tamanoArchivo;
    }

    public void setTamanoArchivo(long tamanoArchivo) {
        this.tamanoArchivo = tamanoArchivo;
    }

    public void agregarPalabraClave(String palabraClave) {
        palabrasClave.add(palabraClave);
    }
    

    @Override
    public String toString() {
        return "DocumentoPDF{" +
                "tamanoArchivo=" + tamanoArchivo +
                ", tamanoPagina=" + tamanoPagina +
                ", numeroPaginas=" + numeroPaginas +
                ", titulo='" + titulo + '\'' +
                ", asunto='" + asunto + '\'' +
                ", palabrasClave=" + palabrasClave +
                ", tipoArchivoPDF='" + tipoArchivoPDF + '\'' +
                ", versionPDF='" + versionPDF + '\'' +
                ", aplicacionCreacion='" + aplicacionCreacion + '\'' +
                ", listaImagenes=" + listaImagenes +
                ", listaFuentes=" + listaFuentes +
                ", fechaCreacion=" + fechaCreacion +
                ", herramientasCreacion='" + herramientasCreacion + '\'' +
                '}';
    }
}
