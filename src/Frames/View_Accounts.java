package Frames;

import DataBase.SQLiteDB;
import DataBase.SQLiteDB_action;
import DataHolder.DataHolder;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Window;
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

public class View_Accounts extends JFrame{
    
    JLabel ID_JL,USERNAME_JL,PASSWORD_JL,SECURITY_QUESTION_JL,ANSWER_JL,USERTYPE_JL;
    JPanel accountInfo;
    DefaultListModel model;
    JList accountList;
    JScrollPane accountPane;
    JButton REMOVE,CLOSE;
    static String username="";
    
    DataHolder dh = DataHolder.getInstance();
    private static View_Accounts instance = null;    
    static int lastX, lastY;
    public static View_Accounts getInstance() {
      if(instance == null) {
         instance = new View_Accounts();
      }
      return instance;
    }
    
    View_Accounts(){
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

        this.setLayout(null); 
        this.setSize(550,330);
        this.setBackground(Color.yellow);
        this.setResizable(false);
        this.setUndecorated(true);
        this.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        this.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
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
        addDataAccount_list();
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

        model = new DefaultListModel();
        accountList = new JList(model);
        accountPane = new JScrollPane(accountList);
        accountPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        accountPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        JScrollBar xb_xx = accountPane.getVerticalScrollBar();
        accountPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        xb_xx.setUI(new Skins.LookAndFeel.MyScrollbarUI());
        
        accountInfo = new JPanel();
        accountInfo.setBounds(215,40,300,200);
        accountInfo.setOpaque(false);
        accountInfo.setBorder(BorderFactory.createTitledBorder("Account info"));
        accountInfo.setLayout(null);
        this.add(accountInfo);
        
        USERNAME_JL = new JLabel("Username: ----");
        USERNAME_JL.setBounds(10,30,300,25);
        accountInfo.add(USERNAME_JL);
        
        PASSWORD_JL = new JLabel("Password: ----");
        PASSWORD_JL.setBounds(10,60,300,25);
        accountInfo.add(PASSWORD_JL);
        
        SECURITY_QUESTION_JL = new JLabel("Security Question: ----");
        SECURITY_QUESTION_JL.setBounds(10,90,300,25);
        accountInfo.add(SECURITY_QUESTION_JL);
        
        ANSWER_JL = new JLabel("Answer: ----");
        ANSWER_JL.setBounds(10,120,300,25);
        accountInfo.add(ANSWER_JL);
        
        USERTYPE_JL = new JLabel("User type: ----");
        USERTYPE_JL.setBounds(10,150,300,25);
        accountInfo.add(USERTYPE_JL);
        
        accountInfo.add(SECURITY_QUESTION_JL);
        accountPane.setBounds(10,40,200,260);
        this.add(accountPane);
        
//        ImageIcon REMOVE_II = new ImageIcon("Skins/C_buttons/"
//                + "REMOVE_LIST_1_BUTTON.png");
        REMOVE = new JButton("Remove");
        REMOVE.setBounds(280,260,100,50);
//        REMOVE.setBounds(400,260,100,50);
        this.add(REMOVE);

//        ImageIcon CLOSE_II = new ImageIcon("Skins/C_buttons/"
//                + "EXIT.png");
        CLOSE = new JButton("Close");
//        CLOSE.setOpaque(false);
//        CLOSE.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        CLOSE.setContentAreaFilled(false);
//        CLOSE.setFocusable(false);
//        CLOSE.setBorderPainted(false);
        CLOSE.setBounds(400,260,100,50);
        this.add(CLOSE);
        
        ImageIcon II = new ImageIcon("Skins/C_background/VIEW_ACCOUNTS_BG.png");
        JLabel BG = new JLabel(II);
        BG.setBounds(0,0,550,330);
        this.add(BG);
    }
    void addDataAccount_list(){
        
        
        ((DefaultListModel)accountList.getModel()).clear();
        for(int x=0;x<dh.getAccount_size();x++){
            model.addElement(dh.getUsername(x));
        }
        System.out.println("Test username list: "+dh.testUsername());
    }
    void setText(String username,String password,String security_question,
            String answer,String usertype){
        USERNAME_JL.setText("Username: "+username);
        PASSWORD_JL.setText("Password: "+password);
        SECURITY_QUESTION_JL.setText("Security Question: "+security_question);
        ANSWER_JL.setText("Answer: "+answer);
        USERTYPE_JL.setText("User type: "+usertype);
    }
    void Listener(){
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
        CLOSE.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(true);
                setVisible(false);
                USERNAME_JL.setText("Username: ----");
                PASSWORD_JL.setText("Password: ----");
                SECURITY_QUESTION_JL.setText("Security Question: ----");
                ANSWER_JL.setText("Answer: ----");
                USERTYPE_JL.setText("User type: ----");
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
//                ImageIcon CLOSE_II = new ImageIcon("Skins/C_buttons/"
//                        + "EXIT_II.png");
//                CLOSE.setIcon(CLOSE_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
//                ImageIcon CLOSE_II = new ImageIcon("Skins/C_buttons/"
//                        + "EXIT.png");
//                CLOSE.setIcon(CLOSE_II);
            }
        });
        REMOVE.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
       int reply = JOptionPane.showConfirmDialog(null, "Are you sure to delete"
               + "?", "", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
                SQLiteDB_action sqlite = SQLiteDB_action.getInstance();
                sqlite.deleteAccount(accountList.getSelectedValue().toString());
                DataHolder dh = DataHolder.getInstance();
                dh.removeAccount(accountList.getSelectedValue().toString());
                addDataAccount_list();
                SQLiteDB sdb = SQLiteDB.getInstance();
                sdb.retrieveUsername();
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(true);
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
        accountList.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
                dh.setAccount_indexAll(accountList.getSelectedValue().toString());
                username=accountList.getSelectedValue().toString();
                setText(dh.getUsername(dh.getIndex()),dh.getPassword(dh.getIndex()),
                        dh.getSecurityQuestion(dh.getIndex()),dh.getAnswer(dh.getIndex()),
                        dh.getUserType(dh.getIndex()));
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
