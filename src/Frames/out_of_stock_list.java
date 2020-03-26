package Frames;

import DataBase.SQLiteDB_action;
import DataHolder.DataHolder;
import Panels.List_Panel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import msinventorysystem.MainFrame;
import msinventorysystem.VisitorRenderer;

public class out_of_stock_list extends JPanel {
    
    JList setList;
    DefaultTableModel  model;
    JTable jtb;
    JScrollPane jsp;
    DataHolder dh = DataHolder.getInstance();
    String setProduct_name = "";
    private static out_of_stock_list instance = null;
    
    public static out_of_stock_list getInstance() {
      if(instance == null) {
         instance = new out_of_stock_list();
         System.out.println("/* Out of stock list */");
      }
      return instance;
    }
    void setTable(){
            model = new DefaultTableModel()
    {
      @Override
      public boolean isCellEditable(int row, int column)
      {
        return false;
      }
      public Class getColumnClass(int column)
      {
        return getValueAt(0, column).getClass();
      }
      };
            
        Object[] columnName = new Object[11];
        columnName[0] = "";
        columnName[1] = "Id";
        columnName[2] = "TYPE";
        columnName[3] = "PRODUCT_CODE";
        columnName[4] = "PRODUCT_NUMBER";
        columnName[5] = "BRAND_NAME";
        columnName[6] = "PRICE";
        columnName[7] = "STRENGTH";
        columnName[8] = "PURCHASED_FROM";
        columnName[9] = "DISK_CHECKER";
        columnName[10] = "PREPARATION";
        model.setColumnIdentifiers(columnName);
        jtb.setModel(model);
        jtb.getColumnModel().getColumn(0).setResizable(false);
        jtb.setAutoCreateRowSorter(true);
        jtb.setIntercellSpacing(new Dimension(0, 0));
        jtb.getTableHeader().setOpaque(false);
        jtb.getTableHeader().setBackground(new Color(0,0,0,0.6f));
        jtb.getTableHeader().setForeground(Color.white);   
        jtb.setShowGrid(false);
        jtb.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        jtb.setFont(new Font("Arial", Font.PLAIN, 15));
        jtb.setRowHeight(30);
        jtb.getColumn("").setMaxWidth(50);
        jtb.setTableHeader(null);
        jtb.setOpaque(false);

        jtb.removeColumn(jtb.getColumnModel().getColumn(2));
        jtb.removeColumn(jtb.getColumnModel().getColumn(1));
        jtb.removeColumn(jtb.getColumnModel().getColumn(2));
        jtb.removeColumn(jtb.getColumnModel().getColumn(2));
        jtb.removeColumn(jtb.getColumnModel().getColumn(2));
        jtb.removeColumn(jtb.getColumnModel().getColumn(2));
        jtb.removeColumn(jtb.getColumnModel().getColumn(2));
        jtb.removeColumn(jtb.getColumnModel().getColumn(2));
        jtb.removeColumn(jtb.getColumnModel().getColumn(2));
        
        int height = jtb.getRowHeight();
        jtb.setRowHeight(height+10);
        jtb.setShowGrid(false); 
        
//    filterTable();
    }
    public void removeAll_data(){
                int rowCount = model.getRowCount();
                //Remove all data row
                    for (int i = rowCount - 1; i >= 0; i--) {
                        model.removeRow(i);
                    }
    }
    public void setRow(String id, String type, String product_number, String brand_name, String price,
            String strength, String purchased_from, String disk_checker,String preparation){
        
            String setLoc = "";
            
        if(type.equals("out_of_stock")){
            setLoc = "Skins/C_extras/out_of_stock.png";
        }else{
            setLoc = "Skins/C_extras/expired.png";
        }
        model.addRow(new Object[]{new ImageIcon(setLoc),id,type,
            product_number+"("+brand_name+")",product_number,
            brand_name,price,strength,purchased_from,
            disk_checker,preparation});
    }
    public void setIcon(){
            boolean view = false;
            int rowcount = jtb.getModel().getRowCount();
            for(int i = 0;i<rowcount;i++){
            String set = (String)jtb.getModel().getValueAt(i, 8);
                if (set.equals("true")) {
                    view = true;
                }
            }
            
            if(view == true){
                List_Panel lp = List_Panel.getInstance();
                lp.setIcon("new");
            }else{
                List_Panel lp = List_Panel.getInstance();
                lp.setIcon(""+rowcount);
            }
    }
    public void setScanner(String addedProduct){
            int rowcount = jtb.getModel().getRowCount();
            for(int i = 0;i<rowcount;i++){
            String set = (String)jtb.getModel().getValueAt(i, 3);
                if ( addedProduct.toLowerCase().indexOf(set.toLowerCase()) != -1 ) {
                    setRemove(set);
                }
            }
    }
   public void setRemove(String product_name){
        SQLiteDB_action sdb = SQLiteDB_action.getInstance();
         sdb.removeOutof_stock(product_name);
    }
   public DefaultTableModel getModel(){
       return model;
   }
   public JList getComboBox(){
       return setList;
   }
   public void setVisibility(boolean set){
       if(set = true){
           setVisible(true);
       }else{
           setVisible(false);
       }
   }
    public boolean isMouseWithinComponent()
    {
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        Rectangle bounds = jsp.getBounds();
        bounds.setLocation(jsp.getLocationOnScreen());
        return bounds.contains(mousePos);
    }
    out_of_stock_list(){
        this.setLayout(null);
        this.setOpaque(false); // background of parent will be painted first
//        this.setBackground( new Color(255, 0, 0, 20) );

        model = new DefaultTableModel();
        jtb = new JTable(model)
    {
            @Override
        public Component prepareRenderer(TableCellRenderer renderer,int row,int column)
        {
            Component comp=super.prepareRenderer(renderer,row, column);
           int modelRow=convertRowIndexToModel(row);
               comp.setBackground(Color.WHITE);
               comp.setForeground(Color.BLACK);
           return comp;
        }
    };
        jtb.setDefaultRenderer(String.class, new VisitorRenderer());
        jsp = new JScrollPane(jtb);
        jsp.setBounds(12,20,290,110);
        jsp.setBorder(createEmptyBorder());
        JScrollBar xb_xx = jsp.getVerticalScrollBar();
        jsp.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        xb_xx.setUI(new Skins.LookAndFeel.MyScrollbarUI());
        jsp.setBorder(createEmptyBorder());
        jsp.getViewport().setOpaque(false);
        jsp.setOpaque(false);
        this.add(jsp);
    jsp.getViewport().getView().addMouseListener(new MouseListener() {

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
        setVisible(false);
    }
        });
        
