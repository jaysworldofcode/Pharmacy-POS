package Frames;

import java.awt.Font;
import javax.swing.*;

public class SaleProduct extends JFrame{
    
    JLabel PRODUCT_NAME,PREPARATION,QUANTITY,PRICE,EXPIRATION;
    JLabel PRODUCT_NAMEx,PREPARATIONx,QUANTITYx,PRICEx,EXPIRATIONx;
    JTextField QUANTITY_SET;
    
    private static SaleProduct instance = null;    
    
    public static SaleProduct getInstance() {
      if(instance == null) {
         instance = new SaleProduct();
      }
      return instance;
    }
    
    SaleProduct(){
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setSize(600,400);
        this.setTitle("Sale");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        Organizer();
        this.setVisible(true);
    }
    public void setLocation(){
        
    }
    
    void Organizer(){
        Components();
        Listener();
    }
    
    void Components(){
        Font SETFONT = new Font("SansSerif", Font.BOLD, 13);

        PRODUCT_NAME = new JLabel("Product name");
        PRODUCT_NAME.setBounds(20,20,100,25);
        PRODUCT_NAME.setFont(SETFONT);
        this.add(PRODUCT_NAME);
        
        PRODUCT_NAMEx = new JLabel("-----");
        PRODUCT_NAMEx.setBounds(120,20,100,25);
        this.add(PRODUCT_NAMEx);
        
        PREPARATION = new JLabel("Preparation");
        PREPARATION.setBounds(20,50,100,25);
        PREPARATION.setFont(SETFONT);
        this.add(PREPARATION);
        
        PREPARATIONx = new JLabel("-----");
        PREPARATIONx.setBounds(120,50,100,25);
        this.add(PREPARATIONx);
        
        QUANTITY = new JLabel("Quantity");
        QUANTITY.setBounds(20,80,100,25);
        QUANTITY.setFont(SETFONT);
        this.add(QUANTITY);
        
        QUANTITYx = new JLabel("-- -");
        QUANTITYx.setBounds(120,80,50,25);
        this.add(QUANTITYx);
        
        QUANTITY_SET = new JTextField();
        QUANTITY_SET.setBounds(150,80,30,25);
        this.add(QUANTITY_SET);
        
        PRICE = new JLabel("Price");
        PRICE.setBounds(20,110,100,25);
        PRICE.setFont(SETFONT);
        this.add(PRICE);
        
        PRICEx = new JLabel("-----");
        PRICEx.setBounds(120,110,100,25);
        this.add(PRICEx);
        
        EXPIRATION = new JLabel("Expiration");
        EXPIRATION.setBounds(20,140,100,25);
        EXPIRATION.setFont(SETFONT);
        this.add(EXPIRATION);
        
        EXPIRATIONx = new JLabel("-----");
        EXPIRATIONx.setBounds(120,140,100,25);
        this.add(EXPIRATIONx);
    }
    void Listener(){
        
    }
    public static void main(String [] args){
        SaleProduct sp = new SaleProduct();
        sp.setVisible(true);
    }
}
