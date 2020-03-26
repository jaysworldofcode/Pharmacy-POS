package Panels;

import DataBase.DataBase;
import DataBase.SQLiteDB;
import DataBase.SQLiteDB_action;
import DataHolder.DataHolder;
import Frames.ChangePassword;
import Frames.ExpireAndOutOfStockListFrame;
import Frames.NewAccount;
import Frames.NewProduct;
import Frames.UpdateProduct;
import Frames.View_Accounts;
import Frames.out_of_stock_list;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.ArrayList;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import msinventorysystem.MainFrame;

public class List_Panel extends JPanel{
    JLabel SET_COLOR_CODE = new JLabel();

    JFrame setEdit_table_header = new JFrame();
    
    JTextField SEARCH_TF;
    JButton ADD_DATA,DELETE_DATA,UPDATE_DATA,ADD_SALE_DATA,BACK,PRINT,OUT_OF_STOCK;
    JPanel PRODUCT_LIST_PANEL,UPDATE_SALE_PANEL;
    JLabel PRODUCT_NAME_JL,QUANTITY_JL,DAYS_LEFT_JL,EXPIRATION_JL,PRICE_JL,
           SELLING_PRICE_JL,DATE_PURCHASE_JL,UNIT_JL,PREPARATION_JL,LIMIT,
           PURCHASE_FROM,SEARCH_JL,BRAND_NAME;
    JLabel PRODUCT_NAME_JLx,QUANTITY_JLx,DAYS_LEFT_JLx,EXPIRATION_JLx,PRICE_JLx,
           SELLING_PRICE_JLx,DATE_PURCHASE_JLx,UNIT_JLx,PREPARATION_JLx,LIMITx,
           PURCHASE_FROMx,BRAND_NAMEx;
    JScrollPane PRODUCT_LIST_PANE,PRODUCT_TABLE_LIST_PANE;
    DefaultTableModel PRODUCT_MODEL;
    JTable PRODUCT_LIST;
    static int tablePane_width = 100;
    JPopupMenu LIST_PRODUCT_POPUP = new JPopupMenu();
    JMenuItem ADD_MENU,DELETE_MENU,UPDATE_MENU;
    
    JLabel text_panel = new JLabel(new ImageIcon("Skins/C_background/"
                + "bg_search_1.png"));
    JLabel product_info = new JLabel(new ImageIcon("Skins/C_background/"
                + "bg_product_info_1.png"));
    
    boolean mDraggingColumn = false;
    boolean mColumnCHangedIndex = false;
    
    //Set classes
    DataHolder dh = DataHolder.getInstance();
    DataBase db = DataBase.getInstance();
    private static List_Panel instance = null;
    public static List_Panel getInstance() {
      if(instance == null) {
         instance = new List_Panel();
      }
      System.out.println("/* List panel class */");
      return instance;
    }
    public void clearType(){
                PURCHASE_FROMx.setText("-----");
                BRAND_NAMEx.setText("-----");
                PRODUCT_NAME_JLx.setText("-----");
                QUANTITY_JLx.setText("-----");
                DAYS_LEFT_JLx.setText("-----");
                EXPIRATION_JLx.setText("-----");
                PRICE_JLx.setText("-----");
                SELLING_PRICE_JLx.setText("-----");
                DATE_PURCHASE_JLx.setText("-----");
                UNIT_JLx.setText("-----");
                PREPARATION_JLx.setText("-----");           
                LIMITx.setText("-----");
    }
    public List_Panel(){
        this.setBounds(0,100,1100,600);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setVisible(false);
        dh.print();
        Organizer();
    }
    void Organizer(){
        Components();
        productTable();
        Listener();
        setIcon_Icon();
    }
    public void setIcon_Icon(){
        SQLiteDB sdb = SQLiteDB.getInstance();
        setIcon_stock(sdb.checkFor_new());
    }
    public JButton getButtons(){
        return OUT_OF_STOCK;
    }
    public void setButtons(String user){
    if(user.equalsIgnoreCase("user")){
        DELETE_DATA.setVisible(false);
        UPDATE_DATA.setVisible(false);
        ADD_SALE_DATA.setBounds(10,30,100,50);
        BACK.setBounds(115,30,100,50);
        OUT_OF_STOCK.setBounds(130,30,100,50);
        PRINT.setBounds(70,10,50,70);
    }else{
        DELETE_DATA.setVisible(true);
        UPDATE_DATA.setVisible(true);
        ADD_SALE_DATA.setBounds(325,30,100,50);
        BACK.setBounds(430,30,100,50);
        OUT_OF_STOCK.setBounds(230,30,100,50);
        PRINT.setBounds(190,10,50,70);
     }
    }
    void new_product(){                
                dh.frameSetVisibleFalse();
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);
                NewProduct n = NewProduct.getInstance();
                n.centreWindow(mf);
                
                ChangePassword cp = ChangePassword.getInstance();
                NewAccount na = NewAccount.getInstance();
                View_Accounts va = View_Accounts.getInstance();

