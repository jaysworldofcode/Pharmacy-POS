package Frames;

import DataBase.DataBase;
import DataBase.SQLiteDB;
import DataBase.SQLiteDB_action;
import DataHolder.DataHolder;
import Panels.List_Panel;
import Panels.Sale_Panel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import java.util.Random;
import javax.swing.*;
import msinventorysystem.MainFrame;
import msinventorysystem.myDecimalConverter;

public class NewProduct extends JFrame{
    JTextField PRODUCT_NAME_TF,QUANTITY_TF,EXPIRATION_TF,PRICE_TF,
            UNIT_TF,PREPARATION_TF,EXPIRED_YEAR_TF,PURCHASE_YEAR_TF,PURCHASE_FROM_TF,
            PRICE_INCREASE_TF,BRAND_NAME_TF,LIMIT_TF, SELLING_PRICE_TOTAL;
    JComboBox EXPIRED_MONTH,EXPIRED_DAY,
              PURCHASE_MONTH_PURCHASE_MONTH,PURCHASE_MONTH_PURCHASE_DAY;
    JButton ADD,CANCEL;
    String setID = null;
    static int lastX, lastY;
    
    myDecimalConverter mDc = new myDecimalConverter();
    
    ArrayList<String> scanner_tf = new ArrayList<String>();
    int expire_month = 0;
    int purchased_month = 0;
    static double setTotal = 0;
    //Set classes
    DataBase db = DataBase.getInstance();
    DataHolder dh = DataHolder.getInstance();

    private static NewProduct instance = null;    
    
