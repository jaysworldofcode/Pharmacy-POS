package Panels;

import DataBase.DataBase;
import DataBase.SQLiteDB;
import DataBase.SQLiteDB_action;
import DataHolder.DataHolder;
import Frames.AddCart;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.*;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import msinventorysystem.DirectPrint;
import msinventorysystem.MainFrame;
import msinventorysystem.MenuSettingsPopUpItem;
import msinventorysystem.MyTableCellRenderer;
import msinventorysystem.myDecimalConverter;

public class Sale_Panel extends JPanel{

    myDecimalConverter mDc = new myDecimalConverter();
    
    JCheckBox DISCOUNT_CB, FREE_CB;
    DefaultTableModel PRODUCT_MODEL,CART_LIST_MODEL;
    JPanel PRODUCT_LIST,CART_LIST,PRODUCT_INFO,TOTAL;
    JLabel TOTAL_UNITS,TOTAL_QUANTITY,TOTAL_JL,SEARCH_JL;
    JButton CLEAR_ALL,REMOVE,UPDATE,PAY,ADD,BACK,DELETE_SEARCH,PRINTER_SETTINGS;
    JTable PRODUCT_LIST_TABLE  = new JTable(),CART_LIST_TABLE;
    JScrollPane PRODUCT_LIST_PANE,CART_LIST_PANE;
    JTextField SEARCH_PRODUCT_LIST,DISCOUNT_TF;
    //PRODUCT INFO LABELS
    JLabel PRODUCT_NAME,QUANTITY_LEFT,EXPIRATION,PRICE,PREPARATION,ORIGINAL_PRICE,
           UNIT,PERCENT;
    JLabel PRODUCT_NAMEx,QUANTITY_LEFTx,EXPIRATIONx,PRICEx,PREPARATIONx,ORIGINAL_PRICEx,
            UNITx;
    JPopupMenu CART_LIST_POP_UP = new JPopupMenu();
    JMenuItem REMOVE_MENU,UPDATE_MENU;

    ArrayList<String> discount_tokenizer = new ArrayList<String>();
    
    static String setID ="";

    static String discount_checker = "true";
    final JPopupMenu printer_list = new JPopupMenu();
    ArrayList<String> productName = new ArrayList<String>();
    ArrayList<Double> productPrice = new ArrayList<Double>();
    ArrayList<Integer> productQuantity = new ArrayList<Integer>();
    static String printData="";
    static double totalPayment=0;
    static float CASH = 0;
    static float CHANGE = 0;
    static float setAll_total =0;
    static int setAll_quantity =0;
    static int setAll_units =0;
    //SET CLASSES
    DataHolder dh = DataHolder.getInstance();
    DataHolder dh2 = new DataHolder();
    private static Sale_Panel instance = null;    

    public static Sale_Panel getInstance() {
      if(instance == null) {
         instance = new Sale_Panel();
      }
      System.out.println("/* Sale panel class */");
      return instance;
    }
    
