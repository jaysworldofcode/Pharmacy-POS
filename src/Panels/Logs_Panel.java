package Panels;

import DataBase.SQLiteDB;
import DataBase.SQLiteDB_action;
import DataHolder.DataHolder;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.print.PrinterException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.border.AbstractBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import msinventorysystem.VisitorRenderer;
import msinventorysystem.myDecimalConverter;

public class Logs_Panel extends JPanel{
    myDecimalConverter mDc = new myDecimalConverter();
    ArrayList<String> SET_TOTAL_PRICE = new ArrayList<String>();
    ArrayList<String> SET_TOTAL_QUANTITY = new ArrayList<String>();
    static float myTotal = 0;
    
    JPanel PRODUCT_INFO,EXPIRED_INFO,PRODUCT_NAME_PANEL;
    JLabel PRODUCT_NAME,QUANTITY,SELLING_PRICE,PAYMENT,DATE,TIME,CASHIER,
           PREPARATION,TRANS_ID,STRENGTH,
            EXPIRED_PRODUCT_NAME,EXPIRED_QUANTITY,EXPIRED_PRICE,EXPIRED_DATE_PURCHASE,EXPIRED_DATE,
            EXPIRED_PURCHASE_FROM,UNIT_PRICE,UNIT_PRICEx;
    String setSearch = "logs";
    JLabel SCANN_BY,SEARCH_JL;
    JTextField SEARCH_TF;
    JButton REFRESH,CLEAR_ALL;
    //FREE SCANN INFO
    
    JPanel FREE_SCANN_PANEL,FREE_MONTHLY_SCANN_JP,FREE_YEARLY_SCANN_JP;
    JButton FREE_MONTHLY_SCANN,FREE_YEARLY_SCANN,FREE_MONTHLY_FILTER,FREE_YEARLY_FILTER;
    JComboBox MONTH_SET_MONTH,MONTH_SET_YEAR,YEAR_SET_YEAR;
    JLabel TOTAL_FREE;
    //Set scann icon
    ImageIcon scann_ii = new ImageIcon("Skins/C_buttons/"
                        + "SCAN_LOGS_1.png");
    JButton BACK_jb,DAILY_PDF,DELETE;
    JComboBox MONTH,DAY,YEAR,PRODUCTS_SOLD,SCANN_INFO;
    JLabel SCANN_INFO_JL;
    JButton SCANN;
    JPanel MONTHLY_INFO,YEARLY_INFO,DAILY_INFO;
    JTable LOGS_LIST = new JTable();
    JScrollPane LOGS_PANE;
    JLabel TOTAL_QUANTITY_SOLD,PRODUCTS_SOLD_JL;
    JTextField TOTAL_INCOME,GROSS_INCOME;
    //YEARLY INFO
    JComboBox YEAR_CB;
    JButton YEAR_SCAN; 
    JLabel YEAR_TOTAL_QUANTITY,YEAR_TOTAL_INCOME,YEARLY_GROSS_INCOME;
    //MONTHLY INFO
    JComboBox MONTHLY_INFO_SET_MONTH,MONTHLY_INFO_SET_YEAR,MONTHLY_INFO_SET_PRODUCTS_SOLD;
    JLabel MONTHLY_TOTAL_QUANTITY,MONTHLY_TOTAL_INCOME,MONTHLY_PRODUCTS_SOLD,
               MONTHLY_GROSS_INCOME;
    JButton MONTHLY_SCANN;
//    JButton MONTHLY_PRINT;
    DefaultTableModel LOGS_MODEL;    
    //EXPIRED INFO
    DefaultTableModel EXPIRED_MODEL;
    JTable EXPIRED_LOGS = new JTable();
    JScrollPane EXPIRED_LOGS_JS;
    JButton EXPIRED_MONTHLY,EXPIRED_YEARLY,EXPIRED_MONTHLY_SCANN,EXPIRED_YEARLY_SCANN;
    JComboBox EXPIRED_MONTHLY_MONTH,EXPIRED_MONTHLY_YEAR,EXPIRED_YEARLY_YEAR;
    JPanel MONTHLY_JP,YEARY_JP,EXP_PRODUCT_INFO_PANEL;
    JLabel EXPIRED_TOTAL, EXP_PRICE,EXP_QUANTITY,EXP_PURCHASED_FROM,EXP_DATE_PUR,EXP_DATE_EXP,
           EXP_PREPARATION, EXP_STRENGTH;
    //Discount table
    JPanel DISCOUNT_PANEL,DISC_PRODUCT_INFO_PANEL;
    DefaultTableModel DISCOUNT_TABLE_MODEL;
    JTable DISCOUNT_TABLE = new JTable();
    JScrollPane DISCOUNT_PANE = new JScrollPane(DISCOUNT_TABLE);
    JButton DISCOUNT_MONTHLY,DISCOUNT_YEARLY,DISCOUNT_MONTHLY_SCANN,DISCOUNT_YEARLY_SCANN;
    JComboBox DISCOUNT_MONTHLY_MONTH,DISCOUNT_MONTHLY_YEAR,DISCOUNT_YEARLY_YEAR;
    JPanel DISCOUNT_MONTHLY_JP,DISCOUNT_YEARY_JP,DISCOUNT_INFO;
    JLabel DISCOUNT_TOTAL,DISC_QUANTITY,DISC_DATE,DISC_CASHIER,DISC_DISCOUNT,DISC_DISCOUNTED_PRICE,
           DISC_PREPARATION,DISC_STRENGTH,DISC_UNIT_PRICE,DISC_DISCOUNTED_PERCENT;

    Font SETFONT = new Font("SansSerif", Font.BOLD, 17);
    Font SETFONTx = new Font("SansSerif", Font.PLAIN, 17);
    Font COMBO_FONT = new Font("SansSerif", Font.PLAIN, 17);
    Font myData_font = new Font("SansSerif", Font.PLAIN, 24);
    
    DataHolder dh = DataHolder.getInstance();
    private static Logs_Panel instance = null;
    
    public static Logs_Panel getInstance() {
      if(instance == null) {
         instance = new Logs_Panel();
      }
      return instance;
    }
        ////
        ArrayList<String> setLoc = new ArrayList<String>();
        ////
    Logs_Panel(){
        this.setBounds(0,100,1100,600);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setVisible(false);
        Organizer();
    }
    