    public static NewProduct getInstance() {
      if(instance == null) {
         instance = new NewProduct();
      }
      return instance;
    }
    public NewProduct(){
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
        this.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        this.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    public void centreWindow(Window frame) {
        this.setVisible(true);
        int x = frame.getX();
        int y = frame.getY();
        this.setLocation(x+260, y+50);
    }
    void randomizeID(){
        Random ran = new Random();
        int x = ran.nextInt(1500);
        if(dh.getProduct_ID().contains(x)){
            randomizeID();
        }else{
            setID = "100"+x;
        }
    }
    void textProcess_perCent(){
                try{
                    if(PRICE_INCREASE_TF.getText().equals("") ||
                        PRICE_TF.getText().equals("")){
                        SELLING_PRICE_TOTAL.setText(null);
                    }else{
                double value = Double.parseDouble(PRICE_TF.getText());
                double percentage = Double.parseDouble(PRICE_INCREASE_TF.getText());

                double k = (double)(value*(percentage/100.0f)); 
                float setValue = (float) (k+value);
                SELLING_PRICE_TOTAL.setText(mDc.roundOffTo2DecPlaces(setValue));
                    }
                }catch(Exception ex){
          }
                if(PRICE_INCREASE_TF.getText().toUpperCase().contains("D") ||
                   PRICE_INCREASE_TF.getText().toUpperCase().contains("F")){
                    PRICE_INCREASE_TF.setText(PRICE_INCREASE_TF.getText().replace("D", ""));
                    PRICE_INCREASE_TF.setText(PRICE_INCREASE_TF.getText().replace("F", ""));
                    PRICE_INCREASE_TF.setText(PRICE_INCREASE_TF.getText().replace("d", ""));
                    PRICE_INCREASE_TF.setText(PRICE_INCREASE_TF.getText().replace("f", ""));
                }
    }
    void Components(){
//        List<Image> icons = new ArrayList<Image>();
//        icons.add(new ImageIcon("Skins/C_extras/FRAME_ICO.png").getImage());
//        icons.add(new ImageIcon("Skins/C_extras/TASKBAR_ICO.png").getImage());
//        this.setIconImages(icons);
//
//        Container c = this.getContentPane(); 
//        c.setBackground(Color.WHITE);
        
        Font myFont = new Font("Segoe UI", Font.PLAIN | Font.BOLD, 15);
        Font newFont = myFont.deriveFont(50F);
        PRODUCT_NAME_TF = new JTextField("Enter product name....");
        PRODUCT_NAME_TF.setFont(myFont);
//        PRODUCT_NAME_TF.setHorizontalAlignment(JTextField.CENTER);
        PRODUCT_NAME_TF.setForeground(Color.GRAY);
        PRODUCT_NAME_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        PRODUCT_NAME_TF.setOpaque(false);
        PRODUCT_NAME_TF.setBounds(130,112,430,25);
        this.add(PRODUCT_NAME_TF);
        
        BRAND_NAME_TF = new JTextField("Enter brand name....");
        BRAND_NAME_TF.setBounds(130,150,430,25);
        BRAND_NAME_TF.setForeground(Color.GRAY);
        BRAND_NAME_TF.setFont(myFont);
        BRAND_NAME_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        BRAND_NAME_TF.setOpaque(false);
//        BRAND_NAME_TF.setHorizontalAlignment(JTextField.CENTER);
        this.add(BRAND_NAME_TF);

        QUANTITY_TF = new JTextField();
//        QUANTITY_TF.setHorizontalAlignment(JTextField.CENTER);
        QUANTITY_TF.setFont(myFont);
        QUANTITY_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        QUANTITY_TF.setOpaque(false);
        QUANTITY_TF.setBounds(130,265,99,25);
        this.add(QUANTITY_TF);
        
        PURCHASE_FROM_TF = new JTextField();
//        PURCHASE_FROM_TF.setHorizontalAlignment(JTextField.CENTER);
        PURCHASE_FROM_TF.setBounds(135,305,253,25);
        PURCHASE_FROM_TF.setFont(myFont);
        PURCHASE_FROM_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        PURCHASE_FROM_TF.setOpaque(false);
        this.add(PURCHASE_FROM_TF);
                
        PRICE_TF = new JTextField();
        PRICE_TF.setBounds(130,459,99,25);
//        PRICE_TF.setHorizontalAlignment(JTextField.CENTER);
        PRICE_TF.setFont(myFont);
        PRICE_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        PRICE_TF.setOpaque(false);
        this.add(PRICE_TF);
        
        SELLING_PRICE_TOTAL = new JTextField();
        SELLING_PRICE_TOTAL.setBounds(130,498,99,25);
        SELLING_PRICE_TOTAL.setOpaque(false);
        SELLING_PRICE_TOTAL.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        SELLING_PRICE_TOTAL.setFont(myFont);
//        numOnly(SELLING_PRICE_TOTAL);
        this.add(SELLING_PRICE_TOTAL);
        
        PRICE_INCREASE_TF = new JTextField("%");
        PRICE_INCREASE_TF.setBounds(330,459,99,25);
//        PRICE_INCREASE_TF.setHorizontalAlignment(JTextField.CENTER);
        PRICE_INCREASE_TF.setFont(myFont);
        PRICE_INCREASE_TF.setOpaque(false);
        PRICE_INCREASE_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        this.add(PRICE_INCREASE_TF);
//        
//        JLabel PERCENT = new JLabel("%");
//        PERCENT.setBounds(340,160,20,25);
//        this.add(PERCENT);
//        
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
        PURCHASE_YEAR_TF.setHorizontalAlignment(JTextField.CENTER);
        PURCHASE_YEAR_TF.setFont(myFont);
        PURCHASE_YEAR_TF.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        PURCHASE_YEAR_TF.setOpaque(false);
        PURCHASE_YEAR_TF.setBounds(290,342,52,25);
        this.add(PURCHASE_YEAR_TF);

        LIMIT_TF = new JTextField();
        LIMIT_TF.setBounds(284,265,100,25);
        LIMIT_TF.setFont(myFont);
        LIMIT_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        LIMIT_TF.setOpaque(false);
        this.add(LIMIT_TF);
        
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
        EXPIRED_YEAR_TF.setHorizontalAlignment(JTextField.CENTER);
        EXPIRED_YEAR_TF.setBounds(290,382,52,25);
        EXPIRED_YEAR_TF.setFont(myFont);
        EXPIRED_YEAR_TF.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        EXPIRED_YEAR_TF.setOpaque(false);
        this.add(EXPIRED_YEAR_TF);

        PREPARATION_TF = new JTextField();
//        PREPARATION_TF.setHorizontalAlignment(JTextField.CENTER);
        PREPARATION_TF.setFont(myFont);
        PREPARATION_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        PREPARATION_TF.setOpaque(false);
        PREPARATION_TF.setBounds(130,226,253,25);
        this.add(PREPARATION_TF);
        
        UNIT_TF = new JTextField();
//        UNIT_TF.setHorizontalAlignment(JTextField.CENTER);
        UNIT_TF.setFont(myFont);
        UNIT_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));
        UNIT_TF.setOpaque(false);
        UNIT_TF.setBounds(130,188,255,25);
        this.add(UNIT_TF);
        