    public Sale_Panel(){
        this.setBounds(0,100,1100,600);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setVisible(false);
        Organizer();
    }
    void Organizer(){
        Components();
        Listener();
        set_Table_info();
        set_Cart_List_info();
        transanctionID();
        setTotal();
//        dh.setTable();
    }
    public void checkBoxUnusable(){
        if(PRODUCT_NAMEx.getText().equals("----")){
            DISCOUNT_CB.setEnabled(false);
            FREE_CB.setEnabled(false);
        }else{
            DISCOUNT_CB.setEnabled(true);
            FREE_CB.setEnabled(true);
        }
    }
    void Components(){
        
        Font SETFONT = new Font("SansSerif", Font.BOLD, 17);
        Font SETFONTx = new Font("SansSerif", Font.PLAIN, 17);
        
        BACK = new JButton("BACK");
        BACK.setBounds(10,10,70,50);
        BACK.setOpaque(false);
        BACK.setCursor(new Cursor(Cursor.HAND_CURSOR));
        BACK.setContentAreaFilled(false);
        BACK.setFocusable(false);
        BACK.setBorderPainted(false);
//        this.add(BACK);

        PRODUCT_LIST = new JPanel();
        PRODUCT_LIST.setBounds(10,10,420,250);
        PRODUCT_LIST.setLayout(null);
        PRODUCT_LIST.setBackground(Color.WHITE);
        this.add(PRODUCT_LIST);
        
        CART_LIST = new JPanel();
        CART_LIST.setBounds(10,270,1080,220);
        CART_LIST.setLayout(null);
        CART_LIST.setBackground(Color.WHITE);
        this.add(CART_LIST);
        
        PRODUCT_INFO = new JPanel();
        PRODUCT_INFO.setBounds(440,10,650,270);
        PRODUCT_INFO.setLayout(null);
        PRODUCT_INFO.setOpaque(false);
        this.add(PRODUCT_INFO);
        
        ImageIcon ADD_IIx = new ImageIcon("Skins/C_buttons/ADD_SALE.png");
        ADD = new JButton(ADD_IIx);
        ADD.setBounds(350,210,260,45);
        ADD.setFont(SETFONT);
        ADD.setOpaque(false);
        ADD.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ADD.setContentAreaFilled(false);
        ADD.setFocusable(false);
        ADD.setBorderPainted(false);
        PRODUCT_INFO.add(ADD);
        
        //PRODUCT INFO COMPONENTS
        PRODUCT_NAME = new JLabel("Product name");
        PRODUCT_NAME.setBounds(10,0,150,25);
        PRODUCT_NAME.setForeground(Color.DARK_GRAY);
        PRODUCT_NAME.setFont(SETFONT);
        PRODUCT_INFO.add(PRODUCT_NAME);
        
        PRODUCT_NAMEx = new JLabel("----");
        PRODUCT_NAMEx.setBounds(150,0,500,25);
        PRODUCT_NAMEx.setFont(SETFONTx);
        PRODUCT_INFO.add(PRODUCT_NAMEx);
                
        QUANTITY_LEFT = new JLabel("Quantity left");
        QUANTITY_LEFT.setBounds(10,210,100,25);
        QUANTITY_LEFT.setForeground(Color.DARK_GRAY);
        QUANTITY_LEFT.setFont(SETFONT);
        PRODUCT_INFO.add(QUANTITY_LEFT);
        
        QUANTITY_LEFTx = new JLabel("----");
        QUANTITY_LEFTx.setBounds(150,210,200,25);
        QUANTITY_LEFTx.setFont(SETFONTx);
        PRODUCT_INFO.add(QUANTITY_LEFTx);
        
        EXPIRATION = new JLabel("Expiration date");
        EXPIRATION.setBounds(10,105,150,25);
        EXPIRATION.setForeground(Color.DARK_GRAY);
        EXPIRATION.setFont(SETFONT);
        PRODUCT_INFO.add(EXPIRATION);
        
        EXPIRATIONx = new JLabel("----");
        EXPIRATIONx.setBounds(150,105,100,25);
        EXPIRATIONx.setFont(SETFONTx);
        PRODUCT_INFO.add(EXPIRATIONx);
        
        PRICE = new JLabel("Selling price");
        PRICE.setBounds(10,175,150,25);
        PRICE.setForeground(Color.DARK_GRAY);
        PRICE.setFont(SETFONT);
        PRODUCT_INFO.add(PRICE);
        
        PRICEx = new JLabel("----");
        PRICEx.setBounds(150,175,100,25);
        PRICEx.setFont(SETFONTx);
        PRODUCT_INFO.add(PRICEx);
        
        PREPARATION = new JLabel("Preparation");
        PREPARATION.setBounds(10,70,150,25);
        PREPARATION.setForeground(Color.DARK_GRAY);
        PREPARATION.setFont(SETFONT);
        PRODUCT_INFO.add(PREPARATION);

        DISCOUNT_CB = new JCheckBox("Discounted",false);
        DISCOUNT_CB.setBounds(350,145,100,25);
        DISCOUNT_CB.setOpaque(false);
        DISCOUNT_CB.setBorderPainted(false);
        DISCOUNT_CB.setFocusable(false);
        PRODUCT_INFO.add(DISCOUNT_CB);

        DISCOUNT_TF = new JTextField();
        DISCOUNT_TF.setBounds(455,145,50,25);
        DISCOUNT_TF.setEditable(false);
        PRODUCT_INFO.add(DISCOUNT_TF);
        
        PERCENT = new JLabel("%");
        PERCENT.setBounds(510,145,50,25);
        PRODUCT_INFO.add(PERCENT);
        
        FREE_CB = new JCheckBox("FREE");
        FREE_CB.setBounds(350,175,100,25);
        FREE_CB.setOpaque(false);
        FREE_CB.setBorderPainted(false);
        FREE_CB.setFocusable(false);
        PRODUCT_INFO.add(FREE_CB);       
        
        PREPARATIONx = new JLabel("----");
        PREPARATIONx.setBounds(150,70,100,25);
        PREPARATIONx.setFont(SETFONTx);
        PRODUCT_INFO.add(PREPARATIONx);
        
        ORIGINAL_PRICE = new JLabel("Unit price");
        ORIGINAL_PRICE.setBounds(10,140,150,25);
        ORIGINAL_PRICE.setForeground(Color.DARK_GRAY);
        ORIGINAL_PRICE.setFont(SETFONT);
        PRODUCT_INFO.add(ORIGINAL_PRICE);
        
        ORIGINAL_PRICEx = new JLabel("----");
        ORIGINAL_PRICEx.setBounds(150,140,100,25);
        ORIGINAL_PRICEx.setFont(SETFONTx);
        PRODUCT_INFO.add(ORIGINAL_PRICEx);
        
        UNIT = new JLabel("Strength");
        UNIT.setBounds(10,35,150,25);
        UNIT.setForeground(Color.DARK_GRAY);
        UNIT.setFont(SETFONT);
        PRODUCT_INFO.add(UNIT);
        
        UNITx = new JLabel("----");
        UNITx.setBounds(150,35,100,25);
        UNITx.setFont(SETFONTx);
        PRODUCT_INFO.add(UNITx);

        TOTAL_QUANTITY = new JLabel("Total Quantity -----");
        TOTAL_QUANTITY.setBounds(490,497,500,25);
        TOTAL_QUANTITY.setForeground(Color.DARK_GRAY);
        TOTAL_QUANTITY.setFont(SETFONT);
//        this.add(TOTAL_QUANTITY);
        
        TOTAL = new JPanel();
        TOTAL.setBounds(10,495,450,100);
//        TOTAL.setOpaque(false);
        TOTAL.setBackground(Color.BLACK);
        TOTAL.setBorder(BorderFactory.createEtchedBorder(1));
        this.add(TOTAL);
        
        TOTAL_JL = new JLabel("-----");
//        TOTAL_JL.setFont(SETFONT);
        TOTAL_JL.setForeground(Color.WHITE);
        TOTAL_JL.setFont(new Font("Tahoma", Font.BOLD, 65));
        TOTAL.add(TOTAL_JL);
        
        ImageIcon CLEAR_ALL_II = new ImageIcon("Skins/C_buttons/CLEAR_ALL_1_BUTTON.png");
        CLEAR_ALL = new JButton(CLEAR_ALL_II);
        CLEAR_ALL.setBounds(790,530,120,50);
        CLEAR_ALL.setOpaque(false);
        CLEAR_ALL.setCursor(new Cursor(Cursor.HAND_CURSOR));
        CLEAR_ALL.setContentAreaFilled(false);
        CLEAR_ALL.setFocusable(false);
        CLEAR_ALL.setBorderPainted(false);
        this.add(CLEAR_ALL);
        
        ImageIcon REMOVE_II = new ImageIcon("Skins/C_buttons/REMOVE_1_BUTTON.png");
        REMOVE = new JButton(REMOVE_II);
        REMOVE.setBounds(920,530,120,50);
        REMOVE.setOpaque(false);
        REMOVE.setCursor(new Cursor(Cursor.HAND_CURSOR));
        REMOVE.setContentAreaFilled(false);
        REMOVE.setFocusable(false);
        REMOVE.setBorderPainted(false);
        this.add(REMOVE);
        
        PRINTER_SETTINGS = new JButton(new ImageIcon("Skins/C_buttons/print_sale_1.png"));
        PRINTER_SETTINGS.setBounds(1035,530,50,55);
        PRINTER_SETTINGS.setToolTipText("Choose printer to use");
        PRINTER_SETTINGS.setOpaque(false);
        PRINTER_SETTINGS.setCursor(new Cursor(Cursor.HAND_CURSOR));
        PRINTER_SETTINGS.setContentAreaFilled(false);
        PRINTER_SETTINGS.setFocusable(false);
        PRINTER_SETTINGS.setBorderPainted(false);
//        this.add(PRINTER_SETTINGS);
                
        JMenuItem choose_printer = new JMenuItem("Choose printer to use");
        choose_printer.setEnabled(false);
        printer_list.add(choose_printer);
        
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Number of print services: " + printServices.length);

        int incrementID = 0;
        
        for (PrintService printer : printServices){
            JMenuItem newMenuItem = new MenuSettingsPopUpItem(incrementID,printer.getName());
            printer_list.add(newMenuItem);
            incrementID++;
        }
                
        ImageIcon UPDATE_II = new ImageIcon("Skins/C_buttons/UPDATE_1_BUTTON.png");
        UPDATE = new JButton(UPDATE_II);
        UPDATE.setBounds(680,510,120,50);
        UPDATE.setOpaque(false);
        UPDATE.setCursor(new Cursor(Cursor.HAND_CURSOR));
        UPDATE.setContentAreaFilled(false);
        UPDATE.setFocusable(false);
        UPDATE.setBorderPainted(false);
//        this.add(UPDATE);
        
        ImageIcon PAY_II = new ImageIcon("Skins/C_buttons/PAY_1_BUTTON.png");
        PAY = new JButton(PAY_II);
        PAY.setBounds(480,530,300,50);
        PAY.setFont(SETFONT);
        PAY.setOpaque(false);
        PAY.setCursor(new Cursor(Cursor.HAND_CURSOR));
        PAY.setContentAreaFilled(false);
        PAY.setFocusable(false);
        PAY.setBorderPainted(false);
        this.add(PAY);
        
        ImageIcon ADD_II = new ImageIcon(
//                "Skins/C_buttons/ADD_1_BUTTON.png"
        );
        
//        PRODUCT_LIST_TABLE = new JTable();
//        PRODUCT_LIST_TABLE{
//
//            //Implement table cell tool tips.           
//            public String getToolTipText(MouseEvent e) {
//                String tip = null;
//                java.awt.Point p = e.getPoint();
//                int rowIndex = rowAtPoint(p);
//                int colIndex = columnAtPoint(p);
//
//                try {
//                    tip = getValueAt(rowIndex, colIndex).toString();
//                } catch (RuntimeException e1) {
//                    //catch null pointer exception if mouse is over an empty line
//                }
//
//                return tip;
//                }
//            };
            
        PRODUCT_LIST_PANE = new JScrollPane(PRODUCT_LIST_TABLE);
        PRODUCT_LIST_PANE.setBounds(0,40,420,200);
        PRODUCT_LIST_PANE.setBorder(createEmptyBorder());
        PRODUCT_LIST_PANE.getViewport().setBackground(Color.WHITE);
//        PRODUCT_LIST_TABLE.setOpaque(false);
        PRODUCT_LIST_PANE.setBackground(Color.WHITE);
        PRODUCT_LIST_TABLE.setBackground(Color.WHITE);
        PRODUCT_LIST.add(PRODUCT_LIST_PANE);
        
//        ImageIcon SEARCH_II = new ImageIcon("Skins/C_extras/SEARCH.png");
//        SEARCH_JL = new JLabel(SEARCH_II);
//        SEARCH_JL.setBounds(165,12,   25,25);
//        PRODUCT_LIST.add(SEARCH_JL);
        
        ImageIcon DELETE_SEARCH_II = new ImageIcon("Skins/C_buttons/DELETE_SEARCH.png");
        DELETE_SEARCH = new JButton(DELETE_SEARCH_II);
        DELETE_SEARCH.setBounds(314,7,25,25);
        DELETE_SEARCH.setOpaque(false);
        DELETE_SEARCH.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DELETE_SEARCH.setContentAreaFilled(false);
        DELETE_SEARCH.setFocusable(false);
        DELETE_SEARCH.setBorderPainted(false);
        DELETE_SEARCH.setVisible(false);
        PRODUCT_LIST.add(DELETE_SEARCH);
        
        SEARCH_PRODUCT_LIST = new JTextField("Search");
        SEARCH_PRODUCT_LIST.setBounds(40,7,275,25);
        SEARCH_PRODUCT_LIST.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        SEARCH_PRODUCT_LIST.setOpaque(false);
        SEARCH_PRODUCT_LIST.setForeground(Color.GRAY);
        PRODUCT_LIST.add(SEARCH_PRODUCT_LIST);
        
        CART_LIST_TABLE = new JTable(){

            //Implement table cell tool tips.           
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);

                try {
                    tip = getValueAt(rowIndex, colIndex).toString();
                } catch (RuntimeException e1) {
                    //catch null pointer exception if mouse is over an empty line
                }

                return tip;
            }
          };
        
        CART_LIST_PANE = new JScrollPane(CART_LIST_TABLE);
        CART_LIST_PANE.setBounds(4,20,1072,190);
        CART_LIST_PANE.setBorder(createEmptyBorder());
        CART_LIST_PANE.getViewport().setOpaque(false);
        CART_LIST_PANE.setOpaque(false);
        CART_LIST.add(CART_LIST_PANE);
        
        REMOVE_MENU = new JMenuItem("REMOVE");
        CART_LIST_POP_UP.add(REMOVE_MENU);
        
        UPDATE_MENU = new JMenuItem("UPDATE");