        setTable();
        
        this.addMouseListener(new MouseListener() {

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
        setVisible(false);
    }
        });
        jtb.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            if(SwingUtilities.isRightMouseButton(e) == true)
            {
            int row = jtb.rowAtPoint(e.getPoint());

            jtb.clearSelection();
            jtb.addRowSelectionInterval(row,row);
            //your popup menu goes here.
            }
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
                setVisible(false);
            }
        });
//        jsp.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//            }
//            @Override
//            public void mousePressed(MouseEvent e) {
//            }
//            @Override
//            public void mouseReleased(MouseEvent e) {
//            }
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                setVisible(true);
//            }
//            @Override
//            public void mouseExited(MouseEvent e) {
//                setVisible(false);
//            }
//        });
        ImageIcon II = new ImageIcon("Skins/C_background/OUT_OF_STOCK.png");
        JLabel setBackground = new JLabel(II);
        setBackground.setBounds(0,0,320,160);
        this.add(setBackground);
//        setLogoChecker();
         setPop_up_menu();
    }
    void setPop_up_menu(){
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
        JMenuItem viewInfo = new JMenuItem("View Info");
        viewInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = jtb.getSelectedRow();
                 selectedRow = jtb.convertRowIndexToModel(selectedRow);                    
                String product_name = (String)jtb.getModel().getValueAt(selectedRow, 3);
                String purchased_from = (String)jtb.getModel().getValueAt(selectedRow, 8);
                String price = (String)jtb.getModel().getValueAt(selectedRow, 6);
                String strength = (String)jtb.getModel().getValueAt(selectedRow, 7);
                String brandname = (String)jtb.getModel().getValueAt(selectedRow, 5);
                String preparation = (String)jtb.getModel().getValueAt(selectedRow, 10);
                
                Product_info pf = new Product_info(product_name,purchased_from,price,strength,brandname,
                preparation);
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);
                pf.centreWindow(mf);
                pf.setVisible(true);
//                setVisible(false);
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
                int selectedRow = jtb.getSelectedRow();
                selectedRow = jtb.convertRowIndexToModel(selectedRow);                    
                String id = (String)jtb.getModel().getValueAt(selectedRow, 1);

                  SQLiteDB_action sdb = SQLiteDB_action.getInstance();
                  sdb.removeOutof_stock(id);
                  setIcon();
                  setVisible(false);
                } else {
                }      
            }
        });
        popupMenu.add(viewInfo);
        popupMenu.add(removeItem);
        jtb.setComponentPopupMenu(popupMenu);
        
jtb.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed (MouseEvent e) {
        int r = jtb.rowAtPoint(e.getPoint());
        if (r >= 0 && r < jtb.getRowCount()) {
            jtb.setRowSelectionInterval(r, r);
        } else {
            jtb.clearSelection();
        }

        int rowindex = jtb.getSelectedRow();
        if (rowindex < 0)
            return;
        if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) {
            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
});
    }
        public void centreWindow(Window frame) {
        int x = frame.getX();
        int y = frame.getY();
                List_Panel lp = List_Panel.getInstance();
        if(dh.getUsert_type().equals("admin")){
                this.setBounds(lp.getButtons().getY()+160, lp.getButtons().getX()-50,320,160);
        }else{
                this.setBounds(lp.getButtons().getY()+50, lp.getButtons().getX()+45,320,160);
        }
    }
}