    void Organizer(){
        setLoc.add("yearly_income_bg");
        setLoc.add("yearly_income_bg_2");
        setLoc.add("monthly_income_bg");
        setLoc.add("monthly_income_bg_2");
        setLoc.add("daily_income_bg");
        setLoc.add("daily_income_bg_2");
        setLoc.add("expired_bg");
        setLoc.add("expired_bg_2");
        setLoc.add("free_bg");
        setLoc.add("free_bg_2");
        setLoc.add("discount_bg");
        setLoc.add("discount_bg_2");
        Components();
        productTable();
        expiredTable();
        discountTable();
        Listener();
    }
    void Components(){
//        UIManager.put("ComboBox.border", new RoundedCornerBorder());
        BACK_jb = new JButton("BACK");
        BACK_jb.setBounds(10,10,70,20);
        BACK_jb.setOpaque(false);
        BACK_jb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        BACK_jb.setContentAreaFilled(false);
        BACK_jb.setFocusable(false);
        BACK_jb.setBorderPainted(false);
//        this.add(BACK_jb);
        
        SCANN_BY = new JLabel("View by:");
        SCANN_BY.setBounds(10,10,70,25);
        this.add(SCANN_BY);
        
        ImageIcon DELETE_II = new ImageIcon("Skins/C_buttons/delete.png");
        DELETE = new JButton(DELETE_II);
        DELETE.setBounds(280,0,50,50);
        DELETE.setOpaque(false);
        DELETE.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DELETE.setToolTipText("Delete the data selected");
        DELETE.setContentAreaFilled(false);
        DELETE.setFocusable(false);
        DELETE.setBorderPainted(false);
        DELETE.setFont(SETFONT);
        this.add(DELETE);
        
        DELETE.addMouseListener(new MouseListener() {
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
                DELETE.setIcon(new ImageIcon("Skins/C_buttons/delete_2.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                DELETE.setIcon(new ImageIcon("Skins/C_buttons/delete.png"));
            }
        });
        
        ImageIcon PRINT_II = new ImageIcon("Skins/C_buttons/set_print.png");
        DAILY_PDF = new JButton(PRINT_II);
        DAILY_PDF.setBounds(240,0,50,50);
        DAILY_PDF.setOpaque(false);
        DAILY_PDF.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DAILY_PDF.setToolTipText("Print");
        DAILY_PDF.setContentAreaFilled(false);
        DAILY_PDF.setFocusable(false);
        DAILY_PDF.setBorderPainted(false);
        DAILY_PDF.setFont(SETFONT);
        this.add(DAILY_PDF);
        
        DAILY_PDF.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(setSearch.equals("logs")){
                    MessageFormat headerFormat = new MessageFormat("Product list");
                    MessageFormat footerFormat = new MessageFormat("- {0} -");
                    try {
                        LOGS_LIST.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
                    } catch (PrinterException ex) {
                        Logger.getLogger(Logs_Panel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(setSearch.equalsIgnoreCase("free")){
                    MessageFormat headerFormat = new MessageFormat("Free list");
                    MessageFormat footerFormat = new MessageFormat("- {0} -");
                    try {
                        LOGS_LIST.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
                    } catch (PrinterException ex) {
                        Logger.getLogger(Logs_Panel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else if(setSearch.equals("discount")){
                    MessageFormat headerFormat = new MessageFormat("Discount list");
                    MessageFormat footerFormat = new MessageFormat("- {0} -");
                    try {
                        DISCOUNT_TABLE.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
                    } catch (PrinterException ex) {
                        Logger.getLogger(Logs_Panel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    MessageFormat headerFormat = new MessageFormat("Expired list");
                    MessageFormat footerFormat = new MessageFormat("- {0} -");
                    try {
                        EXPIRED_LOGS.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
                    } catch (PrinterException ex) {
                        Logger.getLogger(Logs_Panel.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
                        DAILY_PDF.setIcon(new ImageIcon("Skins/C_buttons/set_print_2.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                        DAILY_PDF.setIcon(new ImageIcon("Skins/C_buttons/set_print.png"));
            }
        });

        CLEAR_ALL = new JButton("Clear all");
        CLEAR_ALL.setBounds(645,10,150,30);
        CLEAR_ALL.setFont(SETFONT);
//        this.add(CLEAR_ALL);
        
        ImageIcon SEARCH_II = new ImageIcon("Skins/C_extras/SEARCH.png");
        SEARCH_JL = new JLabel(SEARCH_II);
        SEARCH_JL.setBounds(830,15,25,25);
//        this.add(SEARCH_JL);
        
        JLabel text_panel = new JLabel(new ImageIcon("Skins/C_background/"
                    + "bg_search_1.png"));
        text_panel.setBounds(820,5,240,45);
//        DAILY_INFO.setLayout(null);
//        DAILY_INFO.setOpaque(false);
        this.add(text_panel);
        
        SEARCH_TF = new JTextField("Search");
        SEARCH_TF.setBounds(10,6,190,30);
        SEARCH_TF.setForeground(Color.GRAY);
        SEARCH_TF.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        text_panel.add(SEARCH_TF);
        
        SEARCH_TF.addMouseListener(new MouseListener() {
            public  void mouseEntered(java.awt.event.MouseEvent evt) {
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
        
        ImageIcon refresh_ii = new ImageIcon("Skins/C_buttons/REFRESH_1_BUTTON.png");
        REFRESH = new JButton(refresh_ii);
        REFRESH.setBounds(1060,10,30,30);
        REFRESH.setOpaque(false);
        REFRESH.setCursor(new Cursor(Cursor.HAND_CURSOR));
        REFRESH.setContentAreaFilled(false);
        REFRESH.setFocusable(false);
        REFRESH.setBorderPainted(false);
        this.add(REFRESH);
        
//                LOGS_LIST = new JTable(){
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
//            }
//          };
        LOGS_PANE = new JScrollPane(LOGS_LIST);
        JScrollBar xb_xx = LOGS_PANE.getVerticalScrollBar();
        LOGS_PANE.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
//        xb_xx.setUI(new Skins.LookAndFeel.MyScrollbarUI());
        LOGS_PANE.setBounds(5,50,1080,300);
        LOGS_PANE.setBorder(createEmptyBorder());
        LOGS_PANE.getViewport().setOpaque(false);
        LOGS_PANE.setOpaque(false);
        this.add(LOGS_PANE);
        
//        EXPIRED_LOGS {
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
//            }
//          };
        
        FREE_SCANN_PANEL = new JPanel();
        FREE_SCANN_PANEL.setBounds(10,370,350,200);
        FREE_SCANN_PANEL.setLayout(null);
        FREE_SCANN_PANEL.setOpaque(false);
//        MONTHLY_INFO.setBorder(BorderFactory.createTitledBorder("Monthly Income"));
//        FREE_SCANN_PANEL.setBorder(BorderFactory.createTitledBorder(null, null, TitledBorder.LEFT,
//                TitledBorder.BOTTOM, new Font("Segoe UI",Font.BOLD,14), Color.GRAY));
//        FREE_SCANN_PANEL.setBackground(Color.WHITE);
        FREE_SCANN_PANEL.setVisible(false);
        this.add(FREE_SCANN_PANEL);

        ImageIcon FREE_MONTHLY_II = new ImageIcon("Skins/C_buttons/MONTHLY_II.png");
        FREE_MONTHLY_SCANN = new JButton(FREE_MONTHLY_II);
        FREE_MONTHLY_SCANN.setBounds(70,20,100,30);
        FREE_MONTHLY_SCANN.setOpaque(false);
        FREE_MONTHLY_SCANN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        FREE_MONTHLY_SCANN.setContentAreaFilled(false);
        FREE_MONTHLY_SCANN.setFocusable(false);
        FREE_MONTHLY_SCANN.setBorderPainted(false);
        FREE_SCANN_PANEL.add(FREE_MONTHLY_SCANN);
        
        ImageIcon FREE_YEARLY_II = new ImageIcon("Skins/C_buttons/YEARLY_I.png");
        FREE_YEARLY_SCANN = new JButton(FREE_YEARLY_II);
        FREE_YEARLY_SCANN.setBounds(175,20,100,30);
        FREE_YEARLY_SCANN.setOpaque(false);
        FREE_YEARLY_SCANN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        FREE_YEARLY_SCANN.setContentAreaFilled(false);
        FREE_YEARLY_SCANN.setFocusable(false);
        FREE_YEARLY_SCANN.setBorderPainted(false);
        FREE_SCANN_PANEL.add(FREE_YEARLY_SCANN);
        
        FREE_MONTHLY_SCANN_JP = new JPanel();
        FREE_MONTHLY_SCANN_JP.setBounds(5,64,350,30);
        FREE_MONTHLY_SCANN_JP.setOpaque(false);
        FREE_MONTHLY_SCANN_JP.setLayout(null);
        FREE_SCANN_PANEL.add(FREE_MONTHLY_SCANN_JP);
        
        MONTH_SET_MONTH = new JComboBox();
        MONTH_SET_MONTH.setBounds(163, 5, 100, 20);
        MONTH_SET_MONTH.setFont(COMBO_FONT);
        MONTH_SET_MONTH .setOpaque(false);
        MONTH_SET_MONTH .setEditable(true);
        MONTH_SET_MONTH.setUI(new BasicComboBoxUI());
        MONTH_SET_MONTH.setBorder(BorderFactory.createEmptyBorder());
        MONTH_SET_MONTH.setBackground(new Color(0, 0, 0, 0));
        MONTH_SET_MONTH.setFocusable(false);
        FREE_MONTHLY_SCANN_JP.add(MONTH_SET_MONTH);
        
        MONTH_SET_YEAR = new JComboBox();
        MONTH_SET_YEAR.setBounds(60,5,95,20);
        MONTH_SET_YEAR.setFont(COMBO_FONT);
        MONTH_SET_YEAR .setOpaque(false);
        MONTH_SET_YEAR .setEditable(true);
        MONTH_SET_YEAR.setUI(new BasicComboBoxUI());
        MONTH_SET_YEAR.setBorder(BorderFactory.createEmptyBorder());
        MONTH_SET_YEAR.setBackground(new Color(0, 0, 0, 0));
        MONTH_SET_YEAR.setFocusable(false);
        FREE_MONTHLY_SCANN_JP.add(MONTH_SET_YEAR);
        
        FREE_MONTHLY_FILTER = new JButton(new ImageIcon("Skins/C_buttons/"
                + "scann_1.png"));
        FREE_MONTHLY_FILTER.setBounds(286,0,30,30);
        FREE_MONTHLY_FILTER.setOpaque(false);
        FREE_MONTHLY_FILTER.setCursor(new Cursor(Cursor.HAND_CURSOR));
        FREE_MONTHLY_FILTER.setContentAreaFilled(false);
        FREE_MONTHLY_FILTER.setFocusable(false);
        FREE_MONTHLY_FILTER.setBorderPainted(false);
        FREE_MONTHLY_FILTER.setFont(COMBO_FONT);
        setFilter_listener(FREE_MONTHLY_FILTER);
        FREE_MONTHLY_SCANN_JP.add(FREE_MONTHLY_FILTER);
        
        MONTH_SET_MONTH.addItem("January");
        MONTH_SET_MONTH.addItem("February");
        MONTH_SET_MONTH.addItem("March");
        MONTH_SET_MONTH.addItem("April");
        MONTH_SET_MONTH.addItem("May");
        MONTH_SET_MONTH.addItem("June");
        MONTH_SET_MONTH.addItem("July");
        MONTH_SET_MONTH.addItem("August");
        MONTH_SET_MONTH.addItem("September");
        MONTH_SET_MONTH.addItem("October");
        MONTH_SET_MONTH.addItem("November");
        MONTH_SET_MONTH.addItem("December");
        
        for(int x=2019;x<2030;x++){
            MONTH_SET_YEAR.addItem(x);
        }
        
        FREE_YEARLY_SCANN_JP = new JPanel();
        FREE_YEARLY_SCANN_JP.setBounds(5,64,350,30);
        FREE_YEARLY_SCANN_JP.setOpaque(false);
        FREE_YEARLY_SCANN_JP.setVisible(false);
        FREE_YEARLY_SCANN_JP.setLayout(null);
        FREE_SCANN_PANEL.add(FREE_YEARLY_SCANN_JP);

        YEAR_SET_YEAR = new JComboBox();
        YEAR_SET_YEAR.setBounds(163,5,100,20);
        YEAR_SET_YEAR.setFont(COMBO_FONT);
        YEAR_SET_YEAR.setOpaque(false);
        YEAR_SET_YEAR.setEditable(true);
        YEAR_SET_YEAR.setUI(new BasicComboBoxUI());
        YEAR_SET_YEAR.setBorder(BorderFactory.createEmptyBorder());
        YEAR_SET_YEAR.setBackground(new Color(0, 0, 0, 0));
        YEAR_SET_YEAR.setFocusable(false);
        YEAR_SET_YEAR.setFont(COMBO_FONT);
        FREE_YEARLY_SCANN_JP.add(YEAR_SET_YEAR);

        FREE_YEARLY_FILTER = new JButton(new ImageIcon("Skins/C_buttons/"
                + "scann_1.png"));
        FREE_YEARLY_FILTER.setBounds(286,0,30,30);
        FREE_YEARLY_FILTER.setOpaque(false);
        FREE_YEARLY_FILTER.setCursor(new Cursor(Cursor.HAND_CURSOR));
        FREE_YEARLY_FILTER.setContentAreaFilled(false);
        FREE_YEARLY_FILTER.setFocusable(false);
        FREE_YEARLY_FILTER.setBorderPainted(false);
        FREE_YEARLY_FILTER.setFont(COMBO_FONT);
        setFilter_listener(FREE_YEARLY_FILTER);
        FREE_YEARLY_FILTER.setFont(COMBO_FONT);
        FREE_YEARLY_SCANN_JP.add(FREE_YEARLY_FILTER);
        
        for(int x=2019;x<2030;x++){
            YEAR_SET_YEAR.addItem(x);
        }
        
        TOTAL_FREE = new JLabel("");
        TOTAL_FREE.setBounds(150,120,500,30);
        TOTAL_FREE.setHorizontalTextPosition(JLabel.CENTER);
        TOTAL_FREE.setVerticalTextPosition(JLabel.CENTER);
        TOTAL_FREE.setForeground(new Color(0, 184, 212));
        TOTAL_FREE.setFont(myData_font);
        FREE_SCANN_PANEL.add(TOTAL_FREE);
        
        JLabel free_bg = new JLabel(new ImageIcon("Skins/C_background/"
                + "free_bg.png"));
        free_bg.setBounds(0,0,350,200);
        FREE_SCANN_PANEL.add(free_bg);
        
        setBackground_data(EXPIRED_YEARLY,EXPIRED_INFO,"free",free_bg);
        setBackground_data(FREE_MONTHLY_FILTER,FREE_MONTHLY_SCANN_JP,"free",free_bg);
        setBackground_data(FREE_YEARLY_FILTER,FREE_YEARLY_SCANN_JP,"free",free_bg);
        setBackground_data(null,FREE_SCANN_PANEL,"free",free_bg);
        setBackground_data(FREE_YEARLY_SCANN,null,"free",free_bg);
                
        EXPIRED_LOGS_JS = new JScrollPane(EXPIRED_LOGS);
        JScrollBar expired_scroll = EXPIRED_LOGS_JS.getVerticalScrollBar();
        EXPIRED_LOGS_JS.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
//        expired_scroll.setUI(new Skins.LookAndFeel.MyScrollbarUI());
        EXPIRED_LOGS_JS.setBounds(5,50,1080,300);
        EXPIRED_LOGS_JS.setVisible(false);
        EXPIRED_LOGS_JS.setBorder(createEmptyBorder());
        EXPIRED_LOGS_JS.getViewport().setOpaque(false);
        EXPIRED_LOGS_JS.setOpaque(false);
        this.add(EXPIRED_LOGS_JS);
        
        PRODUCT_INFO = new JPanel();
        PRODUCT_INFO.setBounds(365,420,650,200);
//        PRODUCT_INFO.setBorder(BorderFactory.createTitledBorder("Product Info"));
//        PRODUCT_INFO.setBorder(BorderFactory.createTitledBorder(null, "Product Info", TitledBorder.LEFT,
//                TitledBorder.BOTTOM, new Font("Segoe UI",Font.BOLD,14), Color.BLACK));
        PRODUCT_INFO.setBackground(Color.WHITE);
        PRODUCT_INFO.setLayout(null);
        this.add(PRODUCT_INFO);
                
        JLabel SET_TRANSACTION_ID = new JLabel("Transaction ID: ");
        SET_TRANSACTION_ID.setBounds(10,70,150,25);
        SET_TRANSACTION_ID.setFont(SETFONT);
        SET_TRANSACTION_ID.setForeground(Color.DARK_GRAY);
        PRODUCT_INFO.add(SET_TRANSACTION_ID);
        
        TRANS_ID = new JLabel("");
        TRANS_ID.setBounds(160,70,150,25);
        TRANS_ID.setFont(SETFONTx);
        PRODUCT_INFO.add(TRANS_ID);
        
        JLabel SET_SELLING_PRICE = new JLabel("Selling price");
        SET_SELLING_PRICE.setBounds(300,35,150,25);
        SET_SELLING_PRICE.setFont(SETFONT);
        SET_SELLING_PRICE.setForeground(Color.DARK_GRAY);
        PRODUCT_INFO.add(SET_SELLING_PRICE);
       
        SELLING_PRICE = new JLabel("");
        SELLING_PRICE.setBounds(420,35,150,25);
        SELLING_PRICE.setFont(SETFONTx);
        PRODUCT_INFO.add(SELLING_PRICE);
        
        UNIT_PRICE = new JLabel("Unit Price");
        UNIT_PRICE.setBounds(300,0,150,25);
        UNIT_PRICE.setForeground(Color.DARK_GRAY);
        UNIT_PRICE.setFont(SETFONT);
        PRODUCT_INFO.add(UNIT_PRICE);
        
        UNIT_PRICEx = new JLabel("");
        UNIT_PRICEx.setBounds(420,0,150,25);
        UNIT_PRICEx.setFont(SETFONTx);
        PRODUCT_INFO.add(UNIT_PRICEx);
        
        JLabel SET_PAYMENT = new JLabel("Payment");
        SET_PAYMENT.setBounds(300,105,150,30);
        SET_PAYMENT.setForeground(Color.DARK_GRAY);
        SET_PAYMENT.setFont(SETFONT);
        PRODUCT_INFO.add(SET_PAYMENT);

        PAYMENT = new JLabel("");
        PAYMENT.setBounds(420,105,200,25);
        PAYMENT.setFont(SETFONTx);
        PRODUCT_INFO.add(PAYMENT);
                
        JLabel SET_QUANTITY = new JLabel("Quantity");
        SET_QUANTITY.setBounds(300,70,150,25);
        SET_QUANTITY.setForeground(Color.DARK_GRAY);
        SET_QUANTITY.setFont(SETFONT);
        PRODUCT_INFO.add(SET_QUANTITY);
        
        QUANTITY = new JLabel("");
        QUANTITY.setBounds(420,70,150,25);
        QUANTITY.setFont(SETFONTx);
        PRODUCT_INFO.add(QUANTITY);
        
        JLabel SET_STRENGTH = new JLabel("Strength");
        SET_STRENGTH.setBounds(10,0,150,30);
        SET_STRENGTH.setForeground(Color.DARK_GRAY);
        SET_STRENGTH.setFont(SETFONT);
        PRODUCT_INFO.add(SET_STRENGTH);

        STRENGTH = new JLabel("");
        STRENGTH.setBounds(160,0,200,25);
        STRENGTH.setFont(SETFONTx);
        PRODUCT_INFO.add(STRENGTH);
        
        PRODUCT_NAME_PANEL = new JPanel();
        PRODUCT_NAME_PANEL.setLayout(null);
        PRODUCT_NAME_PANEL.setBounds(365,370,650,40);
        PRODUCT_NAME_PANEL.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        this.add(PRODUCT_NAME_PANEL);
        
        JLabel SET_PRODUCT_NAME = new JLabel("Product name: ");
        SET_PRODUCT_NAME.setBounds(10,2,150,30);
        SET_PRODUCT_NAME.setForeground(Color.DARK_GRAY);
        SET_PRODUCT_NAME.setFont(SETFONT);
        PRODUCT_NAME_PANEL.add(SET_PRODUCT_NAME);
        
        PRODUCT_NAME = new JLabel("");
        PRODUCT_NAME.setFont(SETFONTx);
        PRODUCT_NAME.setBounds(160,2,650,30);
        PRODUCT_NAME_PANEL.add(PRODUCT_NAME);

//        JLabel tester = new JLabel("<html><hr></hr></html>");
//        tester.setBounds(30,5,200,200);
//        PRODUCT_INFO.add(tester);
        
        JLabel SET_CASHIER = new JLabel("Cashier");
        SET_CASHIER.setBounds(10,140,200,25);
        SET_CASHIER.setForeground(Color.DARK_GRAY);
        SET_CASHIER.setFont(SETFONT);
        PRODUCT_INFO.add(SET_CASHIER);
        
        CASHIER = new JLabel("");
        CASHIER.setFont(SETFONTx);
        CASHIER.setBounds(160,140,300,25);
        PRODUCT_INFO.add(CASHIER);
        
        JLabel SET_PREPARATION = new JLabel("Preparation:");
        SET_PREPARATION.setBounds(10,35,150,25);
        SET_PREPARATION.setForeground(Color.DARK_GRAY);
        SET_PREPARATION.setFont(SETFONT);
        PRODUCT_INFO.add(SET_PREPARATION);
        
        PREPARATION = new JLabel("");
        PREPARATION.setFont(SETFONTx);
        PREPARATION.setBounds(160,35,150,25);
        PRODUCT_INFO.add(PREPARATION);
        
        JLabel DATE_PURCHASED = new JLabel("Date & time");
        DATE_PURCHASED.setFont(SETFONT);
        DATE_PURCHASED.setForeground(Color.DARK_GRAY);
        DATE_PURCHASED.setBounds(10,105,200,25);
        PRODUCT_INFO.add(DATE_PURCHASED);
        
        DATE = new JLabel("");
        DATE.setBounds(160,105,300,25);
        DATE.setFont(SETFONTx);
        PRODUCT_INFO.add(DATE);

        EXP_PRODUCT_INFO_PANEL = new JPanel();
        EXP_PRODUCT_INFO_PANEL.setLayout(null);
        EXP_PRODUCT_INFO_PANEL.setBounds(370,415,1000,500);
        EXP_PRODUCT_INFO_PANEL.setOpaque(false);
        this.add(EXP_PRODUCT_INFO_PANEL);

        JLabel SET_EXP_PRICE = new JLabel("Selling Price");
        SET_EXP_PRICE.setBounds(0,120,150,25);
        SET_EXP_PRICE.setForeground(Color.DARK_GRAY);
        SET_EXP_PRICE.setFont(SETFONT);
        EXP_PRODUCT_INFO_PANEL.add(SET_EXP_PRICE);
                
        EXP_PRICE = new JLabel("");
        EXP_PRICE.setBounds(150,120,150,25);
        EXP_PRICE.setFont(SETFONTx);
        EXP_PRODUCT_INFO_PANEL.add(EXP_PRICE);

        JLabel SET_EXP_QUANTITY = new JLabel("Quantity");
        SET_EXP_QUANTITY.setBounds(0,90,150,25);
        SET_EXP_QUANTITY.setForeground(Color.DARK_GRAY);
        SET_EXP_QUANTITY.setFont(SETFONT);
        EXP_PRODUCT_INFO_PANEL.add(SET_EXP_QUANTITY);
        
        EXP_QUANTITY = new JLabel("");
        EXP_QUANTITY.setBounds(150,90,150,25);
        EXP_QUANTITY.setFont(SETFONTx);
        EXP_PRODUCT_INFO_PANEL.add(EXP_QUANTITY);
        
        JLabel SET_PURCHASED_FROM = new JLabel("Purchased from");
        SET_PURCHASED_FROM.setBounds(250,0,150,25);
        SET_PURCHASED_FROM.setForeground(Color.DARK_GRAY);
        SET_PURCHASED_FROM.setFont(SETFONT);
        EXP_PRODUCT_INFO_PANEL.add(SET_PURCHASED_FROM);
        
        EXP_PURCHASED_FROM = new JLabel("");
        EXP_PURCHASED_FROM.setBounds(400,0,150,25);
        EXP_PURCHASED_FROM.setFont(SETFONTx);
        EXP_PRODUCT_INFO_PANEL.add(EXP_PURCHASED_FROM);
        
        JLabel SET_DATE_PUR = new JLabel("Date purchased");
        SET_DATE_PUR.setBounds(250,30,150,25);
        SET_DATE_PUR.setForeground(Color.DARK_GRAY);
        SET_DATE_PUR.setFont(SETFONT);
        EXP_PRODUCT_INFO_PANEL.add(SET_DATE_PUR);
        
        EXP_DATE_PUR = new JLabel("");
        EXP_DATE_PUR.setBounds(400,30,150,25);
        EXP_DATE_PUR.setFont(SETFONTx);
        EXP_PRODUCT_INFO_PANEL.add(EXP_DATE_PUR);
        
        JLabel SET_DATE_EXP = new JLabel("Expire Date");
        SET_DATE_EXP.setForeground(Color.DARK_GRAY);
        SET_DATE_EXP.setBounds(0,60,150,25);
        SET_DATE_EXP.setFont(SETFONT);
        EXP_PRODUCT_INFO_PANEL.add(SET_DATE_EXP);
        
        EXP_DATE_EXP = new JLabel("");
        EXP_DATE_EXP.setBounds(150,60,150,25);
        EXP_DATE_EXP.setFont(SETFONTx);
        EXP_PRODUCT_INFO_PANEL.add(EXP_DATE_EXP);
        
        JLabel SET_PREPARATION_EXP = new JLabel("Preparation");
        SET_PREPARATION_EXP.setForeground(Color.DARK_GRAY);
        SET_PREPARATION_EXP.setBounds(0,30,150,25);
        SET_PREPARATION_EXP.setFont(SETFONT);
        EXP_PRODUCT_INFO_PANEL.add(SET_PREPARATION_EXP);
        
        EXP_PREPARATION = new JLabel("");
        EXP_PREPARATION.setBounds(150,30,150,25);
        EXP_PREPARATION.setFont(SETFONTx);
        EXP_PRODUCT_INFO_PANEL.add(EXP_PREPARATION);
        
        JLabel SET_STRENGTH_PREPARATION = new JLabel("Strength");
        SET_STRENGTH_PREPARATION.setForeground(Color.DARK_GRAY);
        SET_STRENGTH_PREPARATION.setBounds(0,0,150,25);
        SET_STRENGTH_PREPARATION.setFont(SETFONT);
        EXP_PRODUCT_INFO_PANEL.add(SET_STRENGTH_PREPARATION);
        
        EXP_STRENGTH = new JLabel("");
        EXP_STRENGTH.setBounds(150,0,150,25);
        EXP_STRENGTH.setFont(SETFONTx);
        EXP_PRODUCT_INFO_PANEL.add(EXP_STRENGTH);
//        x
        EXPIRED_INFO = new JPanel();
        EXPIRED_INFO.setLayout(null);
        EXPIRED_INFO.setBounds(10,370,350,200);
        EXPIRED_INFO.setLayout(null);
        EXPIRED_INFO.setOpaque(false);
//        EXPIRED_INFO.setOpaque(false);
        EXPIRED_INFO.setLayout(null);
        EXPIRED_INFO.setVisible(false);
        this.add(EXPIRED_INFO);
        
        ImageIcon EXPIRED_MONTHLY_II = new ImageIcon("Skins/C_buttons/MONTHLY_II.png");
        EXPIRED_MONTHLY = new JButton(EXPIRED_MONTHLY_II);
        EXPIRED_MONTHLY.setBounds(70,20,100,30);
        EXPIRED_MONTHLY.setOpaque(false);
        EXPIRED_MONTHLY.setCursor(new Cursor(Cursor.HAND_CURSOR));
        EXPIRED_MONTHLY.setContentAreaFilled(false);
        EXPIRED_MONTHLY.setFocusable(false);
        EXPIRED_MONTHLY.setBorderPainted(false);
//        EXPIRED_MONTHLY.setFont(SETFONT);
        EXPIRED_INFO.add(EXPIRED_MONTHLY);
        
        ImageIcon EXPIRED_YEARLY_II = new ImageIcon("Skins/C_buttons/YEARLY_I.png");
        EXPIRED_YEARLY = new JButton(EXPIRED_YEARLY_II);
        EXPIRED_YEARLY.setBounds(175,20,100,30);
        EXPIRED_YEARLY.setOpaque(false);
        EXPIRED_YEARLY.setCursor(new Cursor(Cursor.HAND_CURSOR));
        EXPIRED_YEARLY.setContentAreaFilled(false);
        EXPIRED_YEARLY.setFocusable(false);
        EXPIRED_YEARLY.setBorderPainted(false);
        EXPIRED_YEARLY.setFont(SETFONT);
        EXPIRED_INFO.add(EXPIRED_YEARLY);
        
        MONTHLY_JP = new JPanel();
        MONTHLY_JP.setBounds(5,64,350,30);
        MONTHLY_JP.setLayout(null);
        MONTHLY_JP.setOpaque(false);
        EXPIRED_INFO.add(MONTHLY_JP);

        EXPIRED_MONTHLY_MONTH = new JComboBox();
        EXPIRED_MONTHLY_MONTH.setBounds(60,5,95,20);
        EXPIRED_MONTHLY_MONTH.setFont(COMBO_FONT);
        EXPIRED_MONTHLY_MONTH .setOpaque(false);
        EXPIRED_MONTHLY_MONTH .setEditable(true);
        EXPIRED_MONTHLY_MONTH.setUI(new BasicComboBoxUI());
        EXPIRED_MONTHLY_MONTH.setBorder(BorderFactory.createEmptyBorder());
        EXPIRED_MONTHLY_MONTH.setBackground(new Color(0, 0, 0, 0));
        EXPIRED_MONTHLY_MONTH.setFocusable(false);
        MONTHLY_JP.add(EXPIRED_MONTHLY_MONTH);
        
        EXPIRED_MONTHLY_YEAR = new JComboBox();
        EXPIRED_MONTHLY_YEAR.setBounds(163,5,100,20);
        EXPIRED_MONTHLY_YEAR.setFont(COMBO_FONT);
        EXPIRED_MONTHLY_YEAR .setOpaque(false);
        EXPIRED_MONTHLY_YEAR .setEditable(true);
        EXPIRED_MONTHLY_YEAR.setUI(new BasicComboBoxUI());
        EXPIRED_MONTHLY_YEAR.setBorder(BorderFactory.createEmptyBorder());
        EXPIRED_MONTHLY_YEAR.setBackground(new Color(0, 0, 0, 0));
        EXPIRED_MONTHLY_YEAR.setFocusable(false);
        EXPIRED_MONTHLY_YEAR.setFont(COMBO_FONT);
        MONTHLY_JP.add(EXPIRED_MONTHLY_YEAR);

        EXPIRED_MONTHLY_SCANN = new JButton(new ImageIcon("Skins/C_buttons/"
                + "scann_1.png"));
        EXPIRED_MONTHLY_SCANN.setBounds(286,0,30,30);
        EXPIRED_MONTHLY_SCANN.setOpaque(false);
        EXPIRED_MONTHLY_SCANN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        EXPIRED_MONTHLY_SCANN.setContentAreaFilled(false);
        EXPIRED_MONTHLY_SCANN.setFocusable(false);
        EXPIRED_MONTHLY_SCANN.setBorderPainted(false);
        EXPIRED_MONTHLY_SCANN.setFont(COMBO_FONT);
        setFilter_listener(EXPIRED_MONTHLY_SCANN);
        MONTHLY_JP.add(EXPIRED_MONTHLY_SCANN);
        
        EXPIRED_MONTHLY_MONTH.addItem("January");
        EXPIRED_MONTHLY_MONTH.addItem("February");
        EXPIRED_MONTHLY_MONTH.addItem("March");
        EXPIRED_MONTHLY_MONTH.addItem("April");
        EXPIRED_MONTHLY_MONTH.addItem("May");
        EXPIRED_MONTHLY_MONTH.addItem("June");
        EXPIRED_MONTHLY_MONTH.addItem("July");
        EXPIRED_MONTHLY_MONTH.addItem("August");
        EXPIRED_MONTHLY_MONTH.addItem("September");
        EXPIRED_MONTHLY_MONTH.addItem("October");
        EXPIRED_MONTHLY_MONTH.addItem("November");
        EXPIRED_MONTHLY_MONTH.addItem("December");
        
        for(int x=2019;x<2030;x++){
            EXPIRED_MONTHLY_YEAR.addItem(x);
        }
        
        YEARY_JP = new JPanel();
        YEARY_JP.setBounds(5,64,350,30);
//        YEARY_JP.setBackground(Color.YELLOW);
        YEARY_JP.setVisible(false);
        YEARY_JP.setLayout(null);
        YEARY_JP.setOpaque(false);
        EXPIRED_INFO.add(YEARY_JP);

        EXPIRED_YEARLY_YEAR = new JComboBox();
        EXPIRED_YEARLY_YEAR.setBounds(163,5,100,20);
        EXPIRED_YEARLY_YEAR.setFont(COMBO_FONT);
        EXPIRED_YEARLY_YEAR .setOpaque(false);
        EXPIRED_YEARLY_YEAR .setEditable(true);
        EXPIRED_YEARLY_YEAR.setUI(new BasicComboBoxUI());
        EXPIRED_YEARLY_YEAR.setBorder(BorderFactory.createEmptyBorder());
        EXPIRED_YEARLY_YEAR.setBackground(new Color(0, 0, 0, 0));
        EXPIRED_YEARLY_YEAR.setFocusable(false);
        YEARY_JP.add(EXPIRED_YEARLY_YEAR);

        EXPIRED_YEARLY_SCANN = new JButton(new ImageIcon("Skins/C_buttons/"
                + "scann_1.png"));
        EXPIRED_YEARLY_SCANN.setBounds(286,0,30,30);
        EXPIRED_YEARLY_SCANN.setOpaque(false);
        EXPIRED_YEARLY_SCANN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        EXPIRED_YEARLY_SCANN.setContentAreaFilled(false);
        EXPIRED_YEARLY_SCANN.setFocusable(false);
        EXPIRED_YEARLY_SCANN.setBorderPainted(false);
        EXPIRED_YEARLY_SCANN.setFont(COMBO_FONT);
        setFilter_listener(EXPIRED_YEARLY_SCANN);
        EXPIRED_YEARLY_SCANN.setFont(COMBO_FONT);
        YEARY_JP.add(EXPIRED_YEARLY_SCANN);
        
        for(int x=2019;x<2030;x++){
            EXPIRED_YEARLY_YEAR.addItem(x);
        }

        EXPIRED_TOTAL = new JLabel("");
        EXPIRED_TOTAL.setFont(SETFONT);
        EXPIRED_TOTAL.setBounds(150,120,500,30);
        EXPIRED_TOTAL.setHorizontalTextPosition(JLabel.CENTER);
        EXPIRED_TOTAL.setVerticalTextPosition(JLabel.CENTER);
        EXPIRED_TOTAL.setForeground(new Color(228, 54, 0));
        EXPIRED_TOTAL.setFont(myData_font);
        EXPIRED_INFO.add(EXPIRED_TOTAL);
        
        JLabel expiry_bg = new JLabel(new ImageIcon("Skins/C_background/"
                + "expired_bg.png"));
        expiry_bg.setBounds(0,0,350,200);
        EXPIRED_INFO.add(expiry_bg);
        
        setBackground_data(EXPIRED_YEARLY,EXPIRED_INFO,"expired",expiry_bg);
        setBackground_data(EXPIRED_MONTHLY_SCANN,MONTHLY_JP,"expired",expiry_bg);
        setBackground_data(EXPIRED_YEARLY_SCANN,YEARY_JP,"expired",expiry_bg);
        setBackground_data(EXPIRED_MONTHLY,YEARY_JP,"expired",expiry_bg);
        setBackground_data(EXPIRED_YEARLY_SCANN,null,"expired",expiry_bg);
        
        JLabel SET_EXPIRED_NAME = new JLabel("Product name");
        SET_EXPIRED_NAME.setBounds(10,5,150,25);
        SET_EXPIRED_NAME.setFont(SETFONT);
//        EXPIRED_INFO.add(SET_EXPIRED_NAME);
        
        EXPIRED_PRODUCT_NAME = new JLabel("");
        EXPIRED_PRODUCT_NAME.setBounds(160,5,500,25);
        EXPIRED_PRODUCT_NAME.setFont(SETFONTx);
//        EXPIRED_INFO.add(EXPIRED_PRODUCT_NAME);
        
        JLabel SET_EXPIRED_QUANTITY = new JLabel("Quantity");
        SET_EXPIRED_QUANTITY.setBounds(10,45,150,25);
        SET_EXPIRED_QUANTITY.setFont(SETFONT);
//        EXPIRED_INFO.add(SET_EXPIRED_QUANTITY);
        
        EXPIRED_QUANTITY = new JLabel("");
        EXPIRED_QUANTITY.setBounds(160,45,500,25);
        EXPIRED_QUANTITY.setFont(SETFONTx);
//        EXPIRED_INFO.add(EXPIRED_QUANTITY);
        
        JLabel SET_EXPIRED_PRICE = new JLabel("Price");
        SET_EXPIRED_PRICE.setBounds(10,85,150,25);
        SET_EXPIRED_PRICE.setFont(SETFONT);
//        EXPIRED_INFO.add(SET_EXPIRED_PRICE);
        
        EXPIRED_PRICE = new JLabel("");
        EXPIRED_PRICE.setBounds(160,85,500,25);
        EXPIRED_PRICE.setFont(SETFONTx);
//        EXPIRED_INFO.add(EXPIRED_PRICE);

        JLabel SET_EXPIRED_DATE = new JLabel("Expired date");
        SET_EXPIRED_DATE.setBounds(10,125,150,25);
        SET_EXPIRED_DATE.setFont(SETFONT);
//        EXPIRED_INFO.add(SET_EXPIRED_DATE);
        
        EXPIRED_DATE = new JLabel("");
        EXPIRED_DATE.setBounds(160,125,500,25);
        EXPIRED_DATE.setFont(SETFONTx);
//        EXPIRED_INFO.add(EXPIRED_DATE);
        
        JLabel SET_EXPIRED_DATE_PURCHASED = new JLabel("Date purchased");
        SET_EXPIRED_DATE_PURCHASED.setBounds(10,165,150,25);
        SET_EXPIRED_DATE_PURCHASED.setFont(SETFONT);
//        EXPIRED_INFO.add(SET_EXPIRED_DATE_PURCHASED);
        
        EXPIRED_DATE_PURCHASE= new JLabel("");
        EXPIRED_DATE_PURCHASE.setBounds(160,165,500,25);
        EXPIRED_DATE_PURCHASE.setFont(SETFONTx);
//        EXPIRED_INFO.add(EXPIRED_DATE_PURCHASE);
        
        JLabel SET_EXPIRED_PURCHASED = new JLabel("Purchased from");
        SET_EXPIRED_PURCHASED.setBounds(300,165,150,25);
        SET_EXPIRED_PURCHASED.setFont(SETFONT);
//        EXPIRED_INFO.add(SET_EXPIRED_PURCHASED);
        
        EXPIRED_PURCHASE_FROM = new JLabel("");
        EXPIRED_PURCHASE_FROM.setBounds(450,165,500,25);
        EXPIRED_PURCHASE_FROM.setFont(SETFONTx);
//        EXPIRED_INFO.add(EXPIRED_PURCHASE_FROM);
        
        //Discount ui
        
        DISCOUNT_PANEL = new JPanel();
        DISCOUNT_PANEL.setBounds(5,50,1080,1000);
        DISCOUNT_PANEL.setLayout(null);
        DISCOUNT_PANEL.setOpaque(false);
        this.add(DISCOUNT_PANEL);
        
        JScrollBar discount_scroll = DISCOUNT_PANE.getVerticalScrollBar();
        DISCOUNT_PANE.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
//        expired_scroll.setUI(new Skins.LookAndFeel.MyScrollbarUI());
        DISCOUNT_PANE.setBounds(0,0,1080,300);
        DISCOUNT_PANE.setBorder(createEmptyBorder());
        DISCOUNT_PANE.getViewport().setOpaque(false);
        DISCOUNT_PANE.setOpaque(false);
        DISCOUNT_PANEL.setVisible(false);
        DISCOUNT_PANEL.add(DISCOUNT_PANE);
        
        DISC_PRODUCT_INFO_PANEL = new JPanel();
        DISC_PRODUCT_INFO_PANEL.setLayout(null);
        DISC_PRODUCT_INFO_PANEL.setBounds(370,415,1000,500);
        DISC_PRODUCT_INFO_PANEL.setOpaque(false);
        this.add(DISC_PRODUCT_INFO_PANEL);
        
        JLabel SET_DISC_QUANTITY = new JLabel("Quantiy");
        SET_DISC_QUANTITY.setBounds(250,0,100,25);
        SET_DISC_QUANTITY.setForeground(Color.DARK_GRAY);
        SET_DISC_QUANTITY.setFont(SETFONT);
        DISC_PRODUCT_INFO_PANEL.add(SET_DISC_QUANTITY);
                
        DISC_QUANTITY = new JLabel("");
        DISC_QUANTITY.setBounds(360,0,150,25);
        DISC_QUANTITY.setFont(SETFONTx);
        DISC_PRODUCT_INFO_PANEL.add(DISC_QUANTITY);
        
        JLabel SET_DISC_UNIT_PRICE = new JLabel("Unit Price");
        SET_DISC_UNIT_PRICE.setBounds(250,30,100,25);
        SET_DISC_UNIT_PRICE.setForeground(Color.DARK_GRAY);
        SET_DISC_UNIT_PRICE.setFont(SETFONT);
        DISC_PRODUCT_INFO_PANEL.add(SET_DISC_UNIT_PRICE);
                
        DISC_UNIT_PRICE = new JLabel("");
        DISC_UNIT_PRICE.setBounds(360,30,150,25);
        DISC_UNIT_PRICE.setFont(SETFONTx);
        DISC_PRODUCT_INFO_PANEL.add(DISC_UNIT_PRICE);

        JLabel SET_DISC_DATE = new JLabel("Date & Time");
        SET_DISC_DATE.setBounds(0,60,100,25);
        SET_DISC_DATE.setForeground(Color.DARK_GRAY);
        SET_DISC_DATE.setFont(SETFONT);
        DISC_PRODUCT_INFO_PANEL.add(SET_DISC_DATE);
        
        DISC_DATE = new JLabel("");
        DISC_DATE.setBounds(110,60,150,25);
        DISC_DATE.setFont(SETFONTx);
        DISC_PRODUCT_INFO_PANEL.add(DISC_DATE);
        
        JLabel SET_DISC_CASHIER = new JLabel("Cashier");
        SET_DISC_CASHIER.setBounds(0,90,100,25);
        SET_DISC_CASHIER.setForeground(Color.DARK_GRAY);
        SET_DISC_CASHIER.setFont(SETFONT);
        DISC_PRODUCT_INFO_PANEL.add(SET_DISC_CASHIER);
        
        DISC_CASHIER = new JLabel("");
        DISC_CASHIER.setBounds(110,90,150,25);
        DISC_CASHIER.setFont(SETFONTx);
        DISC_PRODUCT_INFO_PANEL.add(DISC_CASHIER);
        
        JLabel SET_DISC_PREPARATION = new JLabel("Preparation");
        SET_DISC_PREPARATION.setBounds(0,30,100,25);
        SET_DISC_PREPARATION.setForeground(Color.DARK_GRAY);
        SET_DISC_PREPARATION.setFont(SETFONT);
        DISC_PRODUCT_INFO_PANEL.add(SET_DISC_PREPARATION);
        
        DISC_PREPARATION = new JLabel("");
        DISC_PREPARATION.setBounds(110,30,150,25);
        DISC_PREPARATION.setFont(SETFONTx);
        DISC_PRODUCT_INFO_PANEL.add(DISC_PREPARATION);
        
        JLabel SET_DISC_STRENGTH = new JLabel("Strength");
        SET_DISC_STRENGTH.setBounds(0,0,100,25);
        SET_DISC_STRENGTH.setForeground(Color.DARK_GRAY);
        SET_DISC_STRENGTH.setFont(SETFONT);
        DISC_PRODUCT_INFO_PANEL.add(SET_DISC_STRENGTH);

        DISC_STRENGTH = new JLabel("");
        DISC_STRENGTH.setBounds(110,0,150,25);
        DISC_STRENGTH.setFont(SETFONTx);
        DISC_PRODUCT_INFO_PANEL.add(DISC_STRENGTH);
        
        JLabel SET_DISCOUNTED_PRICE = new JLabel("Disc. Price");
        SET_DISCOUNTED_PRICE.setBounds(250,60,100,25);
        SET_DISCOUNTED_PRICE.setForeground(Color.DARK_GRAY);
        SET_DISCOUNTED_PRICE.setFont(SETFONT);
        DISC_PRODUCT_INFO_PANEL.add(SET_DISCOUNTED_PRICE);
        
        DISC_DISCOUNTED_PRICE = new JLabel("");
        DISC_DISCOUNTED_PRICE.setBounds(360,60,150,25);
        DISC_DISCOUNTED_PRICE.setFont(SETFONTx);
        DISC_PRODUCT_INFO_PANEL.add(DISC_DISCOUNTED_PRICE);
        
        JLabel SET_DISCOUNTED_PERCENT = new JLabel("Disc. %");
        SET_DISCOUNTED_PERCENT.setBounds(250,90,100,25);
        SET_DISCOUNTED_PERCENT.setForeground(Color.DARK_GRAY);
        SET_DISCOUNTED_PERCENT.setFont(SETFONT);
        DISC_PRODUCT_INFO_PANEL.add(SET_DISCOUNTED_PERCENT);
        
        DISC_DISCOUNTED_PERCENT = new JLabel("");
        DISC_DISCOUNTED_PERCENT.setBounds(360,90,150,25);
        DISC_DISCOUNTED_PERCENT.setFont(SETFONTx);
        DISC_PRODUCT_INFO_PANEL.add(DISC_DISCOUNTED_PERCENT);
        
        JLabel SET_DISCOUNT = new JLabel("Discount");
        SET_DISCOUNT.setBounds(250,120,100,25);
        SET_DISCOUNT.setForeground(Color.DARK_GRAY);
        SET_DISCOUNT.setFont(SETFONT);
        DISC_PRODUCT_INFO_PANEL.add(SET_DISCOUNT);
        
        DISC_DISCOUNT = new JLabel("asdasdsadsa");
        DISC_DISCOUNT.setBounds(360,120,150,25);
        DISC_DISCOUNT.setFont(SETFONTx);
        DISC_PRODUCT_INFO_PANEL.add(DISC_DISCOUNT);

        DISCOUNT_INFO = new JPanel();
        DISCOUNT_INFO.setBounds(5,320,350,200);
        DISCOUNT_INFO.setLayout(null);
        DISCOUNT_INFO.setOpaque(false);
        DISCOUNT_PANEL.add(DISCOUNT_INFO);

        ImageIcon DISCOUNT_MONTHLY_II = new ImageIcon("Skins/C_buttons/MONTHLY_II.png");
        DISCOUNT_MONTHLY = new JButton(DISCOUNT_MONTHLY_II);
        DISCOUNT_MONTHLY.setBounds(70,20,100,30);
        DISCOUNT_MONTHLY.setOpaque(false);
        DISCOUNT_MONTHLY.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DISCOUNT_MONTHLY.setContentAreaFilled(false);
        DISCOUNT_MONTHLY.setFocusable(false);
        DISCOUNT_MONTHLY.setBorderPainted(false);
        DISCOUNT_MONTHLY.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DISCOUNT_INFO.add(DISCOUNT_MONTHLY);

        ImageIcon DISCOUNT_YEARLY_II = new ImageIcon("Skins/C_buttons/YEARLY_I.png");
        DISCOUNT_YEARLY = new JButton(DISCOUNT_YEARLY_II);
        DISCOUNT_YEARLY.setBounds(175,20,100,30);
        DISCOUNT_YEARLY.setOpaque(false);
        DISCOUNT_YEARLY.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DISCOUNT_YEARLY.setContentAreaFilled(false);
        DISCOUNT_YEARLY.setFocusable(false);
        DISCOUNT_YEARLY.setBorderPainted(false);
        DISCOUNT_YEARLY.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DISCOUNT_INFO.add(DISCOUNT_YEARLY);
        
        DISCOUNT_MONTHLY_JP = new JPanel();
        DISCOUNT_MONTHLY_JP.setBounds(5,64,350,30);
        DISCOUNT_MONTHLY_JP.setLayout(null);
        DISCOUNT_MONTHLY_JP.setOpaque(false);
        DISCOUNT_INFO.add(DISCOUNT_MONTHLY_JP);
        
        DISCOUNT_MONTHLY_MONTH = new JComboBox();
        DISCOUNT_MONTHLY_MONTH.setBounds(60,5,95,20);
        DISCOUNT_MONTHLY_MONTH.setFont(COMBO_FONT);
        DISCOUNT_MONTHLY_MONTH .setOpaque(false);
        DISCOUNT_MONTHLY_MONTH .setEditable(true);
        DISCOUNT_MONTHLY_MONTH.setUI(new BasicComboBoxUI());
        DISCOUNT_MONTHLY_MONTH.setBorder(BorderFactory.createEmptyBorder());
        DISCOUNT_MONTHLY_MONTH.setBackground(new Color(0, 0, 0, 0));
        DISCOUNT_MONTHLY_MONTH.setFocusable(false);
        DISCOUNT_MONTHLY_JP.add(DISCOUNT_MONTHLY_MONTH);

        DISCOUNT_MONTHLY_YEAR = new JComboBox();
        DISCOUNT_MONTHLY_YEAR.setBounds(163,5,100,20);
        DISCOUNT_MONTHLY_YEAR.setFont(COMBO_FONT);
        DISCOUNT_MONTHLY_YEAR .setOpaque(false);
        DISCOUNT_MONTHLY_YEAR .setEditable(true);
        DISCOUNT_MONTHLY_YEAR.setUI(new BasicComboBoxUI());
        DISCOUNT_MONTHLY_YEAR.setBorder(BorderFactory.createEmptyBorder());
        DISCOUNT_MONTHLY_YEAR.setBackground(new Color(0, 0, 0, 0));
        DISCOUNT_MONTHLY_YEAR.setFocusable(false);
        DISCOUNT_MONTHLY_YEAR.setFont(COMBO_FONT);
        DISCOUNT_MONTHLY_JP.add(DISCOUNT_MONTHLY_YEAR);
        
        DISCOUNT_MONTHLY_SCANN = new JButton(new ImageIcon("Skins/C_buttons/"
                + "scann_1.png"));
        DISCOUNT_MONTHLY_SCANN.setBounds(286,0,30,30);
        DISCOUNT_MONTHLY_SCANN.setOpaque(false);
        DISCOUNT_MONTHLY_SCANN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DISCOUNT_MONTHLY_SCANN.setContentAreaFilled(false);
        DISCOUNT_MONTHLY_SCANN.setFocusable(false);
        DISCOUNT_MONTHLY_SCANN.setBorderPainted(false);
        DISCOUNT_MONTHLY_SCANN.setFont(COMBO_FONT);
        setFilter_listener(DISCOUNT_MONTHLY_SCANN);
        DISCOUNT_MONTHLY_SCANN.setFont(COMBO_FONT);
        DISCOUNT_MONTHLY_JP.add(DISCOUNT_MONTHLY_SCANN);
        
        DISCOUNT_MONTHLY_MONTH.addItem("January");
        DISCOUNT_MONTHLY_MONTH.addItem("February");
        DISCOUNT_MONTHLY_MONTH.addItem("March");
        DISCOUNT_MONTHLY_MONTH.addItem("April");
        DISCOUNT_MONTHLY_MONTH.addItem("May");
        DISCOUNT_MONTHLY_MONTH.addItem("June");
        DISCOUNT_MONTHLY_MONTH.addItem("July");
        DISCOUNT_MONTHLY_MONTH.addItem("August");
        DISCOUNT_MONTHLY_MONTH.addItem("September");
        DISCOUNT_MONTHLY_MONTH.addItem("October");
        DISCOUNT_MONTHLY_MONTH.addItem("November");
        DISCOUNT_MONTHLY_MONTH.addItem("December");
        
        for(int x=2019;x<2030;x++){
            DISCOUNT_MONTHLY_YEAR.addItem(x);
        }
        
        DISCOUNT_YEARY_JP = new JPanel();
        DISCOUNT_YEARY_JP.setBounds(5,64,350,30);
        DISCOUNT_YEARY_JP.setLayout(null);
        DISCOUNT_YEARY_JP.setVisible(false);
        DISCOUNT_YEARY_JP.setOpaque(false);
        DISCOUNT_INFO.add(DISCOUNT_YEARY_JP);

        DISCOUNT_YEARLY_YEAR = new JComboBox();
        DISCOUNT_YEARLY_YEAR.setBounds(163,5,100,20);
        DISCOUNT_YEARLY_YEAR.setFont(COMBO_FONT);
        DISCOUNT_YEARLY_YEAR .setOpaque(false);
        DISCOUNT_YEARLY_YEAR .setEditable(true);
        DISCOUNT_YEARLY_YEAR.setUI(new BasicComboBoxUI());
        DISCOUNT_YEARLY_YEAR.setBorder(BorderFactory.createEmptyBorder());
        DISCOUNT_YEARLY_YEAR.setBackground(new Color(0, 0, 0, 0));
        DISCOUNT_YEARLY_YEAR.setFocusable(false);
        DISCOUNT_YEARY_JP.add(DISCOUNT_YEARLY_YEAR);

        DISCOUNT_YEARLY_SCANN = new JButton(new ImageIcon("Skins/C_buttons/"
                + "scann_1.png"));
        DISCOUNT_YEARLY_SCANN.setBounds(286,0,30,30);
        DISCOUNT_YEARLY_SCANN.setOpaque(false);
        DISCOUNT_YEARLY_SCANN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DISCOUNT_YEARLY_SCANN.setContentAreaFilled(false);
        DISCOUNT_YEARLY_SCANN.setFocusable(false);
        DISCOUNT_YEARLY_SCANN.setBorderPainted(false);
        DISCOUNT_YEARLY_SCANN.setFont(COMBO_FONT);
        setFilter_listener(DISCOUNT_YEARLY_SCANN);
        DISCOUNT_YEARLY_SCANN.setFont(COMBO_FONT);
        DISCOUNT_YEARY_JP.add(DISCOUNT_YEARLY_SCANN);
        
        for(int x=2019;x<2030;x++){
            DISCOUNT_YEARLY_YEAR.addItem(x);
        }

        DISCOUNT_TOTAL = new JLabel("");
        DISCOUNT_TOTAL.setBounds(150,120,500,30);
        DISCOUNT_TOTAL.setHorizontalTextPosition(JLabel.CENTER);
        DISCOUNT_TOTAL.setVerticalTextPosition(JLabel.CENTER);
        DISCOUNT_TOTAL.setForeground(new Color(255, 214, 0));
        DISCOUNT_TOTAL.setFont(myData_font);
        DISCOUNT_INFO.add(DISCOUNT_TOTAL);
        
        JLabel discount_bg = new JLabel(new ImageIcon("Skins/C_background/"
                + "discount_bg.png"));
        discount_bg.setBounds(0,0,350,200);
        DISCOUNT_INFO.add(discount_bg);
        
        setBackground_data(DISCOUNT_YEARLY,DISCOUNT_INFO,"discount",discount_bg);
        setBackground_data(DISCOUNT_MONTHLY_SCANN,DISCOUNT_MONTHLY_JP,"discount",discount_bg);
        setBackground_data(DISCOUNT_YEARLY_SCANN,DISCOUNT_YEARY_JP,"discount",discount_bg);
        setBackground_data(DISCOUNT_MONTHLY,DISCOUNT_INFO,"discount",discount_bg);
        setBackground_data(DISCOUNT_YEARLY_SCANN,null,"discount",discount_bg);
        
        DAILY_INFO = new JPanel();
        DAILY_INFO.setLayout(null);
        DAILY_INFO.setBounds(10,370,350,200);
        DAILY_INFO.setLayout(null);
        DAILY_INFO.setOpaque(false);
//        DAILY_INFO.setOpaque(false);
//        DAILY_INFO.setBorder(BorderFactory.createTitledBorder("Daily Income"));
//        DAILY_INFO.setBorder(BorderFactory.createTitledBorder(null, "Daily Income", TitledBorder.LEFT,
//                TitledBorder.BOTTOM, new Font("Segoe UI",Font.BOLD,14), Color.BLACK));
//        DAILY_INFO.setBackground(Color.WHITE);
        DAILY_INFO.setVisible(true);
        this.add(DAILY_INFO);
//                x
        MONTH = new JComboBox();
        MONTH.setBounds(55,25,95,20);
        MONTH .setOpaque(false);
        MONTH .setEditable(true);
        MONTH.setUI(new BasicComboBoxUI());
        MONTH.setBorder(BorderFactory.createEmptyBorder());
        MONTH.setBackground(new Color(0, 0, 0, 0));
        MONTH.setFocusable(false);
        MONTH.setFont(COMBO_FONT);
        DAILY_INFO.add(MONTH);
        
        MONTH.addItem("January");
        MONTH.addItem("February");
        MONTH.addItem("March");
        MONTH.addItem("April");
        MONTH.addItem("May");
        MONTH.addItem("June");
        MONTH.addItem("July");
        MONTH.addItem("August");
        MONTH.addItem("September");
        MONTH.addItem("October");
        MONTH.addItem("November");
        MONTH.addItem("December");

        DAY = new JComboBox();
        DAY.setBounds(155,25,50,20);
        DAY.setFont(COMBO_FONT);
        DAY .setOpaque(false);
        DAY .setEditable(true);
        DAY.setUI(new BasicComboBoxUI());
        DAY.setBorder(BorderFactory.createEmptyBorder());
        DAY.setBackground(new Color(0, 0, 0, 0));
        DAY.setFocusable(false);
        DAILY_INFO.add(DAY);
        
        for(int x=1;x<32;x++){
            DAY.addItem(x);
        }
    
        YEAR = new JComboBox();
        YEAR.setBounds(210,25,80,20);
        YEAR .setOpaque(false);
        YEAR .setEditable(true);
        YEAR.setUI(new BasicComboBoxUI());
        YEAR.setBorder(BorderFactory.createEmptyBorder());
        YEAR.setBackground(new Color(0, 0, 0, 0));
        YEAR.setFocusable(false);
        YEAR.setFont(COMBO_FONT);
        DAILY_INFO.add(YEAR);

        for(int x=2019;x<2030;x++){
            YEAR.addItem(x);
        }

        SCANN = new JButton(new ImageIcon("Skins/C_buttons/"
                + "scann_1.png"));
        SCANN.setBounds(303,20,30,30);
        SCANN.setOpaque(false);
        SCANN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        SCANN.setContentAreaFilled(false);
        SCANN.setFocusable(false);
        SCANN.setBorderPainted(false);
        SCANN.setFont(COMBO_FONT);
        setFilter_listener(SCANN);
        DAILY_INFO.add(SCANN);
        
        TOTAL_QUANTITY_SOLD = new JLabel("Total Quantities");
        TOTAL_QUANTITY_SOLD.setBounds(20,60,500,25);
        TOTAL_QUANTITY_SOLD.setFont(SETFONTx);
//        DAILY_INFO.add(TOTAL_QUANTITY_SOLD);
        
        TOTAL_INCOME = new JTextField("");
        TOTAL_INCOME.setBounds(10,100,150,25);
        TOTAL_INCOME.setEnabled(false);
        TOTAL_INCOME.setHorizontalAlignment(JTextField.CENTER);
        TOTAL_INCOME.setBackground(new Color(0,0,0,0));
        TOTAL_INCOME.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        TOTAL_INCOME.setFont(myData_font);
        TOTAL_INCOME.setForeground(new Color(156, 204, 101));
        DAILY_INFO.add(TOTAL_INCOME);

        GROSS_INCOME = new JTextField("");
        GROSS_INCOME.setBounds(180,100,150,25);
        GROSS_INCOME.setEnabled(false);
        GROSS_INCOME.setHorizontalAlignment(JTextField.CENTER);
        GROSS_INCOME.setBackground(new Color(0,0,0,0));
        GROSS_INCOME.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        GROSS_INCOME.setFont(myData_font);
        GROSS_INCOME.setForeground(new Color(156, 204, 101));
        DAILY_INFO.add(GROSS_INCOME);

//        DAILY_PDF = new JButton("PRINT REPORT");
//        DAILY_PDF.setBounds(20,545,310,30);
//        DAILY_PDF.setFont(COMBO_FONT);
//        this.add(DAILY_PDF);
        
//        PRODUCTS_SOLD = new JComboBox();
//        PRODUCTS_SOLD.setBounds(115,120,200,25);
//        DAILY_INFO.add(PRODUCTS_SOLD);
        
        Font myFont = new Font("Segoe UI", Font.PLAIN | Font.BOLD, 15);
        
        SCANN_INFO = new JComboBox();
        SCANN_INFO.setUI(new BasicComboBoxUI());
        SCANN_INFO.setFont(myFont);
        SCANN_INFO.setBounds(80,10,150,25);
        this.add(SCANN_INFO);
        
//        SCANN_INFO.addItem("Weekly");
        SCANN_INFO.addItem("Daily");
        SCANN_INFO.addItem("Monthly");
        SCANN_INFO.addItem("Yearly");
        SCANN_INFO.addItem("Expired");
        SCANN_INFO.addItem("Free");
        SCANN_INFO.addItem("Discount");
        
        SCANN_INFO_JL = new JLabel("Scann by");
        SCANN_INFO_JL.setBounds(660,50,70,25);
        this.add(SCANN_INFO_JL);

        JLabel daily_bg = new JLabel(new ImageIcon("Skins/C_background/"
                + "daily_income_bg.png"));
        daily_bg.setBounds(0,0,350,200);
        DAILY_INFO.add(daily_bg);
        
        setBackground_data(SCANN,DAILY_INFO,"daily",daily_bg);
        
        //WEEKLY LOGS SCANN
        
        YEARLY_INFO = new JPanel();
        YEARLY_INFO.setBounds(10,370,350,200);
        YEARLY_INFO.setLayout(null);
        YEARLY_INFO.setOpaque(false);
//        MONTHLY_INFO.setBorder(BorderFactory.createTitledBorder("Monthly Income"));
//        YEARLY_INFO.setBorder(BorderFactory.createTitledBorder(null, "Yearly Income", TitledBorder.LEFT,
//                TitledBorder.BOTTOM, new Font("Segoe UI",Font.BOLD,14), Color.BLACK));
//        YEARLY_INFO.setBackground(Color.WHITE);
        YEARLY_INFO.setVisible(false);
        this.add(YEARLY_INFO);

        YEAR_CB = new JComboBox();
        YEAR_CB.setFont(COMBO_FONT);
        YEAR_CB.setBounds(55,25,135,20);
        YEAR_CB .setOpaque(false);
        YEAR_CB .setEditable(true);
        YEAR_CB.setUI(new BasicComboBoxUI());
        YEAR_CB.setBorder(BorderFactory.createEmptyBorder());
        YEAR_CB.setBackground(new Color(0, 0, 0, 0));
        YEAR_CB.setFocusable(false);
        YEARLY_INFO.add(YEAR_CB);
        
        for(int x=2019;x<2030;x++){
            YEAR_CB.addItem(x);
        }
        
        YEAR_SCAN = new JButton(new ImageIcon("Skins/C_buttons/"
                + "scann_1.png"));
        YEAR_SCAN.setBounds(198,20,30,30);
        YEAR_SCAN.setOpaque(false);
        YEAR_SCAN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        YEAR_SCAN.setContentAreaFilled(false);
        YEAR_SCAN.setFocusable(false);
        YEAR_SCAN.setBorderPainted(false);
        YEAR_SCAN.setFont(COMBO_FONT);
        setFilter_listener(YEAR_SCAN);
        YEARLY_INFO.add(YEAR_SCAN);
        
        YEAR_TOTAL_QUANTITY = new JLabel("Total Quantities");
        YEAR_TOTAL_QUANTITY.setBounds(20,60,500,25);
        YEAR_TOTAL_QUANTITY.setFont(SETFONTx);
        
        YEAR_TOTAL_INCOME = new JLabel();
        YEAR_TOTAL_INCOME.setBounds(20,100,150,25);
        YEAR_TOTAL_INCOME.setEnabled(false);
        YEAR_TOTAL_INCOME.setHorizontalAlignment(JTextField.CENTER);
        YEAR_TOTAL_INCOME.setBackground(new Color(0,0,0,0));
//        YEAR_TOTAL_INCOME.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        YEAR_TOTAL_INCOME.setFont(myData_font);
        YEAR_TOTAL_INCOME.setForeground(new Color(239, 83, 80));
        YEARLY_INFO.add(YEAR_TOTAL_INCOME);
        
        YEARLY_GROSS_INCOME = new JLabel("");
        YEARLY_GROSS_INCOME.setBounds(180,100,150,25);
        YEARLY_GROSS_INCOME.setEnabled(false);
        YEARLY_GROSS_INCOME.setHorizontalAlignment(JTextField.CENTER);
        YEARLY_GROSS_INCOME.setBackground(new Color(0,0,0,0));
        YEARLY_GROSS_INCOME.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        YEARLY_GROSS_INCOME.setFont(myData_font);
        YEARLY_GROSS_INCOME.setForeground(new Color(239, 83, 80));
        YEARLY_INFO.add(YEARLY_GROSS_INCOME);

        JLabel yearly_bg = new JLabel(new ImageIcon("Skins/C_background/"
                + "yearly_income_bg.png"));
        yearly_bg.setBounds(0,0,350,200);
        YEARLY_INFO.add(yearly_bg);

        setBackground_data(YEAR_SCAN,YEARLY_INFO,"year",yearly_bg);
        //MONTHLY SCANN
        
        MONTHLY_INFO = new JPanel();
        MONTHLY_INFO.setBounds(10,370,350,200);
        MONTHLY_INFO.setLayout(null);
        MONTHLY_INFO.setOpaque(false);
//        MONTHLY_INFO.setBorder(BorderFactory.createTitledBorder("Monthly Income"));
//        MONTHLY_INFO.setBorder(BorderFactory.createTitledBorder(null, "Monthly Income", TitledBorder.LEFT,
//                TitledBorder.BOTTOM, new Font("Segoe UI",Font.BOLD,14), Color.BLACK));
        MONTHLY_INFO.setBackground(Color.WHITE);
        MONTHLY_INFO.setVisible(false);
        this.add(MONTHLY_INFO);

        MONTHLY_INFO_SET_MONTH = new JComboBox();
        MONTHLY_INFO_SET_MONTH.setBounds(55,25,120,20);
        MONTHLY_INFO_SET_MONTH .setOpaque(false);
        MONTHLY_INFO_SET_MONTH .setEditable(true);
        MONTHLY_INFO_SET_MONTH.setUI(new BasicComboBoxUI());
        MONTHLY_INFO_SET_MONTH.setBorder(BorderFactory.createEmptyBorder());
        MONTHLY_INFO_SET_MONTH.setBackground(new Color(0, 0, 0, 0));
        MONTHLY_INFO_SET_MONTH.setFocusable(false);
        MONTHLY_INFO_SET_MONTH.setFont(COMBO_FONT);
        MONTHLY_INFO.add(MONTHLY_INFO_SET_MONTH);
        
        MONTHLY_INFO_SET_MONTH.addItem("January");
        MONTHLY_INFO_SET_MONTH.addItem("February");
        MONTHLY_INFO_SET_MONTH.addItem("March");
        MONTHLY_INFO_SET_MONTH.addItem("April");
        MONTHLY_INFO_SET_MONTH.addItem("May");
        MONTHLY_INFO_SET_MONTH.addItem("June");
        MONTHLY_INFO_SET_MONTH.addItem("July");
        MONTHLY_INFO_SET_MONTH.addItem("August");
        MONTHLY_INFO_SET_MONTH.addItem("September");
        MONTHLY_INFO_SET_MONTH.addItem("October");
        MONTHLY_INFO_SET_MONTH.addItem("November");
        MONTHLY_INFO_SET_MONTH.addItem("December");

        MONTHLY_INFO_SET_YEAR = new JComboBox();
        MONTHLY_INFO_SET_YEAR.setBounds(185,25,80,20);
        MONTHLY_INFO_SET_YEAR .setOpaque(false);
        MONTHLY_INFO_SET_YEAR .setEditable(true);
        MONTHLY_INFO_SET_YEAR.setUI(new BasicComboBoxUI());
        MONTHLY_INFO_SET_YEAR.setBorder(BorderFactory.createEmptyBorder());
        MONTHLY_INFO_SET_YEAR.setBackground(new Color(0, 0, 0, 0));
        MONTHLY_INFO_SET_YEAR.setFocusable(false);
        MONTHLY_INFO_SET_YEAR.setFont(COMBO_FONT);
        MONTHLY_INFO.add(MONTHLY_INFO_SET_YEAR);
        
        for(int x=2019;x<2030;x++){
            MONTHLY_INFO_SET_YEAR.addItem(x);
        }
        
        MONTHLY_SCANN = new JButton(new ImageIcon("Skins/C_buttons/"
                + "scann_1.png"));
        MONTHLY_SCANN.setBounds(285,20,30,30);
        MONTHLY_SCANN.setOpaque(false);
        MONTHLY_SCANN.setCursor(new Cursor(Cursor.HAND_CURSOR));
        MONTHLY_SCANN.setContentAreaFilled(false);
        MONTHLY_SCANN.setFocusable(false);
        MONTHLY_SCANN.setBorderPainted(false);
        MONTHLY_SCANN.setFont(COMBO_FONT);
        setFilter_listener(MONTHLY_SCANN);
        MONTHLY_SCANN.setFont(COMBO_FONT);
        MONTHLY_INFO.add(MONTHLY_SCANN);
        
        MONTHLY_TOTAL_QUANTITY = new JLabel("Total Quantities");
        MONTHLY_TOTAL_QUANTITY.setBounds(20,60,500,25);
        MONTHLY_TOTAL_QUANTITY.setFont(SETFONTx);
//        MONTHLY_INFO.add(MONTHLY_TOTAL_QUANTITY);
        
        MONTHLY_TOTAL_INCOME = new JLabel("");
        MONTHLY_TOTAL_INCOME.setBounds(10,100,150,25);
        MONTHLY_TOTAL_INCOME.setEnabled(false);
        MONTHLY_TOTAL_INCOME.setHorizontalAlignment(JTextField.CENTER);
        MONTHLY_TOTAL_INCOME.setBackground(new Color(0,0,0,0));
        MONTHLY_TOTAL_INCOME.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        MONTHLY_TOTAL_INCOME.setFont(myData_font);
        MONTHLY_TOTAL_INCOME.setForeground(new Color(255, 167, 38));
        MONTHLY_INFO.add(MONTHLY_TOTAL_INCOME);

        MONTHLY_GROSS_INCOME = new JLabel("");
        MONTHLY_GROSS_INCOME.setBounds(180,100,150,25);
        MONTHLY_GROSS_INCOME.setEnabled(false);
        MONTHLY_GROSS_INCOME.setHorizontalAlignment(JTextField.CENTER);
        MONTHLY_GROSS_INCOME.setBackground(new Color(0,0,0,0));
        MONTHLY_GROSS_INCOME.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        MONTHLY_GROSS_INCOME.setFont(myData_font);
        MONTHLY_GROSS_INCOME.setForeground(new Color(255, 167, 38));
        MONTHLY_INFO.add(MONTHLY_GROSS_INCOME);

        JLabel monthly_bg = new JLabel(new ImageIcon("Skins/C_background/"
                + "monthly_income_bg.png"));
        monthly_bg.setBounds(0,0,350,200);
        MONTHLY_INFO.add(monthly_bg);
        
        setBackground_data(MONTHLY_SCANN,MONTHLY_INFO,"month",monthly_bg);
    }
    public void setBg_loc(JLabel label, String type, String action){
        if(action.equalsIgnoreCase("entered")){
                if(type.equalsIgnoreCase("year")){
                label.setIcon(new ImageIcon("Skins/C_background/"
                + setLoc.get(1)+".png"));
                }else if(type.equalsIgnoreCase("month")){
                label.setIcon(new ImageIcon("Skins/C_background/"
                + setLoc.get(3)+".png"));
                }else if(type.equalsIgnoreCase("daily")){
                label.setIcon(new ImageIcon("Skins/C_background/"
                + setLoc.get(5)+".png"));
                }else if(type.equalsIgnoreCase("expired")){
                label.setIcon(new ImageIcon("Skins/C_background/"
                + setLoc.get(7)+".png"));
                }else if(type.equalsIgnoreCase("free")){
                label.setIcon(new ImageIcon("Skins/C_background/"
                + setLoc.get(9)+".png"));
                }else if(type.equalsIgnoreCase("discount")){
                label.setIcon(new ImageIcon("Skins/C_background/"
                + setLoc.get(11)+".png"));
                }
        }else{
                if(type.equalsIgnoreCase("year")){
                label.setIcon(new ImageIcon("Skins/C_background/"
                + setLoc.get(0)+".png"));
                }else if(type.equalsIgnoreCase("month")){
                label.setIcon(new ImageIcon("Skins/C_background/"
                + setLoc.get(2)+".png"));
                }else if(type.equalsIgnoreCase("daily")){
                label.setIcon(new ImageIcon("Skins/C_background/"
                + setLoc.get(4)+".png"));
                }else if(type.equalsIgnoreCase("expired")){
                label.setIcon(new ImageIcon("Skins/C_background/"
                + setLoc.get(6)+".png"));
                }else if(type.equalsIgnoreCase("free")){
                label.setIcon(new ImageIcon("Skins/C_background/"
                + setLoc.get(8)+".png"));
                }else if(type.equalsIgnoreCase("discount")){
                label.setIcon(new ImageIcon("Skins/C_background/"
                + setLoc.get(10)+".png"));
                }

        }
    }
    public void setBackground_data(JButton jb, JPanel jp,
            String type, JLabel label){
    if(jb != null){
        jb.addMouseListener(new MouseListener() {
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
                    setBg_loc(label,type,"entered");
                }
            @Override
            public void mouseExited(MouseEvent e) {
                    setBg_loc(label,type,"exit");
            }
        });
    }

    if(jp != null){
        jp.addMouseListener(new MouseListener() {
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
                setBg_loc(label,type,"entered");
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setBg_loc(label,type,"exit");
            }
        });
        }
    }
    void discountTable(){
            DISCOUNT_TABLE_MODEL = new DefaultTableModel()
    {
      @Override
      public boolean isCellEditable(int row, int column)
      {
        return false;
      }
//      public Class getColumnClass(int column)
//      {
//        return getValueAt(0, column).getClass();
//      }
    };
//        Object[] columnName = new Object[13];
//        columnName[0] = "";
//        columnName[1] = "Id";
//        columnName[2] = "Product name";
//        columnName[3] = "Date";
//        columnName[4] = "Quantity";
//        columnName[5] = "Disc. Price";
//        columnName[6] = "Discount";
//        columnName[7] = "%";
//        columnName[8] = "Cashier";
//        columnName[9] = "Strength";
//        columnName[10] = "Preparation";
//        columnName[11] = "Transac. ID";
//        columnName[12] = "Unit price";

      Object[] columnName = new Object[14];
        columnName[0] = "";
        columnName[1] = "Id";
        columnName[2] = "Product name";
        columnName[3] = "Quantity";
        columnName[4] = "Unit price";            
        columnName[5] = "Disc. Price";
        columnName[6] = "%";
        columnName[7] = "Discount";
        columnName[8] = "Date";
        columnName[9] = "Time";
        columnName[10] = "Cashier";
        columnName[11] = "Strength";
        columnName[12] = "Preparation";
        columnName[13] = "Transac. ID";
        DISCOUNT_TABLE_MODEL.setColumnIdentifiers(columnName);
        DISCOUNT_TABLE.setModel(DISCOUNT_TABLE_MODEL);
        DISCOUNT_TABLE.getColumnModel().getColumn(0).setResizable(false);
        DISCOUNT_TABLE.setAutoCreateRowSorter(true);
        DISCOUNT_TABLE.setIntercellSpacing(new Dimension(0, 0));
//        DISCOUNT_TABLE.getTableHeader().setOpaque(false);
//        DISCOUNT_TABLE.getTableHeader().setBackground(new Color(0,0,0,0.6f));
//        DISCOUNT_TABLE.getTableHeader().setForeground(Color.white);   
        DISCOUNT_TABLE.setShowGrid(false);
        DISCOUNT_TABLE.setFont(new Font("Arial", Font.PLAIN, 14));
        DISCOUNT_TABLE.setRowHeight(30);
        DISCOUNT_TABLE.setSelectionBackground(new java.awt.Color(1, 198, 83));
        DISCOUNT_TABLE.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        DISCOUNT_TABLE.getColumn("Date").setMaxWidth(100);
        DISCOUNT_TABLE.getColumn("Date").setMinWidth(40);
        DISCOUNT_TABLE.getColumn("Time").setMaxWidth(100);
        DISCOUNT_TABLE.getColumn("Time").setMinWidth(40);
        DISCOUNT_TABLE.getColumn("Quantity").setMaxWidth(70);
        DISCOUNT_TABLE.getColumn("%").setMaxWidth(40);
        DISCOUNT_TABLE.getColumn("Discount").setMaxWidth(70);
        DISCOUNT_TABLE.getColumn("Disc. Price").setMaxWidth(70);
        DISCOUNT_TABLE.getColumn("").setMaxWidth(40);
        DISCOUNT_TABLE.removeColumn(DISCOUNT_TABLE.getColumnModel().getColumn(0));
        DISCOUNT_TABLE.removeColumn(DISCOUNT_TABLE.getColumnModel().getColumn(0));
        DISCOUNT_TABLE.removeColumn(DISCOUNT_TABLE.getColumnModel().getColumn(9));
        DISCOUNT_TABLE.removeColumn(DISCOUNT_TABLE.getColumnModel().getColumn(9));
        DISCOUNT_TABLE.removeColumn(DISCOUNT_TABLE.getColumnModel().getColumn(9));
//        DISCOUNT_TABLE.removeColumn(DISCOUNT_TABLE.getColumnModel().getColumn(8));
//        DISCOUNT_TABLE.removeColumn(DISCOUNT_TABLE.getColumnModel().getColumn(8));
//        DISCOUNT_TABLE.setDefaultRenderer(String.class, new VisitorRenderer());
////        DISCOUNT_TABLE.getColumn("Expiration Date").setMaxWidth(100);
    }
    public void setUserPrivelege(String userType){
        if(userType.equalsIgnoreCase("Admin")){
            CLEAR_ALL.setVisible(true);
            DELETE.setVisible(true);
//            DAILY_PDF.setVisible(true);
        }else{
            CLEAR_ALL.setVisible(false);
            DELETE.setVisible(false);
//            DAILY_PDF.setVisible(false);
        }
//        System.out.println("Tester!");
    }
    void expiredTable(){
            EXPIRED_MODEL = new DefaultTableModel()
    {
      @Override
      public boolean isCellEditable(int row, int column)
      {
        return false;
      }
    };
        
        Object[] columnName = new Object[10];
        columnName[0] = "";
        columnName[1] = "Product name";
        columnName[2] = "Quantity";
        columnName[3] = "Selling price";
        columnName[4] = "Date Purchase";
        columnName[5] = "Expiration Date";
        columnName[6] = "Purchase From";
        columnName[7] = "id";
        columnName[8] = "Strength";
        columnName[9] = "Preparation";

        EXPIRED_MODEL.setColumnIdentifiers(columnName);
        EXPIRED_LOGS.setModel(EXPIRED_MODEL);
        EXPIRED_LOGS.getColumnModel().getColumn(0).setResizable(false);
        EXPIRED_LOGS.setAutoCreateRowSorter(true);
        EXPIRED_LOGS.setIntercellSpacing(new Dimension(0, 0));
//        EXPIRED_LOGS.getTableHeader().setOpaque(false);
//        EXPIRED_LOGS.getTableHeader().setBackground(new Color(0,0,0,0.6f));
//        EXPIRED_LOGS.getTableHeader().setForeground(Color.white);   
        EXPIRED_LOGS.setShowGrid(false);
        EXPIRED_LOGS.setFont(new Font("Arial", Font.PLAIN, 14));
        EXPIRED_LOGS.setRowHeight(30);
        EXPIRED_LOGS.setSelectionBackground(new java.awt.Color(1, 198, 83));
        EXPIRED_LOGS.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
//        LOGS_LIST.getColumn("Product name").setMaxWidth(150);
        EXPIRED_LOGS.getColumn("").setMaxWidth(30);
        EXPIRED_LOGS.getColumn("Quantity").setMaxWidth(70);
//        EXPIRED_LOGS.getColumn("Payment").setMaxWidth(70);
        EXPIRED_LOGS.getColumn("Selling price").setMaxWidth(70);
//        EXPIRED_LOGS.getColumn("Selling price").setMinWidth(70);
        EXPIRED_LOGS.getColumn("Date Purchase").setMaxWidth(100);
//        EXPIRED_LOGS.getColumn("Date Purchase").setMinWidth(100);
        EXPIRED_LOGS.getColumn("Expiration Date").setMaxWidth(100);
//        EXPIRED_LOGS.getColumn("Expiration Date").setMinWidth(100);
        EXPIRED_LOGS.setDefaultRenderer(String.class, new VisitorRenderer());
        EXPIRED_LOGS.removeColumn(EXPIRED_LOGS.getColumnModel().getColumn(7));
        EXPIRED_LOGS.removeColumn(EXPIRED_LOGS.getColumnModel().getColumn(0));
        EXPIRED_LOGS.removeColumn(EXPIRED_LOGS.getColumnModel().getColumn(7));
        EXPIRED_LOGS.removeColumn(EXPIRED_LOGS.getColumnModel().getColumn(6));
        expired_table();
    }
    public void removeExpired_table(){
                int rowCount = EXPIRED_MODEL.getRowCount();
                //Remove all data row
                    for (int i = rowCount - 1; i >= 0; i--) {
                        EXPIRED_MODEL.removeRow(i);
                    }
    }
    public String getLastFour(String myString) {
    if(myString.length() > 4)
        return myString.substring(myString.length()-4);
    else
        return myString;
}
    public void setTobe_remove_year(String year){
                    for (int rows = 0; rows < EXPIRED_LOGS.getRowCount(); rows++) {
                    String set = EXPIRED_LOGS.getModel().getValueAt(rows, 5).toString();
                if(getLastFour(set).equals(year)){
                        EXPIRED_MODEL.removeRow(rows);
                    }
                }

    }
    public void addData_expired(String product_name, String quantity, String price, String date_purchased,
            String expiration, String purchased, String id, String strength,
            String preparation){
//                    String setLoc = "Skins/C_background/EXPIRED_LOGO.png";
                    
    for(int x=0;x<dh.getExpiredSize();x++){
        EXPIRED_MODEL.addRow(new Object[]{new ImageIcon("Skins/C_background/EXPIRED_LOGO.png"),product_name,
            quantity,price,date_purchased,expiration,purchased,strength,preparation});
    }
    }
    public void expired_table(){
                int rowCount = EXPIRED_MODEL.getRowCount();
                //Remove all data row
                    for (int i = rowCount - 1; i >= 0; i--) {
                        EXPIRED_MODEL.removeRow(i);
                    }

                    String setLoc = "Skins/C_background/EXPIRED_LOGO.png";
                    
    for(int x=0;x<dh.getExpiredSize();x++){
        EXPIRED_MODEL.addRow(new Object[]{new ImageIcon(setLoc),dh.getProduct_name(x),
            dh.getQuantity(x),dh.getPrice(x),
            dh.getDate_purchased(x),dh.getExpiration(x),dh.getPurchased(x),
            dh.getID(x),dh.getStrength(x),dh.getPreparation(x)});
    }

    System.out.println("Size of model to be add in expired is "+dh.getExpiredSize());
    
                LOGS_LIST.invalidate();
                LOGS_LIST.repaint();

//                getNewRenderedTable(LOGS_LIST);
    }
    public void clearAll_expired_list(){
                int rowCount = EXPIRED_MODEL.getRowCount();
                //Remove all data row
                    for (int i = rowCount - 1; i >= 0; i--) {
                        EXPIRED_MODEL.removeRow(i);
                    }
    }
    public void expired_addData(String product_name, String quantity, String price, String date_purchased,
            String expiration, String purchased_from){
            String setLoc = "Skins/C_background/EXPIRED_LOGO.png";

            EXPIRED_MODEL.addRow(new Object[]{new ImageIcon(setLoc),product_name,
            quantity,price,date_purchased,expiration,purchased_from});
    }
    void setUpdateType(String id){
                SQLiteDB_action sdb = SQLiteDB_action.getInstance();
                sdb.setDelete_logs(SCANN_INFO.getSelectedItem().toString(),id);
    }
    public void search(String query){
        if(setSearch.equals("logs")){
            TableRowSorter<DefaultTableModel> df = new TableRowSorter<DefaultTableModel>(LOGS_MODEL);
            LOGS_LIST.setRowSorter(df);
            df.setRowFilter(RowFilter.regexFilter(query));
        }else if(setSearch.equals("discount")){
            TableRowSorter<DefaultTableModel> df = new TableRowSorter<DefaultTableModel>(DISCOUNT_TABLE_MODEL);
            DISCOUNT_TABLE.setRowSorter(df);
            df.setRowFilter(RowFilter.regexFilter(query));
        }else{
            TableRowSorter<DefaultTableModel> df = new TableRowSorter<DefaultTableModel>(EXPIRED_MODEL);
            EXPIRED_LOGS.setRowSorter(df);
            df.setRowFilter(RowFilter.regexFilter(query));
          }
        }
    public void setFree_table(){
                int rowCount = LOGS_MODEL.getRowCount();
                //Remove all data row
                    for (int i = rowCount - 1; i >= 0; i--) {
                        String setFree = LOGS_LIST.getModel().getValueAt(i, 6).toString();
                        if(!setFree.equalsIgnoreCase("Free")){
                            LOGS_MODEL.removeRow(i);
                        }
                    }
    }
    void productTable(){
            LOGS_MODEL = new DefaultTableModel()
    {
      @Override
      public boolean isCellEditable(int row, int column)
      {
        return false;
      }
    };
        Object[] columnName = new Object[11];
        columnName[0] = "Id";
        columnName[1] = "Trans. ID";
        columnName[2] = "Product name";
        columnName[3] = "Quantity";
        columnName[4] = "Unit price";
        columnName[5] = "Selling price";
        columnName[6] = "Payment";
        columnName[7] = "Date";
        columnName[8] = "Time";
        columnName[9] = "Cashier";
        columnName[10] = "discount_checker";

        LOGS_MODEL.setColumnIdentifiers(columnName);
        LOGS_LIST.setModel(LOGS_MODEL);
        LOGS_LIST.getColumnModel().getColumn(0).setResizable(false);
        LOGS_LIST.setAutoCreateRowSorter(true);
        LOGS_LIST.setIntercellSpacing(new Dimension(0, 0));
//        LOGS_LIST.getTableHeader().setOpaque(false);
//        LOGS_LIST.getTableHeader().setBackground(new Color(0,0,0,0.6f));
//        LOGS_LIST.getTableHeader().setForeground(Color.white);   
        LOGS_LIST.setShowGrid(false);
        LOGS_LIST.setFont(new Font("Arial", Font.PLAIN, 14));
        LOGS_LIST.setRowHeight(30);
        LOGS_LIST.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        LOGS_LIST.getColumn("Id").setMaxWidth(70);
        LOGS_LIST.setSelectionBackground(new java.awt.Color(1, 198, 83));
//        LOGS_LIST.getColumn("Product name").setMaxWidth(150);
        LOGS_LIST.getColumn("Quantity").setMaxWidth(70);
        LOGS_LIST.getColumn("Payment").setMaxWidth(70);
        LOGS_LIST.getColumn("Selling price").setMaxWidth(70);
        LOGS_LIST.getColumn("Unit price").setMaxWidth(70);
        LOGS_LIST.getColumn("Date").setMaxWidth(100);
        LOGS_LIST.getColumn("Trans. ID").setMaxWidth(100);
        LOGS_LIST.getColumn("Time").setMaxWidth(70);
        LOGS_LIST.removeColumn(LOGS_LIST.getColumnModel().getColumn(0));
        LOGS_LIST.setDefaultRenderer(String.class, new VisitorRenderer());
        LOGS_LIST.removeColumn(LOGS_LIST.getColumnModel().getColumn(9));
//        LOGS_LIST.removeColumn(LOGS_LIST.getColumnModel().getColumn(8));
//        PRODUCT_LIST.getColumn("Price").setMaxWidth(50);
//        PRODUCT_LIST.getColumn("Selling Price").setMaxWidth(75);
//        PRODUCT_LIST.getColumn("Expiration").setMaxWidth(75);
//        PRODUCT_LIST.getColumn("Unit").setMaxWidth(50);
//        PRODUCT_LIST.getColumn("Date Purchase").setMaxWidth(120);
//        
//        int height = PRODUCT_LIST.getRowHeight();
//        PRODUCT_LIST.setRowHeight(height+10);
//        PRODUCT_LIST.setShowGrid(false); 
                try{
                        getNewRenderedTable(LOGS_LIST);
                  }catch(Exception ex){
                }
    filterTable();
    }
    public static JTable getNewRenderedTable(final JTable table) {
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                try{
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
//                String status = (String)table.getModel().getValueAt(row, 6);
            String status = (String) table.getModel().getValueAt(table.convertRowIndexToModel(row), 10);
                Color bg = Color.decode("#CFD8DC"); /* Expired today */
                if(status.equalsIgnoreCase("yes")) { /* Mid Expiry**/
                    setBackground(bg);
//                  setForeground(Color.WHITE);
                }else{
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }       
                }catch(Exception ex){
                }
                return this;
            }   
        });
        return table;
    }
    public void clearAll_data_view(){
                TOTAL_FREE.setText("");
                TRANS_ID.setText("");
                SELLING_PRICE.setText("");
                UNIT_PRICEx.setText("");
                STRENGTH.setText("");
                PAYMENT.setText("");
                PRODUCT_NAME.setText("");
                QUANTITY.setText("");
                CASHIER.setText("");
                PREPARATION.setText("");
                DATE.setText("");
                EXP_PRICE.setText("");
                EXP_QUANTITY.setText("");
                EXP_PURCHASED_FROM.setText("");
                EXP_DATE_PUR.setText("");
                EXP_DATE_EXP.setText("");
                EXP_STRENGTH.setText("");
                EXP_PREPARATION.setText("");
                DISC_QUANTITY.setText("");
                DISC_DATE.setText("");
                DISC_CASHIER.setText("");
                DISC_PREPARATION.setText("");
                DISC_STRENGTH.setText("");
                DISC_DISCOUNTED_PRICE.setText("");
                DISC_DISCOUNT.setText("");
                DISC_UNIT_PRICE.setText("");
                DISC_DISCOUNTED_PERCENT.setText(""); 
        }
    public void filterTable(){
                int rowCount = LOGS_MODEL.getRowCount();
                //Remove all data row
                    for (int i = rowCount - 1; i >= 0; i--) {
                        LOGS_MODEL.removeRow(i);
                    }
                    
    for(int x=0;x<dh.getLogs_size();x++){
        LOGS_MODEL.addRow(new Object[]{
            dh.getLog_id(x),dh.getTransaction_ID(x),dh.getLog_product(x),
            dh.getLog_quantity(x),dh.getLog_price(x),dh.getLog_selling_price(x),
            dh.getLog_payment(x),dh.getLog_date(x),
            dh.getLog_time(x),dh.getLog_cashier(x),
            dh.getLog_discount_checker(x)});
    }

                LOGS_LIST.invalidate();
                LOGS_LIST.repaint();

//                getNewRenderedTable(LOGS_LIST);
    }
    public void clearLogs_list(){
                int rowCount = LOGS_MODEL.getRowCount();
                //Remove all data row
                    for (int i = rowCount - 1; i >= 0; i--) {
                        LOGS_MODEL.removeRow(i);
                    }
    }
    public void clearDiscount_filter(){
                int rowCount = DISCOUNT_TABLE_MODEL.getRowCount();
                //Remove all data row
                    for (int i = rowCount - 1; i >= 0; i--) {
                        DISCOUNT_TABLE_MODEL.removeRow(i);
                    }
    }
    public void discountFilter(String id, String product_name, String date, String quantity, String selling_price,
            String price_and_percent, String cashier,String strength, String preparation,
            String transaction_id,String unit_price){

     ArrayList<String> set = new ArrayList<String>();
        
     StringTokenizer st = new StringTokenizer(price_and_percent," ");  
     while (st.hasMoreTokens()) {  
         set.add(st.nextToken().toString());
     }  
//     DISCOUNT_TABLE_MODEL.addRow(new Object[]{
//                new ImageIcon("Skins/C_background/DISCOUNT_LOGO.png"),id,
//                product_name,date,quantity,selling_price,set.get(0),
//                set.get(1),cashier,strength,preparation,transaction_id,
//                unit_price
//            });

     DISCOUNT_TABLE_MODEL.addRow(new Object[]{
                new ImageIcon("Skins/C_background/DISCOUNT_LOGO.png"),id,
                product_name,quantity,unit_price,selling_price,set.get(1),
                set.get(0),date.split(" ")[0],date.split(" ")[1],cashier,strength,preparation,transaction_id});

     set.clear();
    }
    public void MonthlyFilter_table(){
                int rowCount = LOGS_MODEL.getRowCount();
                //Remove all data row
                    for (int i = rowCount - 1; i >= 0; i--) {
                        LOGS_MODEL.removeRow(i);
                    }

        for(int x=0;x<dh.getScannedID_size();x++){
        dh.setIndex_forLogs(dh.getMonthly_ID(x));
        LOGS_MODEL.addRow(new Object[]{
            dh.getLog_id(dh.getIndex()),dh.getTransaction_ID(dh.getIndex()),dh.getLog_product(dh.getIndex()),
            dh.getLog_quantity(dh.getIndex()),dh.getLog_price(dh.getIndex()),dh.getLog_selling_price(dh.getIndex()),
            dh.getLog_payment(dh.getIndex()),dh.getLog_date(dh.getIndex()),
            dh.getLog_time(dh.getIndex()),dh.getLog_cashier(dh.getIndex()),
            dh.getLog_discount_checker(dh.getIndex())
            });
    }

                LOGS_LIST.invalidate();
                LOGS_LIST.repaint();

//                getNewRenderedTable(LOGS_LIST);
    }
    public void DailyFilter_table(){
                int rowCount = LOGS_MODEL.getRowCount();
                //Remove all data row
                    for (int i = rowCount - 1; i >= 0; i--) {
                        LOGS_MODEL.removeRow(i);
                    }
                    
    for(int x=0;x<dh.getScannedID_size();x++){
        dh.setIndex_forLogs(dh.getMonthly_ID(x));
        LOGS_MODEL.addRow(new Object[]{
            dh.getLog_id(dh.getIndex()),dh.getLog_product(dh.getIndex()),
            dh.getLog_quantity(dh.getIndex()),dh.getLog_selling_price(dh.getIndex()),
            dh.getLog_payment(dh.getIndex()),dh.getLog_date(dh.getIndex()),
            dh.getLog_time(dh.getIndex()),dh.getLog_cashier(dh.getIndex())
            });
    }
                LOGS_LIST.invalidate();
                LOGS_LIST.repaint();
    }
    public void setText(){
        PRODUCT_NAME.setText("");
        QUANTITY.setText("");
        SELLING_PRICE.setText("");
        PAYMENT.setText("");
        DATE.setText("");
        //TIME.setText("");
        CASHIER.setText("");
        PREPARATION.setText("");
        STRENGTH.setText("");
        TRANS_ID.setText("");
        EXPIRED_PRODUCT_NAME.setText("");
        EXPIRED_QUANTITY.setText("");
        EXPIRED_PRICE.setText("");
        EXPIRED_DATE_PURCHASE.setText("-");
        EXPIRED_DATE.setText("");
        EXPIRED_PURCHASE_FROM.setText("");
    }
    private void dailyPrint(){
    int result;
        
    JFileChooser chooser = new JFileChooser(); 
    chooser.setCurrentDirectory(new java.io.File("."));
    chooser.setDialogTitle("Choose location to save");
    chooser.setApproveButtonText("Save here");
    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    //
    // disable the "All files" option.
    //
    chooser.setAcceptAllFileFilterUsed(false);
    //    
    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(
            chooser.getSelectedFile()+"\\Business Inventory "
            + "System Report "+
            MONTHLY_INFO_SET_MONTH.getSelectedItem().toString()+" ,"+
            MONTHLY_INFO_SET_YEAR.getSelectedItem().toString()+
            ".pdf"));
            doc.open();
            Paragraph p = new Paragraph();
            p.add(
                "Daily report date: "+ 
                MONTH.getSelectedItem().toString()+" "+
                DAY.getSelectedItem().toString()+","+
                YEAR.getSelectedItem().toString()+"\n"
            );
            p.setAlignment(Element.ALIGN_CENTER);
            doc.add(p);
            Paragraph px = new Paragraph();
            px.add(
                ""            
            );
            px.setAlignment(Element.ALIGN_CENTER);
            doc.add(px);
            PdfPTable pdfTable = new PdfPTable(LOGS_LIST.getColumnCount());
            //adding table headers
            for (int i = 0; i < LOGS_LIST.getColumnCount(); i++) {
                pdfTable.addCell(LOGS_LIST.getColumnName(i));
            }
            //extracting data from the JTable and inserting it to PdfPTable
            for (int rows = 0; rows < LOGS_LIST.getRowCount(); rows++) {
                for (int cols = 0; cols < LOGS_LIST.getColumnCount(); cols++) {
                    pdfTable.addCell(LOGS_LIST.getModel().getValueAt(rows, cols).toString());

                }
            }
            doc.add(pdfTable);
            Paragraph x = new Paragraph();
            x.add(
                " Total Quantity: "+dh.getTotalQuantities()+
                " Total Income: "+dh.getTotalIncome()
            );
            x.setAlignment(Element.ALIGN_CENTER);
            doc.add(x);
            doc.close();
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null,"File ");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,ex);
        }
      }
    }
    public void setFilter_listener(JButton button){
        button.addMouseListener(new MouseListener() {

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
                button.setIcon(new ImageIcon("Skins/C_buttons/"
                    + "scann_2.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setIcon(new ImageIcon("Skins/C_buttons/"
                    + "scann_1.png"));
            }
        });
    }
    private void monthlyPrint() throws PrinterException{
        int result;

        JFileChooser chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Choose location to save");
        chooser.setApproveButtonText("Save here");
        // Set the tool tip
        chooser.setApproveButtonToolTipText("New Approve Tool Tip");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //    
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
            try {
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(
                chooser.getSelectedFile()+"\\Business Inventory "
                + "System Report "+
                MONTHLY_INFO_SET_MONTH.getSelectedItem().toString()+" ,"+
                MONTHLY_INFO_SET_YEAR.getSelectedItem().toString()+
                ".pdf"));
                doc.open();
                Paragraph p = new Paragraph();
                p.add(
                        "Monthly report date: "+ 
                        MONTHLY_INFO_SET_MONTH.getSelectedItem().toString()+"/"+
                        MONTHLY_INFO_SET_YEAR.getSelectedItem().toString()+"(M/Y)/n"
                );
                p.setAlignment(Element.ALIGN_CENTER);
                doc.add(p);
                Paragraph px = new Paragraph();
                px.add(
                    ""            
                );
                px.setAlignment(Element.ALIGN_CENTER);
                doc.add(px);
                PdfPTable pdfTable = new PdfPTable(LOGS_LIST.getColumnCount());
                //adding table headers
                for (int i = 0; i < LOGS_LIST.getColumnCount(); i++) {
                    pdfTable.addCell(LOGS_LIST.getColumnName(i));
                }
                //extracting data from the JTable and inserting it to PdfPTable
                for (int rows = 0; rows < LOGS_LIST.getRowCount(); rows++) {
                    for (int cols = 0; cols < LOGS_LIST.getColumnCount(); cols++) {
                        pdfTable.addCell(LOGS_LIST.getModel().getValueAt(rows, cols).toString());
                    }
                }
                doc.add(pdfTable);
                Paragraph x = new Paragraph();
                x.add(
                    " Total Quantity: "+dh.getTotalQuantities()+
                    " Total Income: "+dh.getTotalIncome()
                );
                x.setAlignment(Element.ALIGN_CENTER);
                doc.add(x);
                doc.close();
            } catch (DocumentException ex) {
                JOptionPane.showMessageDialog(null,"File ");
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null,ex);
            }
        }
    }
    void freeMonthly_scann(){
        myTotal = 0;
        SET_TOTAL_PRICE.clear();
        SET_TOTAL_QUANTITY.clear();
        for (int count = 0; count < LOGS_MODEL.getRowCount(); count++){
            SET_TOTAL_PRICE.add(LOGS_MODEL.getValueAt(count, 4).toString());
            SET_TOTAL_QUANTITY.add(LOGS_MODEL.getValueAt(count, 3).toString());
        }
        for(int x=0;x<SET_TOTAL_PRICE.size();x++){
            float parsePrice = Float.parseFloat(SET_TOTAL_PRICE.get(x));
            int parseQuantity = Integer.parseInt(SET_TOTAL_QUANTITY.get(x));
            
            float setTotal = parsePrice * parseQuantity;            
            myTotal = setTotal + myTotal;
        }
    }
    void freeYearly_scann(){
        myTotal = 0;
        SET_TOTAL_PRICE.clear();
        SET_TOTAL_QUANTITY.clear();
        for (int count = 0; count < LOGS_MODEL.getRowCount(); count++){
            System.out.println("Get date "+LOGS_MODEL.getValueAt(count, 7).toString());
            System.out.println("Check if free "+LOGS_MODEL.getValueAt(count, 6).toString());
            if(LOGS_MODEL.getValueAt(count, 6).toString().equalsIgnoreCase("FREE")
                 && getLastFour(LOGS_MODEL.getValueAt(count, 7).toString()).equals(YEAR_SET_YEAR.getSelectedItem().toString())){
                SET_TOTAL_PRICE.add(LOGS_MODEL.getValueAt(count, 4).toString());
                SET_TOTAL_QUANTITY.add(LOGS_MODEL.getValueAt(count, 3).toString());
            }else{
                LOGS_MODEL.removeRow(count);
            }
        }
        for(int x=0;x<SET_TOTAL_PRICE.size();x++){
            float parsePrice = Float.parseFloat(SET_TOTAL_PRICE.get(x));
            int parseQuantity = Integer.parseInt(SET_TOTAL_QUANTITY.get(x));
            
            float setTotal = parsePrice * parseQuantity;            
            myTotal = setTotal + myTotal;
        }
    }
    void setGross_income(){
        myTotal = 0;
        SET_TOTAL_PRICE.clear();
        SET_TOTAL_QUANTITY.clear();

        for (int count = 0; count < LOGS_MODEL.getRowCount(); count++){
            if(LOGS_MODEL.getValueAt(count, 6).toString().equalsIgnoreCase("FREE")){
                SET_TOTAL_PRICE.add(0+"");
            }else{
                SET_TOTAL_PRICE.add(LOGS_MODEL.getValueAt(count, 6).toString());
            }
        }
        
        for(int x=0;x<SET_TOTAL_PRICE.size();x++){
            float parsePrice = Float.parseFloat(SET_TOTAL_PRICE.get(x));
            myTotal = parsePrice + myTotal;
        }
    }
    public void setDiscount_total(){
        float setFloat = Float.parseFloat(mDc.roundOffTo2DecPlaces((float) dh.getTotalIncome()));
        DISCOUNT_TOTAL.setText(""+setFloat);
    }
    void Listener(){
        DISCOUNT_YEARLY_SCANN.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            clearAll_data_view();
                SQLiteDB_action sdb = SQLiteDB_action.getInstance();
                
                int setYear = Integer.parseInt(DISCOUNT_YEARLY_YEAR.getSelectedItem().toString());
                
                sdb.discountYearly_calculator(setYear);
                setDiscount_total();
                dh.clearIncome();
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
        DISCOUNT_MONTHLY_SCANN.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            clearAll_data_view();
                SQLiteDB_action sdb = SQLiteDB_action.getInstance();
                
                        String month = "";

            switch(DISCOUNT_MONTHLY_MONTH.getSelectedItem().toString()) {
               case "January" :
                  month = "1";
                  break;
               case "February" :
                  month = "2";
                  break;
               case "March" :
                  month = "3";
                  break;
               case "April" :
                  month = "4";
                  break;
               case "May" :
                  month = "5";
                  break;
               case "June" :
                  month = "6";
                  break;
               case "July" :
                  month = "7";
                  break;
               case "August" :
                  month = "8";
                  break;
               case "September" :
                  month = "9";
                  break;
               case "October" :
                  month = "10";
                  break;
               case "November" :
                  month = "11";
                  break;
               case "December" :
                  month = "12";
                  break;
               default : // Optional
                  // Statements
              }

                int setMonth = Integer.parseInt(month);
                int setYear = Integer.parseInt(DISCOUNT_MONTHLY_YEAR.getSelectedItem().toString());
                clearDiscount_filter();
                sdb.discountMonth_calculator(setYear,setMonth);
                setDiscount_total();
                dh.clearIncome();
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
        DISCOUNT_YEARLY.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DISCOUNT_MONTHLY_JP.setVisible(false);
                DISCOUNT_YEARY_JP.setVisible(true);
                DISCOUNT_TOTAL.setText("");
                ImageIcon EXPIRED_MONTHLY_II = new ImageIcon("Skins/C_buttons/MONTHLY_I.png");
                DISCOUNT_MONTHLY.setIcon(EXPIRED_MONTHLY_II);
                ImageIcon EXPIRED_YEARLY_II = new ImageIcon("Skins/C_buttons/YEARLY_II.png");
                DISCOUNT_YEARLY.setIcon(EXPIRED_YEARLY_II);
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
        DISCOUNT_MONTHLY.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ImageIcon EXPIRED_MONTHLY_II = new ImageIcon("Skins/C_buttons/MONTHLY_II.png");
                DISCOUNT_MONTHLY.setIcon(EXPIRED_MONTHLY_II);
                ImageIcon EXPIRED_YEARLY_II = new ImageIcon("Skins/C_buttons/YEARLY_I.png");
                DISCOUNT_YEARLY.setIcon(EXPIRED_YEARLY_II);
                DISCOUNT_MONTHLY_JP.setVisible(true);
                DISCOUNT_YEARY_JP.setVisible(false);
                DISCOUNT_TOTAL.setText("");
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
        EXPIRED_YEARLY.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MONTHLY_JP.setVisible(false);
                YEARY_JP.setVisible(true);
        ImageIcon EXPIRED_MONTHLY_II = new ImageIcon("Skins/C_buttons/MONTHLY_I.png");
        EXPIRED_MONTHLY.setIcon(EXPIRED_MONTHLY_II);
        ImageIcon EXPIRED_YEARLY_II = new ImageIcon("Skins/C_buttons/YEARLY_II.png");
        EXPIRED_YEARLY.setIcon(EXPIRED_YEARLY_II);
        EXPIRED_TOTAL.setText("");
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
        EXPIRED_MONTHLY.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MONTHLY_JP.setVisible(true);
                YEARY_JP.setVisible(false);
        ImageIcon EXPIRED_MONTHLY_II = new ImageIcon("Skins/C_buttons/MONTHLY_II.png");
        EXPIRED_MONTHLY.setIcon(EXPIRED_MONTHLY_II);
        ImageIcon EXPIRED_YEARLY_II = new ImageIcon("Skins/C_buttons/YEARLY_I.png");
        EXPIRED_YEARLY.setIcon(EXPIRED_YEARLY_II);
        EXPIRED_TOTAL.setText("null");
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
        CLEAR_ALL.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Tester");
//          String answer;
//
//          JPasswordField pwd = new JPasswordField(10);
//    int action = JOptionPane.showConfirmDialog(null, pwd,"Enter Password",JOptionPane.OK_CANCEL_OPTION);
//
//          if (new String(pwd.getPassword()).equals(dh.getPassword())){
//            int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
//                if (reply == JOptionPane.YES_OPTION) {
//                if(SCANN_INFO.getSelectedItem().toString().equalsIgnoreCase("Daily")){
////                    
////                    int row = LOGS_LIST.getSelectedRow();
////                    String discount_checker = LOGS_LIST.getModel().getValueAt(row, 10).toString();
////                    
////                    dh.clearLogs();
////                    dh.setData_remover();
////                    if(discount_checker.equals("yes")){
////                        
////                    }else{
//////                        SQLiteDB_action sdb = SQLiteDB_action.getInstance();
//////                        sdb.deleteFree_logs();
////                    }
////                    productTable();
//                }else if(SCANN_INFO.getSelectedItem().toString().equalsIgnoreCase("Monthly")){
//                    dh.clearLogs();
//                    dh.setData_remover();
//                    SQLiteDB_action sdb = SQLiteDB_action.getInstance();
//                    sdb.deleteFree_logs();
//                    productTable();
//                }else if(SCANN_INFO.getSelectedItem().toString().equalsIgnoreCase("Yearly")){
//                    dh.clearLogs();
//                    dh.setData_remover();
//                    SQLiteDB_action sdb = SQLiteDB_action.getInstance();
//                    sdb.deleteFree_logs();
//                    productTable();
//                }else if(SCANN_INFO.getSelectedItem().toString().equalsIgnoreCase("Free")){
//                    dh.free_Remover();
//                    dh.setData_remover();
//                    SQLiteDB_action sdb = SQLiteDB_action.getInstance();
//                    sdb.deleteFree_logs();
//                    productTable();
//                }else if(SCANN_INFO.getSelectedItem().toString().equalsIgnoreCase("Discount")){
//                    SQLiteDB_action sdb =  SQLiteDB_action.getInstance();
//                    try {
//                        sdb.clearDiscountData();
//                        SQLiteDB sdbx = SQLiteDB.getInstance();
//                        sdbx.retrieveDiscount_data();
//                    } catch (SQLException ex) {
//                        Logger.getLogger(Logs_Panel.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    
//                }else{
//                    SQLiteDB_action sdb = SQLiteDB_action.getInstance();
//                    try {
//                        sdb.clear_Expired();
//                    } catch (SQLException ex) {
//                        Logger.getLogger(Logs_Panel.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    dh.clearAll_expire();
//                    expired_table();
//                }
//                if(SCANN_INFO.getSelectedItem().toString().equals("Free")){
//                    setFree_table();
//                }else{
////                    productTable();
//                    SQLiteDB db = SQLiteDB.getInstance();
//                    db.retrieveDiscount_data();
//                    db.retrieveLogs();
//                    clearLogs_list();
//                    clearAll_expired_list();
//                    filterTable();
//                    expired_table();
//                    SEARCH_TF.setText("Search");
//                    SEARCH_TF.setForeground(Color.GRAY);
//                }
//                setText();
//                }
//          }else if(action<0){
//              JOptionPane.showMessageDialog(null,"Wrong password!");
//          }
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
        FREE_YEARLY_FILTER.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            clearAll_data_view();
            filterTable();
            setFree_table();
            freeYearly_scann();
            TOTAL_FREE.setText(""+mDc.roundOffTo2DecPlaces(myTotal));
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
        FREE_MONTHLY_FILTER.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            clearAll_data_view();
                        String month = "";
                        String year = MONTH_SET_YEAR.getSelectedItem().toString();

            switch(MONTH_SET_MONTH.getSelectedItem().toString()) {
               case "January" :
                  month = "1";
                  break;
               case "February" :
                  month = "2";
                  break;
               case "March" :
                  month = "3";
                  break;
               case "April" :
                  month = "4";
                  break;
               case "May" :
                  month = "5";
                  break;
               case "June" :
                  month = "6";
                  break;
               case "July" :
                  month = "7";
                  break;
               case "August" :
                  month = "8";
                  break;
               case "September" :
                  month = "9";
                  break;
               case "October" :
                  month = "10";
                  break;
               case "November" :
                  month = "11";
                  break;
               case "December" :
                  month = "12";
                  break;
               default : // Optional
                  // Statements
              }
            int setYear = Integer.parseInt(year);
            int setMonth = Integer.parseInt(month);
            try{
            dh.freeMonyhly_scann(setYear,setMonth);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }
            
            float setTotal_income = (float) dh.getTotalIncome();
            
            MONTHLY_TOTAL_QUANTITY.setText(""+dh.getTotalQuantities());
            MONTHLY_TOTAL_INCOME.setText(""+mDc.roundOffTo2DecPlaces(setTotal_income));
            try{
            MonthlyFilter_table();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Second try error");
            }
            freeMonthly_scann();
            TOTAL_FREE.setText(""+mDc.roundOffTo2DecPlaces(myTotal));
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
        FREE_YEARLY_SCANN.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            clearAll_data_view();
                productTable();
                setFree_table();
                FREE_MONTHLY_SCANN_JP.setVisible(false);
                FREE_YEARLY_SCANN_JP.setVisible(true);
        ImageIcon FREE_MONTHLY_II = new ImageIcon("Skins/C_buttons/MONTHLY_I.png");
        FREE_MONTHLY_SCANN.setIcon(FREE_MONTHLY_II);
        ImageIcon FREE_YEARLY_II = new ImageIcon("Skins/C_buttons/YEARLY_II.png");
        FREE_YEARLY_SCANN.setIcon(FREE_YEARLY_II);
        TOTAL_FREE.setText("");
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
        FREE_MONTHLY_SCANN.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            clearAll_data_view();
                productTable();
                setFree_table();
                FREE_MONTHLY_SCANN_JP.setVisible(true);
                FREE_YEARLY_SCANN_JP.setVisible(false);
                ImageIcon FREE_MONTHLY_II = new ImageIcon("Skins/C_buttons/MONTHLY_II.png");
                FREE_MONTHLY_SCANN.setIcon(FREE_MONTHLY_II);
                ImageIcon FREE_YEARLY_II = new ImageIcon("Skins/C_buttons/YEARLY_I.png");
                FREE_YEARLY_SCANN.setIcon(FREE_YEARLY_II);
                TOTAL_FREE.setText("");
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
//        DAILY_PDF.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if(setSearch.equals("logs")){
//                    MessageFormat headerFormat = new MessageFormat("Product list");
//                    MessageFormat footerFormat = new MessageFormat("- {0} -");
//                    try {
//                        LOGS_LIST.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
//                    } catch (PrinterException ex) {
//                        Logger.getLogger(Logs_Panel.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }else if(setSearch.equals("free")){
//                    MessageFormat headerFormat = new MessageFormat("Product list");
//                    MessageFormat footerFormat = new MessageFormat("- {0} -");
//                    try {
//                        LOGS_LIST.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
//                    } catch (PrinterException ex) {
//                        Logger.getLogger(Logs_Panel.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }else if(setSearch.equals("discount")){
//                    MessageFormat headerFormat = new MessageFormat("Product list");
//                    MessageFormat footerFormat = new MessageFormat("- {0} -");
//                    try {
//                        DISCOUNT_TABLE.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
//                    } catch (PrinterException ex) {
//                        Logger.getLogger(Logs_Panel.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }else{
//                    MessageFormat headerFormat = new MessageFormat("Product list");
//                    MessageFormat footerFormat = new MessageFormat("- {0} -");
//                    try {
//                        EXPIRED_LOGS.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
//                    } catch (PrinterException ex) {
//                        Logger.getLogger(Logs_Panel.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                System.out.println("Printing in progress.....!");
//            }
//            @Override
//            public void mousePressed(MouseEvent e) {
//            }
//            @Override
//            public void mouseReleased(MouseEvent e) {
//            }
//            @Override
//            public void mouseEntered(MouseEvent e) {
//            }
//            @Override
//            public void mouseExited(MouseEvent e) {
//            }
//        });
       YEAR_SCAN.addMouseListener(new MouseListener() {
           @Override
           public void mouseClicked(MouseEvent e) {
               String getYear = YEAR_CB.getSelectedItem().toString();
                       
                dh.yearly_Scann(getYear);
                YEAR_TOTAL_QUANTITY.setText(""+dh.getTotalQuantities());
                float setIncome = Float.parseFloat(dh.getTotalIncome()+"");
                YEAR_TOTAL_INCOME.setText(""+mDc.roundOffTo2DecPlaces(setIncome));
                setGross_income();
                YEARLY_GROSS_INCOME.setText(""+mDc.roundOffTo2DecPlaces(myTotal));
                productTable();
                 float setText = 0;
                for (int rows = 0; rows < LOGS_LIST.getRowCount(); rows++) {
                    String set = LOGS_LIST.getModel().getValueAt(rows, 6).toString();
                    float x;
                    if(set.equalsIgnoreCase("Free")){
                        x = Float.parseFloat("0.00");
                    }else{
                        x = Float.parseFloat(set);
                    }
                    setText  = setText + x;
            }
                YEARLY_GROSS_INCOME.setText(""+mDc.roundOffTo2DecPlaces(setText));
            clearAll_data_view();
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
        SCANN.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clearAll_data_view();
                String month = "";
                String day = DAY.getSelectedItem().toString();
                String year = YEAR.getSelectedItem().toString();
                
    switch(MONTH.getSelectedItem().toString()) {
       case "January" :
          month = "1";
          break;
       case "February" :
          month = "2";
          break;
       case "March" :
          month = "3";
          break;
       case "April" :
          month = "4";
          break;
       case "May" :
          month = "5";
          break;
       case "June" :
          month = "6";
          break;
       case "July" :
          month = "7";
          break;
       case "August" :
          month = "8";
          break;
       case "September" :
          month = "9";
          break;
       case "October" :
          month = "10";
          break;
       case "November" :
          month = "11";
          break;
       case "December" :
          month = "12";
          break;
       default : // Optional
          // Statements
    }
                String setDate =month+"/"+day+"/"+year;
                dh.daily_scann(setDate);
                TOTAL_QUANTITY_SOLD.setText("Total Quantities      "+dh.getTotalQuantities());
                float setIncome = Float.parseFloat(dh.getTotalIncome()+"");
                TOTAL_INCOME.setText(""+mDc.roundOffTo2DecPlaces(setIncome));
                MonthlyFilter_table();
                setGross_income();
//                GROSS_INCOME.setText("Gross Income           "+mDc.roundOffTo2DecPlaces(myTotal));
                float setText = 0;
                for (int rows = 0; rows < LOGS_LIST.getRowCount(); rows++) {
                    String set = LOGS_LIST.getModel().getValueAt(rows, 6).toString();
                    float x;
                    if(set.equalsIgnoreCase("Free")){
                        x = Float.parseFloat("0.00");
                    }else{
                        x = Float.parseFloat(set);
                    }
                    setText  = setText + x;
            }
                GROSS_INCOME.setText(""+mDc.roundOffTo2DecPlaces(setText));
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
        DELETE.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Delete button");
          String answer;

    JPasswordField pwd = new JPasswordField(10);
    int action = JOptionPane.showConfirmDialog(null, pwd,"Enter Password",JOptionPane.OK_CANCEL_OPTION);

          if (new String(pwd.getPassword()).equals(dh.getPassword())){
            int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", null, JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                if(SCANN_INFO.getSelectedItem().toString().equalsIgnoreCase("Daily") ||
                   SCANN_INFO.getSelectedItem().toString().equalsIgnoreCase("Monthly") ||
                   SCANN_INFO.getSelectedItem().toString().equalsIgnoreCase("Yearly")){
                    int row = LOGS_LIST.getSelectedRow();
                    String discout_checker = LOGS_LIST.getModel().getValueAt(row, 10).toString();
                    String transaction_id = LOGS_LIST.getModel().getValueAt(row, 1).toString();
                    String product_name = LOGS_LIST.getModel().getValueAt(row, 2).toString();
                    if(discout_checker.equalsIgnoreCase("yes")){
                        SQLiteDB_action sdb = SQLiteDB_action.getInstance();
                        sdb.delete_for_logs(transaction_id,
                        product_name);
                        sdb.delete_for_discount(transaction_id,
                        product_name);
                    }else{
                        int column = 0;
                        String value = LOGS_LIST.getModel().getValueAt(row, column).toString();
                        setUpdateType(value);
                    }
                }
//                else if(SCANN_INFO.getSelectedItem().toString().equalsIgnoreCase("Monthly")){
//                    int row = LOGS_LIST.getSelectedRow();
//                    String discout_checker = LOGS_LIST.getModel().getValueAt(row, 10).toString();
//                    String transaction_id = LOGS_LIST.getModel().getValueAt(row, 9).toString();
//                    String product_name = LOGS_LIST.getModel().getValueAt(row, 1).toString();
//                    if(discout_checker.equalsIgnoreCase("yes")){
//                        SQLiteDB_action sdb = SQLiteDB_action.getInstance();
//                        sdb.delete_for_logs(transaction_id,
//                        product_name);
//                        sdb.delete_for_discount(transaction_id,
//                        product_name);
//                    }else{
//                        int column = 0;
//                        String value = LOGS_LIST.getModel().getValueAt(row, column).toString();
//                        setUpdateType(value);
//                    }
//                }else if(SCANN_INFO.getSelectedItem().toString().equalsIgnoreCase("Yearly")){
//                    int row = LOGS_LIST.getSelectedRow();
//                    String discout_checker = LOGS_LIST.getModel().getValueAt(row, 10).toString();
//                    String transaction_id = LOGS_LIST.getModel().getValueAt(row, 9).toString();
//                    String product_name = LOGS_LIST.getModel().getValueAt(row, 1).toString();
//                    if(discout_checker.equalsIgnoreCase("yes")){
//                        SQLiteDB_action sdb = SQLiteDB_action.getInstance();
//                        sdb.delete_for_logs(transaction_id,
//                        product_name);
//                        sdb.delete_for_discount(transaction_id,
//                        product_name);
//                    }else{
//                        int column = 0;
//                        String value = LOGS_LIST.getModel().getValueAt(row, column).toString();
//                        setUpdateType(value);
//                    }
//                }
                else if(SCANN_INFO.getSelectedItem().toString().equalsIgnoreCase("Free")){
                    int column = 0;
                    int row = LOGS_LIST.getSelectedRow();
                    String value = LOGS_LIST.getModel().getValueAt(row, column).toString();
                    setUpdateType(value);
                }else if(SCANN_INFO.getSelectedItem().toString().equalsIgnoreCase("Discount")){
//                    System.out.println("Discount!");
//                    int selectedRow = DISCOUNT_TABLE.getSelectedRow();
//                    selectedRow = DISCOUNT_TABLE.convertRowIndexToModel(selectedRow);                    
//                    setUpdateType((String)DISCOUNT_TABLE.getModel().getValueAt(selectedRow, 1));
                    
                    int row = DISCOUNT_TABLE.getSelectedRow();
                    String transaction_id = DISCOUNT_TABLE.getModel().getValueAt(row, 11).toString();
                    String product_name = DISCOUNT_TABLE.getModel().getValueAt(row, 2).toString();
                        SQLiteDB_action sdb = SQLiteDB_action.getInstance();
                        sdb.delete_for_logs(transaction_id,
                        product_name);
                        sdb.delete_for_discount(transaction_id,
                        product_name);
                }else if(SCANN_INFO.getSelectedItem().toString().equalsIgnoreCase("Expired")){
                    int selectedRow = EXPIRED_LOGS.getSelectedRow();
                     selectedRow = EXPIRED_LOGS.convertRowIndexToModel(selectedRow);                    
                        setUpdateType((String)EXPIRED_LOGS.getModel().getValueAt(selectedRow, 7));
                }
             }
          }else if(action<0){
              JOptionPane.showMessageDialog(null,"Wrong password!");
          }        
                if(SCANN_INFO.getSelectedItem().toString().equals("Free")){
                    setFree_table();
                }else{
                    SQLiteDB db = SQLiteDB.getInstance();
                    db.retrieveDiscount_data();
                    db.retrieveLogs();
                    clearLogs_list();
                    clearAll_expired_list();
                    filterTable();
                    expired_table();
                    SEARCH_TF.setText("Search");
                    SEARCH_TF.setForeground(Color.GRAY);
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
        REFRESH.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SCANN_INFO.getSelectedItem().toString().equals("Free")){
                    setFree_table();
                }else{
                    SQLiteDB db = SQLiteDB.getInstance();
                    db.retrieveDiscount_data();
                    db.retrieveLogs();
                    clearLogs_list();
                    clearAll_expired_list();
                    db.retrieveExpired();
                    filterTable();
                    expired_table();
                    SEARCH_TF.setText("Search");
                    SEARCH_TF.setForeground(Color.GRAY);
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
                ImageIcon refresh_ii = new ImageIcon("Skins/C_buttons/"
                        + "REFRESH_2_BUTTON.png");
                REFRESH.setIcon(refresh_ii);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon refresh_ii = new ImageIcon("Skins/C_buttons/"
                        + "REFRESH_1_BUTTON.png");
                REFRESH.setIcon(refresh_ii);
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
                search(query);
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
            }

            @Override
            public void focusLost(FocusEvent e) {
            if(SEARCH_TF.getText().isEmpty()){
            SEARCH_TF.setForeground(Color.GRAY);
            SEARCH_TF.setText("Search");
            }else{
            SEARCH_TF.setForeground(Color.BLACK);
            SEARCH_TF.setText(SEARCH_TF.getText());
            }
            }
        });
        BACK_jb.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                SecondMainPanel s = SecondMainPanel.getInstance();
                s.setVisible(true);
                TOTAL_QUANTITY_SOLD.setText("Total Quantities");
                TOTAL_INCOME.setText("");
                MONTHLY_TOTAL_QUANTITY.setText("");
                MONTHLY_TOTAL_INCOME.setText("");
                YEAR_TOTAL_QUANTITY.setText("Total Quantities");
                YEAR_TOTAL_INCOME.setText("");
                YEARLY_GROSS_INCOME.setText("");
                MONTHLY_GROSS_INCOME.setText("-");
                GROSS_INCOME.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                BACK_jb.setForeground(Color.BLUE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                BACK_jb.setForeground(Color.BLACK);
            }
        });
        EXPIRED_YEARLY_SCANN.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            clearAll_data_view();
            dh.expireYearly_scann(EXPIRED_YEARLY_YEAR.getSelectedItem().toString());
            float setTotal_income = (float) dh.getTotalIncome();
            
            EXPIRED_TOTAL.setText(""+mDc.roundOffTo2DecPlaces(setTotal_income));
