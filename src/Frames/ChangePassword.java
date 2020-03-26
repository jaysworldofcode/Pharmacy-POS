package Frames;

import DataBase.SQLiteDB_action;
import DataHolder.DataHolder;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import msinventorysystem.MainFrame;

public class ChangePassword extends JFrame{

    JLabel NEW_PASSWORD,CONFIRM_PASSWORD,CURRENT_PASSWORD_SET,CHECKER,CHECKER_CURRENT;
    JPasswordField CURRENT_PASSWORD_PF,NEW_PASSWORD_PF,CONFIRM_PASSWORD_PF;
    JButton CONFIRM,CANCEL;
    static int lastX, lastY;
    //SET CLASSES
    DataHolder dh = DataHolder.getInstance();
    SQLiteDB_action sqlite = SQLiteDB_action.getInstance();
    private static ChangePassword instance = null;    
    
    public static ChangePassword getInstance() {
      if(instance == null) {
         instance = new ChangePassword();
      }
      return instance;
    }
    
        Font SETFONT = new Font("SansSerif", Font.BOLD, 13);
    
    ChangePassword(){
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

        this.setBounds(0,0,310,300);
        this.setLayout(null);
        this.setVisible(false);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setUndecorated(true);
        this.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        this.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
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
        int x = frame.getX();
        int y = frame.getY();
        this.setLocation(x+300, y+200);
    }
    void Components(){
        List<Image> icons = new ArrayList<Image>();
        icons.add(new ImageIcon("Skins/C_extras/FRAME_ICO.png").getImage());
        icons.add(new ImageIcon("Skins/C_extras/TASKBAR_ICO.png").getImage());
        this.setIconImages(icons);
        
        Container c = this.getContentPane(); 
        c.setBackground(Color.WHITE);
        
        CHECKER_CURRENT = new JLabel();
        CHECKER_CURRENT.setBounds(220,65,25,25);
        this.add(CHECKER_CURRENT);
        
        CHECKER = new JLabel();
        CHECKER.setBounds(220,165,25,25);
        this.add(CHECKER);
        
        CURRENT_PASSWORD_SET = new JLabel("Enter current password");
        CURRENT_PASSWORD_SET.setBounds(10,40,200,25);
        CURRENT_PASSWORD_SET.setForeground(Color.DARK_GRAY);
        CURRENT_PASSWORD_SET.setFont(SETFONT);
//        this.add(CURRENT_PASSWORD_SET);
        
        CURRENT_PASSWORD_PF = new JPasswordField("Enter current password....");
        CURRENT_PASSWORD_PF.setHorizontalAlignment(JTextField.CENTER);
        CURRENT_PASSWORD_PF.setBounds(30,110,254,25);
        CURRENT_PASSWORD_PF.setEchoChar((char) 0);
        CURRENT_PASSWORD_PF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        CURRENT_PASSWORD_PF.setOpaque(false);
        CURRENT_PASSWORD_PF.setForeground(Color.GRAY);
        this.add(CURRENT_PASSWORD_PF);
        
        NEW_PASSWORD = new JLabel("New password");
        NEW_PASSWORD.setBounds(10,90,150,25);
        NEW_PASSWORD.setFont(SETFONT);
        NEW_PASSWORD.setForeground(Color.DARK_GRAY);
//        this.add(NEW_PASSWORD);
        
        NEW_PASSWORD_PF = new JPasswordField("Enter new password....");
        NEW_PASSWORD_PF.setHorizontalAlignment(JTextField.CENTER);
        NEW_PASSWORD_PF.setBounds(30,150,254,25);
        NEW_PASSWORD_PF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        NEW_PASSWORD_PF.setOpaque(false);
        NEW_PASSWORD_PF.setEchoChar((char) 0);
        NEW_PASSWORD_PF.setForeground(Color.GRAY);
        this.add(NEW_PASSWORD_PF);
        
        CONFIRM_PASSWORD = new JLabel("Confirm password");
        CONFIRM_PASSWORD.setBounds(10,140,150,25);
        CONFIRM_PASSWORD.setFont(SETFONT);
        CONFIRM_PASSWORD.setForeground(Color.DARK_GRAY);
//        this.add(CONFIRM_PASSWORD);
        
        CONFIRM_PASSWORD_PF = new JPasswordField("Confirm password....");
        CONFIRM_PASSWORD_PF.setHorizontalAlignment(JTextField.CENTER);
        CONFIRM_PASSWORD_PF.setBounds(30,190,254,25);
        CONFIRM_PASSWORD_PF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        CONFIRM_PASSWORD_PF.setOpaque(false);
        CONFIRM_PASSWORD_PF.setEchoChar((char) 0);
        CONFIRM_PASSWORD_PF.setForeground(Color.GRAY);
        this.add(CONFIRM_PASSWORD_PF);
        
        ImageIcon CONFIRM_II = new ImageIcon("Skins/C_buttons/"
                + "save-change-password.png");
        CONFIRM = new JButton(CONFIRM_II);
        CONFIRM.setBounds(0,250,310,50);
//        CONFIRM.setOpaque(false);
//        CONFIRM.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        CONFIRM.setContentAreaFilled(false);
//        CONFIRM.setFocusable(false);
//        CONFIRM.setBorderPainted(false);
        this.add(CONFIRM);
        
        ImageIcon CANCEL_II = new ImageIcon("Skins/C_buttons/"
                + "second-frame.png");
        CANCEL = new JButton(CANCEL_II);
        CANCEL.setBounds(270,0,40,40);
        CANCEL.setOpaque(false);
        CANCEL.setCursor(new Cursor(Cursor.HAND_CURSOR));
        CANCEL.setContentAreaFilled(false);
        CANCEL.setFocusable(false);
        CANCEL.setBorderPainted(false);
        this.add(CANCEL);
        
        ImageIcon II = new ImageIcon("Skins/C_background/CHANGE_PASSWORD_BG.png");
        JLabel BG = new JLabel(II);
        BG.setBounds(0,0,310,300);
        this.add(BG);
    }
    void Listener(){
//        CONFIRM_PASSWORD_PF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                CONFIRM_PASSWORD_PF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                CONFIRM_PASSWORD_PF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//            }
//        });        
//        NEW_PASSWORD_PF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                NEW_PASSWORD_PF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                NEW_PASSWORD_PF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//            }
//        });        
//        CURRENT_PASSWORD_PF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                CURRENT_PASSWORD_PF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                CURRENT_PASSWORD_PF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
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
        CURRENT_PASSWORD_PF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {                
                if(dh.getPassword().equals(CURRENT_PASSWORD_PF.getText())){
//                    ImageIcon CHECKER_II = new ImageIcon("Skins"
//                    + "/C_extras/CORRECT.png");
//                    CHECKER_CURRENT.setIcon(CHECKER_II);
                      CURRENT_PASSWORD_PF.setForeground(Color.GREEN);
                }else{
//                    ImageIcon CHECKER_II = new ImageIcon("Skins"
//                    + "/C_extras/WRONG.png");
//                    CHECKER_CURRENT.setIcon(CHECKER_II);
                      CURRENT_PASSWORD_PF.setForeground(Color.RED);
                }
            }
        });
        CONFIRM_PASSWORD_PF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {                
                if(CONFIRM_PASSWORD_PF.getText().equals(NEW_PASSWORD_PF.getText())){
//                    ImageIcon CHECKER_II = new ImageIcon("Skins"
//                    + "/C_extras/CORRECT.png");
//                    CHECKER_CURRENT.setIcon(CHECKER_II);
                      CONFIRM_PASSWORD_PF.setForeground(Color.GREEN);
                }else{
//                    ImageIcon CHECKER_II = new ImageIcon("Skins"
//                    + "/C_extras/WRONG.png");
//                    CHECKER_CURRENT.setIcon(CHECKER_II);
                      CONFIRM_PASSWORD_PF.setForeground(Color.RED);
                }
            }
        });
