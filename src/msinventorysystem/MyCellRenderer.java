package msinventorysystem;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

public class MyCellRenderer implements ListCellRenderer {

    private JPanel p;
    private JPanel iconPanel;
    private JLabel l;
    private JTextArea ta;

    public MyCellRenderer() {
        p = new JPanel();
        p.setLayout(new BorderLayout());

        // icon
        iconPanel = new JPanel(new BorderLayout());
        ImageIcon ii = new ImageIcon("Skins/C_extras/restock.png");
        l = new JLabel(ii); // <-- this will be an icon instead of a
                                // text
        iconPanel.setOpaque(false);
        l .setOpaque(false);
        iconPanel.add(l, BorderLayout.NORTH);
        p.add(iconPanel, BorderLayout.WEST);

        // text
       Font myFont = new Font("Segoe UI", Font.PLAIN | Font.PLAIN, 15);
        ta = new JTextArea();
        ta.setFont(myFont);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        p.add(ta, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(final JList list,
            final Object value, final int index, final boolean isSelected,
            final boolean hasFocus) {

        ta.setText((String) value);
        int width = list.getWidth();
        // this is just to lure the ta's internal sizing mechanism into action
        if (width > 0)
            ta.setSize(width, Short.MAX_VALUE);
        return p;

    }
}