//            expired_table();
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
        EXPIRED_MONTHLY_SCANN.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            clearAll_data_view();
                        String month = "";
                        String year = EXPIRED_MONTHLY_YEAR.getSelectedItem().toString();

            switch(EXPIRED_MONTHLY_MONTH.getSelectedItem().toString()) {
               case "January" :
                  month = "1";
                  break;
               case "February" :
                  month = "2";
                  break;
               case "March" :
                  month = "3";
                  break;
               case "April" :
                  month = "4";
                  break;
               case "May" :
                  month = "5";
                  break;
               case "June" :
                  month = "6";
                  break;
               case "July" :
                  month = "7";
                  break;
               case "August" :
                  month = "8";
                  break;
               case "September" :
                  month = "9";
                  break;
               case "October" :
                  month = "10";
                  break;
               case "November" :
                  month = "11";
                  break;
               case "December" :
                  month = "12";
                  break;
               default : // Optional
                  // Statements
              }
            int setYear = Integer.parseInt(year);
            int setMonth = Integer.parseInt(month);
            dh.expireMonthly_scann(setYear,setMonth);
            
            float setTotal_income = (float) dh.getTotalIncome();
            
            EXPIRED_TOTAL.setText(""+mDc.roundOffTo2DecPlaces(setTotal_income));
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
        MONTHLY_SCANN.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            clearAll_data_view();
                        String month = "";
                        String year = MONTHLY_INFO_SET_YEAR.getSelectedItem().toString();

            switch(MONTHLY_INFO_SET_MONTH.getSelectedItem().toString()) {
               case "January" :
                  month = "1";
                  break;
               case "February" :
                  month = "2";
                  break;
               case "March" :
                  month = "3";
                  break;
               case "April" :
                  month = "4";
                  break;
               case "May" :
                  month = "5";
                  break;
               case "June" :
                  month = "6";
                  break;
               case "July" :
                  month = "7";
                  break;
               case "August" :
                  month = "8";
                  break;
               case "September" :
                  month = "9";
                  break;
               case "October" :
                  month = "10";
                  break;
               case "November" :
                  month = "11";
                  break;
               case "December" :
                  month = "12";
                  break;
               default : // Optional
                  // Statements
              }
            int setYear = Integer.parseInt(year);
            int setMonth = Integer.parseInt(month);
            try{
            dh.monthly_scann(setYear,setMonth);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex);
            }
            
            float setTotal_income = (float) dh.getTotalIncome();
            
            MONTHLY_TOTAL_QUANTITY.setText(""+dh.getTotalQuantities());
            MONTHLY_TOTAL_INCOME.setText(""+setTotal_income);
            try{
            MonthlyFilter_table();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Second try error");
            }
                setGross_income();
            MONTHLY_GROSS_INCOME.setText(""+mDc.roundOffTo2DecPlaces(myTotal));
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
        SCANN_INFO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(SCANN_INFO.getSelectedItem().toString().equals("Monthly")){
                    setSearch = "logs";
                    SEARCH_TF.setText("Search");
                    SEARCH_TF.setForeground(Color.GRAY);
                    YEARLY_INFO.setVisible(false);
                    MONTHLY_INFO.setVisible(true);
                    DAILY_INFO.setVisible(false);
                    LOGS_PANE.setVisible(true);
                    EXPIRED_LOGS_JS.setVisible(false);
                    EXPIRED_INFO.setVisible(false);
                    PRODUCT_INFO.setVisible(true);
                    PRODUCT_NAME_PANEL.setVisible(true);
                    FREE_SCANN_PANEL.setVisible(false);
                    DISCOUNT_PANEL.setVisible(false);
                    EXP_PRODUCT_INFO_PANEL.setVisible(false);
                    DISC_PRODUCT_INFO_PANEL.setVisible(false);
                    productTable();
                    clearAll_data_view();
                }else if(SCANN_INFO.getSelectedItem().toString().equals("Yearly")){
                    setSearch = "logs";
                    SEARCH_TF.setText("Search");
                    SEARCH_TF.setForeground(Color.GRAY);
                    YEARLY_INFO.setVisible(true);
                    MONTHLY_INFO.setVisible(false);
                    DAILY_INFO.setVisible(false);
                    LOGS_PANE.setVisible(true);
                    EXPIRED_LOGS_JS.setVisible(false);
                    EXPIRED_INFO.setVisible(false);
                    PRODUCT_INFO.setVisible(true);
                    PRODUCT_NAME_PANEL.setVisible(true);
                    FREE_SCANN_PANEL.setVisible(false);
                    DISCOUNT_PANEL.setVisible(false);
                    EXP_PRODUCT_INFO_PANEL.setVisible(false);
                    DISC_PRODUCT_INFO_PANEL.setVisible(false);
                    productTable();
                    clearAll_data_view();
                }else if(SCANN_INFO.getSelectedItem().toString().equals("Daily")){
                    setSearch = "logs";
                    SEARCH_TF.setText("Search");
                    SEARCH_TF.setForeground(Color.GRAY);
                    YEARLY_INFO.setVisible(false);
                    MONTHLY_INFO.setVisible(false);
                    DAILY_INFO.setVisible(true);
                    LOGS_PANE.setVisible(true);
                    EXPIRED_LOGS_JS.setVisible(false);
                    EXPIRED_INFO.setVisible(false);
                    PRODUCT_INFO.setVisible(true);
                    PRODUCT_NAME_PANEL.setVisible(true);
                    FREE_SCANN_PANEL.setVisible(false);
                    DISCOUNT_PANEL.setVisible(false);
                    EXP_PRODUCT_INFO_PANEL.setVisible(false);
                    DISC_PRODUCT_INFO_PANEL.setVisible(false);
                    productTable();
                    clearAll_data_view();
                }else if(SCANN_INFO.getSelectedItem().toString().equals("Free")){
                    setSearch = "free";
                    SEARCH_TF.setText("Search");
                    SEARCH_TF.setForeground(Color.GRAY);
                    YEARLY_INFO.setVisible(false);
                    MONTHLY_INFO.setVisible(false);
                    DAILY_INFO.setVisible(false);
                    LOGS_PANE.setVisible(true);
                    EXPIRED_LOGS_JS.setVisible(false);
                    EXPIRED_INFO.setVisible(false);
                    PRODUCT_INFO.setVisible(true);
                    PRODUCT_NAME_PANEL.setVisible(true);
                    FREE_SCANN_PANEL.setVisible(true);
                    DISCOUNT_PANEL.setVisible(false);
                    EXP_PRODUCT_INFO_PANEL.setVisible(false);
                    DISC_PRODUCT_INFO_PANEL.setVisible(false);
                    setFree_table();
                    clearAll_data_view();
                }else if(SCANN_INFO.getSelectedItem().toString().equals("Discount")){
                    setSearch = "discount";
                    SEARCH_TF.setText("Search");
                    SEARCH_TF.setForeground(Color.GRAY);
                    YEARLY_INFO.setVisible(false);
                    MONTHLY_INFO.setVisible(false);
                    DAILY_INFO.setVisible(false);
                    LOGS_PANE.setVisible(false);
                    EXPIRED_LOGS_JS.setVisible(false);
                    EXPIRED_INFO.setVisible(false);
                    PRODUCT_INFO.setVisible(false);
                    PRODUCT_NAME_PANEL.setVisible(true);
                    FREE_SCANN_PANEL.setVisible(false);
                    DISCOUNT_PANEL.setVisible(true);
                    EXP_PRODUCT_INFO_PANEL.setVisible(false);
                    DISC_PRODUCT_INFO_PANEL.setVisible(true);
                    productTable();
                    clearAll_data_view();
                }else{
                    setSearch = "expired";
                    SEARCH_TF.setText("Search");
                    SEARCH_TF.setForeground(Color.GRAY);
                    YEARLY_INFO.setVisible(false);
                    MONTHLY_INFO.setVisible(false);
                    DAILY_INFO.setVisible(false);
                    LOGS_PANE.setVisible(false);
                    EXPIRED_LOGS_JS.setVisible(true);
                    expiredTable();
                    EXPIRED_INFO.setVisible(true);
                    PRODUCT_INFO.setVisible(false);
                    PRODUCT_NAME_PANEL.setVisible(true);
                    FREE_SCANN_PANEL.setVisible(false);
                    DISCOUNT_PANEL.setVisible(false);
                    EXP_PRODUCT_INFO_PANEL.setVisible(true);
                    DISC_PRODUCT_INFO_PANEL.setVisible(false);
                    productTable();
                    clearAll_data_view();
                }
                TOTAL_QUANTITY_SOLD.setText("Total Quantities");
                TOTAL_INCOME.setText("");
                MONTHLY_TOTAL_QUANTITY.setText("");
                MONTHLY_TOTAL_INCOME.setText("");
                YEAR_TOTAL_QUANTITY.setText("");
                YEAR_TOTAL_INCOME.setText("");
                YEARLY_GROSS_INCOME.setText("");
                GROSS_INCOME.setText("-");
                MONTHLY_GROSS_INCOME.setText("");
                clearAll_data_view();
            }
        });
