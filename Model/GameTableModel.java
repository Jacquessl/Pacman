package Model;

import javax.swing.table.AbstractTableModel;

public class GameTableModel extends AbstractTableModel {
    int[][] mapa;
    public GameTableModel(int[][] mapa){
        this.mapa = mapa;
    }

    @Override
    public int getRowCount() {
        return mapa.length;
    }

    @Override
    public int getColumnCount() {
        return mapa[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return mapa[rowIndex][columnIndex];
    }
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    @Override
    public void setValueAt(Object value, int row, int column){
        if(value.getClass().equals(Integer.class)) {
            mapa[row][column] = (int) value;
            fireTableDataChanged();
        }
    }
}
