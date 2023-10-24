/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import pdfmetadatareader.PDFFile;


public class PdfDetailWindow extends javax.swing.JFrame {
    private Main_window MainWindow;
    StyleButtonActionListener custom;
    private SimpleAttributeSet atributos;
    private PDFFile pdfFile;
    private String [] headJ1 = new String[]{"Nombre", "Autor","Asunto", "Titulo", "Palabras clave", "Fecha de creacion","Tipo de archivo"};
    private String [] headJ2 = new String[]{"Tamaño (Mb)", "Numero de paginas", "Tamaño de página", "Version pdf", "Aplicación de creación", "Imagenes", "Fuentes", "Herramienta de creación"};
    
    // Modelo inicial de la tabla superior para mejor manejo
    private DefaultTableModel modelT1 = new DefaultTableModel(){
            boolean[] canEdit = new boolean[]{false,false,false,false,false,false,false};
                @Override
            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }
        };
    // Modelo modificado de la tabla superior con la actualizacion de la funcion isCellEditable
    private DefaultTableModel modelT2 = new DefaultTableModel(){
            boolean[] canEdit = new boolean[]{false,false,false,false,false,false,false};
            @Override
            public boolean isCellEditable(int row, int column){
                return canEdit[column];
            }
    };
    // Modelo inicial de la tabla inferior para mejorar manejo
    private DefaultTableModel modelT1U = new DefaultTableModel(){
            boolean[] canEdit = new boolean[]{true,false,true,true,true,false,false};
                @Override
            public boolean isCellEditable(int row, int column) {
                return canEdit[column];
            }
        };
    
    //Modelo modificado de la tabla inferior con la actualizacion de la funcion isCellEditable
    private DefaultTableModel modelT2U = new DefaultTableModel(){
            boolean[] canEdit = new boolean[]{false,false,false,false,false,false,false};
            @Override
            public boolean isCellEditable(int row, int column){
                return canEdit[column];
            }
    };
    
    // Datos para llenar la tabla superior
    private Object takeDataJ1(){
        String nombre = pdfFile.getNombreArchivo();
        String autor = pdfFile.getAsunto();
        String asunto = pdfFile.getAsunto();
        String titulo = pdfFile.getTitulo();
        String palabrasClave = pdfFile.getPalabrasClave();
        String fechaCreacion = pdfFile.getFechaCreacion();
        String tipoArchivo = pdfFile.getTipoArchivoPDF();
        
        return new Object[]{nombre,autor,asunto,titulo,palabrasClave,fechaCreacion,tipoArchivo};
    }
    // Datos para llenar la tabla inferior
    private Object takeDataJ2(){
        double tamano = pdfFile.getTamanoArchivo();
        int numeroPaginas = pdfFile.getNumeroPaginas();
        String tamanoPaginas = pdfFile.getTamanoPagina();
        String version = pdfFile.getVersionPDF();
        String aplicacion = pdfFile.getAplicacionCreacion();
        String imagenes = pdfFile.getListaImagenes();
        String fuentes = pdfFile.getListaFuentes();
        String herramientas = pdfFile.getHerramientasCreacion();
        
        return new Object[]{tamano,numeroPaginas,tamanoPaginas,version,aplicacion,imagenes,fuentes,herramientas};
    }
    
    // Tabla inicial con datos restringidos
    private void initialTable(){  
        modelT1.setColumnIdentifiers(headJ1);
        Tabla1.setModel(modelT1);
        Tabla1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        modelT1.addRow((Object[]) takeDataJ1());
        modelT2.setColumnIdentifiers(headJ2);
        jTable2.setModel(modelT2);
        modelT2.addRow((Object[]) takeDataJ2());
        String resumen = pdfFile.getResumen();
        jTextPane1.setContentType("text/html"); // Establecer el tipo de contenido HTML
        jTextPane1.setText(pdfFile.getAnotaciones());
        jTextPane2.setText(resumen);
    }
    
    //Modelo con las opciones de modificaciones:
    private void editableEnable() throws BadLocationException{
        modelT1U.setColumnIdentifiers(headJ1);
        Tabla1.setModel(modelT1U);
        modelT1U.addRow((Object[]) takeDataJ1());
        modelT2U.setColumnIdentifiers(headJ2);
        jTable2.setModel(modelT2U);
        modelT2U.addRow((Object[]) takeDataJ2());
        String resumen = pdfFile.getResumen();
        jTextPane1.setContentType("text/html"); // Establecer el tipo de contenido HTML
        jTextPane1.setText(pdfFile.getAnotaciones());
        jTextPane2.setText(resumen);
        jTextPane1.setEditable(true);
        jTextPane2.setEditable(true);
    }
    
    public String obtenerTextoConFormato() throws BadLocationException {
        StyledDocument doc = jTextPane1.getStyledDocument();
        int length = doc.getLength();
        String texto = jTextPane1.getText();
        List<String> textoConEstilos = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            Element element = doc.getCharacterElement(i);
            AttributeSet atributos = element.getAttributes();
            String textoFragmento;

            // Verificar los estilos aplicados
            if (StyleConstants.isBold(atributos)) {
                textoFragmento = "<b>" + doc.getText(i, 1) + "</b>";
            } else if (StyleConstants.isItalic(atributos)) {
                textoFragmento = "<i>" + doc.getText(i, 1) + "</i>";
            } else if (StyleConstants.isUnderline(atributos)) {
                textoFragmento = "<u>" + doc.getText(i, 1) + "</u>";
            } else {
                textoFragmento = doc.getText(i, 1);
            }

            textoConEstilos.add(textoFragmento);
            }
        
        return String.join("", textoConEstilos);
    }
    
    // Funcion para guardar los datos modificados:
    private void saverDataNew() throws BadLocationException{
        Object nombre = modelT1U.getValueAt(0, 0);
        Object asunto = modelT1U.getValueAt(0,2);
        Object titulo = modelT1U.getValueAt(0,3);
        Object palabrasClave = modelT1U.getValueAt(0,4);
        String resumen = jTextPane2.getText();
        String anotaciones = obtenerTextoConFormato();
        pdfFile.setNombreArchivo((String) nombre);
        pdfFile.setAsunto((String) asunto);
        pdfFile.setTitulo((String) titulo);
        pdfFile.setPalabrasClave((String) palabrasClave);
        pdfFile.setResumen(resumen);
        pdfFile.setAnotaciones(anotaciones);
        JOptionPane.showMessageDialog(null, "Datos guardados con éxito", "Aviso", JOptionPane.DEFAULT_OPTION);
    }
    
    public PdfDetailWindow(Main_window MainWindow, PDFFile pdfFile) {
        initComponents();
        atributos = new SimpleAttributeSet();
        Button_bold.addActionListener(new StyleButtonActionListener(jTextPane1,"bold",atributos));
        Button_italic.addActionListener(new StyleButtonActionListener(jTextPane1, "italic", atributos));
        Button_subrayado.addActionListener(new StyleButtonActionListener(jTextPane1, "underline", atributos));
        this.MainWindow = MainWindow;
        this.pdfFile = pdfFile;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initialTable();
        jTextPane1.setEditable(false);
        jTextPane2.setEditable(false);
    }
    
    
    private PdfDetailWindow() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenuBar6 = new javax.swing.JMenuBar();
        jMenu11 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        tabla1 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        Button_bold = new javax.swing.JButton();
        Button_italic = new javax.swing.JButton();
        Button_subrayado = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem3.setText("jMenuItem3");

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        jMenu7.setText("File");
        jMenuBar4.add(jMenu7);

        jMenu8.setText("Edit");
        jMenuBar4.add(jMenu8);

        jMenuItem4.setText("jMenuItem4");

        jMenu9.setText("File");
        jMenuBar5.add(jMenu9);

        jMenu10.setText("Edit");
        jMenuBar5.add(jMenu10);

        jMenu11.setText("File");
        jMenuBar6.add(jMenu11);

        jMenu12.setText("Edit");
        jMenuBar6.add(jMenu12);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tabla1.setAutoscrolls(true);

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nombre ", "Asunto", "Autor", "Título", "Palabras Clave", "Fecha de creación", "Tipo de archivo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla1.getTableHeader().setReorderingAllowed(false);
        tabla1.setViewportView(Tabla1);
        if (Tabla1.getColumnModel().getColumnCount() > 0) {
            Tabla1.getColumnModel().getColumn(0).setHeaderValue("Nombre ");
            Tabla1.getColumnModel().getColumn(1).setHeaderValue("Asunto");
            Tabla1.getColumnModel().getColumn(2).setHeaderValue("Autor");
            Tabla1.getColumnModel().getColumn(3).setHeaderValue("Título");
            Tabla1.getColumnModel().getColumn(4).setHeaderValue("Palabras Clave");
            Tabla1.getColumnModel().getColumn(5).setHeaderValue("Fecha de creación");
            Tabla1.getColumnModel().getColumn(6).setHeaderValue("Tipo de archivo");
        }

        jLabel2.setText("Resumen");

        jScrollPane3.setViewportView(jTextPane2);

        jLabel3.setText("Anotaciones");

        jScrollPane4.setViewportView(jTextPane1);

        jScrollPane2.setAutoscrolls(true);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tamaño (Mb)", "Tamaño de página", "Version pdf", "Aplicación de creación", "Imagenes", "Fuentes", "Herramienta de creación"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2);

        Button_bold.setText("Negrita");
        Button_bold.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Button_boldMouseClicked(evt);
            }
        });
        Button_bold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_boldActionPerformed(evt);
            }
        });

        Button_italic.setText("Italica");
        Button_italic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_italicActionPerformed(evt);
            }
        });

        Button_subrayado.setText("Subrayado");
        Button_subrayado.setToolTipText("");

        jMenu1.setText("File");

        jMenuItem2.setText("Guardar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem5.setText("Editar");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabla1)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(237, 237, 237)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_bold)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_italic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Button_subrayado)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabla1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Button_italic, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(Button_bold)
                        .addComponent(Button_subrayado)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        MainWindow.getMiBoton().setEnabled(true);
    }//GEN-LAST:event_formWindowClosed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        try {
            saverDataNew();
        } catch (BadLocationException ex) {
            Logger.getLogger(PdfDetailWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        jMenuItem2.setEnabled(false);
        jMenuItem5.setEnabled(true);
        this.MainWindow.savePDFFileList();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void Button_boldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_boldActionPerformed
        
    }//GEN-LAST:event_Button_boldActionPerformed

    private void Button_boldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Button_boldMouseClicked

    }//GEN-LAST:event_Button_boldMouseClicked

    private void Button_italicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_italicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Button_italicActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        try {
            editableEnable();
        } catch (BadLocationException ex) {
            Logger.getLogger(PdfDetailWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        jMenuItem5.setEnabled(false);
        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_bold;
    private javax.swing.JButton Button_italic;
    private javax.swing.JButton Button_subrayado;
    private javax.swing.JTable Tabla1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuBar jMenuBar6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JScrollPane tabla1;
    // End of variables declaration//GEN-END:variables

}