//        columnName[0] = "Id";
//        columnName[1] = "Product name";
//        columnName[2] = "Quantity";
//        columnName[3] = "Unit price";
//        columnName[4] = "Selling price";
//        columnName[5] = "Payment";
//        columnName[6] = "Date";
//        columnName[7] = "Time";
//        columnName[8] = "Cashier";
//        columnName[9] = "Trans. ID"; 
        EXPIRED_LOGS.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                int selectedRow = EXPIRED_LOGS.getSelectedRow();
                 selectedRow = EXPIRED_LOGS.convertRowIndexToModel(selectedRow);                    
                String product_name = (String)EXPIRED_LOGS.getModel().getValueAt(selectedRow, 1);
                String quantity = (String)EXPIRED_LOGS.getModel().getValueAt(selectedRow, 2);
                String selling_price = (String)EXPIRED_LOGS.getModel().getValueAt(selectedRow, 3);
                String date_purchased = (String)EXPIRED_LOGS.getModel().getValueAt(selectedRow, 4);
                String expired_date = (String)EXPIRED_LOGS.getModel().getValueAt(selectedRow, 5);
                String purchase_from = (String)EXPIRED_LOGS.getModel().getValueAt(selectedRow, 6);
                String strength = (String)EXPIRED_LOGS.getModel().getValueAt(selectedRow, 8);
                String preparation = (String)EXPIRED_LOGS.getModel().getValueAt(selectedRow, 9);
                
