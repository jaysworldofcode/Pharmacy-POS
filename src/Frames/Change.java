package Frames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import msinventorysystem.MainFrame;

public class Change extends JFrame implements KeyListener{

    JLabel TOTAL,CHANGE;
    JButton OK;

    static int lastX, lastY;
    
    private static Change instance = null;
    public static Change getInstance() {
      if(instance == null) {
         instance = new Change();
      }
      return instance;
    }        

    public Change() {
        this.setSize(455,455);
        this.setResizable(false);
        this.setLayout(null);
        this.setFocusable(true);
        this.setTitle("Billing");   
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        this.setUndecorated(true);
        addKeyListener(this);
        Organizer();
        this.setVisible(true);

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
        int x = frame.getX();
        int y = frame.getY();
        this.setLocation(x+200, y+200);
    }
    void Components(){
        Font SETFONT = new Font("SansSerif", Font.BOLD, 60);
        TOTAL = new JLabel();
        TOTAL.setBounds(160,0,1000,200);
        TOTAL.setFont(SETFONT);
        this.add(TOTAL);
        
        Font SETFONTx = new Font("SansSerif", Font.BOLD, 70);
        CHANGE = new JLabel();
        CHANGE.setBounds(150,180,1000,200);
        CHANGE.setFont(SETFONTx);
        this.add(CHANGE);
        
        ImageIcon II = new ImageIcon("Skins/C_buttons/OK_1.png");
        OK = new JButton(II);
        OK.setBounds(305,370,100,50);
        OK.setOpaque(false);
        OK.setCursor(new Cursor(Cursor.HAND_CURSOR));
        OK.setContentAreaFilled(false);
        OK.setFocusable(false);
        OK.setBorderPainted(false);
        this.add(OK);
        
        ImageIcon BG_II = new ImageIcon("Skins/C_background/CHANGE.png");
        JLabel BG = new JLabel(BG_II);
        BG.setBounds(0,0,455,455);
        this.add(BG);
    }
    public void setText(String total,String change){
       TOTAL.setText(total);
       CHANGE.setText(change);
    }
    void Listener(){
        OK.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(true);
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
                ImageIcon II = new ImageIcon("Skins/C_buttons/OK_2.png");
                OK.setIcon(II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon II = new ImageIcon("Skins/C_buttons/OK_1.png");
                OK.setIcon(II);
            }
        });
        OK.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
    public static void main(String [] args){
        Change c = new Change();
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_ENTER){
            setVisible(false);
        }
    }
}