//        CART_LIST_POP_UP.add(UPDATE_MENU);
        
        CART_LIST_TABLE.setComponentPopupMenu(CART_LIST_POP_UP);        
        
        //BG's here
        ImageIcon PRODUCT_LIST_II = new ImageIcon("Skins/C_background/PRODUCT_LIST_SALE.png");
        JLabel PRODUCT_LIST_BG = new JLabel(PRODUCT_LIST_II);
        PRODUCT_LIST_BG.setBounds(0,0,350,250);
        PRODUCT_LIST.add(PRODUCT_LIST_BG);
        
        this.setBackground(Color.WHITE);
//        ImageIcon PANEL_II = new ImageIcon("Skins/C_background/PANELS_BG.png");
//        JLabel PANELS_BG = new JLabel(PANEL_II);
//        PANELS_BG.setBounds(0,0,900,600);
//        this.add(PANELS_BG);
    }
    void set_Cart_List_info(){
            CART_LIST_MODEL = new DefaultTableModel()
    {
      @Override
      public boolean isCellEditable(int row, int column)
      {
        return false;
      }
    };

        Object[] columnName = new Object[11];
        columnName[0] = "Id";
        columnName[1] = "Product Name";
        columnName[2] = "Preparation";
        columnName[3] = "Strength";
        columnName[4] = "Quantity";
        columnName[5] = "Price";
        columnName[6] = "Ext. Price";
        columnName[7] = "Discount checker";
        columnName[8] = "Original price";
        columnName[9] = "Product id";
        columnName[10] = "Brand name";
 
        CART_LIST_MODEL.setColumnIdentifiers(columnName);
        CART_LIST_TABLE.setModel(CART_LIST_MODEL);
        CART_LIST_TABLE.getColumnModel().getColumn(0).setResizable(false);
        CART_LIST_TABLE.setAutoCreateRowSorter(true);
        CART_LIST_TABLE.setIntercellSpacing(new Dimension(0, 0));
//        CART_LIST_TABLE.getTableHeader().setOpaque(false);
//        CART_LIST_TABLE.getTableHeader().setBackground(new Color(0,0,0,0.6f));
//        CART_LIST_TABLE.getTableHeader().setForeground(Color.white);   
        CART_LIST_TABLE.setShowGrid(false);
        CART_LIST_TABLE.setFont(new Font("Arial", Font.PLAIN, 14));
        CART_LIST_TABLE.setRowHeight(30);
        CART_LIST_TABLE.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        CART_LIST_TABLE.getColumn("Id").setMaxWidth(60);
        CART_LIST_TABLE.getColumn("Quantity").setMaxWidth(100);
        CART_LIST_TABLE.getColumn("Price").setMaxWidth(100);
        CART_LIST_TABLE.getColumn("Ext. Price").setMaxWidth(100);
//        CART_LIST_TABLE.getColumn("Preparation").setMaxWidth(500);
        CART_LIST_TABLE.removeColumn(CART_LIST_TABLE.getColumnModel().getColumn(7));
        CART_LIST_TABLE.removeColumn(CART_LIST_TABLE.getColumnModel().getColumn(7));
        CART_LIST_TABLE.removeColumn(CART_LIST_TABLE.getColumnModel().getColumn(7));
        CART_LIST_TABLE.removeColumn(CART_LIST_TABLE.getColumnModel().getColumn(7));
        CART_LIST_TABLE.removeColumn(CART_LIST_TABLE.getColumnModel().getColumn(0));
//        CART_LIST_TABLE.removeColumn(CART_LIST_TABLE.getColumnModel().getColumn(7));
//        CART_LIST_TABLE.removeColumn(CART_LIST_TABLE.getColumnModel().getColumn(0));
        
        filter_cart_table();       
    MyTableCellRenderer renderer = new MyTableCellRenderer();
    CART_LIST_TABLE.getColumnModel().getColumn(4).setCellRenderer(renderer);
    }
    void transanctionID(){
        Random ran = new Random();
        int x = ran.nextInt(1500);
        if(dh.getTransaction().contains(x)){
            transanctionID();
        }else{
            setID = "100"+x;
        }
    }
    public void filter_cart_table(){
                int rowCount = CART_LIST_MODEL.getRowCount();
                //Remove all data row
                    for (int i = rowCount - 1; i >= 0; i--) {
                        CART_LIST_MODEL.removeRow(i);
                    }
                    
//        columnName[0] = "Id";
//        columnName[1] = "Product Name";
//        columnName[2] = "Preparation";
//        columnName[3] = "Strength";
//        columnName[4] = "Quantity";
//        columnName[5] = "Price";
//        columnName[6] = "Ext. Price";
//        columnName[7] = "Discount checker";
//        columnName[8] = "Original price";
//        columnName[9] = "Product id";
//        columnName[10] = "Brand name";
    for(int x=0;x<dh.getCart_size();x++){
        CART_LIST_MODEL.addRow(new Object[]{dh.getCart_id(x),
                                          dh.getCart_product_name(x),
                                          dh.getCart_preparation(x),
                                          dh.getCart_strength(x),
                                          dh.getCart_quantity(x),
                                          dh.getCart_selling_price(x),
                                          dh.getCart_total(x),
                                          dh.getProduct_checker(x),
                                          dh.getCart_price(x),
                                          dh.getCart_product_id(x),
                                          dh.getCart_brand_name(x)
        });
    }
                CART_LIST_TABLE.invalidate();
                CART_LIST_TABLE.repaint();
    }
    public void set_Table_info(){
            PRODUCT_MODEL = new DefaultTableModel()
    {
      @Override
      public boolean isCellEditable(int row, int column)
      {
        return false;
      }
    };
        Object[] columnName = new Object[3];
        columnName[0] = "Id";
        columnName[1] = "Product Name";
        columnName[2] = "Brand Name";

        PRODUCT_MODEL.setColumnIdentifiers(columnName);
        PRODUCT_LIST_TABLE.setModel(PRODUCT_MODEL);
        PRODUCT_LIST_TABLE.getColumnModel().getColumn(0).setResizable(false);
        PRODUCT_LIST_TABLE.setAutoCreateRowSorter(true);
        PRODUCT_LIST_TABLE.setIntercellSpacing(new Dimension(0, 0));
//        PRODUCT_LIST_TABLE.getTableHeader().setOpaque(false);
//        PRODUCT_LIST_TABLE.getTableHeader().setBackground(new Color(0,0,0,0.6f));
//        PRODUCT_LIST_TABLE.getTableHeader().setForeground(Color.white);   
        PRODUCT_LIST_TABLE.setFont(new Font("Arial", Font.PLAIN, 14));
        PRODUCT_LIST_TABLE.setRowHeight(30);
        PRODUCT_LIST_TABLE.setShowGrid(false);
        PRODUCT_LIST_TABLE.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        PRODUCT_LIST_TABLE.getColumn("Id").setMaxWidth(60);
        PRODUCT_LIST_TABLE.removeColumn(PRODUCT_LIST_TABLE.getColumnModel().getColumn(0));
        filterTable();
            }
    public void searc_Product_List_table(String query){
        TableRowSorter<DefaultTableModel> df = new TableRowSorter<DefaultTableModel>(PRODUCT_MODEL);
        PRODUCT_LIST_TABLE.setRowSorter(df);
        df.setRowFilter(RowFilter.regexFilter(query));
    }
    public void filterTable(){
                int rowCount = PRODUCT_MODEL.getRowCount();
                //Remove all data row
                    for (int i = rowCount - 1; i >= 0; i--) {
                        PRODUCT_MODEL.removeRow(i);
                    }
                    
    for(int x=0;x<dh.retrieveProduct_size();x++){
        String setMy_quantity = dh.retieveQuantity(x);
        int convert_quantity = Integer.parseInt(setMy_quantity);
        if(convert_quantity<=0){
        }else{
            PRODUCT_MODEL.addRow(new Object[]{dh.retrieveID(x),dh.retrieveProduct_name(x),
                dh.retrieveBrand_name(x)
            });
        }
    }
                PRODUCT_LIST.invalidate();
                PRODUCT_LIST.repaint();
    }
    static String brand_name = null;
    void product_List_info_setText(String product_name,String quantity,
            String expiration,String price,String preparation,String original_price,
            String unit, String brand_name){
        PRODUCT_NAMEx.setText(product_name);
        QUANTITY_LEFTx.setText(quantity);
        EXPIRATIONx.setText(expiration);
        
                    String setPrice = "";
            
            if(price.substring(0, 1).equals(".")){
                setPrice = "0"+price;
                setPrice = String.format("%.2f", Double.parseDouble(setPrice));
            }else{
                setPrice = price;
                setPrice = String.format("%.2f", Double.parseDouble(setPrice));
            }

        PRICEx.setText(setPrice);
        PREPARATIONx.setText(preparation);
        
                    String setOriginalPrice = "";
            
            if(original_price.substring(0, 1).equals(".")){
                setOriginalPrice = "0"+original_price;
                setOriginalPrice = String.format("%.2f", Double.parseDouble(setOriginalPrice));
            }else{
                setOriginalPrice = original_price;
                setOriginalPrice = String.format("%.2f", Double.parseDouble(setOriginalPrice));
            }
        ORIGINAL_PRICEx.setText(setOriginalPrice);
        UNITx.setText(unit);    
        System.out.println("Unit is: "+unit);
        this.brand_name =  brand_name;
    }
    public void setTotal(){
        setAll_total = 0;
        setAll_quantity = 0;
        int total=0;
        int quantity=0;
        //SET TOTAL
        for (int i = 0; i < CART_LIST_MODEL.getRowCount(); i++){
            double xsetAll_total = 0;
            if( CART_LIST_MODEL.getValueAt(i, 6).toString().equalsIgnoreCase("Free")){
                xsetAll_total = 0;
            }else{
            xsetAll_total =Double.parseDouble( 
                CART_LIST_MODEL.getValueAt(i, 6).toString() 
            );
            }
            setAll_total = (float) (setAll_total+xsetAll_total);
        }
        //SET QUANTITY TOTAL
        for (int i = 0; i < CART_LIST_MODEL.getRowCount(); i++){
            if(CART_LIST_MODEL.getValueAt(i, 5).toString().equalsIgnoreCase("Free")){
            }else{
            setAll_quantity +=Integer.parseInt(
                CART_LIST_MODEL.getValueAt(i, 4).toString() 
            );
            }
        }
        //SET TOTAL UNITS
        for (int i = 0; i < CART_LIST_MODEL.getRowCount(); i++){
            setAll_units++;
        }
        TOTAL_JL.setText(mDc.roundOffTo2DecPlaces(setAll_total)+"");
        TOTAL_QUANTITY.setText("Total Quantity: "+setAll_quantity);
    }
    public void scan_cartList(){
            for(int x=0;x<dh.getCartSize();x++){
                PRODUCT_MODEL.removeRow(dh.setName(dh.setName_id(x)));
            }
    }
         static JTable itemsTable;
         public static  int total_item_count=0;
         public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss a";
         public  static String title[] = new String[] {"QTY ","ITEM","Price","Extended Price"};
	
