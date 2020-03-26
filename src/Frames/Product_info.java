package Frames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.*;

public class Product_info extends JFrame{

    JPanel setPanel;

    JLabel setProduct_name,setPurchased_from,setPrice,setStrength,setBrand_name,setPreparation;
    JLabel viewProduct_name,viewPurchased_from,viewPrice,viewStrength,viewBrand_name,viewPreparation;
    JButton EXIT;
    
    static int lastX, lastY;
    
    public Product_info(
            String product_name,String purchased_from, String price, String strength, String brand_name,
            String preparation){
        
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

     Font SETFONT = new Font("SansSerif", Font.BOLD, 14);
     Font SETFONTx = new Font("SansSerif", Font.PLAIN, 14);

     this.setUndecorated(true);
     this.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
     this.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
     this.setSize(500,300);  
     this.setLayout(null);
    
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

//    setPanel = new JPanel();
//    setPanel.setBounds(10,10,490,490);
//    setPanel.setBackground(Color.WHITE);
//    setPanel.setLayout(null);
//    add(setPanel);
    int setMinus_for_reposition = 30;
        
    viewProduct_name = new JLabel("Product name");
    viewProduct_name.setBounds(20,80-setMinus_for_reposition,120,30);
    viewProduct_name.setFont(SETFONT);
    viewProduct_name.setForeground(Color.DARK_GRAY);
    this.add(viewProduct_name);
    
    setProduct_name = new JLabel(product_name.replaceAll("\\(.*?\\)", ""));
    setProduct_name.setBounds(170,80-setMinus_for_reposition,500,30);
    setProduct_name.setFont(SETFONTx);
    this.add(setProduct_name);
    
    viewPurchased_from = new JLabel("Purchased from");
    viewPurchased_from.setBounds(20,255-setMinus_for_reposition,120,30);
    viewPurchased_from.setForeground(Color.DARK_GRAY);
    viewPurchased_from.setFont(SETFONT);
    this.add(viewPurchased_from);
    
    setPurchased_from = new JLabel(purchased_from);
    setPurchased_from.setBounds(170,255-setMinus_for_reposition,120,30);
    setPurchased_from.setFont(SETFONTx);
    this.add(setPurchased_from);
    
    viewPrice = new  JLabel("Unit Price");
    viewPrice.setBounds(20,220-setMinus_for_reposition,120,30);
    viewPrice.setForeground(Color.DARK_GRAY);
    viewPrice.setFont(SETFONT);
    this.add(viewPrice);
    
    setPrice = new JLabel(price);
    setPrice.setBounds(170,220-setMinus_for_reposition,500,30);
    setPrice.setFont(SETFONTx);
    this.add(setPrice);
    
    viewStrength = new JLabel("Strength");
    viewStrength.setBounds(20,150-setMinus_for_reposition,120,30);
    viewStrength.setForeground(Color.DARK_GRAY);
    viewStrength.setFont(SETFONT);
    this.add(viewStrength);
    
    setStrength = new JLabel(strength);
    setStrength.setBounds(170,150-setMinus_for_reposition,500,30);
    setStrength.setFont(SETFONTx);
    this.add(setStrength);
    
    viewBrand_name = new JLabel("Brand name");
    viewBrand_name.setBounds(20,115-setMinus_for_reposition,120,30);
    viewBrand_name.setForeground(Color.DARK_GRAY);
    viewBrand_name.setFont(SETFONT);
    this.add(viewBrand_name);

    setBrand_name = new JLabel(brand_name);
    setBrand_name.setBounds(170,115-setMinus_for_reposition,500,30);
    setBrand_name.setFont(SETFONTx);
    this.add(setBrand_name);

    setPreparation = new JLabel("Preparation");
    setPreparation.setBounds(20,185-setMinus_for_reposition,120,30);
    setPreparation.setForeground(Color.DARK_GRAY);
    setPreparation.setFont(SETFONT);
    this.add(setPreparation);
    
    viewPreparation = new JLabel(preparation);
    viewPreparation.setBounds(170,185-setMinus_for_reposition,500,30);
    viewPreparation.setFont(SETFONTx);
    this.add(viewPreparation);
    
    ImageIcon EXIT_II = new ImageIcon("Skins/C_buttons/second-frame.png");
    EXIT = new JButton(EXIT_II);
    EXIT.setBounds(445,12,40,40);
    EXIT.setOpaque(false);
    EXIT.setCursor(new Cursor(Cursor.HAND_CURSOR));
    EXIT.setContentAreaFilled(false);
    EXIT.setFocusable(false);
    EXIT.setBorderPainted(false);

    EXIT.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon EXIT_II = new ImageIcon("Skins/C_buttons/second-"
                        + "frame-2.png");
                EXIT.setIcon(EXIT_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon EXIT_II = new ImageIcon("Skins/C_buttons/second-"
                        + "frame.png");
                EXIT.setIcon(EXIT_II);
            }
        });
    this.add(EXIT);
    
    ImageIcon II = new ImageIcon("Skins/C_background/PRODUCT_INFO.png");
    JLabel BG = new JLabel(II);
    BG.setBounds(0,0,500,300);
    this.add(BG);
    } 
    public void centreWindow(Window frame) {
        int x = frame.getX();
        int y = frame.getY();
        this.setLocation(x+300, y+200);
    }
    public static void main(String [] args){
        Product_info pi = new Product_info("Paracetamol","Drugman","14.00","140mg","fern-c","mg");
        pi.setVisible(true);
    }
}