                na.setVisible(false);
                va.setVisible(false);
                cp.setVisible(false);
    }
    void update_product(MouseEvent e){
                dh.frameSetVisibleFalse();
                int row = PRODUCT_LIST.getSelectedRow();
                row = PRODUCT_LIST.convertRowIndexToModel(row);                    
                String id = PRODUCT_LIST.getModel().getValueAt(row, 1).toString();
                String product_name = PRODUCT_LIST.getModel().getValueAt(row, 2).toString();
                String brand_name = PRODUCT_LIST.getModel().getValueAt(row, 3).toString();
                String quantity = PRODUCT_LIST.getModel().getValueAt(row, 10).toString();
                String days_left = PRODUCT_LIST.getModel().getValueAt(row, 7).toString();
                String expiration = PRODUCT_LIST.getModel().getValueAt(row, 6).toString();
                String price = PRODUCT_LIST.getModel().getValueAt(row, 8).toString();
                String selling_price = PRODUCT_LIST.getModel().getValueAt(row, 9).toString();
                String date_purchase = PRODUCT_LIST.getModel().getValueAt(row, 11).toString();
                String unit = PRODUCT_LIST.getModel().getValueAt(row, 4).toString();
                String preparation = PRODUCT_LIST.getModel().getValueAt(row, 5).toString();
                String purchase_from = PRODUCT_LIST.getModel().getValueAt(row, 12).toString();
                String limit = PRODUCT_LIST.getModel().getValueAt(row, 13).toString();

                UpdateProduct up = UpdateProduct.getInstance();
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                up.setLocation_Frame(getLocationOnScreen().x + x - x,
                getLocationOnScreen().y + y - y);

                up.setData_info(id,product_name,quantity,expiration,price,
                        selling_price,date_purchase,unit,preparation,true,purchase_from,
                        brand_name, limit,dh.getPriceIncreaseByID(id));

                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);   
                
                ChangePassword cp = ChangePassword.getInstance();
                NewAccount na = NewAccount.getInstance();
                NewProduct np = NewProduct.getInstance();
                View_Accounts va = View_Accounts.getInstance();
                
                na.setVisible(false);
                va.setVisible(false);
                np.setVisible(false);
                cp.setVisible(false);
    }
    void remove_product(){
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, 
                        "Are you sure to remove?", "", dialogButton);
                if(dialogResult == 0) {
                int row = PRODUCT_LIST.getSelectedRow();
                String value = PRODUCT_LIST.getModel().getValueAt(row, 1).toString();
                try{
                db.remove_product_list(value);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"Database Error");
                }
                try{
                dh.deleteProduct_date(value);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"DataHolder Error");
                }

                Sale_Panel sp = Sale_Panel.getInstance();
                sp.set_Table_info();

                PURCHASE_FROMx.setText("-----");
                BRAND_NAMEx.setText("-----");
                PRODUCT_NAME_JLx.setText("-----");
                QUANTITY_JLx.setText("-----");
                DAYS_LEFT_JLx.setText("-----");
                EXPIRATION_JLx.setText("-----");
                PRICE_JLx.setText("-----");
                SELLING_PRICE_JLx.setText("-----");
                DATE_PURCHASE_JLx.setText("-----");
                UNIT_JLx.setText("-----");
                PREPARATION_JLx.setText("-----");           
                LIMITx.setText("-----");
                try{
                filterTable();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex);
                }                 
            } else {
        }
    }
    public void setIcon_stock(boolean set){
        if(set == true){
        ImageIcon II = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK_NEW.png");
        OUT_OF_STOCK.setIcon(II);
        }else{
        ImageIcon II = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK.png");
        OUT_OF_STOCK.setIcon(II);
        }
    }
    void Components(){
        setEdit_table_header = new JFrame();
        setEdit_table_header.setType(javax.swing.JFrame.Type.UTILITY);
        setEdit_table_header.setLayout(null);
        setEdit_table_header.setSize(150,100);
        
        ImageIcon COLOR_CODE_II = new ImageIcon("Skins/C_background/COLOR_CODE.png");
        SET_COLOR_CODE = new JLabel(COLOR_CODE_II);
        SET_COLOR_CODE.setBounds(790,0,300,40);
        this.add(SET_COLOR_CODE);

        ImageIcon ADD_DATA_II = new ImageIcon("Skins/C_buttons/"
                + "ADD_LIST_1_BUTTON.png");
        ADD_DATA = new JButton(ADD_DATA_II);
        ADD_DATA.setBounds(10,10,50,70);
        ADD_DATA.setOpaque(false);
        ADD_DATA.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ADD_DATA.setContentAreaFilled(false);
        ADD_DATA.setFocusable(false);
        ADD_DATA.setBorderPainted(false);
        ADD_DATA.setToolTipText("Add new product");
        this.add(ADD_DATA);
        
        ImageIcon REMOVE_DATA_II = new ImageIcon("Skins/C_buttons/"
                + "REMOVE_LIST_1_BUTTON.png");
        DELETE_DATA = new JButton(REMOVE_DATA_II);
        DELETE_DATA.setBounds(70,10,50,70);
        DELETE_DATA.setOpaque(false);
        DELETE_DATA.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DELETE_DATA.setContentAreaFilled(false);
        DELETE_DATA.setFocusable(false);
        DELETE_DATA.setBorderPainted(false);
        DELETE_DATA.setToolTipText("Delete product");
        this.add(DELETE_DATA);
        
        ImageIcon UPDATE_DATE_II = new ImageIcon("Skins/C_buttons/"
                + "UPDATE_LIST_1_BUTTON.png");
        UPDATE_DATA = new JButton(UPDATE_DATE_II);
        UPDATE_DATA.setBounds(130,10,50,70);
        UPDATE_DATA.setOpaque(false);
        UPDATE_DATA.setCursor(new Cursor(Cursor.HAND_CURSOR));
        UPDATE_DATA.setContentAreaFilled(false);
        UPDATE_DATA.setFocusable(false);
        UPDATE_DATA.setBorderPainted(false);
        UPDATE_DATA.setToolTipText("Update product");
        this.add(UPDATE_DATA);
        
        ImageIcon PRINT_II = new ImageIcon("Skins/C_buttons/PRINT_1.png");
        PRINT = new JButton(PRINT_II);
        PRINT.setBounds(190,10,50,70);
        PRINT.setOpaque(false);
        PRINT.setCursor(new Cursor(Cursor.HAND_CURSOR));
        PRINT.setContentAreaFilled(false);
        PRINT.setFocusable(false);
        PRINT.setBorderPainted(false);
        PRINT.setToolTipText("Print table");
        this.add(PRINT);
        
        ImageIcon sale_ii = new ImageIcon("Skins/C_buttons/"
                + "SALE_LIST_1_BUTTON.png");
        ADD_SALE_DATA = new JButton(sale_ii);
        ADD_SALE_DATA.setBounds(325,30,100,50);
        ADD_SALE_DATA.setOpaque(false);
        ADD_SALE_DATA.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ADD_SALE_DATA.setContentAreaFilled(false);
        ADD_SALE_DATA.setFocusable(false);
        ADD_SALE_DATA.setBorderPainted(false);
//        this.add(ADD_SALE_DATA);
        
        Icon  II = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK.png");
        OUT_OF_STOCK = new JButton(II);
        OUT_OF_STOCK.setRolloverIcon(II); // Set the icon attaching with the roll-over event
        OUT_OF_STOCK.setBounds(230,30,100,50);
        OUT_OF_STOCK.setOpaque(false);
        OUT_OF_STOCK.setCursor(new Cursor(Cursor.HAND_CURSOR));
        OUT_OF_STOCK.setContentAreaFilled(false);
        OUT_OF_STOCK.setFocusable(false);
        OUT_OF_STOCK.setBorderPainted(false);
        OUT_OF_STOCK.setToolTipText("Out of stock and expired product list");
        this.add(OUT_OF_STOCK);
        
        ImageIcon back_ii = new ImageIcon("Skins/C_buttons/"
                + "BACK_LIST_1_BUTTON.png");
        BACK = new JButton(back_ii);
        BACK.setBounds(430,30,100,50);
        BACK.setOpaque(false);
        BACK.setCursor(new Cursor(Cursor.HAND_CURSOR));
        BACK.setContentAreaFilled(false);
        BACK.setFocusable(false);
        BACK.setBorderPainted(false);
        
        text_panel.setBounds(830,43,240,45);
        text_panel.setBackground(Color.red);
        this.add(text_panel);
        
        SEARCH_TF = new JTextField("Search");
        SEARCH_TF.setForeground(Color.GRAY);
        SEARCH_TF.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        SEARCH_TF.setBounds(10,6,190,30);
        text_panel.add(SEARCH_TF);
               
        SEARCH_TF.addMouseListener(new MouseListener() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(isMouseWithinComponent(text_panel)){
                    text_panel.setIcon(new ImageIcon("Skins/C_background/"
                + "bg_search_2.png"));
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(!isMouseWithinComponent(text_panel)){
                    text_panel.setIcon(new ImageIcon("Skins/C_background/"
                + "bg_search_1.png"));
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
               
        text_panel.addMouseListener(new MouseListener() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(isMouseWithinComponent(text_panel)){
                    text_panel.setIcon(new ImageIcon("Skins/C_background/"
                + "bg_search_2.png"));
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(!isMouseWithinComponent(text_panel)){
                    text_panel.setIcon(new ImageIcon("Skins/C_background/"
                + "bg_search_1.png"));
                }
            }
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
        
        Font SETFONT = new Font("SansSerif", Font.BOLD, 13);

        PRODUCT_LIST_PANEL = new JPanel();
        PRODUCT_LIST_PANEL.setBackground(Color.WHITE);
        PRODUCT_LIST_PANEL.setPreferredSize(new Dimension(1500, 300));
        PRODUCT_LIST_PANEL.setLayout(null);

        PRODUCT_LIST_PANE = new JScrollPane(PRODUCT_LIST_PANEL);
        PRODUCT_LIST_PANE.setBounds(10,95,1080,300);
        PRODUCT_LIST_PANE.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        JScrollBar xb_x = PRODUCT_LIST_PANE.getHorizontalScrollBar();
        PRODUCT_LIST_PANE.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        PRODUCT_LIST_PANE.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));
        PRODUCT_LIST_PANE.getViewport().setOpaque(false);
        PRODUCT_LIST_PANE.setBorder(null);
        this.add(PRODUCT_LIST_PANE);
        
        PRODUCT_LIST = new JTable();

        PRODUCT_TABLE_LIST_PANE = new JScrollPane(PRODUCT_LIST);
        PRODUCT_TABLE_LIST_PANE.setBounds(0,0,1560,290);
        PRODUCT_TABLE_LIST_PANE.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        PRODUCT_TABLE_LIST_PANE.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        PRODUCT_TABLE_LIST_PANE.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        PRODUCT_TABLE_LIST_PANE.setBorder(createEmptyBorder());
        PRODUCT_TABLE_LIST_PANE.getViewport().setOpaque(false);
        PRODUCT_TABLE_LIST_PANE.setOpaque(false);
        PRODUCT_LIST_PANEL.add(PRODUCT_TABLE_LIST_PANE);

        UPDATE_SALE_PANEL = new JPanel();
        UPDATE_SALE_PANEL.setBounds(10,435,900,155);
        UPDATE_SALE_PANEL.setLayout(null);
        UPDATE_SALE_PANEL.setOpaque(false);
        UPDATE_SALE_PANEL.setFont(SETFONT);
        this.add(UPDATE_SALE_PANEL);
        
        JPanel setProductNamepanel = new JPanel();
        setProductNamepanel.setBounds(10,400,700,30);
        setProductNamepanel.setOpaque(false);
        setProductNamepanel.setLayout(null);
        this.add(setProductNamepanel);
        
        PRODUCT_NAME_JL = new JLabel("Product name");
        PRODUCT_NAME_JL.setFont(SETFONT);
        PRODUCT_NAME_JL.setBounds(10,8,200,25);
        PRODUCT_NAME_JL.setForeground(Color.DARK_GRAY);
        setProductNamepanel.add(PRODUCT_NAME_JL);
        
        PRODUCT_NAME_JLx = new JLabel ("<html><h2>-----</h2></html>");
        PRODUCT_NAME_JLx.setBounds(120,8,500,25);
        setProductNamepanel.add(PRODUCT_NAME_JLx);
        
        LIMIT = new JLabel("Limit");
        LIMIT.setBounds(715,405,70,30);
        LIMIT.setFont(SETFONT);
        LIMIT.setForeground(Color.DARK_GRAY);
        this.add(LIMIT);
        
        LIMITx = new JLabel("-----");
        LIMITx.setBounds(785,405,500,30);
        this.add(LIMITx);
        
        DAYS_LEFT_JL = new JLabel("Days left");
        DAYS_LEFT_JL.setFont(SETFONT);
        DAYS_LEFT_JL.setBounds(10,120,100,25);
        DAYS_LEFT_JL.setForeground(Color.DARK_GRAY);
        UPDATE_SALE_PANEL.add(DAYS_LEFT_JL);
        
        DAYS_LEFT_JLx = new JLabel("-----");
        DAYS_LEFT_JLx.setBounds(110,120,500,25);
        UPDATE_SALE_PANEL.add(DAYS_LEFT_JLx);
        
        EXPIRATION_JL = new JLabel("Expiration");
        EXPIRATION_JL.setFont(SETFONT);
        EXPIRATION_JL.setBounds(10,95,100,25);
        EXPIRATION_JL.setForeground(Color.DARK_GRAY);
        UPDATE_SALE_PANEL.add(EXPIRATION_JL);
        
        EXPIRATION_JLx = new JLabel("-----");
        EXPIRATION_JLx.setBounds(110,95,500,25);
        UPDATE_SALE_PANEL.add(EXPIRATION_JLx);
        
        PRICE_JL = new JLabel("Unit Price");
        PRICE_JL.setFont(SETFONT);
        PRICE_JL.setBounds(405,45,100,25);
        PRICE_JL.setForeground(Color.DARK_GRAY);
        UPDATE_SALE_PANEL.add(PRICE_JL);
        
        PRICE_JLx = new JLabel("-----");
        PRICE_JLx.setBounds(505,45,500,25);
        UPDATE_SALE_PANEL.add(PRICE_JLx);
        
        SELLING_PRICE_JL = new JLabel("Selling price");
        SELLING_PRICE_JL.setFont(SETFONT);
        SELLING_PRICE_JL.setBounds(405,70,100,25);
        SELLING_PRICE_JL.setForeground(Color.DARK_GRAY);
        UPDATE_SALE_PANEL.add(SELLING_PRICE_JL);
        
        SELLING_PRICE_JLx = new JLabel("-----");
        SELLING_PRICE_JLx.setBounds(505,70,500,25);
        UPDATE_SALE_PANEL.add(SELLING_PRICE_JLx);
        
        BRAND_NAME = new JLabel("Brand name");
        BRAND_NAME.setBounds(10,20,100,25);
        BRAND_NAME.setFont(SETFONT);
        BRAND_NAME.setForeground(Color.DARK_GRAY);
        UPDATE_SALE_PANEL.add(BRAND_NAME);
        
        BRAND_NAMEx = new JLabel("-----");
        BRAND_NAMEx.setBounds(110,20,500,25);
        UPDATE_SALE_PANEL.add(BRAND_NAMEx);
        
        UNIT_JL = new JLabel("Strength");
        UNIT_JL.setFont(SETFONT);
        UNIT_JL.setBounds(10,45,100,25);
        UNIT_JL.setForeground(Color.DARK_GRAY);
        UPDATE_SALE_PANEL.add(UNIT_JL);
        
        UNIT_JLx = new JLabel("-----");
        UNIT_JLx.setBounds(110,45,500,25);
        UPDATE_SALE_PANEL.add(UNIT_JLx);
        
        PREPARATION_JL = new JLabel("Preparation");
        PREPARATION_JL.setFont(SETFONT);
        PREPARATION_JL.setBounds(10,70,100,25);
        PREPARATION_JL.setForeground(Color.DARK_GRAY);
        UPDATE_SALE_PANEL.add(PREPARATION_JL);
        
        PREPARATION_JLx = new JLabel("-----");
        PREPARATION_JLx.setBounds(110,70,500,25);
        UPDATE_SALE_PANEL.add(PREPARATION_JLx);
        
        QUANTITY_JL = new JLabel("Quantity");
        QUANTITY_JL.setFont(SETFONT);
        QUANTITY_JL.setBounds(405,20,100,25);
        QUANTITY_JL.setForeground(Color.DARK_GRAY);
        UPDATE_SALE_PANEL.add(QUANTITY_JL);
        
        QUANTITY_JLx = new JLabel ("-----");
        QUANTITY_JLx.setBounds(505,20,500,25);
        UPDATE_SALE_PANEL.add(QUANTITY_JLx);
         
        DATE_PURCHASE_JL = new JLabel("Date purchase");
        DATE_PURCHASE_JL.setFont(SETFONT);
        DATE_PURCHASE_JL.setBounds(405,95,100,25);
        DATE_PURCHASE_JL.setForeground(Color.DARK_GRAY);
        UPDATE_SALE_PANEL.add(DATE_PURCHASE_JL);
        
        DATE_PURCHASE_JLx = new JLabel("-----");
        DATE_PURCHASE_JLx.setBounds(505,95,500,25);
        UPDATE_SALE_PANEL.add(DATE_PURCHASE_JLx);
       
        PURCHASE_FROM = new JLabel("Purchase from");
        PURCHASE_FROM.setFont(SETFONT);
        PURCHASE_FROM.setBounds(405,120,100,25);
        PURCHASE_FROM.setForeground(Color.DARK_GRAY);
        UPDATE_SALE_PANEL.add(PURCHASE_FROM);
        
        PURCHASE_FROMx = new JLabel("-----");
        PURCHASE_FROMx.setBounds(505,120,500,25);
        UPDATE_SALE_PANEL.add(PURCHASE_FROMx);
        
        ADD_MENU = new JMenuItem("ADD");
        LIST_PRODUCT_POPUP.add(ADD_MENU);
        
        DELETE_MENU = new JMenuItem("DELETE");
        LIST_PRODUCT_POPUP.add(DELETE_MENU);
        
        UPDATE_MENU = new JMenuItem("UPDATE");
        LIST_PRODUCT_POPUP.add(UPDATE_MENU);
        
//        PRODUCT_LIST.setComponentPopupMenu(LIST_PRODUCT_POPUP);
        PRODUCT_LIST.setBackground(new Color(0, 255, 0, 0));
        PRODUCT_LIST.setOpaque(false);
        
        product_info.setBounds(1,400,1095,195);
        product_info.setLayout(null);
        product_info.setOpaque(false);
        this.add(product_info);
        
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
        JMenuItem newProduct = new JMenuItem("New Product");
        newProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new_product();
            }
        });
        JMenuItem updateProduct = new JMenuItem("Update Product");
        updateProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = PRODUCT_LIST.getSelectedRow();
                row = PRODUCT_LIST.convertRowIndexToModel(row);                    
                String id = PRODUCT_LIST.getModel().getValueAt(row, 1).toString();
                String product_name = PRODUCT_LIST.getModel().getValueAt(row, 2).toString();
                String brand_name = PRODUCT_LIST.getModel().getValueAt(row, 3).toString();
                String quantity = PRODUCT_LIST.getModel().getValueAt(row, 10).toString();
                String days_left = PRODUCT_LIST.getModel().getValueAt(row, 7).toString();
                String expiration = PRODUCT_LIST.getModel().getValueAt(row, 6).toString();
                String price = PRODUCT_LIST.getModel().getValueAt(row, 8).toString();
                String selling_price = PRODUCT_LIST.getModel().getValueAt(row, 9).toString();
                String date_purchase = PRODUCT_LIST.getModel().getValueAt(row, 11).toString();
                String unit = PRODUCT_LIST.getModel().getValueAt(row, 4).toString();
                String preparation = PRODUCT_LIST.getModel().getValueAt(row, 5).toString();
                String purchase_from = PRODUCT_LIST.getModel().getValueAt(row, 12).toString();
                String limit = PRODUCT_LIST.getModel().getValueAt(row, 13).toString();

                UpdateProduct up = UpdateProduct.getInstance();
                up.setLocationRelativeTo(null);  // <<--- plain and simple

                up.setData_info(id,product_name,quantity,expiration,price,
                        selling_price,date_purchase,unit,preparation,true,purchase_from,
                        brand_name, limit, dh.getPriceIncreaseByID(id));

                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);               
                
                ChangePassword cp = ChangePassword.getInstance();
                NewAccount na = NewAccount.getInstance();
                NewProduct np = NewProduct.getInstance();
                View_Accounts va = View_Accounts.getInstance();
                
                na.setVisible(false);
                va.setVisible(false);
                np.setVisible(false);
                cp.setVisible(false);
            }
        });
        updateProduct.addMouseListener(new MouseListener() {
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
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        JMenuItem removeProduct = new JMenuItem("Remove Product");
        removeProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove_product();
            }
        });