public static void setItems(Object[][] printitem){
        Object data[][]=printitem;
        DefaultTableModel model = new DefaultTableModel();
       //assume jtable has 4 columns.
        model.addColumn(title[0]);
        model.addColumn(title[1]);
        model.addColumn(title[2]);
        model.addColumn(title[3]);
        

        int rowcount=printitem.length;
        
        addtomodel(model, data, rowcount);
       

        itemsTable = new JTable(model);
}

public void addCart(String myQuantity){
                setAll_total =0;
                setAll_quantity =0;
                setAll_units =0;

                int selectedRow = PRODUCT_LIST_TABLE.getSelectedRow();
                selectedRow = PRODUCT_LIST_TABLE.convertRowIndexToModel(selectedRow);                    
                String id = (String)PRODUCT_LIST_TABLE.getModel().getValueAt(selectedRow, 0);

//                JFrame frame = new JFrame("InputDialog Example #1");
//                String name = JOptionPane.showInputDialog(frame, "Enter quantity");
                
                SQLiteDB_action sa = SQLiteDB_action.getInstance();
                
                float total = Float.parseFloat(myQuantity);
                int quantities_left = Integer.parseInt(QUANTITY_LEFTx.getText());
                double totalIncrease = 0;
                double setTotal_increase =0;
                
//                if (name.equals(JOptionPane.NO_OPTION)) {
//                }else if (name .equals(JOptionPane.CANCEL_OPTION)) {
//                }else {
                    
                    if(total>quantities_left){
                        JOptionPane.showMessageDialog(null,"Not enough quantities left");
                    }else{
                    String sellingPrice = "";
                    double setSellingprice = 0;
                    String setAll = "";
                    String finalSelling_price = "";
                    String setter = "";
                    
                    dh.subtractQuantity(id, total+"");
                    
                    if(DISCOUNT_CB.isSelected()){
                        double  parsePrice_discount = Double.parseDouble(DISCOUNT_TF.getText());
                        double setSellingPrice = Double.parseDouble(PRICEx.getText());
                        double originalPrice = Double.parseDouble(ORIGINAL_PRICEx.getText());
                        
                        double setTotal_decrease = setSellingPrice-originalPrice;
                        double getTotal = (double)(setTotal_decrease*(parsePrice_discount/100.0f));
                        
                        setSellingprice = setSellingPrice-getTotal;
                        finalSelling_price = setSellingprice+"";
                        float setResult = (float) (setSellingprice*total);
                        sellingPrice = setResult+"";
                        setAll = setSellingprice+"";
 
                        float setSelling_price = Float.parseFloat(sellingPrice);
                        setter = mDc.roundOffTo2DecPlaces(setSelling_price);
                    }else{
                        if(FREE_CB.isSelected()){
//                            setAll = "Free";
//                            sellingPrice = 0+"";
//                            finalSelling_price = "Free";

                            //My new code for free changes
                            double price = Double.parseDouble(PRICEx.getText());
                            finalSelling_price = price+"";
                            sellingPrice = "Free";
                            setter = sellingPrice;
                        }else{
                            double price = Double.parseDouble(PRICEx.getText());
                            finalSelling_price = price+"";
                            float setTotal = (float) (price*total);
                            sellingPrice = setTotal+"";
                            setAll = sellingPrice+"";
                            
                             float setSelling_price = Float.parseFloat(sellingPrice);
                             setter = mDc.roundOffTo2DecPlaces(setSelling_price);
                            //My new code for free changes
                        }
                    }
                    float setFinal_selling_price = Float.parseFloat(finalSelling_price);
                    
                    if(DISCOUNT_CB.isSelected()){
                        float parseSelling_price = Float.parseFloat(PRICEx.getText());
                        discount_checker = mDc.roundOffTo2DecPlaces(parseSelling_price-setFinal_selling_price)+" "+
                                DISCOUNT_TF.getText();
                    }else{
                        discount_checker = "false";
                    }
                    
                String set_productID = (String)PRODUCT_LIST_TABLE.getModel().getValueAt(selectedRow, 0);

                
//        ArrayList<String> View_product_data = new ArrayList<String>();
//        ArrayList<String> View_product_id = new ArrayList<String>();
//        for (int i = 0; i < CART_LIST_MODEL.getRowCount(); i++){
//            String getCart_product_name = CART_LIST_MODEL.getValueAt(i, 1).toString();
//            
//            View_product_data.add(CART_LIST_MODEL.getValueAt(i, 1).toString());
//            View_product_id.add(CART_LIST_MODEL.getValueAt(i, 0).toString());
//        }
//        
//        if(View_product_data.contains(PRODUCT_NAMEx.getText()) &&
//                FREE_CB.isSelected()!=true && DISCOUNT_CB.isSelected() !=true){
//            
//        }else{
//                sa.addCart_data(PRODUCT_NAMEx.getText().toString(), 
//                                    name,
//                                    mDc.roundOffTo2DecPlaces(setFinal_selling_price)+"",
//                                    PREPARATIONx.getText().toString(), 
//                                    setter,id,ORIGINAL_PRICEx.getText(),
//                                    discount_checker,set_productID,UNITx.getText(),
//                                    brand_name);
//        }
                
                dh.addCart_data(
                        dh.getCart_size()+1,
                        PRODUCT_NAMEx.getText().toString(),
                        myQuantity,
                        mDc.roundOffTo2DecPlaces(setFinal_selling_price),
                        PREPARATIONx.getText(),
                        setter,
                        set_productID,
                        ORIGINAL_PRICEx.getText(),
                        discount_checker,
                        UNITx.getText(),
                        brand_name
                );
//                dh.clear_all_cartData();
//                SQLiteDB retrieve = SQLiteDB.getInstance();
//                retrieve.retrieveData();
                filter_cart_table();
                setTotal();
//                PRODUCT_MODEL.removeRow(selectedRow);
                //Set text
                PRODUCT_NAMEx.setText("----");
                QUANTITY_LEFTx.setText("----");
                EXPIRATIONx.setText("----");
                PRICEx.setText("----");
                PREPARATIONx.setText("----");
                ORIGINAL_PRICEx.setText("----");
                UNITx.setText("----");
                DISCOUNT_CB.setSelected(false);
                FREE_CB.setSelected(false);
                DISCOUNT_TF.setText("");
                DISCOUNT_TF.setEditable(false);
//                List_Panel lp = List_Panel.getInstance();
//                lp.filterTable();
                    }
//                }
                PRODUCT_LIST_TABLE.getSelectionModel().clearSelection();
                PREPARATIONx.setText("----");
}
public static void addtomodel(DefaultTableModel model,Object [][]data,int rowcount){
        int count=0;
        while(count < rowcount){
         model.addRow(data[count]);
         count++;
        }
        if(model.getRowCount()!=rowcount)
          addtomodel(model, data, rowcount);
}
          
