package Frames;

import DataBase.DataBase;
import DataBase.SQLiteDB_action;
import DataHolder.DataHolder;
import Panels.List_Panel;
import Panels.Sale_Panel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import msinventorysystem.MainFrame;
import msinventorysystem.myDecimalConverter;

public final class UpdateProduct extends JFrame{

    ArrayList<String> expire_date = new ArrayList<String>();
    ArrayList<String> purchase_date = new ArrayList<String>();
    
        Font SETFONT = new Font("SansSerif", Font.BOLD, 17);
        Font SETFONTx = new Font("SansSerif", Font.PLAIN, 17);
    
    JLabel PRODUCT_NAME,QUANTITY,EXPIRATION,PRICE,SELLING_PRICE,
            DATE_PURCHASE,UNIT,PREPARATION,PURCHASE_FROM,SELLING_PRICE_JL,
            BRAND_NAME
//            SELLING_PRICE_TOTAL
            ,PRICE_INCREASE,SET_LIMIT;
    JTextField PRODUCT_NAME_TF,QUANTITY_TF,EXPIRATION_TF,PRICE_TF,SELLING_PRICE_TF,
            UNIT_TF,PREPARATION_TF,EXPIRED_YEAR_TF,PURCHASE_YEAR_TF,PURCHASE_FROM_TF,
            BRAND_NAME_TF,PRICE_INCREASE_TF,SET_LIMIT_TF;
    JComboBox EXPIRED_MONTH,EXPIRED_DAY,
              PURCHASE_MONTH_PURCHASE_MONTH,PURCHASE_MONTH_PURCHASE_DAY;
    JButton UPDATE,CANCEL;
    myDecimalConverter mDc = new myDecimalConverter();

    static String ID = "";
    String delims = "/";
    
    int expire_month = 0;
    int purchased_month = 0;
    static int lastX, lastY;
    DataBase db = DataBase.getInstance();
    DataHolder dh = DataHolder.getInstance();
    private static UpdateProduct instance = null;    
        
      public static UpdateProduct getInstance() {
      if(instance == null) {
         instance = new UpdateProduct();
      }
      System.out.println("/* Update product */");
      return instance;
    }  
      
        Font myFont = new Font("Segoe UI", Font.PLAIN | Font.BOLD, 15);
        
      public UpdateProduct(){
                dh.addFrameList(this);
        // Set this to front
        this.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }
            @Override
            public void windowLostFocus(WindowEvent e) {
                toFront();

            }
        });
        
        this.setSize(600,600);
        this.setTitle("Add product");
        this.setLayout(null);
        this.setResizable(false);
        this.setUndecorated(true);