//        CONFIRM_PASSWORD_PF.addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//            }
//            @Override
//            public void keyPressed(KeyEvent e) {
//            }
//            @Override
//            public void keyReleased(KeyEvent e) {
//                if(NEW_PASSWORD_PF.getText().equals(CONFIRM_PASSWORD_PF.getText())){
//                    ImageIcon CHECKER_II = new ImageIcon("Skins"
//                    + "/C_extras/CORRECT.png");
//                    CHECKER.setIcon(CHECKER_II);
//                }else{
//                    ImageIcon CHECKER_II = new ImageIcon("Skins"
//                    + "/C_extras/WRONG.png");
//                    CHECKER.setIcon(CHECKER_II);
//                }
//            }
//        });
        CONFIRM_PASSWORD_PF.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            if(CONFIRM_PASSWORD_PF.getText().equals("Confirm password....")){
            CONFIRM_PASSWORD_PF.setText(null);
            CONFIRM_PASSWORD_PF.setEchoChar('\u2022');
            CONFIRM_PASSWORD_PF.setForeground(Color.BLACK);
            }else{
            CONFIRM_PASSWORD_PF.setText(CONFIRM_PASSWORD_PF.getText());
            CONFIRM_PASSWORD_PF.setEchoChar('\u2022');
            }
            }

            @Override
            public void focusLost(FocusEvent e) {
//            CONFIRM_PASSWORD_PF.setForeground(Color.GRAY);
            if(CONFIRM_PASSWORD_PF.getText().isEmpty()){
            CONFIRM_PASSWORD_PF.setText("Confirm password....");
            CONFIRM_PASSWORD_PF.setEchoChar((char) 0);
            CONFIRM_PASSWORD_PF.setForeground(Color.GRAY);
            }else{
            CONFIRM_PASSWORD_PF.setText(CONFIRM_PASSWORD_PF.getText());
//            CONFIRM_PASSWORD_PF.setForeground(Color.BLACK);
            }
            }
        });
        NEW_PASSWORD_PF.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            if(NEW_PASSWORD_PF.getText().equals("Enter new password....")){
            NEW_PASSWORD_PF.setText(null);
            NEW_PASSWORD_PF.setEchoChar('\u2022');
            NEW_PASSWORD_PF.setForeground(Color.BLACK);
            }else{
            NEW_PASSWORD_PF.setText(NEW_PASSWORD_PF.getText());
            NEW_PASSWORD_PF.setEchoChar('\u2022');
            }
            }

            @Override
            public void focusLost(FocusEvent e) {
//            NEW_PASSWORD_PF.setForeground(Color.GRAY);
            if(NEW_PASSWORD_PF.getText().isEmpty()){
            NEW_PASSWORD_PF.setText("Enter new password....");
            NEW_PASSWORD_PF.setEchoChar((char) 0);
            NEW_PASSWORD_PF.setForeground(Color.GRAY);
            }else{
            NEW_PASSWORD_PF.setText(NEW_PASSWORD_PF.getText());
//            NEW_PASSWORD_PF.setForeground(Color.BLACK);
            }
            }
        });
        CURRENT_PASSWORD_PF.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            if(CURRENT_PASSWORD_PF.getText().equals("Enter current password....")){
            CURRENT_PASSWORD_PF.setText(null);
            CURRENT_PASSWORD_PF.setEchoChar('\u2022');
            }else{
            CURRENT_PASSWORD_PF.setText(CURRENT_PASSWORD_PF.getText());
            CURRENT_PASSWORD_PF.setEchoChar('\u2022');
            }