public Object[][] getTableData (JTable table) {
    int itemcount=table.getRowCount();
    
    DefaultTableModel dtm = (DefaultTableModel) table.getModel();
    int nRow = dtm.getRowCount(), nCol =dtm.getColumnCount();
    Object[][] tableData = new Object[nRow][nCol];
    if(itemcount==nRow)                                        //check is there any data loss.
    {
    for (int i = 0 ; i < nRow ; i++){
        for (int j = 0 ; j < nCol ; j++){
            tableData[i][j] = dtm.getValueAt(i,j);           //pass data into object array.
            }}
     if(tableData.length!=itemcount){                      //check for data losses in object array
     getTableData(table);                                  //recursively call method back to collect data
     }   
    }
    else{
                                                           //collecting data again because of data loss.
   getTableData(table);
   }
   return tableData;                                       //return object array with data.
    }     

public static PageFormat getPageFormat(PrinterJob pj){
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();    
             
                double middleHeight =total_item_count*1.0;  //dynamic----->change with the row count of jtable
                double headerHeight = 5.0;                  //fixed----->but can be mod
        	double footerHeight = 5.0;                  //fixed----->but can be mod
                
                double width = convert_CM_To_PPI(7);      //printer know only point per inch.default value is 72ppi
        	double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
            paper.setSize(width, height);
            paper.setImageableArea(
                            convert_CM_To_PPI(0.25), 
                            convert_CM_To_PPI(0.5), 
                            width - convert_CM_To_PPI(0.35), 
                            height - convert_CM_To_PPI(1));   //define boarder size    after that print area width is about 180 points
            
            pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
            pf.setPaper(paper);    
            
            return pf;
}
void addSales_cart_list(){
//    int setReturn = 0;
//    
//    if(DISCOUNT_CB.isSelected()){
//        for (int row = 0; row < CART_LIST_MODEL.getRowCount(); row++){
//                if(PRICEx.getText().equals(CART_LIST_MODEL.getValueAt(row, 4).toString())){
//
//                }else{
//                    System.out.println("False");
//                }
//        }
//    }else if(FREE_CB.isSelected()){
//        
//    }else if(){
//        
//    }
}
protected static double convert_CM_To_PPI(double cm) {            
	        return toPPI(cm * 0.393600787);            
}

protected static double toPPI(double inch) {            
	        return inch * 72d;            
}