        ImageIcon NEW_PRODUCT_II = new ImageIcon("Skins/C_buttons/"
                + "ADD_NEW_PRODUCT_1_BUTTON.png");
        ADD = new JButton(NEW_PRODUCT_II);
        ADD.setBounds(395,530,80,40);
        ADD.setOpaque(false);
        ADD.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ADD.setContentAreaFilled(false);
        ADD.setFocusable(false);
        ADD.setBorderPainted(false);
        this.add(ADD);
        
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
        
        ImageIcon BG_II = new ImageIcon("Skins/C_background/ADD_PRODUCT.png");
        JLabel BG = new JLabel(BG_II);
        BG.setBounds(0,0,600,600);
        this.add(BG);
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
    void Listener(){
//        SELLING_PRICE_TOTAL.addKeyListener(new KeyAdapter() {
//                public void keyTyped(KeyEvent e) {
//                    char vChar = e.getKeyChar();
////                    if (!(Character.isDigit(vChar)
////                            || (vChar == KeyEvent.VK_BACK_SPACE)
////                            || (vChar == KeyEvent.VK_DELETE))
////                            || (vChar == KeyEvent.VK_PERIOD)) {
////                        e.consume();
////                    }else{
////                        System.out.println("Period");
////                    }
//                    if (((vChar < '0') || (vChar > '9'))
//                    && (vChar != '\b')) {
//                        System.out.println("ambot");
//                    }else{
//                        System.out.println("dagay");                        
//                    }
//                }
        SELLING_PRICE_TOTAL.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                
             boolean ret = true;
                try {
                    Double.parseDouble(SELLING_PRICE_TOTAL.getText()+e.getKeyChar());
                }catch (NumberFormatException ee) {
                    ret = false;
                }
            if (!ret) {
                e.consume();
             }
                if(SELLING_PRICE_TOTAL.getText().isEmpty()){
                    PRICE_INCREASE_TF.setText(null);
                }
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(SELLING_PRICE_TOTAL.getText().isEmpty()){
                    PRICE_INCREASE_TF.setText(null);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
              try{
                float unit_price = Float.valueOf(PRICE_TF.getText());
                float selling_price = Float.valueOf(SELLING_PRICE_TOTAL.getText());
                PRICE_INCREASE_TF.setText(setPrice_percent_increase(unit_price,selling_price)+"");
                
                if(PRICE_TF.getText().equals(null) && 
                   SELLING_PRICE_TOTAL.getText().equals(null)){
                    PRICE_INCREASE_TF.setText(null);
                }
                if(SELLING_PRICE_TOTAL.getText().isEmpty()){
                    PRICE_INCREASE_TF.setText(null);
                }
              }catch(Exception ex){
                   }
                if(SELLING_PRICE_TOTAL.getText().toUpperCase().contains("D") ||
                   SELLING_PRICE_TOTAL.getText().toUpperCase().contains("F")){
                    SELLING_PRICE_TOTAL.setText(SELLING_PRICE_TOTAL.getText().replace("D", ""));
                    SELLING_PRICE_TOTAL.setText(SELLING_PRICE_TOTAL.getText().replace("F", ""));
                    SELLING_PRICE_TOTAL.setText(SELLING_PRICE_TOTAL.getText().replace("d", ""));
                    SELLING_PRICE_TOTAL.setText(SELLING_PRICE_TOTAL.getText().replace("f", ""));
                }
            }
        });
