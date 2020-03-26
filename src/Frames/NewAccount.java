package Frames;

import DataBase.SQLiteDB;
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

public class NewAccount extends JFrame{

    JLabel USER_TYPE,PASSWORD,CONFIRM_PASSWORD,SECURITY_QUESTION,ANSWER,USERNAME,
           CHECKER_JL;
    JTextField USERNAME_TF,ANSWER_TF;
    JPasswordField PASSWORD_PF,PASSWORD_CONFIRM_PF;
    JComboBox USER_TYPE_CB,SECURITY_QUESTION_CB;
    JButton ADD,CANCEL,EXIT;
    static int lastX, lastY;
    //SET CLASSES
    DataHolder dh = DataHolder.getInstance();
    private static NewAccount instance = null;    
    
    public static NewAccount getInstance() {
      if(instance == null) {
         instance = new NewAccount();
      }
      return instance;
    }   
    
    NewAccount(){
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

        this.setSize(600,400);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("New Account");        
        this.setUndecorated(true);
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
        this.setLocation(x+250, y+150);
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

        Container c = this.getContentPane(); 
        c.setBackground(Color.WHITE);
        
        int space = 40;
        int spaceX = 40;
        
        USER_TYPE = new JLabel("Usertype");
        USER_TYPE.setFont(new Font("Tahoma", Font.BOLD, 14));
        USER_TYPE.setBounds(10,30+space,120,25);
//        this.add(USER_TYPE);
        
        USER_TYPE_CB = new JComboBox();
        USER_TYPE_CB.setBounds(46,55,300,30);
        this.add(USER_TYPE_CB);
        
        USER_TYPE_CB.addItem("admin");
        USER_TYPE_CB.addItem("user");
        
        USERNAME = new JLabel("Username");
        USERNAME.setBounds(10,60+space,120,25);
        USERNAME.setFont(new Font("Tahoma", Font.BOLD, 14));
//        this.add(USERNAME);

        USERNAME_TF = new JTextField("Enter username....");
        USERNAME_TF.setForeground(Color.GRAY);
//        USERNAME_TF.setHorizontalAlignment(JTextField.CENTER);
        USERNAME_TF.setOpaque(false);
        USERNAME_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        USERNAME_TF.setBounds(85,103,318,25);
        this.add(USERNAME_TF);
        
        PASSWORD = new JLabel("Password");
        PASSWORD.setBounds(10,90+space,120,25);
        PASSWORD.setFont(new Font("Tahoma", Font.BOLD, 14));
//        this.add(PASSWORD);
        
        PASSWORD_PF = new JPasswordField("Enter password....");
//        PASSWORD_PF.setHorizontalAlignment(JTextField.CENTER);
        PASSWORD_PF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        PASSWORD_PF.setBounds(85,140,318,25);
        PASSWORD_PF.setEchoChar((char) 0);  
        PASSWORD_PF.setForeground(Color.GRAY);
        this.add(PASSWORD_PF);
        
        CONFIRM_PASSWORD = new JLabel("Confirm Password");
        CONFIRM_PASSWORD.setFont(new Font("Tahoma", Font.BOLD, 14));
        CONFIRM_PASSWORD.setBounds(10,120+space,150,25);
//        this.add(CONFIRM_PASSWORD);
        
        PASSWORD_CONFIRM_PF = new JPasswordField("Confirm password....");
//        PASSWORD_CONFIRM_PF.setHorizontalAlignment(JTextField.CENTER);
        PASSWORD_CONFIRM_PF.setBounds(85,182,318,25);
        PASSWORD_CONFIRM_PF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        PASSWORD_CONFIRM_PF.setEchoChar((char) 0);
        PASSWORD_CONFIRM_PF.setForeground(Color.GRAY);
        this.add(PASSWORD_CONFIRM_PF);

        CHECKER_JL = new JLabel();
        CHECKER_JL.setBounds(250+spaceX,118+space,30,30);
//        this.add(CHECKER_JL);
        
        SECURITY_QUESTION = new JLabel("Security Question");
        SECURITY_QUESTION.setBounds(10,150+space,150,25);
        SECURITY_QUESTION.setFont(new Font("Tahoma", Font.BOLD, 14));
//        this.add(SECURITY_QUESTION);
        
        SECURITY_QUESTION_CB = new JComboBox();
        SECURITY_QUESTION_CB.setBounds(46,220,300,30);
        this.add(SECURITY_QUESTION_CB);
        
        SECURITY_QUESTION_CB.addItem("What is your pet’s name?");
        SECURITY_QUESTION_CB.addItem("In what year was your father born?");
        SECURITY_QUESTION_CB.addItem("What is your favorite _____?");
        SECURITY_QUESTION_CB.addItem("What time of the day were you born? (hh:mm)");

        ANSWER = new JLabel("Answer");
        ANSWER.setBounds(10,180+space,120,25);
        ANSWER.setFont(new Font("Tahoma", Font.BOLD, 14));
//        this.add(ANSWER);

        ANSWER_TF = new JTextField("Enter answer....");
//        ANSWER_TF.setHorizontalAlignment(JTextField.CENTER);
        ANSWER_TF.setBounds(48,265,350,25);
        ANSWER_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        ANSWER_TF.setForeground(Color.GRAY);
        this.add(ANSWER_TF);
        
        ImageIcon ADD_II = new ImageIcon("Skins/C_buttons/"
                + "new-account-add.png");
        ADD = new JButton(ADD_II);
        ADD.setBounds(0,340,600,60);
//        ADD.setOpaque(false);
        ADD.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        ADD.setContentAreaFilled(false);
        ADD.setFocusable(false);
        ADD.setBackground(Color.BLUE);
        ADD.setBorderPainted(false);
        this.add(ADD);
        
        ImageIcon CANCEL_II = new ImageIcon("Skins/C_buttons/"
                + "CANCEL_PRODUCT_1_BUTTON.png");
        CANCEL = new JButton(CANCEL_II);
        CANCEL.setBounds(380,220,100,30);
        CANCEL.setOpaque(false);
        CANCEL.setCursor(new Cursor(Cursor.HAND_CURSOR));
        CANCEL.setContentAreaFilled(false);
        CANCEL.setFocusable(false);
        CANCEL.setBorderPainted(false);
//        this.add(CANCEL);
        
        ImageIcon EXIT_II = new ImageIcon("Skins/C_buttons/second-frame.png");
        EXIT = new JButton(EXIT_II);
        EXIT.setBounds(560,0,40,40);
        EXIT.setOpaque(false);
        EXIT.setCursor(new Cursor(Cursor.HAND_CURSOR));
        EXIT.setContentAreaFilled(false);
        EXIT.setFocusable(false);
        EXIT.setBorderPainted(false);
        this.add(EXIT);

        ImageIcon II = new ImageIcon("Skins/C_background/accounts_list_bg.png");
        JLabel BG = new JLabel(II);
        BG.setBounds(0,0,600,400);
        this.add(BG);
    }
    void Listener(){
//        ANSWER_TF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                ANSWER_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                ANSWER_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//            }
//        });        
//        PASSWORD_CONFIRM_PF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                PASSWORD_CONFIRM_PF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                PASSWORD_CONFIRM_PF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//            }
//        });        
//        PASSWORD_PF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                PASSWORD_PF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                PASSWORD_PF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
//            }
//        });        
//        USERNAME_TF.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                USERNAME_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                USERNAME_TF.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
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
        ANSWER_TF.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            if(ANSWER_TF.getText().equals("Enter answer....")){
            ANSWER_TF.setText(null);
            }else{
            ANSWER_TF.setText(ANSWER_TF.getText());
            }
            ANSWER_TF.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
            ANSWER_TF.setForeground(Color.GRAY);
            if(ANSWER_TF.getText().isEmpty()){
            ANSWER_TF.setText("Enter answer....");
            }else{
            ANSWER_TF.setText(ANSWER_TF.getText());
            ANSWER_TF.setForeground(Color.BLACK);
            }
            }
        });
        PASSWORD_CONFIRM_PF.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            if(PASSWORD_CONFIRM_PF.getText().equals("Confirm password....")){
            PASSWORD_CONFIRM_PF.setText(null);
            PASSWORD_CONFIRM_PF.setEchoChar('\u2022');
            }else{
            PASSWORD_CONFIRM_PF.setText(PASSWORD_CONFIRM_PF.getText());
            PASSWORD_CONFIRM_PF.setEchoChar('\u2022');
            }
            PASSWORD_CONFIRM_PF.setForeground(Color.BLACK);
            setForeground_checker();
            }

            @Override
            public void focusLost(FocusEvent e) {
            PASSWORD_CONFIRM_PF.setForeground(Color.GRAY);
            if(PASSWORD_CONFIRM_PF.getText().isEmpty()){
            PASSWORD_CONFIRM_PF.setText("Confirm password....");
            PASSWORD_CONFIRM_PF.setEchoChar((char) 0);
            PASSWORD_CONFIRM_PF.setForeground(Color.GRAY);
            }else{
            PASSWORD_CONFIRM_PF.setText(PASSWORD_CONFIRM_PF.getText());
            PASSWORD_CONFIRM_PF.setForeground(Color.BLACK);
            }
            setForeground_checker();
            }
        });
        PASSWORD_PF.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            if(PASSWORD_PF.getText().equals("Enter password....")){
            PASSWORD_PF.setText(null);
            PASSWORD_PF.setEchoChar('\u2022');
            }else{
            PASSWORD_PF.setText(PASSWORD_PF.getText());
            PASSWORD_PF.setEchoChar('\u2022');
            }
            PASSWORD_PF.setForeground(Color.BLACK);
            setForeground_checker();
            }

            @Override
            public void focusLost(FocusEvent e) {
            PASSWORD_PF.setForeground(Color.GRAY);
            if(PASSWORD_PF.getText().isEmpty()){
            PASSWORD_PF.setText("Enter password....");
            PASSWORD_PF.setEchoChar((char) 0);
            PASSWORD_PF.setForeground(Color.GRAY);
            }else{
            PASSWORD_PF.setText(PASSWORD_PF.getText());
            PASSWORD_PF.setForeground(Color.BLACK);
            }
            setForeground_checker();
            }
        });
        
        PASSWORD_PF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                setForeground_checker();
            }
            @Override
            public void keyPressed(KeyEvent e) {
                setForeground_checker();
            }
            @Override
            public void keyReleased(KeyEvent e) {
                setForeground_checker();
            }
        });
        PASSWORD_CONFIRM_PF.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                setForeground_checker();
            }
            @Override
            public void keyPressed(KeyEvent e) {
                setForeground_checker();
            }
            @Override
            public void keyReleased(KeyEvent e) {
                setForeground_checker();
          }
        });
        
        USERNAME_TF.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            if(USERNAME_TF.getText().equals("Enter username....")){
            USERNAME_TF.setText(null);
            }else{
            USERNAME_TF.setText(USERNAME_TF.getText());
            }
            USERNAME_TF.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
            USERNAME_TF.setForeground(Color.GRAY);
            if(USERNAME_TF.getText().isEmpty()){
            USERNAME_TF.setText("Enter username....");
            }else{
            USERNAME_TF.setText(USERNAME_TF.getText());
            USERNAME_TF.setForeground(Color.BLACK);
            }
            }
        });
        ADD.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(USERNAME_TF.getText().equals("Enter username....") ||
                   PASSWORD_PF.getText().equals("Enter password....")){
                    JOptionPane.showMessageDialog(null, "Please enter details");
                }else{
                if(dh.viewUsername().contains(USERNAME_TF.getText())){
                    JOptionPane.showMessageDialog(null,"Username "
                    +USERNAME_TF.getText()+" already exists.");
                }else{
                    if(PASSWORD_PF.getText().equals(PASSWORD_CONFIRM_PF.getText())){
                    SQLiteDB_action sqlite = SQLiteDB_action.getInstance();
                    sqlite.newAccount(USER_TYPE_CB.getSelectedItem().toString(),
                                        USERNAME_TF.getText(),
                                        PASSWORD_CONFIRM_PF.getText(),
                                        SECURITY_QUESTION_CB.getSelectedItem().toString(),
                                        ANSWER_TF.getText());
                    
                    dh.add_Account(dh.getAccount_max(),USER_TYPE_CB.getSelectedItem().toString(),
                                        USERNAME_TF.getText(),
                                        PASSWORD_CONFIRM_PF.getText(),
                                        SECURITY_QUESTION_CB.getSelectedItem().toString(),
                                        ANSWER_TF.getText());
                    
                    View_Accounts c = View_Accounts.getInstance();
                    c.addDataAccount_list();
                    
                USERNAME_TF.setText("Enter username....");
                USERNAME_TF.setForeground(Color.GRAY);

                PASSWORD_PF.setText("Enter password....");
                PASSWORD_PF.setEchoChar((char) 0);  
                PASSWORD_PF.setForeground(Color.GRAY);

                PASSWORD_CONFIRM_PF.setText("Confirm password....");
                PASSWORD_CONFIRM_PF.setEchoChar((char) 0);
                PASSWORD_CONFIRM_PF.setForeground(Color.GRAY);

                ANSWER_TF.setText("Enter answer....");
                ANSWER_TF.setForeground(Color.GRAY);
                
                CHECKER_JL.setIcon(null); 
                View_Accounts va = new View_Accounts();
                va.addDataAccount_list();

                MainFrame mf = MainFrame.getInstance();
                mf.setFront(true);
                
                SQLiteDB sdb = SQLiteDB.getInstance();
                sdb.retrieveUsername();
                    }else{
                        JOptionPane.showMessageDialog(null,"Wrong password");
                    }
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
                ImageIcon ADD_II = new ImageIcon("Skins/C_buttons/"
                + "new-account-add-2.png");
                ADD.setIcon(ADD_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon ADD_II = new ImageIcon("Skins/C_buttons/"
                + "new-account-add.png");
                ADD.setIcon(ADD_II);
            }
        });
        EXIT.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                USERNAME_TF.setText("Enter username....");
                USERNAME_TF.setForeground(Color.GRAY);

                PASSWORD_PF.setText("Enter password....");
                PASSWORD_PF.setEchoChar((char) 0);  
                PASSWORD_PF.setForeground(Color.GRAY);

                PASSWORD_CONFIRM_PF.setText("Confirm password....");
                PASSWORD_CONFIRM_PF.setEchoChar((char) 0);
                PASSWORD_CONFIRM_PF.setForeground(Color.GRAY);

                ANSWER_TF.setText("Enter answer....");
                ANSWER_TF.setForeground(Color.GRAY);
                
                CHECKER_JL.setIcon(null);
                
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
                EXIT.setIcon(CANCEL_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon CANCEL_II = new ImageIcon("Skins/C_buttons/"
                        + "second-frame.png");
                EXIT.setIcon(CANCEL_II);
            }
        });
    }
    void setForeground_checker(){
                if(PASSWORD_PF.getText().equals(PASSWORD_CONFIRM_PF.getText())){
                    PASSWORD_PF.setForeground(Color.GREEN);
                }else{
                    PASSWORD_PF.setForeground(Color.RED);
                }
                if(PASSWORD_PF.getText().equals("Enter password....")){
                    PASSWORD_PF.setForeground(Color.BLACK);
                }
                if(PASSWORD_PF.getText().equals(PASSWORD_CONFIRM_PF.getText())){
                    PASSWORD_CONFIRM_PF.setForeground(Color.GREEN);
                }else{
                    PASSWORD_CONFIRM_PF.setForeground(Color.RED);
                }
                if(PASSWORD_CONFIRM_PF.getText().equals("Confirm password....")){
                    PASSWORD_PF.setForeground(Color.BLACK);
                }
    }
    public static void main(String [] args){
        new NewAccount().setVisible(true);
    }
}