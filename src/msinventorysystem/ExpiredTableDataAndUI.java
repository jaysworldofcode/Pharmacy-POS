package msinventorysystem;

import DataBase.SQLiteDB;
import DataBase.SQLiteDB_action;
import Frames.Product_info;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.table.TableColumn;

public class ExpiredTableDataAndUI extends JPanel{
    
    JTable data_tb;
    String[][] data={};    
    String column[]={"","ID","PRODUCT NAME","QUANITY","PRICE","DATE PURCHASED",
                     "EXPIRATION DATE","PURCHASED FROM","STRENGTH","PREPARATION"};
    SQLiteDB sdb = SQLiteDB.getInstance();
    
    JPanel TopPanel_ForSearchBar;
    JTextField SearchBar;
    
    public ExpiredTableDataAndUI(){
        setLayout(new BorderLayout());
        setBackground(Color.yellow);
        data_tb = new JTable(sdb.retrieveExpiredStockList(data),column);
        data_tb.setDefaultEditor(Object.class, null);
        JScrollPane table_scrollpane = new JScrollPane(data_tb);    
        add(table_scrollpane, BorderLayout.CENTER);
        
        TopPanel_ForSearchBar = new JPanel();
        TopPanel_ForSearchBar.setBackground(Color.red);
        add(TopPanel_ForSearchBar, BorderLayout.NORTH);
                
        SearchBar = new JTextField("Search....");
        SearchBar.setMinimumSize(new Dimension(999999,100));
        TopPanel_ForSearchBar.setLayout(new BoxLayout(TopPanel_ForSearchBar, BoxLayout.Y_AXIS));
        TopPanel_ForSearchBar.add(SearchBar);
        
        SearchBar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(SearchBar.getText().equals("Search...."))
                    SearchBar.setText(null);
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(SearchBar.getText().isEmpty())
                    SearchBar.setText("Search....");
            }
        });;
        
        //Set width for column
        TableColumn GetFirstColumn = data_tb.getColumnModel().getColumn(0);;
        TableColumn GetSecondColumn = data_tb.getColumnModel().getColumn(2);;
        GetFirstColumn.setPreferredWidth(1);
        GetSecondColumn.setMinWidth(200);
        
        for(int x=0;x<data_tb.getRowCount();x++)
            data_tb.setRowHeight(x, 40);
        
        JMenuItem viewInfo = new JMenuItem("View Info");
        viewInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = data_tb.getSelectedRow();
                selectedRow = data_tb.convertRowIndexToModel(selectedRow);                    
                String product_name = (String)data_tb.getModel().getValueAt(selectedRow, 1);
                String purchased_from = (String)data_tb.getModel().getValueAt(selectedRow, 7);
                String price = (String)data_tb.getModel().getValueAt(selectedRow, 4);
                String strength = (String)data_tb.getModel().getValueAt(selectedRow, 8);
                String brandname = (String)data_tb.getModel().getValueAt(selectedRow, 4);
                String preparation = (String)data_tb.getModel().getValueAt(selectedRow, 9);
                
                Product_info pf = new Product_info(product_name,purchased_from,price,strength,brandname,
                preparation);
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);
                pf.centreWindow(mf);
                pf.setVisible(true);
//                setVisible(false);
            }
        });
        
        final JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                setVisible(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        JMenuItem removeItem = new JMenuItem("Remove");
        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, 
                        "Are you sure to remove?", "", dialogButton);
                if(dialogResult == 0) {
                int selectedRow = data_tb.getSelectedRow();
                selectedRow = data_tb.convertRowIndexToModel(selectedRow);                    
                String id = (String)data_tb.getModel().getValueAt(selectedRow, 1);

                  SQLiteDB_action sdb = SQLiteDB_action.getInstance();
                  sdb.removeOutof_stock(id);
//                  setIcon();
                  setVisible(false);
                } else {
                }      
            }
        });
        
//        popupMenu.add(viewInfo);
        popupMenu.add(removeItem);
//        data_tb.setComponentPopupMenu(popupMenu);     }
    }
}