//        PRICE_INCREASE_TF.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//            }
//            @Override
//            public void keyPressed(KeyEvent e) {
//                String set = PRICE_INCREASE_TF.getText();
//                try {// if is number
//                        Integer.parseInt(set);
//                } catch (NumberFormatException ex) {
//                }
//            }
//            @Override
//            public void keyReleased(KeyEvent e) {
//            }
//        });
        LIMIT_TF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                String set = LIMIT_TF.getText();
                try {// if is number
                        Integer.parseInt(set);
                } catch (NumberFormatException ex) {
                }

            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
//        PRICE_INCREASE_TF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                PRICE_INCREASE_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                PRICE_INCREASE_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//            }
//        });        
//        PREPARATION_TF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                PREPARATION_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                PREPARATION_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//            }
//        });        
//        PURCHASE_FROM_TF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                PURCHASE_FROM_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                PURCHASE_FROM_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//            }
//        });        
//        QUANTITY_TF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                QUANTITY_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                QUANTITY_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//            }
//        });        
//        UNIT_TF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                UNIT_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                UNIT_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//            }
//        });        
//        PRICE_TF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                PRICE_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                PRICE_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//            }
//        });
//        LIMIT_TF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                LIMIT_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                LIMIT_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//            }
//        });
//        PURCHASE_YEAR_TF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                PURCHASE_YEAR_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                PURCHASE_YEAR_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//            }
//        });
//        EXPIRED_YEAR_TF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                EXPIRED_YEAR_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                EXPIRED_YEAR_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//            }
//        });
//        BRAND_NAME_TF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                BRAND_NAME_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                BRAND_NAME_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//            }
//        });
//        PRODUCT_NAME_TF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                PRODUCT_NAME_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                PRODUCT_NAME_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//            }
//        });
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
//        BRAND_NAME_TF.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                if (BRAND_NAME_TF.getText().length() >= 20 ) // limit to 3 characters
//                    e.consume();
//            }
//        });
        BRAND_NAME_TF.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            if(BRAND_NAME_TF.getText().equals("Enter brand name....")){
            BRAND_NAME_TF.setText(null);
            }else{
            BRAND_NAME_TF.setText(BRAND_NAME_TF.getText());
            }
            BRAND_NAME_TF.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
            BRAND_NAME_TF.setForeground(Color.GRAY);
            if(BRAND_NAME_TF.getText().isEmpty()){
            BRAND_NAME_TF.setText("Enter brand name....");
            }else{
            BRAND_NAME_TF.setText(BRAND_NAME_TF.getText());
            BRAND_NAME_TF.setForeground(Color.BLACK);
            }
            }
        });