public static String now() {
//get current date and time as a String output   
Calendar cal = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
return sdf.format(cal.getTime());

}
static DataHolder dhx = DataHolder.getInstance();
//                    String t = ;
public void printData(String transactionNumber, String date,String cashier){
    System.out.println("Print data!");
    DirectPrint dp = new DirectPrint();
	for(int i = 0;i < CART_LIST_TABLE.getRowCount(); i++){
            
                          String quantity = CART_LIST_TABLE.getModel().getValueAt(i, 4).toString();
                          String itemname = CART_LIST_TABLE.getModel().getValueAt(i, 1).toString();
                          String price = CART_LIST_TABLE.getModel().getValueAt(i, 5).toString();
                          String ext_price =CART_LIST_TABLE.getModel().getValueAt(i, 6).toString();
                          String discount_price =CART_LIST_TABLE.getModel().getValueAt(i, 7).toString();
                          String brand_name =CART_LIST_TABLE.getModel().getValueAt(i, 10).toString();
                          String strength =CART_LIST_TABLE.getModel().getValueAt(i, 3).toString();
                          String preparation =CART_LIST_TABLE.getModel().getValueAt(i, 2).toString();
                          
                                      String set = totalPayment+"";

                                      if(!discount_price.equalsIgnoreCase("false")){
                                         ext_price = ext_price+"*";
                                     }
//    public void setDetails(String transactionNumber, String date, String cashier){
                 dp.setDetails(transactionNumber,date,cashier,set);
                 dp.addData(quantity,itemname,price,ext_price,
                         preparation);           
                 dp.printString();
   }
}
//    void dataChecker(){
//        int set = 0;
//        for (int row = 0; row < CART_LIST_MODEL.getRowCount(); row++){
//                if(PRODUCT_NAMEx.getText().equals(CART_LIST_MODEL.getValueAt(row, 1).toString())
//                && CART_LIST_MODEL.getValueAt(row, 5).toString().equals("Free")
//                && CART_LIST_MODEL.getValueAt(row, 4).toString().equals(PRICEx.getText())){
//
//                }else{
//                    System.out.println("False");
//                }
//        }
//    }
    void Listener(){
        PRINTER_SETTINGS.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                printer_list.show(PRINTER_SETTINGS,0,0);
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                PRINTER_SETTINGS.setIcon(new ImageIcon("Skins/C_buttons/print_sale_2.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                PRINTER_SETTINGS.setIcon(new ImageIcon("Skins/C_buttons/print_sale_1.png"));
            }
        });
        DISCOUNT_TF.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (DISCOUNT_TF.getText().length() >= 3){ // limit to 3 characters
                    e.consume();
                }
                if(!DISCOUNT_TF.getText().equals("")){
                    int set = Integer.parseInt(DISCOUNT_TF.getText());
                    if( set > 100)
                        e.consume();
                    }                
            }
            public void keyReleased(KeyEvent ke){
                String data=DISCOUNT_TF.getText();
                try{
                    int val=Integer.parseInt(data);
                    if(val>100){
                      DISCOUNT_TF.setText("100");    
                    }
                }
                catch(Exception e){}
            }
        });
        DELETE_SEARCH.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        SEARCH_PRODUCT_LIST.setText(null);
                        DELETE_SEARCH.setVisible(false);
                        searc_Product_List_table("");              
            }
        });
        DISCOUNT_TF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    dh.frameSetVisibleFalse();
                    MainFrame mf = MainFrame.getInstance();
                    mf.setFront(false);
                    
                    AddCart a = AddCart.getInstance();
                    a.centreWindow(mf);
                    a.setVisible(true);
                }
             if(SEARCH_PRODUCT_LIST.getText().equals("Search")){
                    DELETE_SEARCH.setVisible(false);
                }else{
                    DELETE_SEARCH.setVisible(true);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        SEARCH_PRODUCT_LIST.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                if(SEARCH_PRODUCT_LIST.getText().equals("Search")){
                    DELETE_SEARCH.setVisible(false);
                }else{
                    DELETE_SEARCH.setVisible(true);
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    dh.frameSetVisibleFalse();
                    MainFrame mf = MainFrame.getInstance();
                    mf.setFront(false);
                    
                    AddCart a = AddCart.getInstance();
                    a.centreWindow(mf);
                    a.setVisible(true);
                }
             if(SEARCH_PRODUCT_LIST.getText().equals("Search")){
                    DELETE_SEARCH.setVisible(false);
                }else{
                    DELETE_SEARCH.setVisible(true);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if(SEARCH_PRODUCT_LIST.getText().equals("Search")){
                    DELETE_SEARCH.setVisible(false);
                }else{
                    DELETE_SEARCH.setVisible(true);
                }
            }
        });
        DISCOUNT_TF.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char vChar = e.getKeyChar();
                    if (!(Character.isDigit(vChar)
                            || (vChar == KeyEvent.VK_BACK_SPACE)
                            || (vChar == KeyEvent.VK_DELETE))) {
                        e.consume();
                    }
                }
            });
        
    FREE_CB.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(FREE_CB.isSelected()){
             DISCOUNT_CB.setSelected(false);
             DISCOUNT_TF.setEditable(false);
             DISCOUNT_TF.setText(null);
            }
        }
    });
    DISCOUNT_CB.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
         if (DISCOUNT_CB.isSelected()) {
             DISCOUNT_TF.setEditable(true);
             FREE_CB.setSelected(false);
         }else{
             DISCOUNT_TF.setEditable(false);
             DISCOUNT_TF.setText(null);
        }
        }
    });
        BACK.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                SecondMainPanel s = SecondMainPanel.getInstance();
                s.setVisible(true);
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        ADD.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                if(PRODUCT_NAMEx.getText().equals("----")){
                    JOptionPane.showMessageDialog(null,"No product selected!");
                }else{
                    dh.frameSetVisibleFalse();
                    MainFrame mf = MainFrame.getInstance();
                    mf.setFront(false);
                    
                    AddCart a = AddCart.getInstance();
                    a.centreWindow(mf);
                    a.setVisible(true);
//                int rows = CART_LIST_TABLE.getRowCount();
//                for(int x=0;x<dh.getCart_size();x++){
//                String set = CART_LIST_TABLE.getModel().getValueAt(x, 7).toString();
//                }
//	              for(int i = 0;i < CART_LIST_TABLE.getRowCount(); i++){
//	                	/*Assume that all parameters are in string data type for this situation
//                                 * All other premetive data types are accepted.
//                                */
//
//                          String discount_price =CART_LIST_TABLE.getModel().getValueAt(i, 7).toString();
//                          
//                          System.out.println("Discount price checker is: "+discount_price);
//                      }
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
//                ImageIcon ADD_II = new ImageIcon("Skins/C_buttons/ADD_2_BUTTON.png");
//                ADD.setIcon(ADD_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        PAY.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setCash_out();
                List_Panel lp = List_Panel.getInstance();
                lp.clearType();
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
//                ImageIcon PAY_II = new ImageIcon("Skins/C_buttons/PAY_2_BUTTON.png");
//                PAY.setIcon(PAY_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
//                ImageIcon PAY_II = new ImageIcon("Skins/C_buttons/PAY_1_BUTTON.png");
//                PAY.setIcon(PAY_II);
            }
        });
        UPDATE_MENU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = CART_LIST_TABLE.getSelectedRow();
                selectedRow = CART_LIST_TABLE.convertRowIndexToModel(selectedRow);                    
                String produce_name = (String)CART_LIST_TABLE.getModel().getValueAt(selectedRow, 1);
                String product_price = (String)CART_LIST_TABLE.getModel().getValueAt(selectedRow, 3);

                JFrame frame = new JFrame("InputDialog Example #1");
                String name = JOptionPane.showInputDialog(frame, "Enter quantity");
                
                int total = Integer.parseInt(name);
                int setID = Integer.parseInt(CART_LIST_TABLE.getModel().getValueAt(selectedRow, 0).toString());
                
                if (name.equals(JOptionPane.NO_OPTION)) {
                }
                else if (name .equals(JOptionPane.CANCEL_OPTION)) {
                }else{
                SQLiteDB_action uq = SQLiteDB_action.getInstance();
                uq.updateQuantity(setID,name,dh.setName(produce_name)+"",product_price);
                dh.clear_all_cartData();
                SQLiteDB retrieve = SQLiteDB.getInstance();
                retrieve.retrieveData();
                filter_cart_table();
                setTotal();
                filterTable();
                }
            }
        });
        CLEAR_ALL.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearCart();
                filterTable();
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon CLEAR_ALL_II = new ImageIcon("Skins/C_buttons/"
                        + "CLEAR_ALL_2_BUTTON.png");
                CLEAR_ALL.setIcon(CLEAR_ALL_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon CLEAR_ALL_II = new ImageIcon("Skins/C_buttons/"
                        + "CLEAR_ALL_1_BUTTON.png");
                CLEAR_ALL.setIcon(CLEAR_ALL_II);
            }
        });
        REMOVE_MENU.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
        try {
            int rowcheck = CART_LIST_TABLE.getSelectedRow();
            if (rowcheck > -1) {
        int rows = CART_LIST_TABLE.getRowCount();
                if(rows == 0){
                    JOptionPane.showMessageDialog(null,"Cart list is empty.");
                }else{
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, 
                        "Are you sure to remove?", "", dialogButton);
                if(dialogResult == 0) {
//                    setAll_total =0;
//                    setAll_quantity =0;
//                    setAll_units =0;
//
//                int selectedRow = CART_LIST_TABLE.getSelectedRow();
//                selectedRow = CART_LIST_TABLE.convertRowIndexToModel(selectedRow);                    
//                int set = Integer.parseInt(CART_LIST_TABLE.getModel().getValueAt(selectedRow, 0).toString());
//                SQLiteDB_action sa = SQLiteDB_action.getInstance();
//                sa.deleteCart_product(set);
//                dh.clear_all_cartData();
//                SQLiteDB retrieve = SQLiteDB.getInstance();
//                retrieve.retrieveData();
//                filter_cart_table();
//                setTotal();
//                filterTable();
//                dh.clearAll_product();
//                setCart_minus();
//                dh.setTable();
//                retrieve.setRetrieve_to_be_remove_cart();
                int selectedRow = CART_LIST_TABLE.getSelectedRow();
                selectedRow = CART_LIST_TABLE.convertRowIndexToModel(selectedRow);                    
                String id = (String)CART_LIST_TABLE.getModel().getValueAt(selectedRow, 9);
                String quantity = (String)CART_LIST_TABLE.getModel().getValueAt(selectedRow, 4);
                String table_id = (String)CART_LIST_TABLE.getModel().getValueAt(selectedRow, 0).toString();

                dh.setRemove(id,quantity);
                dh.setRemove_cart_table(table_id);
                filter_cart_table();
                }
                    CART_LIST_TABLE.clearSelection();
                }
            }
        }catch (NullPointerException ex) {
            }                
                PRODUCT_NAMEx.setText("----");
                QUANTITY_LEFTx.setText("----");
                EXPIRATIONx.setText("----");
                PRICEx.setText("----");
                PREPARATIONx.setText("----");
                ORIGINAL_PRICEx.setText("----");
                UNITx.setText("----");
                DISCOUNT_CB.setSelected(false);
                FREE_CB.setSelected(false);
                DISCOUNT_TF.setText("");
                DISCOUNT_TF.setEditable(false);
                
                int rows = CART_LIST_TABLE.getRowCount();
                if(rows == 0){
                    TOTAL_JL.setText("----");
                }
            }
        });
        REMOVE.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            try {
                int rowcheck = CART_LIST_TABLE.getSelectedRow();
                if (rowcheck > -1) {
            int rows = CART_LIST_TABLE.getRowCount();
                if(rows == 0){
                    JOptionPane.showMessageDialog(null,"Cart list is empty.");
                }else{
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, 
                        "Are you sure to remove?", "", dialogButton);
                if(dialogResult == 0) {
                int selectedRow = CART_LIST_TABLE.getSelectedRow();
                selectedRow = CART_LIST_TABLE.convertRowIndexToModel(selectedRow);                    
                String id = (String)CART_LIST_TABLE.getModel().getValueAt(selectedRow, 9);
                String quantity = (String)CART_LIST_TABLE.getModel().getValueAt(selectedRow, 4);
                String table_id = (String)CART_LIST_TABLE.getModel().getValueAt(selectedRow, 0).toString();
                String product_id = (String)CART_LIST_TABLE.getModel().getValueAt(selectedRow, 9).toString();

                dh.setRemove(id,quantity);
                dh.setRemove_cart_table(table_id);
                filter_cart_table();
                filterTable();
                }
                    CART_LIST_TABLE.clearSelection();
                }
            }
            }catch (NullPointerException ex) {
                }                
                PRODUCT_NAMEx.setText("----");
                QUANTITY_LEFTx.setText("----");
                EXPIRATIONx.setText("----");
                PRICEx.setText("----");
                PREPARATIONx.setText("----");
                ORIGINAL_PRICEx.setText("----");
                UNITx.setText("----");
                DISCOUNT_CB.setSelected(false);
                FREE_CB.setSelected(false);
                DISCOUNT_TF.setText("");
                DISCOUNT_TF.setEditable(false);
                
                int rows = CART_LIST_TABLE.getRowCount();
                if(rows == 0){
                    TOTAL_JL.setText("----");
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
                ImageIcon REMOVE_II = new ImageIcon("Skins/C_buttons/"
                        + "REMOVE_2_BUTTON.png");
                REMOVE.setIcon(REMOVE_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon REMOVE_II = new ImageIcon("Skins/C_buttons/"
                        + "REMOVE_1_BUTTON.png");
                REMOVE.setIcon(REMOVE_II);
            }
        });

        PRODUCT_LIST_TABLE.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = PRODUCT_LIST_TABLE.getSelectedRow();
                selectedRow = PRODUCT_LIST_TABLE.convertRowIndexToModel(selectedRow);                    
                String id = (String)PRODUCT_LIST_TABLE.getModel().getValueAt(selectedRow, 0);
                
                dh.setIndex(id);
                product_List_info_setText(
                        dh.retrieveProduct_name(dh.getIndex()),
                        dh.retieveQuantity(dh.getIndex()),
                        dh.retrieveExpiration_date(dh.getIndex()),
                        dh.retrieveSelling_price(dh.getIndex()),
                        dh.retrievePreparation(dh.getIndex()),
                        dh.retrievePrice(dh.getIndex()),
                        dh.retrieveUnit(dh.getIndex()),
                        dh.retrieveBrand_name(dh.getIndex())
                );
                                
                SEARCH_PRODUCT_LIST.grabFocus();
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        SEARCH_PRODUCT_LIST.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
                String query = SEARCH_PRODUCT_LIST.getText();
                searc_Product_List_table(query);
            }
        });
        SEARCH_PRODUCT_LIST.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            if(SEARCH_PRODUCT_LIST.getText().equals("Search")){
            SEARCH_PRODUCT_LIST.setText(null);
            }else{
            SEARCH_PRODUCT_LIST.setText(SEARCH_PRODUCT_LIST.getText());
            }
            SEARCH_PRODUCT_LIST.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
            if(SEARCH_PRODUCT_LIST.getText().isEmpty()){
            SEARCH_PRODUCT_LIST.setForeground(Color.GRAY);
            SEARCH_PRODUCT_LIST.setText("Search");
            }else{
            SEARCH_PRODUCT_LIST.setForeground(Color.BLACK);
            SEARCH_PRODUCT_LIST.setText(SEARCH_PRODUCT_LIST.getText());
            }
            }
        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    setPay();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        DISCOUNT_TF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(!DISCOUNT_TF.getText().equals("")){
                    double setDiscount = Double.parseDouble(DISCOUNT_TF.getText());
                    if(setDiscount>=100){
                        DISCOUNT_TF.setText(DISCOUNT_TF.getText());
                    }
                }
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    setCash_out();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if(!DISCOUNT_TF.getText().equals("")){
                    double setDiscount = Double.parseDouble(DISCOUNT_TF.getText());
                    if(setDiscount>=100){
                        DISCOUNT_TF.setText(DISCOUNT_TF.getText());
                    }
                }
            }
        });
        CART_LIST_TABLE.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = 8;
                int row = CART_LIST_TABLE.getSelectedRow();
                String value = CART_LIST_TABLE.getModel().getValueAt(row, column).toString();
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
    public void setCash_out(){
                int rows = CART_LIST_TABLE.getRowCount();
                
                if(rows == 0){
                    JOptionPane.showMessageDialog(null,"Cart list is empty.");
                }else{
                transanctionID();
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, 
                        "Continue to Payment?", "", dialogButton);
            if(dialogResult == 0) {
                    productName.clear();
                    productPrice.clear();
                    printData = null;
                    totalPayment= 0;
                    
                SQLiteDB_action sqlite = SQLiteDB_action.getInstance();
                SQLiteDB set_data = SQLiteDB.getInstance();

                for(int x=0;x<dh.getCart_size();x++){
                  String set_product_it = (String)CART_LIST_TABLE.getModel().getValueAt(x, 9);
                
                  sqlite.updateQuantity(dh.retieveQuantity(dh.setProduct_removeRow(set_product_it)),
                          set_product_it);
                  
                    //SET TIME
                    DateFormat getTime = new SimpleDateFormat("HH:mm");
                    Date setTime = new Date();
                    //SET DAY
                    DateFormat getDate = new SimpleDateFormat("M/d/yyyy");
                    Date setDate = new Date();
                    //SET DATABASE
                    DataBase db = DataBase.getInstance();
                    
                    String setter = "";
                    
                                        float setPrice = Float.parseFloat(dh.getCart_price(x));
                                        float setTotal = 0;
                                        float setSelling_price = Float.parseFloat(dh.getCart_selling_price(x));
                    
                                        if(dh.getCart_total(x).equalsIgnoreCase("Free")){
                                            setTotal = 0;
                                            setter = "Free";
                                        }else{
                                            setTotal = Float.parseFloat(dh.getCart_total(x));
                                            setter = mDc.roundOffTo2DecPlaces(setTotal);
                                        }
                String discount_checker = (String)CART_LIST_TABLE.getModel().getValueAt(x, 7);
                String setData_for_discount_checker = null;
                                        if(discount_checker.equalsIgnoreCase("false")){
                                            setData_for_discount_checker = "no";
                                        }else{
                                            setData_for_discount_checker = "yes";
                                        }
                                        
                                     db.add_Logs_list(setID,getTime.format(setTime),
                                     getDate.format(setDate),dh.getCart_quantity(x),
                                     mDc.roundOffTo2DecPlaces(setSelling_price)+"",setter,
                                     dh.getUser(),dh.getCart_product_name(x),mDc.roundOffTo2DecPlaces(setPrice),
                                     dh.getCart_strength(x),dh.getCart_preparation(x),
                                     setData_for_discount_checker); 

                if(!dh.getProduct_checker(x).equalsIgnoreCase("false")){
                String product_name = (String)CART_LIST_TABLE.getModel().getValueAt(x, 1);
                String preparation = (String)CART_LIST_TABLE.getModel().getValueAt(x, 2);
                String quantity = (String)CART_LIST_TABLE.getModel().getValueAt(x, 4);
                String price = (String)CART_LIST_TABLE.getModel().getValueAt(x, 5);
                String myTotal = (String)CART_LIST_TABLE.getModel().getValueAt(x, 5);
                String strength = (String)CART_LIST_TABLE.getModel().getValueAt(x, 3);
                String unit_price = (String)CART_LIST_TABLE.getModel().getValueAt(x, 8);
                        
                sqlite.addDiscount_logs(
                                product_name,
                                quantity,
                                price,
                                dh.getProduct_checker(x),
                                getDate.format(setDate)+" "+getTime.format(setTime),
                                dh.retrieveUserLogin(),
                                unit_price,
                                strength,
                                preparation,
                                setID
                            );
                    }

                discount_tokenizer.clear();
                dh.addLogs(setID,dh.setLogs_max_ID(),getTime.format(setTime),
                                     getDate.format(setDate),dh.getCart_quantity(x),
                                     mDc.roundOffTo2DecPlaces(setSelling_price)+"",setter,
                                     dh.getUser(),dh.getCart_product_name(x),mDc.roundOffTo2DecPlaces(setPrice),
                                     dh.getCart_strength(x),dh.getCart_preparation(x),setData_for_discount_checker);
                    
                    productName.add(dh.getCart_product_name(x));
                    
                    int totalQuantity = Integer.parseInt(dh.getCart_quantity(x));
                    double sellingPrice = 0;

                if(dh.getCart_total(x).equalsIgnoreCase("Free")){
                        sellingPrice = 0;
                }else{
                        sellingPrice = Double.parseDouble(dh.getCart_total(x));
                }
                //                
                double result = sellingPrice;
                    productPrice.add(result);
                    productQuantity.add(totalQuantity);
                    totalPayment = totalPayment+result;
                }                    
                
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);
                
                Object printitem [][]=getTableData(CART_LIST_TABLE);
                setItems(printitem);

                 PREPARATIONx.setText("----");
       
//                PrinterJob pj = PrinterJob.getPrinterJob();
//                pj.setPrintable(new MyPrintable(),getPageFormat(pj));
//                      try {
//                           pj.print();
//                           }
//                       catch (PrinterException ex) {
//                               ex.printStackTrace();
//                           }
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();

                printData(setID,dateFormat.format(date),dh.getUser());
                set_data.retrieveDiscount_data();
                dh.clear_all_cartData();
                filter_cart_table();
                
                dh.clearAll_product_list();
                SQLiteDB sdb = SQLiteDB.getInstance();
                sdb.retrieveData_products();
                Logs_Panel lp = Logs_Panel.getInstance();
                lp.filterTable();
                List_Panel lpx = List_Panel.getInstance();
                lpx.filterTable();
                lpx.setProductsData();
                
                //Set text
                PRODUCT_NAMEx.setText("----");
                QUANTITY_LEFTx.setText("----");
                EXPIRATIONx.setText("----");
                PRICEx.setText("----");
                PREPARATIONx.setText("----");
                ORIGINAL_PRICEx.setText("----");
                TOTAL_JL.setText("----");
                TOTAL_QUANTITY.setText("Total quantity: ----");      
                }else{
            }
        }
    }
    public void clearCart(){
                int rows = CART_LIST_TABLE.getRowCount();
                if(rows == 0){
                    JOptionPane.showMessageDialog(null,"Cart list is empty.");
                }else{
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, 
                        "Are you sure to clear cart list?", "", dialogButton);
                if(dialogResult == 0) {
//                    SQLiteDB_action sqlite = SQLiteDB_action.getInstance();
//                    sqlite.clear_Cart();
                    dh.clear_all_cartData();
                    dh.clearAll_product_list();
                    SQLiteDB retrieve = SQLiteDB.getInstance();
//                    retrieve.retrieveData();
                    retrieve.retrieveData_products();
                    filter_cart_table();
                    filterTable();
                    setTotal();
            }
        }
    }
    void setPay(){
                transanctionID();
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, 
                        "Continue to Payment?", "", dialogButton);
                if(dialogResult == 0) {
                                    JFrame frame = new JFrame("");
                String name = JOptionPane.showInputDialog(frame, "Enter amount");
                
                float totalBuy = Float.valueOf(TOTAL_JL.getText());
                int total = Integer.parseInt(name);
                float set = total - totalBuy;
                if (name.equals(JOptionPane.NO_OPTION)) {
                }else if (name .equals(JOptionPane.CANCEL_OPTION)) {
                    
                }else if (set < 0){
                 JOptionPane.showMessageDialog(null,"Amount Insufficient");
                }else {

                      productName.clear();
                    productPrice.clear();
                    printData = null;
                    totalPayment= 0;

                for(int x=0;x<dh.getCart_size();x++){
                    //SET TIME
                    DateFormat getTime = new SimpleDateFormat("HH:mm");
                    Date setTime = new Date();
                    //SET DAY
                    DateFormat getDate = new SimpleDateFormat("M/d/yyyy");
                    Date setDate = new Date();
                    //SET DATABASE
                    DataBase db = DataBase.getInstance();
//                    db.add_Logs_list(setID,getTime.format(setTime),
//                                     getDate.format(setDate),dh.getCart_quantity(x),
//                                     dh.getCart_selling_price(x),dh.getCart_total(x),
//                                     dh.getUser(),dh.getCart_product_name(x),dh.getCart_price(x)); 
//                    dh.addLogs(setID,dh.setLogs_max_ID(),getTime.format(setTime),
//                                     getDate.format(setDate),dh.getCart_quantity(x),
//                                     dh.getCart_selling_price(x),dh.getCart_total(x),
//                                     dh.getUser(),dh.getCart_product_name(x),dh.getCart_price(x));
                    
                    productName.add(dh.getCart_product_name(x));
                    
                    int totalQuantity = Integer.parseInt(dh.getCart_quantity(x));
                    double sellingPrice = 0;

                if(dh.getCart_selling_price(x).equalsIgnoreCase("Free")){
                        sellingPrice = 0;

                }else{
                        sellingPrice = Double.parseDouble(dh.getCart_total(x));
                }
                
                double result = sellingPrice;
                    productPrice.add(result);
                    productQuantity.add(totalQuantity);
                    totalPayment = totalPayment+result;
                }
                                //Set quantity
                    for (int count = 0; count < CART_LIST_TABLE.getRowCount(); count++){
                    int convertString = Integer.parseInt(CART_LIST_TABLE.getValueAt(count, 0).toString());
                    int quantity = Integer.parseInt(CART_LIST_TABLE.getValueAt(count, 2).toString());

                    dh.updateTotal_units(convertString,quantity);
                    DataBase db = DataBase.getInstance();
                    db.updateDataLogsQuantities(dh.retrieveID(dh.getIndex()));
                    //Scann if quantity is empty
                    int quantityLeft = Integer.parseInt(dh.getTotalSubtract_quantity());
                    if(quantityLeft <= 0){
                        db.remove_product_list(dh.retrieveID());
                        dh.removeID_afterQuantityRetrieve();
                    }
                }
                    
                float x = Float.valueOf(TOTAL_JL.getText());
                float totalAll = total - x;
                CHANGE = totalAll;
                CASH = total;
//                JOptionPane.showMessageDialog(null,"Change: "+totalAll);
                 
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);

//                Change c = Change.getInstance();
//                c.setVisible(true);
//                c.setText(total+"",totalAll+"");
//                c.centreWindow(mf);
                
                Object printitem [][]=getTableData(CART_LIST_TABLE);
                 setItems(printitem);
       
//                PrinterJob pj = PrinterJob.getPrinterJob();
//                pj.setPrintable(new MyPrintable(),getPageFormat(pj));
//                      try {
//                           pj.print();
//
//                           }
//                       catch (PrinterException ex) {
//                               ex.printStackTrace();
//                           }
                
                List_Panel lpx = List_Panel.getInstance();
                lpx.filterTable();
                dh.clear_all_cartData();
                SQLiteDB_action sqlite = SQLiteDB_action.getInstance();
                sqlite.clear_Cart();
                filter_cart_table();
                setTotal();
                filterTable();
                Logs_Panel lp = Logs_Panel.getInstance();
                lp.filterTable();

                //Set text
                PRODUCT_NAMEx.setText("----");
                QUANTITY_LEFTx.setText("----");
                EXPIRATIONx.setText("----");
                PRICEx.setText("----");
                PREPARATIONx.setText("----");
                ORIGINAL_PRICEx.setText("----");
                TOTAL_JL.setText("----");
                TOTAL_QUANTITY.setText("Total quantity: ----");
          }
       }
    }
}