//                TRANS_ID.setText(trans_id);
                PRODUCT_NAME.setText(product_name);
                EXP_QUANTITY.setText(quantity);
                EXP_PRICE.setText(selling_price);
                EXP_PURCHASED_FROM.setText(purchase_from); 
                EXP_DATE_PUR.setText(date_purchased);
                EXP_DATE_EXP.setText(expired_date);
                EXP_STRENGTH.setText(strength);
                EXP_PREPARATION.setText(preparation);
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
        DISCOUNT_TABLE.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
        int selectedRow = DISCOUNT_TABLE.getSelectedRow();
                 selectedRow = DISCOUNT_TABLE.convertRowIndexToModel(selectedRow);                    
                String product_name = (String)DISCOUNT_TABLE.getModel().getValueAt(selectedRow, 2);
                String quantity = (String)DISCOUNT_TABLE.getModel().getValueAt(selectedRow, 3);
                String date = (String)DISCOUNT_TABLE.getModel().getValueAt(selectedRow, 8);
                String cashier = (String)DISCOUNT_TABLE.getModel().getValueAt(selectedRow, 9);
                String discount = (String)DISCOUNT_TABLE.getModel().getValueAt(selectedRow, 7);
                String discounted_price = (String)DISCOUNT_TABLE.getModel().getValueAt(selectedRow, 5);
                String strength = (String)DISCOUNT_TABLE.getModel().getValueAt(selectedRow, 10);
                String preparation = (String)DISCOUNT_TABLE.getModel().getValueAt(selectedRow, 11);
                String discount_percent = (String)DISCOUNT_TABLE.getModel().getValueAt(selectedRow, 6);
                String unit_price = (String)DISCOUNT_TABLE.getModel().getValueAt(selectedRow, 4);

                PRODUCT_NAME.setText(product_name);
                DISC_QUANTITY.setText(quantity);
                DISC_DATE.setText(date);
                DISC_CASHIER.setText(cashier);
                DISC_DISCOUNT.setText(discount);
                DISC_STRENGTH.setText(strength);
                DISC_UNIT_PRICE.setText(unit_price);
                DISC_DISCOUNT.setText(discount);
                DISC_PREPARATION.setText(preparation);
                DISC_DISCOUNTED_PRICE.setText(discounted_price); 
                DISC_DISCOUNTED_PERCENT.setText(discount_percent); 
                
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
        LOGS_LIST.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = LOGS_LIST.getSelectedRow();
                selectedRow = LOGS_LIST.convertRowIndexToModel(selectedRow);                    
                String product_name = (String)LOGS_LIST.getModel().getValueAt(selectedRow, 2);
                String quantity = (String)LOGS_LIST.getModel().getValueAt(selectedRow, 3);
                String selling_price = (String)LOGS_LIST.getModel().getValueAt(selectedRow, 5);
                String payment = (String)LOGS_LIST.getModel().getValueAt(selectedRow, 6);
                String date_time = (String)LOGS_LIST.getModel().getValueAt(selectedRow, 7) + " "+
                        (String)LOGS_LIST.getModel().getValueAt(selectedRow, 8);
                String cashier = (String)LOGS_LIST.getModel().getValueAt(selectedRow, 9);
                String trans_id = (String)LOGS_LIST.getModel().getValueAt(selectedRow, 1);
                String unit_price = (String)LOGS_LIST.getModel().getValueAt(selectedRow, 4);
                
                TRANS_ID.setText(trans_id);
                PRODUCT_NAME.setText(product_name);
                QUANTITY.setText(quantity);
                CASHIER.setText(cashier);
                SELLING_PRICE.setText(selling_price); 
                PAYMENT.setText(payment);
                DATE.setText(date_time);
                UNIT_PRICEx.setText(unit_price);
                PAYMENT.setText(payment);
                PREPARATION.setText(dh.getLog_preparation(trans_id));
                STRENGTH.setText(dh.getLog_strength(trans_id));
                System.out.println("Transaction ID is: "+trans_id);
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
        EXPIRED_LOGS.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = EXPIRED_LOGS.getSelectedRow();
                selectedRow = EXPIRED_LOGS.convertRowIndexToModel(selectedRow);                    
                String product_name = (String)EXPIRED_LOGS.getModel().getValueAt(selectedRow, 1);
                String quantity = (String)EXPIRED_LOGS.getModel().getValueAt(selectedRow, 2);
                String price = (String)EXPIRED_LOGS.getModel().getValueAt(selectedRow, 3);
                String date_purchased = (String)EXPIRED_LOGS.getModel().getValueAt(selectedRow, 4);
                String expiration_date = (String)EXPIRED_LOGS.getModel().getValueAt(selectedRow, 5);
                String purchase_from = (String)EXPIRED_LOGS.getModel().getValueAt(selectedRow, 6);

                EXPIRED_PRODUCT_NAME.setText(product_name);
                EXPIRED_QUANTITY.setText(quantity);
                EXPIRED_PRICE.setText(price);
                EXPIRED_DATE_PURCHASE.setText(date_purchased);
                EXPIRED_DATE.setText(expiration_date);
                EXPIRED_PURCHASE_FROM.setText(purchase_from);
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
    public boolean isMouseWithinComponent(Component c)
    {
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        Rectangle bounds = c.getBounds();
        bounds.setLocation(c.getLocationOnScreen());
        return bounds.contains(mousePos);
    }
    class RoundedCornerBorder extends AbstractBorder {
        @Override public void paintBorder(
            Component c, Graphics g, int x, int y, int width, int height) {
          Graphics2D g2 = (Graphics2D)g.create();
          g2.setRenderingHint(
              RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          int r = 12;
          int w = width  - 1;
          int h = height - 1;
          Area round = new Area(new RoundRectangle2D.Float(x, y, w, h, r, r));
          Container parent = c.getParent();
          if(parent!=null) {
            g2.setColor(parent.getBackground());
            Area corner = new Area(new Rectangle2D.Float(x, y, width, height));
            corner.subtract(round);
            g2.fill(corner);
          }
          g2.setPaint(c.getForeground());
          g2.draw(round);
          g2.dispose();
        }
        @Override public Insets getBorderInsets(Component c) {
          return new Insets(4, 8, 4, 8);
        }
        @Override public Insets getBorderInsets(Component c, Insets insets) {
          insets.left = insets.right = 8;
          insets.top = insets.bottom = 4;
          return insets;
        }
    }
}