//        PRODUCT_NAME_TF.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                if (PRODUCT_NAME_TF.getText().length() >= 20 ) // limit to 3 characters
//                    e.consume();
//            }
//        });
        PRODUCT_NAME_TF.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            if(PRODUCT_NAME_TF.getText().equals("Enter product name....")){
            PRODUCT_NAME_TF.setText(null);
            }else{
            PRODUCT_NAME_TF.setText(PRODUCT_NAME_TF.getText());
            }
            PRODUCT_NAME_TF.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
            PRODUCT_NAME_TF.setForeground(Color.GRAY);
            if(PRODUCT_NAME_TF.getText().isEmpty()){
            PRODUCT_NAME_TF.setText("Enter product name....");
            }else{
            PRODUCT_NAME_TF.setText(PRODUCT_NAME_TF.getText());
            PRODUCT_NAME_TF.setForeground(Color.BLACK);
            }
            }
        });
        PRICE_INCREASE_TF.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if(PRICE_INCREASE_TF.getText().equals("%")){
                    PRICE_INCREASE_TF.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(PRICE_INCREASE_TF.getText().isEmpty()){
                    PRICE_INCREASE_TF.setText("%");
                }
            }
        });
        PRICE_INCREASE_TF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                textProcess_perCent();
                boolean ret = true;
                try {
                    Double.parseDouble(PRICE_TF.getText()+e.getKeyChar());
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
                if(PRICE_TF.getText().toUpperCase().contains("D") ||
                   PRICE_TF.getText().toUpperCase().contains("F")){
                    PRICE_TF.setText(PRICE_TF.getText().replace("D", ""));
                    PRICE_TF.setText(PRICE_TF.getText().replace("F", ""));
                    PRICE_TF.setText(PRICE_TF.getText().replace("d", ""));
                    PRICE_TF.setText(PRICE_TF.getText().replace("f", ""));
                }
            }
        });
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
    LIMIT_TF.addKeyListener(new KeyAdapter() {
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
        }
    });
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
                if(PRICE_TF.getText().toUpperCase().contains("D") ||
                   PRICE_TF.getText().toUpperCase().contains("F")){
                    PRICE_TF.setText(PRICE_TF.getText().replace("D", ""));
                    PRICE_TF.setText(PRICE_TF.getText().replace("F", ""));
                    PRICE_TF.setText(PRICE_TF.getText().replace("d", ""));
                    PRICE_TF.setText(PRICE_TF.getText().replace("f", ""));
                }
            }
        });
