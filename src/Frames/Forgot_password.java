package Frames;

import DataHolder.DataHolder;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Forgot_password extends JFrame{
    JLabel USERNAMEx,SECURITY_QUESTIONx,ANSWERx;
    JLabel SECURITY_QUESTIONS;
    JTextField USERNAME;
    JPasswordField ANSWER;
    JButton CONFIRM,CANCEL;
    JCheckBox SHOW_ANSWER;
    static int lastX, lastY;
    static boolean answer_CB_checker = true;
    DataHolder dh = DataHolder.getInstance();
    private static Forgot_password instance = null;
    
    public Forgot_password(){
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

        this.setSize(455,200);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Forgot password");   
        Organizer();
        this.setVisible(false);
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
    public void centreWindow(Window frame) {
        int x = frame.getX();
        int y = frame.getY();
        this.setLocation(x+250, y+200);
    }
    void Organizer(){
        Components();
        Listener();
    }
    void Components(){
        List<Image> icons = new ArrayList<Image>();
        icons.add(new ImageIcon("Skins/C_extras/FRAME_ICO.png").getImage());
        icons.add(new ImageIcon("Skins/C_extras/TASKBAR_ICO.png").getImage());
        this.setIconImages(icons);

        USERNAMEx = new JLabel("Username");
        USERNAMEx.setBounds(10,10,100,30);
        this.add(USERNAMEx);
        
        USERNAME = new JTextField();
        USERNAME.setHorizontalAlignment(JTextField.CENTER);
        USERNAME.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        USERNAME.setOpaque(false);
        USERNAME.setBounds(120,10,150,25);
        this.add(USERNAME);
        
        SECURITY_QUESTIONx = new JLabel("Security Question");
        SECURITY_QUESTIONx.setBounds(10,40,400,30);
//        this.add(SECURITY_QUESTIONx);
        
        SECURITY_QUESTIONS = new JLabel("Enter username to view security question");
        SECURITY_QUESTIONS.setBounds(10,40,320,30);
        this.add(SECURITY_QUESTIONS);
//        
//        SECURITY_QUESTIONS.addItem("What is the first name of the person you first kissed?");
//        SECURITY_QUESTIONS.addItem("In what city or town does your nearest sibling live?");
//        SECURITY_QUESTIONS.addItem("What is your pet’s name?");
//        SECURITY_QUESTIONS.addItem("In what year was your father born?");
//        SECURITY_QUESTIONS.addItem("What is your favorite _____?");
//        SECURITY_QUESTIONS.addItem("What time of the day were you born? (hh:mm)");

        ANSWERx = new JLabel("Answer");
        ANSWERx.setBounds(10,70,100,30);
        this.add(ANSWERx);
        
        ANSWER = new JPasswordField();
        ANSWER.setHorizontalAlignment(JTextField.CENTER);
        ANSWER.setBounds(120,75,150,25);
        ANSWER.setOpaque(false);
        ANSWER.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        ANSWER.setEchoChar('*');
        this.add(ANSWER);
        
        SHOW_ANSWER = new JCheckBox("Show answer");
        SHOW_ANSWER.setBounds(275,73,100,30);
        this.add(SHOW_ANSWER);
        
        CONFIRM = new JButton("Confirm");
        CONFIRM.setBounds(220,115,100,30);
        this.add(CONFIRM);
        
        CANCEL = new JButton("Cancel");
        CANCEL.setBounds(325,115,100,30);
        this.add(CANCEL);
    }
    void Listener(){
        ANSWER.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ANSWER.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
            }

            @Override
            public void focusLost(FocusEvent e) {
                ANSWER.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
            }
        });        
        USERNAME.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(dh.getIndex_username(USERNAME.getText())<0){
                    USERNAME.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }else{
                    USERNAME.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GREEN));
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(dh.getIndex_username(USERNAME.getText())<0){
                    USERNAME.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                }else{
                    USERNAME.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GREEN));
                }
            }
        });        
        USERNAME.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println(dh.getIndex_username(USERNAME.getText()));
                if(dh.getIndex_username(USERNAME.getText())<0){
                    USERNAME.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED));
                    SECURITY_QUESTIONS.setText("");
                }else{
                    USERNAME.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GREEN));
                    SECURITY_QUESTIONS.setText(dh.getSecurityQuestion(dh.getIndex()));
                    System.out.println(dh.getAnswer(dh.getIndex()));
                }
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
        SHOW_ANSWER.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(answer_CB_checker == true){
                    ANSWER.setEchoChar((char)0);
                    answer_CB_checker = false;
                }else{
                    ANSWER.setEchoChar('*');
                    answer_CB_checker = true;
                }
            }
        });
        CONFIRM.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dh.getIndex_username(USERNAME.getText());
                System.out.println("Password is "+dh.getSecurityQuestionAnswer());
                if(dh.getIndex_username(USERNAME.getText())<0){
                    JOptionPane.showMessageDialog(null,"Unknown user!");
                }else{
                if(dh.getSecurityQuestionAnswer().equals(ANSWER.getText().toString())){
                   JOptionPane.showMessageDialog(null,"Your password is "
                   +dh.getPassword(dh.getIndex()));
                
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);
                setVisible(false);
                }else{
                   JOptionPane.showMessageDialog(null,"Invalid information!");
                   ANSWER.setText("");
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
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        CANCEL.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(true);
                setVisible(false);
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
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
}