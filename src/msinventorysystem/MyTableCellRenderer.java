package msinventorysystem;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class MyTableCellRenderer extends JLabel implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (table.getModel().getValueAt(row, 7).equals("false")) {
            setForeground(Color.BLACK);
        } else {
            setForeground(Color.GREEN);
        }
        setText(String.valueOf(value));
        return this;
    }
}