//    PRICE_TF.addKeyListener(new KeyAdapter() {
//        public void keyTyped(KeyEvent e) {
//             boolean ret = true;
//                try {
//
//                    Double.parseDouble(PRICE_TF.getText()+e.getKeyChar());
//                }catch (NumberFormatException ee) {
//                    ret = false;
//                }
//
//            if (!ret) {
//                e.consume();
//            }
//        }
//    });
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
        ADD.addMouseListener(new MouseListener() {
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
                 || PRICE_INCREASE_TF.getText().isEmpty()
                 || LIMIT_TF.getText().isEmpty()){
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
                                                System.out.println("Different days is: "+diffDays);
                                                System.out.println("Expiry days is: "+expiryDate);
                                                if(setDate<=0){
                             JOptionPane.showMessageDialog(null,"Item already expired!");
                                                }else{
                randomizeID();
                monthCalculator();
                System.out.println("Set total: "+setTotal);
                //Add to database
                SQLiteDB_action sdb = SQLiteDB_action.getInstance();
                sdb.delete_out_of_stock_list(
                        PRODUCT_NAME_TF.getText(),
                        BRAND_NAME_TF.getText());
                out_of_stock_list o = out_of_stock_list.getInstance();
                sdb.updateOut_of_stock();
                List_Panel lp = List_Panel.getInstance();
                lp.setIcon_Icon();
                o.setIcon();
                if(PRICE_INCREASE_TF.getText().isEmpty()){
                    PRICE_INCREASE_TF.setText("0");
                }
                db.add_product_list(
                setID, 
                PRODUCT_NAME_TF.getText(),
                QUANTITY_TF.getText(),
                expire_month+"/"+EXPIRED_DAY.getSelectedItem().toString()+"/"+EXPIRED_YEAR_TF.getText(),
                PRICE_TF.getText(),
                SELLING_PRICE_TOTAL.getText()+"/"+PRICE_INCREASE_TF.getText(),
                purchased_month+"/"+PURCHASE_MONTH_PURCHASE_DAY.getSelectedItem()
                .toString()+"/"+PURCHASE_YEAR_TF.getText(),
                UNIT_TF.getText(),
                PREPARATION_TF.getText(),
                PURCHASE_FROM_TF.getText(),
                BRAND_NAME_TF.getText(),
                LIMIT_TF.getText());
                //Add to dataHolder
                dh.addData_product(
                setID, 
                PRODUCT_NAME_TF.getText(),
                QUANTITY_TF.getText(),
                expire_month+"/"+EXPIRED_DAY.getSelectedItem().toString()+"/"+EXPIRED_YEAR_TF.getText(),
                PRICE_TF.getText(),SELLING_PRICE_TOTAL.getText()+"/"+PRICE_INCREASE_TF.getText(),
                purchased_month+"/"+PURCHASE_MONTH_PURCHASE_DAY.getSelectedItem()
                .toString()+"/"+PURCHASE_YEAR_TF.getText(),UNIT_TF.getText(),
                PREPARATION_TF.getText(),PURCHASE_FROM_TF.getText(),BRAND_NAME_TF.getText(),
                LIMIT_TF.getText());
                
                o.setScanner(PRODUCT_NAME_TF.getText());
                System.out.println("asdkjhfvsdkhvkjhdskjvsdfvhjxcvgjhxcjh"+SELLING_PRICE_TOTAL.getText()+"/"+PRICE_INCREASE_TF.getText());
                SQLiteDB sdbx = SQLiteDB.getInstance();
                sdbx.retrieveOut_of_stock_data();
                
                PRODUCT_NAME_TF.setText("Enter product name....");
                PRODUCT_NAME_TF.setForeground(Color.GRAY);
                BRAND_NAME_TF.setText("Enter brand name....");
                BRAND_NAME_TF.setForeground(Color.GRAY);
                QUANTITY_TF.setText(null);
                PRICE_TF.setText(null);
                UNIT_TF.setText(null);
                PREPARATION_TF.setText(null);
                EXPIRED_YEAR_TF.setText(null);
                PURCHASE_YEAR_TF.setText(null);
                PRICE_INCREASE_TF.setText(null);
                SELLING_PRICE_TOTAL.setText(null);
                PRICE_INCREASE_TF.setText(null);
                LIMIT_TF.setText(null);
                PURCHASE_FROM_TF.setText(null);
                
                lp.filterTable();

                Sale_Panel sp = Sale_Panel.getInstance();
                sp.set_Table_info();
                
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(true);
                setVisible(false);
                JOptionPane.showMessageDialog(null,"Successfully added");           
                                
                EXPIRED_MONTH.setSelectedIndex(0);
                EXPIRED_MONTH.setSelectedIndex(0);
                PURCHASE_MONTH_PURCHASE_MONTH.setSelectedIndex(0);
                PURCHASE_MONTH_PURCHASE_DAY.setSelectedIndex(0);
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
            ImageIcon NEW_PRODUCT_II = new ImageIcon("Skins/C_buttons/"
                    + "ADD_NEW_PRODUCT_2_BUTTON.png");
            ADD.setIcon(NEW_PRODUCT_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
            ImageIcon NEW_PRODUCT_II = new ImageIcon("Skins/C_buttons/"
                    + "ADD_NEW_PRODUCT_1_BUTTON.png");
            ADD.setIcon(NEW_PRODUCT_II);
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
        CANCEL.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PRODUCT_NAME_TF.setText("Enter product name....");
                QUANTITY_TF.setText(null);
                PRICE_TF.setText(null);
                UNIT_TF.setText(null);
                PREPARATION_TF.setText(null);
                EXPIRED_YEAR_TF.setText(null);
                PURCHASE_YEAR_TF.setText(null);
                PRICE_INCREASE_TF.setText(null);
                SELLING_PRICE_TOTAL.setText(null);
                PRICE_INCREASE_TF.setText(null);
                BRAND_NAME_TF.setText("Enter brand name....");
                LIMIT_TF.setText(null);
                PURCHASE_FROM_TF.setText(null);
                
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(true);
                
                EXPIRED_MONTH.setSelectedIndex(0);
                EXPIRED_MONTH.setSelectedIndex(0);
                PURCHASE_MONTH_PURCHASE_MONTH.setSelectedIndex(0);
                PURCHASE_MONTH_PURCHASE_DAY.setSelectedIndex(0);
                
                setVisible(false);
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
}