//        popupMenu.add(newProduct);
        popupMenu.add(updateProduct);
        popupMenu.add(removeProduct);
        PRODUCT_LIST.setComponentPopupMenu(popupMenu);
//        ImageIcon PANEL_II = new ImageIcon("Skins/C_background/PANELS_BG.png");
//        JLabel PANELS_BG = new JLabel(PANEL_II);
//        PANELS_BG.setBounds(0,0,900,600);
//        this.add(PANELS_BG);
    }
public boolean isMouseWithinComponent(Component c)
{
    Point mousePos = MouseInfo.getPointerInfo().getLocation();
    Rectangle bounds = c.getBounds();
    bounds.setLocation(c.getLocationOnScreen());
    return bounds.contains(mousePos);
}
    public void setIcon(String set){
//        int x = Integer.parseInt(set);
//        
//        if(set.equals("new")){
//            Icon  II = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK_NEW.png");
//            OUT_OF_STOCK.setIcon(II);
//        }if(set.equals("1")){
//            Icon  II = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK_1.png");
//            OUT_OF_STOCK.setIcon(II);
//        }if(set.equals("2")){
//            Icon  II = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK_2.png");
//            OUT_OF_STOCK.setIcon(II);
//        }if(set.equals("3")){
//            Icon  II = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK_3.png");
//            OUT_OF_STOCK.setIcon(II);
//        }if(set.equals("4")){
//            Icon  II = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK_4.png");
//            OUT_OF_STOCK.setIcon(II);
//        }if(set.equals("5")){
//            Icon  II = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK_5.png");
//            OUT_OF_STOCK.setIcon(II);
//        }else{
//            Icon  II = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK_5_above.png");
//            OUT_OF_STOCK.setIcon(II);
//        System.out.println("Set else");
//        }
        switch (set) {

            case "new":
                
            Icon  II = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK_NEW.png");
            OUT_OF_STOCK.setIcon(II);
                break;

            case "1":

            Icon  II_1 = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK_1.png");
            OUT_OF_STOCK.setIcon(II_1);
                break;

            case "2":

            Icon  II_2 = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK_2.png");
            OUT_OF_STOCK.setIcon(II_2);
                break;

            case "3":

            Icon  II_3 = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK_3.png");
            OUT_OF_STOCK.setIcon(II_3);
                break;

            case "4":

            Icon  II_4 = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK_4.png");
            OUT_OF_STOCK.setIcon(II_4);
                break;

            case "5":

            Icon  II_5 = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK_5.png");
            OUT_OF_STOCK.setIcon(II_5);
                break;

            case "0":

            Icon  II_0 = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK.png");
            OUT_OF_STOCK.setIcon(II_0);
                break;

            default:
            Icon  above = new ImageIcon("Skins/C_buttons/OUT_OF_STOCK_5_above.png");
            OUT_OF_STOCK.setIcon(above);

            break;
        }
    }
    void setTextLabel(String panel_border_title,String quantity,String daysLeft,
            String expiration,String price,String selling_price,String date_purchase,
            String unit,String preparation,String purchase_from){
        UPDATE_SALE_PANEL.setBorder(BorderFactory.createTitledBorder(panel_border_title));
        QUANTITY_JL.setText("Quantity "+quantity);
        DAYS_LEFT_JL.setText("Days left "+daysLeft); 
        EXPIRATION_JL.setText("Expiration "+expiration);
        PRICE_JL.setText("Price "+price);
        SELLING_PRICE_JL.setText("Selling price "+selling_price);
        DATE_PURCHASE_JL.setText("Date purchase "+date_purchase);
        UNIT_JL.setText("Unit "+unit);
        PREPARATION_JL.setText("Preparation "+preparation);
        PURCHASE_FROM.setText("Purchase From "+purchase_from);
    }
    public void filter(String query){
        TableRowSorter<DefaultTableModel> df = new TableRowSorter<DefaultTableModel>(PRODUCT_MODEL);
        PRODUCT_LIST.setRowSorter(df);
        df.setRowFilter(RowFilter.regexFilter(query));
                        String status = (String)PRODUCT_LIST.getModel().getValueAt(1, 1);
                        String id = PRODUCT_LIST.getModel().getValueAt(0, 1).toString();
                        String idx = PRODUCT_LIST.getModel().getValueAt(1, 1).toString();
    }
    void productTable(){
            PRODUCT_MODEL = new DefaultTableModel()
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
            
        Object[] columnName = new Object[14];
        columnName[0] = "";
        columnName[1] = "Id";
        columnName[2] = "Product Name";
        columnName[3] = "Brand Name";
        columnName[4] = "Strength";
        columnName[5] = "Preparation";
        columnName[6] = "Expiration";
        columnName[7] = "Days Left";
        columnName[8] = "Unit Price";
        columnName[9] = "Selling Price";
        columnName[10] = "Quantity";
        columnName[11] = "Date Purchase";
        columnName[12] = "Purchased From";
        columnName[13] = "SET_LIMIT";

        PRODUCT_MODEL.setColumnIdentifiers(columnName);
        PRODUCT_LIST.setModel(PRODUCT_MODEL);
        PRODUCT_LIST.getColumnModel().getColumn(0).setResizable(false);
        PRODUCT_LIST.setAutoCreateRowSorter(true);
        PRODUCT_LIST.setIntercellSpacing(new Dimension(0, 0));
        PRODUCT_LIST.setShowGrid(false);
        PRODUCT_LIST.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        PRODUCT_LIST.setFont(new Font("Arial", Font.PLAIN, 15));
        PRODUCT_LIST.setRowHeight(30);
        PRODUCT_LIST.setSelectionBackground(new java.awt.Color(238,238,238));
        PRODUCT_LIST.getColumn("").setMaxWidth(50);
        
        PRODUCT_LIST.getColumn("Days Left").setMaxWidth(75);
        PRODUCT_LIST.getColumn("Quantity").setMaxWidth(110);
        PRODUCT_LIST.getColumn("Unit Price").setMaxWidth(80);
        PRODUCT_LIST.getColumn("Selling Price").setMaxWidth(110);
        PRODUCT_LIST.getColumn("Unit Price").setMaxWidth(110);
        PRODUCT_LIST.getColumn("Expiration").setMaxWidth(110);
        PRODUCT_LIST.getColumn("Strength").setMaxWidth(80);
        PRODUCT_LIST.getColumn("Preparation").setMaxWidth(80);
        PRODUCT_LIST.getColumn("Date Purchase").setMaxWidth(100);
        PRODUCT_LIST.getColumn("Date Purchase").setMinWidth(100);
        PRODUCT_LIST.getColumn("Expiration").setMaxWidth(100);
        PRODUCT_LIST.getColumn("Expiration").setMinWidth(100);
        PRODUCT_LIST.removeColumn(PRODUCT_LIST.getColumnModel().getColumn(1));
        PRODUCT_LIST.removeColumn(PRODUCT_LIST.getColumnModel().getColumn(12));

        int height = PRODUCT_LIST.getRowHeight();
        PRODUCT_LIST.setRowHeight(height+10);
        PRODUCT_LIST.setShowGrid(false); 

    PRODUCT_LIST.getTableHeader().addMouseListener(new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {
            if (mDraggingColumn && mColumnCHangedIndex) {
                System.out.println("Column changed");
                listOfColumns();
            }
            mDraggingColumn = false;
            mColumnCHangedIndex = false;
        }
//        @Override
//        public void mouseEntered(MouseEvent e) {
//            PointerInfo a = MouseInfo.getPointerInfo();
//            Point b = a.getLocation();
//            int x = (int) b.getX();
//            int y = (int) b.getY();
//                setEdit_table_header.setLocation(x+100,y);
//                setEdit_table_header.setVisible(true);
//                dh.setBackground("false");
//                System.out.println(PRODUCT_LIST.getLocationOnScreen().y);
//        }
//        @Override
//        public void mouseExited(MouseEvent e) {
//                setEdit_table_header.setVisible(false);
//        }
    });
    PRODUCT_LIST.getColumnModel().addColumnModelListener(new TableColumnModelListener() {
        @Override
        public void columnAdded(TableColumnModelEvent e) {}
        @Override
        public void columnRemoved(TableColumnModelEvent e) {}
        @Override
        public void columnMoved(TableColumnModelEvent e) {
            mDraggingColumn = true;
            if (e.getFromIndex() != e.getToIndex()) {
                mColumnCHangedIndex = true;
            }
        }
        @Override
        public void columnMarginChanged(ChangeEvent e) {}
        @Override
        public void columnSelectionChanged(ListSelectionEvent e) {}
    });
        
    filterTable();
    listOfColumns();
    }
    void listOfColumns(){
        ArrayList<String> move = new ArrayList<String>();
        for(int x=0;x<PRODUCT_LIST.getColumnCount();x++){
            move.add(PRODUCT_LIST.getColumnName(x));
        }
            System.out.println(move);
    }
    public void filterTable(){
                int rowCount = PRODUCT_MODEL.getRowCount();
                //Remove all data row
                    for (int i = rowCount - 1; i >= 0; i--) {
                        PRODUCT_MODEL.removeRow(i);
                    }
                    
                    String setLoc = "Skins/C_extras/WARNING.png";
                    
        for(int x=0;x<dh.retrieveProduct_size();x++){
        boolean limitChecker = false;
        int limitConverter = Integer.parseInt(dh.retrieveLimit(x));
        int quantityConverter = Integer.parseInt(dh.retieveQuantity(x));

        if(limitConverter>=quantityConverter){
            setLoc = "Skins/C_extras/WARNING.png";
        }else{
            setLoc = null;
        }

        String setMy_quantity = dh.retieveQuantity(x);
        int convert_quantity = Integer.parseInt(setMy_quantity);
        if(convert_quantity <= 0){
            SQLiteDB_action sdb = SQLiteDB_action.getInstance();
            SQLiteDB sdbx = SQLiteDB.getInstance();
            sdb.remove_zero_quantity(dh.retrieveID(x));
            
            SQLiteDB sdb_main = SQLiteDB.getInstance();
            sdb_main.addOut_of_stock(dh.retrieveProduct_name(x),dh.retrieveBrand_name(x),
            "out_of_stock",dh.retrievePrice(x),dh.retrieveUnit(x),
            dh.retrievePurchase_from(x),dh.retrievePreparation(x));
                        
            sdbx.retrieveOut_of_stock_data();
            dh.removeID_if_quantity_is_zero(dh.retrieveID(x));
            sdbx.retrieveOut_of_stock_data();
        }else{
            
            String set = "";
            
            if(dh.retrievePrice(x).substring(0, 1).equals(".")){
                set = "0"+dh.retrievePrice(x);
                set = String.format("%.2f", Double.parseDouble(set));
            }else{
                set = dh.retrievePrice(x);
                set = String.format("%.2f", Double.parseDouble(set));
            }
            
            String setSelling_price = "";
            
            if(dh.retrieveSelling_price(x).substring(0, 1).equals(".")){
                setSelling_price = "0"+dh.retrieveSelling_price(x);
                setSelling_price = String.format("%.2f", Double.parseDouble(setSelling_price));
            }else{
                setSelling_price = dh.retrieveSelling_price(x);
                setSelling_price = String.format("%.2f", Double.parseDouble(setSelling_price));
            }
            
        PRODUCT_MODEL.addRow(new Object[]{new ImageIcon(setLoc),dh.retrieveID(x),dh.retrieveProduct_name(x),
        dh.retrieveBrand_name(x),dh.retrieveUnit(x),dh.retrievePreparation(x),dh.retrieveExpiration_date(x),
        dh.retrieveDays_left(x),set,setSelling_price,setMy_quantity,
        dh.retrieveDate_purchase(x),dh.retrievePurchase_from(x),dh.retrieveLimit(x)});
        }
    }

                PRODUCT_LIST.invalidate();
                PRODUCT_LIST.repaint();

                getNewRenderedTable(PRODUCT_LIST);
    }
    public static JTable getNewRenderedTable(final JTable table) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