//        this.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.LIGHT_GRAY));
        this.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        this.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        Organizer();
        MouseAdapter ma = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            lastX = e.getXOnScreen();
            lastY = e.getYOnScreen();
        }
        @Override
        public void mouseDragged(MouseEvent e) {
            int x = e.getXOnScreen();
            int y = e.getYOnScreen();
            // Move frame by the mouse delta
            setLocation(getLocationOnScreen().x + x - lastX,
            getLocationOnScreen().y + y - lastY);
            lastX = x;
            lastY = y;
        }
    };
        addMouseListener(ma);
        addMouseMotionListener(ma);    
      }
      void Organizer(){
          Components();
          Listener();
      }
    public void setLocation_Frame(int x,int y){
        this.setLocation(x+310  ,y+60);
    }
      void Components(){
        List<Image> icons = new ArrayList<Image>();
        icons.add(new ImageIcon("Skins/C_extras/FRAME_ICO.png").getImage());
        icons.add(new ImageIcon("Skins/C_extras/TASKBAR_ICO.png").getImage());
        this.setIconImages(icons);

        Container c = this.getContentPane(); 
        c.setBackground(Color.WHITE);

        PRODUCT_NAME = new JLabel("Product name");
        PRODUCT_NAME.setBounds(50,35,100,25);
//        this.add(PRODUCT_NAME);
        
        PRODUCT_NAME_TF = new JTextField();
//        PRODUCT_NAME_TF.setHorizontalAlignment(JTextField.CENTER);
        PRODUCT_NAME_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        PRODUCT_NAME_TF.setBounds(130,112,430,25);
        PRODUCT_NAME_TF.setForeground(Color.BLACK);
        PRODUCT_NAME_TF.setFont(SETFONT);
        this.add(PRODUCT_NAME_TF);
        
        BRAND_NAME = new JLabel("Brand name");
        BRAND_NAME.setBounds(250,35,100,25);
//        this.add(BRAND_NAME);
        
        BRAND_NAME_TF = new JTextField();
        BRAND_NAME_TF.setBounds(130,150,430,25);
        BRAND_NAME_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        BRAND_NAME_TF.setForeground(Color.BLACK);
        BRAND_NAME_TF.setFont(SETFONT);
//        BRAND_NAME_TF.setHorizontalAlignment(JTextField.CENTER);
        this.add(BRAND_NAME_TF);
        
        QUANTITY = new JLabel("Quantity");
        QUANTITY.setBounds(20,70,100,25);
//        this.add(QUANTITY);
        
        QUANTITY_TF = new JTextField();
//        QUANTITY_TF.setHorizontalAlignment(JTextField.CENTER);
        QUANTITY_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        QUANTITY_TF.setForeground(Color.BLACK);
        QUANTITY_TF.setFont(SETFONT);
        QUANTITY_TF.setBounds(130,226,99,25);
        this.add(QUANTITY_TF);
        
        PURCHASE_FROM = new JLabel("Purchase from");
        PURCHASE_FROM.setBounds(175,70,90,25);
//        this.add(PURCHASE_FROM);
        
        PURCHASE_FROM_TF = new JTextField();
//        PURCHASE_FROM_TF.setHorizontalAlignment(JTextField.CENTER);
        PURCHASE_FROM_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        PURCHASE_FROM_TF.setForeground(Color.BLACK);
        PURCHASE_FROM_TF.setFont(SETFONT);
        PURCHASE_FROM_TF.setBounds(135,305,253,25);
        this.add(PURCHASE_FROM_TF);
        
        EXPIRATION = new JLabel("Expiry date");
        EXPIRATION.setBounds(20,100,100,25);
//        this.add(EXPIRATION);
        
        EXPIRED_MONTH = new JComboBox();
        EXPIRED_MONTH.setBounds(130,382,100,25);
        this.add(EXPIRED_MONTH);
        
        EXPIRED_MONTH.addItem("January");
        EXPIRED_MONTH.addItem("February");
        EXPIRED_MONTH.addItem("March");
        EXPIRED_MONTH.addItem("April");
        EXPIRED_MONTH.addItem("May");
        EXPIRED_MONTH.addItem("June");
        EXPIRED_MONTH.addItem("July");
        EXPIRED_MONTH.addItem("August");
        EXPIRED_MONTH.addItem("September");
        EXPIRED_MONTH.addItem("October");
        EXPIRED_MONTH.addItem("November");
        EXPIRED_MONTH.addItem("December");
        
        EXPIRED_DAY = new JComboBox();
        EXPIRED_DAY.setBounds(235,382,50,25);
        this.add(EXPIRED_DAY);
        
        for(int x=1;x<32;x++){
            EXPIRED_DAY.addItem(x);
        }
        
        EXPIRED_YEAR_TF = new JTextField();
//        EXPIRED_YEAR_TF.setHorizontalAlignment(JTextField.CENTER);
//        EXPIRED_YEAR_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        EXPIRED_YEAR_TF.setForeground(Color.BLACK);
        EXPIRED_YEAR_TF.setFont(SETFONT);
        EXPIRED_YEAR_TF.setBounds(290,382,52,25);
        this.add(EXPIRED_YEAR_TF);
        
        PRICE = new JLabel("Price");
        PRICE.setBounds(20,130,100,25);
//        this.add(PRICE);
        
        PRICE_TF = new JTextField();
        PRICE_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        PRICE_TF.setForeground(Color.BLACK);
        PRICE_TF.setFont(SETFONT);
        PRICE_TF.setBounds(130,459,99,25);
        this.add(PRICE_TF);
        
        SELLING_PRICE = new JLabel("Selling price");
        SELLING_PRICE.setBounds(20,160,100,25);
//        this.add(SELLING_PRICE);
        
        SELLING_PRICE_TF = new JTextField();
        SELLING_PRICE_TF.setBounds(130,498,99,25);
        SELLING_PRICE_TF.setOpaque(false);
        SELLING_PRICE_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        SELLING_PRICE_TF.setFont(myFont);
        this.add(SELLING_PRICE_TF);
        
        PRICE_INCREASE = new JLabel("Price increase");
        PRICE_INCREASE.setBounds(180,160,100,25);
//        this.add(PRICE_INCREASE);
        
        PRICE_INCREASE_TF = new JTextField("");
        PRICE_INCREASE_TF.setBounds(330,459,99,25);
        PRICE_INCREASE_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        PRICE_INCREASE_TF.setForeground(Color.BLACK);
        PRICE_INCREASE_TF.setFont(SETFONT);
//        PRICE_INCREASE_TF.setHorizontalAlignment(JTextField.CENTER);
        this.add(PRICE_INCREASE_TF);
        
        JLabel PERCENT = new JLabel("%");
        PERCENT.setBounds(340,160,20,25);
//        this.add(PERCENT);
        
        DATE_PURCHASE = new JLabel("Date purchase");
        DATE_PURCHASE.setBounds(20,190,100,25);
//        this.add(DATE_PURCHASE);
        
        PURCHASE_MONTH_PURCHASE_MONTH = new JComboBox();
        PURCHASE_MONTH_PURCHASE_MONTH.setBounds(130,342,100,25);
        this.add(PURCHASE_MONTH_PURCHASE_MONTH);
        
        PURCHASE_MONTH_PURCHASE_MONTH.addItem("January");
        PURCHASE_MONTH_PURCHASE_MONTH.addItem("February");
        PURCHASE_MONTH_PURCHASE_MONTH.addItem("March");
        PURCHASE_MONTH_PURCHASE_MONTH.addItem("April");
        PURCHASE_MONTH_PURCHASE_MONTH.addItem("May");
        PURCHASE_MONTH_PURCHASE_MONTH.addItem("June");
        PURCHASE_MONTH_PURCHASE_MONTH.addItem("July");
        PURCHASE_MONTH_PURCHASE_MONTH.addItem("August");
        PURCHASE_MONTH_PURCHASE_MONTH.addItem("September");
        PURCHASE_MONTH_PURCHASE_MONTH.addItem("October");
        PURCHASE_MONTH_PURCHASE_MONTH.addItem("November");
        PURCHASE_MONTH_PURCHASE_MONTH.addItem("December");
        
        PURCHASE_MONTH_PURCHASE_DAY = new JComboBox();
        PURCHASE_MONTH_PURCHASE_DAY.setBounds(235,342,50,25);
        this.add(PURCHASE_MONTH_PURCHASE_DAY);
        
        for(int x=1;x<32;x++){
            PURCHASE_MONTH_PURCHASE_DAY.addItem(x);
        }

        PURCHASE_YEAR_TF = new JTextField();
//        PURCHASE_YEAR_TF.setHorizontalAlignment(JTextField.CENTER);
//        PURCHASE_YEAR_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        PURCHASE_YEAR_TF.setForeground(Color.BLACK);
        PURCHASE_YEAR_TF.setFont(SETFONT);
        PURCHASE_YEAR_TF.setBounds(290,342,52,25);
        this.add(PURCHASE_YEAR_TF);
        
        PREPARATION = new JLabel("Preparation");
        PREPARATION.setBounds(20,220,100,25);
//        this.add(PREPARATION);

        SET_LIMIT = new JLabel("Limit");
        SET_LIMIT.setBounds(20,255,100,25);
//        this.add(SET_LIMIT);
        
        SET_LIMIT_TF = new JTextField();
        SET_LIMIT_TF.setBounds(284,226,100,25);
//        SET_LIMIT_TF.setHorizontalAlignment(JTextField.CENTER);
        SET_LIMIT_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        SET_LIMIT_TF.setForeground(Color.BLACK);
        SET_LIMIT_TF.setFont(SETFONT);
        this.add(SET_LIMIT_TF);
        
        PREPARATION_TF = new JTextField();
//        PREPARATION_TF.setHorizontalAlignment(JTextField.CENTER);
        PREPARATION_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        PREPARATION_TF.setForeground(Color.BLACK);
        PREPARATION_TF.setFont(SETFONT);
        PREPARATION_TF.setBounds(130,265,253,25);
        this.add(PREPARATION_TF);
        
        UNIT = new JLabel("Unit");
        UNIT.setBounds(175,130,100,25);
//        this.add(UNIT);
        
        UNIT_TF = new JTextField();
//        UNIT_TF.setHorizontalAlignment(JTextField.CENTER);
        UNIT_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        UNIT_TF.setForeground(Color.BLACK);
        UNIT_TF.setFont(SETFONT);
        UNIT_TF.setBounds(130,188,255,25);
        this.add(UNIT_TF);
        
        ImageIcon CANCEL_II = new ImageIcon("Skins/C_buttons/"
                + "CANCEL_PRODUCT_1_BUTTON.png");
        CANCEL = new JButton(CANCEL_II);
        CANCEL.setBounds(480,530,80,40);
        CANCEL.setOpaque(false);
        CANCEL.setCursor(new Cursor(Cursor.HAND_CURSOR));
        CANCEL.setContentAreaFilled(false);
        CANCEL.setFocusable(false);
        CANCEL.setBorderPainted(false);
        this.add(CANCEL);

        ImageIcon UPDATE_II = new ImageIcon("Skins/C_buttons/"
        + "UPDATE_PRODUCT_1_BUTTON.png");
        UPDATE = new JButton(UPDATE_II);
        UPDATE.setBounds(395,530,80,40);
        UPDATE.setOpaque(false);
        UPDATE.setCursor(new Cursor(Cursor.HAND_CURSOR));
        UPDATE.setContentAreaFilled(false);
        UPDATE.setFocusable(false);
        UPDATE.setBorderPainted(false);
        this.add(UPDATE);
                
        ImageIcon BG_II = new ImageIcon("Skins/C_background/UPDATE_PRODUCT.png");
        JLabel BG = new JLabel(BG_II);
        BG.setBounds(0,0,600,600);
        this.add(BG);
                
        this.setVisible(true);
      }
    int setPrice_percent_increase(float unit_price,float selling_price){
        
        float c = selling_price-unit_price;
        float b = unit_price/100;
        float discountedPrice = 0;
        
        try{
            discountedPrice=c/b;
        }catch(Exception ex){
        }

        return (int) discountedPrice;
    }
      public void setData_info(String id,String product_name,String quantity,
              String expiration,String price,String selling_price,
              String date_purchased,String unit,String preparation,boolean frame_visible,
              String purchase_from,String brand_name,String limit, String price_increase){

        SET_LIMIT_TF.setText(limit);
          
        expire_date.clear();
        purchase_date.clear();

        this.setVisible(frame_visible);
        
        ID = id;
        try{
        //Extract expiry date
        String[] tokens = expiration.split(delims);
        int tokenCount = tokens.length;
        for (int j = 0; j < tokenCount; j++) {
        expire_date.add(tokens[j]);
        }
        //Extract
        String[] tokensx = date_purchased.split(delims);
        int tokenCountx = tokensx.length;
        for (int j = 0; j < tokenCountx; j++) {
        purchase_date.add(tokensx[j]);
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
                setDate_data();    
                PURCHASE_YEAR_TF.setText(purchase_date.get(2));
                EXPIRED_YEAR_TF.setText(expire_date.get(2));
                int setIndexOf_purchase_day = Integer.parseInt(purchase_date.get(1));
                PURCHASE_MONTH_PURCHASE_DAY.setSelectedIndex(setIndexOf_purchase_day-1);  
//                PURCHASE_MONTH_PURCHASE_DAY.setSelectedItem(expire_date.get(1));
                int setIndexOf_expired_day = Integer.parseInt(expire_date.get(1));
                EXPIRED_DAY.setSelectedIndex(setIndexOf_expired_day-1);
                PRODUCT_NAME_TF.setText(product_name);
                QUANTITY_TF.setText(quantity);
                PRICE_TF.setText(price);
                SELLING_PRICE_TF.setText(selling_price);
                UNIT_TF.setText(unit);
                PREPARATION_TF.setText(preparation);
                PURCHASE_FROM_TF.setText(purchase_from);
                BRAND_NAME_TF.setText(brand_name);
                PRICE_INCREASE_TF.setText(price_increase);
      }
      void setDate_data(){
              switch(expire_date.get(0)) {
       case "1" :
           EXPIRED_MONTH.setSelectedItem("January");
          break;
       case "2" :
           EXPIRED_MONTH.setSelectedItem("February");
          break;
       case "3" :
           EXPIRED_MONTH.setSelectedItem("March");
          break;
       case "4" :
           EXPIRED_MONTH.setSelectedItem("April");
          break;
       case "5" :
           EXPIRED_MONTH.setSelectedItem("May");
          break;
       case "6" :
           EXPIRED_MONTH.setSelectedItem("June");
          break;
       case "7" :
           EXPIRED_MONTH.setSelectedItem("July");
          break;
       case "8" :
           EXPIRED_MONTH.setSelectedItem("August");
          break;
       case "9" :
           EXPIRED_MONTH.setSelectedItem("September");
          break;
       case "10" :
           EXPIRED_MONTH.setSelectedItem("October");
          break;
       case "11" :
           EXPIRED_MONTH.setSelectedItem("November");
          break;
       case "12" :
           EXPIRED_MONTH.setSelectedItem("December");
          break;
       default : // Optional
          // Statements
    }    
              switch(purchase_date.get(0)) {
       case "1" :
           PURCHASE_MONTH_PURCHASE_MONTH.setSelectedItem("January");
          break;
       case "2" :
           PURCHASE_MONTH_PURCHASE_MONTH.setSelectedItem("February");
          break;
       case "3" :
           PURCHASE_MONTH_PURCHASE_MONTH.setSelectedItem("March");
          break;
       case "4" :
           PURCHASE_MONTH_PURCHASE_MONTH.setSelectedItem("April");
          break;
       case "5" :
           PURCHASE_MONTH_PURCHASE_MONTH.setSelectedItem("May");
          break;
       case "6" :
           PURCHASE_MONTH_PURCHASE_MONTH.setSelectedItem("June");
          break;
       case "7" :
           PURCHASE_MONTH_PURCHASE_MONTH.setSelectedItem("July");
          break;
       case "8" :
           PURCHASE_MONTH_PURCHASE_MONTH.setSelectedItem("August");
          break;
       case "9" :
           PURCHASE_MONTH_PURCHASE_MONTH.setSelectedItem("September");
          break;
       case "10" :
           PURCHASE_MONTH_PURCHASE_MONTH.setSelectedItem("October");
          break;
       case "11" :
           PURCHASE_MONTH_PURCHASE_MONTH.setSelectedItem("November");
          break;
       case "12" :
           PURCHASE_MONTH_PURCHASE_MONTH.setSelectedItem("December");
          break;
       default : // Optional
          // Statements
    }    
              
      }
    void monthCalculator(){
        //Set expired month data
    switch(PURCHASE_MONTH_PURCHASE_MONTH.getSelectedItem().toString()) {
       case "January" :
          purchased_month = 1;
          break;
       case "February" :
          purchased_month = 2;
          break;
       case "March" :
          purchased_month = 3;
          break;
       case "April" :
          purchased_month = 4;
          break;
       case "May" :
          purchased_month = 5;
          break;
       case "June" :
          purchased_month = 6;
          break;
       case "July" :
          purchased_month = 7;
          break;
       case "August" :
          purchased_month = 8;
          break;
       case "September" :
          purchased_month = 9;
          break;
       case "October" :
          purchased_month = 10;
          break;
       case "November" :
          purchased_month = 11;
          break;
       case "December" :
          purchased_month = 12;
          break;
       default : // Optional
          // Statements
    }    
        //Set purchase month data
    switch(EXPIRED_MONTH.getSelectedItem().toString()) {
       case "January" :
          expire_month = 1;
          break;
       case "February" :
          expire_month = 2;
          break;
       case "March" :
          expire_month = 3;
          break;
       case "April" :
          expire_month = 4;
          break;
       case "May" :
          expire_month = 5;
          break;
       case "June" :
          expire_month = 6;
          break;
       case "July" :
          expire_month = 7;
          break;
       case "August" :
          expire_month = 8;
          break;
       case "September" :
          expire_month = 9;
          break;
       case "October" :
          expire_month = 10;
          break;
       case "November" :
          expire_month = 11;
          break;
       case "December" :
          expire_month = 12;
          break;
       default : // Optional
          // Statements
        }    
    }
    void textProcess_perCent(){
                try{
                    if(PRICE_INCREASE_TF.getText().equals("") ||
                        PRICE_TF.getText().equals("")){
                        SELLING_PRICE_TF.setText(null);
                    }else{
                double value = Double.parseDouble(PRICE_TF.getText());
                double percentage = Double.parseDouble(PRICE_INCREASE_TF.getText());

                double k = (double)(value*(percentage/100.0f)); 
                float setValue = (float) (k+value);
                SELLING_PRICE_TF.setText(mDc.roundOffTo2DecPlaces(setValue));
                    }
                }catch(Exception ex){
                    
                }
    }
    void Listener(){
        PRICE_TF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if(PRICE_TF.getText().isEmpty()){
                    PRICE_INCREASE_TF.setText(null);
                }
            }
        });
        PRICE_INCREASE_TF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                textProcess_perCent();
                boolean ret = true;
                try {

                    Double.parseDouble(PRICE_INCREASE_TF.getText()+e.getKeyChar());
                }catch (NumberFormatException ee) {
                    ret = false;
                }

            if (!ret) {
                e.consume();
              }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                textProcess_perCent();
            }
            @Override
            public void keyReleased(KeyEvent e) {
                textProcess_perCent();
            PRICE_INCREASE_TF.setText(PRICE_INCREASE_TF.getText().replace("d", ""));
            PRICE_INCREASE_TF.setText(PRICE_INCREASE_TF.getText().replace("D", ""));
            PRICE_INCREASE_TF.setText(PRICE_INCREASE_TF.getText().replace("f", ""));
            PRICE_INCREASE_TF.setText(PRICE_INCREASE_TF.getText().replace("F", ""));
            }
        });
        this.setFocusable(true);
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
          int code = e.getKeyCode();
          if(code == KeyEvent.VK_ESCAPE){
               setVisible(false);
          }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        QUANTITY_TF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
              boolean ret = true;
                try {

                    Double.parseDouble(QUANTITY_TF.getText()+e.getKeyChar());
                }catch (NumberFormatException ee) {
                    ret = false;
                }

                if (!ret) {
                    e.consume();
                 }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        EXPIRED_YEAR_TF.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char vChar = e.getKeyChar();
                    if (!(Character.isDigit(vChar)
                            || (vChar == KeyEvent.VK_BACK_SPACE)
                            || (vChar == KeyEvent.VK_DELETE))) {
                        e.consume();
                    }
                }
            });
         PURCHASE_YEAR_TF.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char vChar = e.getKeyChar();
                    if (!(Character.isDigit(vChar)
                            || (vChar == KeyEvent.VK_BACK_SPACE)
                            || (vChar == KeyEvent.VK_DELETE))) {
                        e.consume();
                    }
                }
            });
       SET_LIMIT_TF.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char vChar = e.getKeyChar();
                    if (!(Character.isDigit(vChar)
                            || (vChar == KeyEvent.VK_BACK_SPACE)
                            || (vChar == KeyEvent.VK_DELETE))) {
                        e.consume();
                    }
                }
            });
        QUANTITY_TF.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char vChar = e.getKeyChar();
                    if (!(Character.isDigit(vChar)
                            || (vChar == KeyEvent.VK_BACK_SPACE)
                            || (vChar == KeyEvent.VK_DELETE))) {
                        e.consume();
                    }
                }
            });
