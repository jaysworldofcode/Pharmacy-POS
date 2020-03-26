package Frames;

import DataHolder.DataHolder;
import Panels.Sale_Panel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import msinventorysystem.MainFrame;

public class AddCart extends JFrame{

    JTextField quantity;
    DataHolder dh = DataHolder.getInstance();
    
    private static AddCart instance = null;    
    
    public static AddCart getInstance() {
      if(instance == null) {
         instance = new AddCart();
      }
      return instance;
    }
    public void centreWindow(Window frame) {
        this.setVisible(true);
        int x = frame.getX();
        int y = frame.getY();
        this.setLocation(x, y+150);
    }
    public AddCart(){
        setSize(1100,300);
        setUndecorated(true);
//        setOpacity(0);
        setLayout(null);
        setBackground(new Color(0,0,0,0));
        
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
        
        quantity = new JTextField();
        quantity.setBounds(423,125,295,33);
        quantity.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        quantity.setHorizontalAlignment(JTextField.CENTER);
        quantity.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(quantity);
        
        quantity.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char vChar = e.getKeyChar();
                    if (!(Character.isDigit(vChar)
                            || (vChar == KeyEvent.VK_BACK_SPACE)
                            || (vChar == KeyEvent.VK_DELETE))) {
                        e.consume();
                    }
                }
            });
            quantity.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if(code == KeyEvent.VK_ESCAPE){
                     MainFrame mf = MainFrame.getInstance();
                     mf.setFront(true);
                     mf.requestFocus();
                     mf.toFront();
                     setVisible(false);
                     quantity.setText("");
                }
                if(code == KeyEvent.VK_ENTER){
                     MainFrame mf = MainFrame.getInstance();
                     mf.setFront(true);
                     mf.requestFocus();
                     mf.toFront();
                     setVisible(false);
                     Sale_Panel sp = Sale_Panel.getInstance();
                     sp.addCart(quantity.getText());
                     sp.filterTable();
                     quantity.setText("");
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
            
        ImageIcon ii_bg = new ImageIcon("Skins/C_background/sale_get_quantity_bg.png");
        JLabel bg = new JLabel(ii_bg);
        bg.setBounds(0,0,1100,300);
        add(bg);
    }
    
    public static void main(String [] args){
        new AddCart().setVisible(true);
    }
}