//            CURRENT_PASSWORD_PF.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
            CURRENT_PASSWORD_PF.setForeground(Color.GRAY);
            if(CURRENT_PASSWORD_PF.getText().isEmpty()){
            CURRENT_PASSWORD_PF.setText("Enter current password....");
            CURRENT_PASSWORD_PF.setEchoChar((char) 0);
            CURRENT_PASSWORD_PF.setForeground(Color.GRAY);
            }else{
            CURRENT_PASSWORD_PF.setText(CURRENT_PASSWORD_PF.getText());
//            CURRENT_PASSWORD_PF.setForeground(Color.BLACK);
            }
            }
        });
        CONFIRM.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(CURRENT_PASSWORD_PF.getText().equals(dh.getPassword())){
                    if(NEW_PASSWORD_PF.getText().equals(CONFIRM_PASSWORD_PF.getText())){
                        sqlite.updatePassword(dh.getUser(),NEW_PASSWORD_PF.getText());
                        dh.updateAccount(NEW_PASSWORD_PF.getText());
                        JOptionPane.showMessageDialog(null,"Successfully updated.");
                        CURRENT_PASSWORD_SET.setText(dh.retrievePassword());
                        NEW_PASSWORD_PF.setText(null);
                        CONFIRM_PASSWORD_PF.setText(null);

                CURRENT_PASSWORD_PF.setText("Enter current password....");
                CURRENT_PASSWORD_PF.setEchoChar((char) 0);
                CURRENT_PASSWORD_PF.setForeground(Color.GRAY);
                
                NEW_PASSWORD_PF.setText("Enter new password....");
                NEW_PASSWORD_PF.setBounds(10,75,200,25);
                NEW_PASSWORD_PF.setEchoChar((char) 0);
                NEW_PASSWORD_PF.setForeground(Color.GRAY);
        
                CONFIRM_PASSWORD_PF.setText("Confirm password....");
                CONFIRM_PASSWORD_PF.setEchoChar((char) 0);
                CONFIRM_PASSWORD_PF.setForeground(Color.GRAY);
                
                CHECKER.setIcon(null);
                CHECKER_CURRENT.setIcon(null);
                        
                        setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(null,"Password confirmation "
                                + "does not match.");
                    }
                }else{
                        JOptionPane.showMessageDialog(null,"Password does not "
                                + "match.");
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
                ImageIcon CONFIRM_II = new ImageIcon("Skins/C_buttons/"
                        + "save-change-password-2.png");
                CONFIRM.setIcon(CONFIRM_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon CONFIRM_II = new ImageIcon("Skins/C_buttons/"
                        + "save-change-password.png");
                CONFIRM.setIcon(CONFIRM_II);
            }
        });
        CANCEL.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CURRENT_PASSWORD_PF.setText("Enter current password....");
                CURRENT_PASSWORD_PF.setEchoChar((char) 0);
                CURRENT_PASSWORD_PF.setForeground(Color.GRAY);
                
                NEW_PASSWORD_PF.setText("Enter new password....");
//                NEW_PASSWORD_PF.setBounds(10,75,200,25);
                NEW_PASSWORD_PF.setEchoChar((char) 0);
                NEW_PASSWORD_PF.setForeground(Color.GRAY);
        
                CONFIRM_PASSWORD_PF.setText("Confirm password....");
                CONFIRM_PASSWORD_PF.setEchoChar((char) 0);
                CONFIRM_PASSWORD_PF.setForeground(Color.GRAY);
                
                CHECKER.setIcon(null);
                CHECKER_CURRENT.setIcon(null);
                
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(true);
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
                        + "second-frame-2.png");
                CANCEL.setIcon(CANCEL_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon CANCEL_II = new ImageIcon("Skins/C_buttons/"
                        + "second-frame.png");
                CANCEL.setIcon(CANCEL_II);
            }
        });
    }
    public static void main(String [] args){
        new ChangePassword().setVisible(true);
    }
}