//        PRICE_INCREASE_TF.addKeyListener(new KeyAdapter() {
//                public void keyTyped(KeyEvent e) {
//                    char vChar = e.getKeyChar();
//                    if (!(Character.isDigit(vChar)
//                            || (vChar == KeyEvent.VK_BACK_SPACE)
//                            || (vChar == KeyEvent.VK_DELETE))) {
//                        e.consume();
//                    }
//                }
//            });
//        PRICE_TF.addKeyListener(new KeyAdapter() {
//                public void keyTyped(KeyEvent e) {
//                    char vChar = e.getKeyChar();
//                    if (!(Character.isDigit(vChar)
//                            || (vChar == KeyEvent.VK_BACK_SPACE)
//                            || (vChar == KeyEvent.VK_DELETE))) {
//                        e.consume();
//                    }
//                }
//            });
//        SELLING_PRICE_TF.addKeyListener(new KeyAdapter() {
//                public void keyTyped(KeyEvent e) {
//                    char vChar = e.getKeyChar();
//                    if (!(Character.isDigit(vChar)
//                            || (vChar == KeyEvent.VK_BACK_SPACE)
//                            || (vChar == KeyEvent.VK_DELETE))) {
//                        e.consume();
//                    }
//                }
//            });
        SELLING_PRICE_TF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                boolean ret = true;
                try {

                    Double.parseDouble(SELLING_PRICE_TF.getText()+e.getKeyChar());
                }catch (NumberFormatException ee) {
                    ret = false;
                }

                if (!ret) {
                    e.consume();
                  }
                if(SELLING_PRICE_TF.getText().isEmpty()){
                    PRICE_INCREASE_TF.setText(null);
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(SELLING_PRICE_TF.getText().isEmpty()){
                    PRICE_INCREASE_TF.setText(null);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
              try{
                float unit_price = Float.valueOf(PRICE_TF.getText());
                float selling_price = Float.valueOf(SELLING_PRICE_TF.getText());
                PRICE_INCREASE_TF.setText(setPrice_percent_increase(unit_price,selling_price)+"");
                
                if(PRICE_TF.getText().equals(null) && 
                   SELLING_PRICE_TF.getText().equals(null)){
                    PRICE_INCREASE_TF.setText(null);
                }
                if(SELLING_PRICE_TF.getText().isEmpty()){
                    PRICE_INCREASE_TF.setText(null);
                }
              }catch(Exception ex){
              }
                if(SELLING_PRICE_TF.getText().toUpperCase().contains("D") ||
                   SELLING_PRICE_TF.getText().toUpperCase().contains("F")){
                    SELLING_PRICE_TF.setText(SELLING_PRICE_TF.getText().replace("D", ""));
                    SELLING_PRICE_TF.setText(SELLING_PRICE_TF.getText().replace("F", ""));
                    SELLING_PRICE_TF.setText(SELLING_PRICE_TF.getText().replace("d", ""));
                    SELLING_PRICE_TF.setText(SELLING_PRICE_TF.getText().replace("f", ""));
                }
            }
        });
        PURCHASE_YEAR_TF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
              boolean ret = true;
                try {

                    Double.parseDouble(PURCHASE_YEAR_TF.getText()+e.getKeyChar());
                }catch (NumberFormatException ee) {
                    ret = false;
                }

                if (!ret) {
                    e.consume();
                 }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        PRICE_TF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
              boolean ret = true;
                try {

                    Double.parseDouble(PRICE_TF.getText()+e.getKeyChar());
                }catch (NumberFormatException ee) {
                    ret = false;
                }

                if (!ret) {
                    e.consume();
                 }
                textProcess_perCent();
            }
            @Override
            public void keyPressed(KeyEvent e) {
                textProcess_perCent();
            }
            @Override
            public void keyReleased(KeyEvent e) {
                textProcess_perCent();
            }
        });
        EXPIRED_YEAR_TF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
              boolean ret = true;
                try {

                    Double.parseDouble(EXPIRED_YEAR_TF.getText()+e.getKeyChar());
                }catch (NumberFormatException ee) {
                    ret = false;
                }

                if (!ret) {
                    e.consume();
                 }
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
//        PRICE_INCREASE_TF.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                try{
//                    if(PRICE_INCREASE_TF.getText().equals("") ||
//                        PRICE_TF.getText().equals("")){
//                        SELLING_PRICE_TF.setText(null);
//                    }else{
//                int value = Integer.parseInt(PRICE_TF.getText());
//                double percentage = Double.parseDouble(PRICE_INCREASE_TF.getText());
//
//                double k = (double)(value*(percentage/100.0f)); 
//                
//                SELLING_PRICE_TF.setText(k+value+"");
//                    }
//                }catch(Exception ex){
//                    
//                }
//            }
//            public void keyPressed(KeyEvent e) {
//                try{
//                    if(PRICE_INCREASE_TF.getText().equals("") ||
//                        PRICE_TF.getText().equals("")){
//                        SELLING_PRICE_TF.setText(null);
//                    }else{
//                int value = Integer.parseInt(PRICE_TF.getText());
//                double percentage = Double.parseDouble(PRICE_INCREASE_TF.getText());
//
//                double k = (double)(value*(percentage/100.0f)); 
//                
//                SELLING_PRICE_TF.setText(k+value+"");
//                    }
//                }catch(Exception ex){
//                    
//                }
//            }
//            @Override
//            public void keyReleased(KeyEvent e) {
//                try{
//                    if(PRICE_INCREASE_TF.getText().equals("") ||
//                        PRICE_TF.getText().equals("")){
//                        SELLING_PRICE_TF.setText(null);
//                    }else{
//                int value = Integer.parseInt(PRICE_TF.getText());
//                double percentage = Double.parseDouble(PRICE_INCREASE_TF.getText());
//
//                double k = (double)(value*(percentage/100.0f)); 
//                
//                SELLING_PRICE_TF.setText(k+value+"");
//                    }
//
//                }catch(Exception ex){
//                    
//                }
//            }
//        });
          UPDATE.addMouseListener(new MouseListener() {

              @Override
              public void mouseClicked(MouseEvent e) {
                if(PRODUCT_NAME_TF.getText().equals("Enter product name....") 
                 || QUANTITY_TF.getText().isEmpty()
                 || PRICE_TF.getText().isEmpty()
                 || UNIT_TF.getText().isEmpty()
                 || PREPARATION_TF.getText().isEmpty() 
                 || EXPIRED_YEAR_TF.getText().isEmpty()
                 || PURCHASE_YEAR_TF.getText().isEmpty() 
                 || BRAND_NAME_TF.getText().equals("Enter brand name....")
                 || SET_LIMIT_TF.getText().isEmpty()){
//                 || PRICE_INCREASE_TF.getText().isEmpty()
                    JOptionPane.showMessageDialog(null,"Please fill up all the information");
                }else{
                int setMonth = 0;                
                    
       switch(EXPIRED_MONTH.getSelectedItem().toString()) {
       case "January" :
          setMonth = 1;
          break;
       case "February" :
          setMonth = 2;
          break;
       case "March" :
          setMonth = 3;
          break;
       case "April" :
          setMonth = 4;
          break;
       case "May" :
          setMonth = 5;
          break;
       case "June" :
          setMonth = 6;
          break;
       case "July" :
          setMonth = 7;
          break;
       case "August" :
          setMonth = 8;
          break;
       case "September" :
          setMonth = 9;
          break;
       case "October" :
          setMonth = 10;
          break;
       case "November" :
          setMonth = 11;
          break;
       case "December" :
          setMonth = 12;
          break;
       default : // Optional
          // Statements
    }    
                                     DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                     Date date = new Date();
        
                                     String dateToday = dateFormat.format(date);
		String expiryDate = setMonth+"/"+EXPIRED_DAY.getSelectedItem().toString()+"/"+EXPIRED_YEAR_TF.getText();

		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(dateToday);
			d2 = format.parse(expiryDate);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffDays = diff / (24 * 60 * 60 * 1000);

                                                int setDate = (int) diffDays;
                                                if(setDate<=0){
                             JOptionPane.showMessageDialog(null,"Item already expired!");
                                                }else{
                monthCalculator();
                
                if(PRICE_INCREASE_TF.getText().isEmpty()){
                    PRICE_INCREASE_TF.setText("0");
                }
                
                dh.updateData_product_list(ID, PRODUCT_NAME_TF.getText(),
                QUANTITY_TF.getText(),expire_month+"/"+
                EXPIRED_DAY.getSelectedItem().toString()+
                "/"+EXPIRED_YEAR_TF.getText(),PRICE_TF.getText(), SELLING_PRICE_TF.getText(),
                purchased_month+"/"+PURCHASE_MONTH_PURCHASE_DAY.getSelectedItem()
                .toString()+"/"+PURCHASE_YEAR_TF.getText(),UNIT_TF.getText(),
                PREPARATION_TF.getText(),PURCHASE_FROM_TF.getText(),BRAND_NAME_TF.getText(),
                SET_LIMIT_TF.getText(),PRICE_INCREASE_TF.getText());
                
                Sale_Panel sp = Sale_Panel.getInstance();
                sp.set_Table_info();                
                
                List_Panel lp = List_Panel.getInstance();
                lp.filterTable();
                setVisible(false);

                MainFrame mf = MainFrame.getInstance();
                mf.setFront(true);

                SQLiteDB_action sdb = SQLiteDB_action.getInstance();
                
                sdb.updateProduct(ID, PRODUCT_NAME_TF.getText(),
                QUANTITY_TF.getText(),expire_month+"/"+
                EXPIRED_DAY.getSelectedItem().toString()+
                "/"+EXPIRED_YEAR_TF.getText(),PRICE_TF.getText(), SELLING_PRICE_TF.getText()
                +"/"+PRICE_INCREASE_TF.getText(),
                purchased_month+"/"+PURCHASE_MONTH_PURCHASE_DAY.getSelectedItem()
                .toString()+"/"+PURCHASE_YEAR_TF.getText(),UNIT_TF.getText(),
                PREPARATION_TF.getText(),PURCHASE_FROM_TF.getText(),BRAND_NAME_TF.getText(),
                SET_LIMIT_TF.getText());
                PRICE_INCREASE_TF.setText(null);
                JOptionPane.showMessageDialog(null,"Successfully updated");
                }
                                            } catch (Exception ex) {
			ex.printStackTrace();
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
                    ImageIcon UPDATE_II = new ImageIcon("Skins/C_buttons/"
                    + "UPDATE_PRODUCT_2_BUTTON.png");
                    UPDATE.setIcon(UPDATE_II);
              }
              @Override
              public void mouseExited(MouseEvent e) {
                    ImageIcon UPDATE_II = new ImageIcon("Skins/C_buttons/"
                    + "UPDATE_PRODUCT_1_BUTTON.png");
                    UPDATE.setIcon(UPDATE_II);
              }
          });
          CANCEL.addMouseListener(new MouseListener() {

              @Override
              public void mouseClicked(MouseEvent e) {
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);
                setVisible(false);
                PRICE_INCREASE_TF.setText(null);
                mf.requestFocus();
                mf.toFront();
              }
              @Override
              public void mousePressed(MouseEvent e) {
              }
              @Override
              public void mouseReleased(MouseEvent e) {
              }
              @Override
              public void mouseEntered(MouseEvent e) {
                ImageIcon CANCEL_II = new ImageIcon("Skins/C_buttons/"
                + "CANCEL_PRODUCT_2_BUTTON.png");
                CANCEL.setIcon(CANCEL_II);
              }
              @Override
              public void mouseExited(MouseEvent e) {
                ImageIcon CANCEL_II = new ImageIcon("Skins/C_buttons/"
                + "CANCEL_PRODUCT_1_BUTTON.png");
                CANCEL.setIcon(CANCEL_II);
              }
          });
      }
    public static void main (String [] args){
        UpdateProduct up = UpdateProduct.getInstance();
        up.setVisible(true);
    }
}