//                String status = (String)table.getModel().getValueAt(row, 6);
            String status = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 7);
                Color stage_1 = Color.decode("#24ff00"); /* Expired today */
                Color stage_2 = Color.decode("#ffc000"); /* 30 days expiry */
                Color stage_3 = Color.decode("#ff3c00"); /* 60 days expiry */
                int result = Integer.parseInt(status);
                if(result >= 0 && result <= 59) { /* Mid Expiry**/
                    setBackground(stage_3);
//                    setForeground(Color.WHITE);
                }else if(result >= 60 && result <= 119){
                    setBackground(stage_2);                    
                }else if(result >= 120 && result <= 180){
                    setBackground(stage_1);
                }else{
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }       
                return this;
            }   
        });
        return table;
    }
    public void setText(){
                PURCHASE_FROMx.setText("-----");
                BRAND_NAMEx.setText("-----");
                PRODUCT_NAME_JLx.setText("-----");
                QUANTITY_JLx.setText("-----");
                DAYS_LEFT_JLx.setText("-----");
                EXPIRATION_JLx.setText("-----");
                PRICE_JLx.setText("-----");
                SELLING_PRICE_JLx.setText("-----");
                DATE_PURCHASE_JLx.setText("-----");
                UNIT_JLx.setText("-----");
                PREPARATION_JLx.setText("-----");
                PURCHASE_FROMx.setText("-----");
    }
    public void setProductsData(){
                out_of_stock_list o = out_of_stock_list.getInstance();
                SQLiteDB_action sdb = SQLiteDB_action.getInstance();
                sdb.updateOut_of_stock();
                setIcon_Icon();
                o.setIcon();
                PRODUCT_LIST .getSelectionModel().clearSelection();
    }
    void Listener(){
        OUT_OF_STOCK.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                out_of_stock_list o = out_of_stock_list.getInstance();
////                if(o.isVisible()){
////                    MainFrame mf = MainFrame.getInstance();
////                    o.centreWindow(mf);
////                    o.setVisible(false);
////                }else{
//                    MainFrame mf = MainFrame.getInstance();
//                    o.centreWindow(mf);
//                    o.setVisible(true);
////                    boolean isHoverInside;
////                try {
////                    TimeUnit.SECONDS.sleep(1);
////                } catch (InterruptedException ex) {
////                    Logger.getLogger(List_Panel.class.getName()).log(Level.SEVERE, null, ex);
////                }
////                    isHoverInside = o.isMouseWithinComponent();
////                    o.setVisibility(isHoverInside);
////                }
//                SQLiteDB_action sdb = SQLiteDB_action.getInstance();
//                sdb.updateOut_of_stock();
//                setIcon_Icon();
//                o.setIcon();
//                PRODUCT_LIST.getSelectionModel().clearSelection();
                
                //For new UI for out of stock and expired stock
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);
                dh.frameSetVisibleFalse();
                
                ExpireAndOutOfStockListFrame eos = new ExpireAndOutOfStockListFrame();
                eos.centreWindow(mf);
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
                out_of_stock_list s = out_of_stock_list.getInstance();
                s.setVisible(false);
            }
        });
        PRINT.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                  MessageFormat headerFormat = new MessageFormat("");
                  MessageFormat footerFormat = new MessageFormat("- {0} -");
                  PRODUCT_LIST.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
                } catch (PrinterException pe) {
                  System.err.println("Error printing: " + pe.getMessage());
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
                ImageIcon PRINT_II = new ImageIcon("Skins/C_buttons/PRINT_2.png");
                PRINT.setIcon(PRINT_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon PRINT_II = new ImageIcon("Skins/C_buttons/PRINT_1.png");
                PRINT.setIcon(PRINT_II);
            }
        });
        UPDATE_MENU.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Tester");
            }
        });
        
        UPDATE_MENU.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                update_product(e);
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
        DELETE_MENU.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, 
                        "Are you sure to remove?", "", dialogButton);
                if(dialogResult == 0) {
                int row = PRODUCT_LIST.getSelectedRow();
                String value = PRODUCT_LIST.getModel().getValueAt(row, 1).toString();
                try{
                db.remove_product_list(value);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"Database Error");
                }
                try{
                dh.deleteProduct_date(value);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"DataHolder Error");
                }

                Sale_Panel sp = Sale_Panel.getInstance();
                sp.set_Table_info();

                PURCHASE_FROMx.setText("-----");
                BRAND_NAMEx.setText("-----");
                PRODUCT_NAME_JLx.setText("-----");
                QUANTITY_JLx.setText("-----");
                DAYS_LEFT_JLx.setText("-----");
                EXPIRATION_JLx.setText("-----");
                PRICE_JLx.setText("-----");
                SELLING_PRICE_JLx.setText("-----");
                DATE_PURCHASE_JLx.setText("-----");
                UNIT_JLx.setText("-----");
                PREPARATION_JLx.setText("-----");                
                try{
                    filterTable();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex);
                    }                 
                } else {
                    }
            }
        });
        ADD_MENU.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                
                MainFrame mf = MainFrame.getInstance();
                NewProduct n = NewProduct.getInstance();
                n.centreWindow(mf);
                n.setVisible(true);
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
        ADD_SALE_DATA.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                Sale_Panel s = Sale_Panel.getInstance();
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
                ImageIcon sale_ii = new ImageIcon("Skins/C_buttons/"
                        + "SALE_LIST_2_BUTTON.png");
                ADD_SALE_DATA.setIcon(sale_ii);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon sale_ii = new ImageIcon("Skins/C_buttons/"
                        + "SALE_LIST_1_BUTTON.png");
                ADD_SALE_DATA.setIcon(sale_ii);
            }
        });
        SEARCH_TF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            if(SEARCH_TF.getText().equals("Search")){
            SEARCH_TF.setText(null);
            }else{
            SEARCH_TF.setText(SEARCH_TF.getText());
            }
            SEARCH_TF.setForeground(Color.BLACK);

                PURCHASE_FROMx.setText("-----");
                BRAND_NAMEx.setText("-----");
                PRODUCT_NAME_JLx.setText("-----");
                QUANTITY_JLx.setText("-----");
                DAYS_LEFT_JLx.setText("-----");
                EXPIRATION_JLx.setText("-----");
                PRICE_JLx.setText("-----");
                SELLING_PRICE_JLx.setText("-----");
                DATE_PURCHASE_JLx.setText("-----");
                UNIT_JLx.setText("-----");
                PREPARATION_JLx.setText("-----");               
                LIMITx.setText("-----");
            }
            @Override
            public void focusLost(FocusEvent e) {
            SEARCH_TF.setForeground(Color.GRAY);
            if(SEARCH_TF.getText().isEmpty()){
            SEARCH_TF.setText("Search");
            }else{
            SEARCH_TF.setText(SEARCH_TF.getText());
            }
            }
        });
        SEARCH_TF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
                String query = SEARCH_TF.getText();
                filter(query);
            }
        });
        PRODUCT_LIST.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = PRODUCT_LIST.getSelectedRow();
                row = PRODUCT_LIST.convertRowIndexToModel(row);                    
                String id = PRODUCT_LIST.getModel().getValueAt(row, 1).toString();
                String product_name = PRODUCT_LIST.getModel().getValueAt(row, 2).toString();
                String brand_name = PRODUCT_LIST.getModel().getValueAt(row, 3).toString();
                String quantity = PRODUCT_LIST.getModel().getValueAt(row, 10).toString();
                String days_left = PRODUCT_LIST.getModel().getValueAt(row, 7).toString();
                String expiration = PRODUCT_LIST.getModel().getValueAt(row, 6).toString();
                String price = PRODUCT_LIST.getModel().getValueAt(row, 8).toString();
                String selling_price = PRODUCT_LIST.getModel().getValueAt(row, 9).toString();
                String date_purchase = PRODUCT_LIST.getModel().getValueAt(row, 11).toString();
                String unit = PRODUCT_LIST.getModel().getValueAt(row, 4).toString();
                String preparation = PRODUCT_LIST.getModel().getValueAt(row, 5).toString();
                String purchase_from = PRODUCT_LIST.getModel().getValueAt(row, 12).toString();
                String limit = PRODUCT_LIST.getModel().getValueAt(row, 13).toString();
                
                PRODUCT_NAME_JLx.setText("<html> <h2>"+product_name+"</h2></html>");
                QUANTITY_JLx.setText("<html> <h2>"+quantity+"</h2></html>");
                DAYS_LEFT_JLx.setText("<html> <h2>"+days_left+"</h2></html>");
                EXPIRATION_JLx.setText("<html> <h2>"+expiration+"</h2></html>");
                PRICE_JLx.setText("<html> <h2>"+price+"</h2></html>");
                SELLING_PRICE_JLx.setText("<html> <h2>"+selling_price+"</h2></html>");
                DATE_PURCHASE_JLx.setText("<html> <h2>"+date_purchase+"</h2></html>");
                UNIT_JLx.setText("<html> <h2>"+unit+"</h2></html>");
                PREPARATION_JLx.setText("<html> <h2>"+preparation+"</h2></html>");
                PURCHASE_FROMx.setText("<html> <h2>"+purchase_from+"</h2></html>");
                BRAND_NAMEx.setText("<html> <h2>"+brand_name+"</h2></html>");
                LIMITx.setText("<html><h2>"+limit+"</h2></html>");
                
            String status = (String) PRODUCT_LIST.getModel().getValueAt(PRODUCT_LIST.convertRowIndexToModel(row), 7);

            int result = Integer.parseInt(status);
                if(result >= 0 && result <= 59) { /* Mid Expiry**/
                    product_info.setIcon(new ImageIcon("Skins/C_background/"
                        + "bg_product_info_1.png"));
                }else if(result >= 60 && result <= 119){
                    product_info.setIcon(new ImageIcon("Skins/C_background/"
                        + "bg_product_info_2.png"));
                }else if(result >= 120 && result <= 180){
                    product_info.setIcon(new ImageIcon("Skins/C_background/"
                        + "bg_product_info_3.png"));
                }else{
                    product_info.setIcon(new ImageIcon("Skins/C_background/"
                        + "bg_product_info_4.png"));
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
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        ADD_DATA.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new_product();
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon ADD_DATA_II = new ImageIcon("Skins/C_buttons/"
                + "ADD_LIST_2_BUTTON.png");
                ADD_DATA.setIcon(ADD_DATA_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon ADD_DATA_II = new ImageIcon("Skins/C_buttons/"
                + "ADD_LIST_1_BUTTON.png");
                ADD_DATA.setIcon(ADD_DATA_II);
            }
        });;
        DELETE_DATA.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    remove_product();
                }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon REMOVE_DATA_II = new ImageIcon("Skins/C_buttons/"
                        + "REMOVE_LIST_2_BUTTON.png");
                DELETE_DATA.setIcon(REMOVE_DATA_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon REMOVE_DATA_II = new ImageIcon("Skins/C_buttons/"
                        + "REMOVE_LIST_1_BUTTON.png");
                DELETE_DATA.setIcon(REMOVE_DATA_II);
            }
        });
        UPDATE_DATA.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                int selectedRow = PRODUCT_LIST.getSelectedRow();
//                selectedRow = PRODUCT_LIST.convertRowIndexToModel(selectedRow);                    
                update_product(e);
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon UPDATE_DATE_II = new ImageIcon("Skins/C_buttons/"
                        + "UPDATE_LIST_2_BUTTON.png");
                UPDATE_DATA.setIcon(UPDATE_DATE_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon UPDATE_DATE_II = new ImageIcon("Skins/C_buttons/"
                        + "UPDATE_LIST_1_BUTTON.png");
                UPDATE_DATA.setIcon(UPDATE_DATE_II);
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
                ImageIcon back_ii = new ImageIcon("Skins/C_buttons/"
                        + "BACK_LIST_2_BUTTON.png");
                BACK.setIcon(back_ii);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon back_ii = new ImageIcon("Skins/C_buttons/"
                        + "BACK_LIST_1_BUTTON.png");
                BACK.setIcon(back_ii);
            }
        });
    }
}