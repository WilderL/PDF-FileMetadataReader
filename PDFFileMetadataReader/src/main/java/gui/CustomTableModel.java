
package gui;

import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel {

    private boolean allowEditing = true; 

    public CustomTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    public void setAllowEditing(boolean allowEditing) {
        this.allowEditing = allowEditing;
        fireTableDataChanged(); // Notificar a la tabla sobre el cambio en la edición
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Permite o evita la edición basándose en el estado de allowEditing
        return allowEditing;
    }
}
