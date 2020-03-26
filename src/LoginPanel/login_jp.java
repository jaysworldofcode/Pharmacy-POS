package LoginPanel;

import DataHolder.DataHolder;
import Frames.Expired_List;
import Frames.Forgot_password;
import Frames.Settings;
import Frames.out_of_stock_list;
import Panels.List_Panel;
import Panels.Logs_Panel;
import Panels.Sale_Panel;
import Panels.SecondMainPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import msinventorysystem.MainFrame;

public class login_jp extends JPanel{
    //Variable to center the screen
    Dimension screenSize,frameSize;
    int x,y;
    //Login components
    JPanel login_jp;
    JLabel icon = new JLabel(new ImageIcon("Skins\\C_extras\\icon.png"));
    JComboBox username_tf;
    JComboBox user_jcb = new JComboBox();
    JCheckBox CHECKER = new JCheckBox("");
//    JTextField text = (JTextField)user_jcb.getEditor().getEditorComponent();
    JPasswordField password_tf;
    JLabel username_jl,password_jl,setPanel_LOGIN_BG;
    JButton forgot_password,login;
    //Set classes
    DataHolder dh = DataHolder.getInstance();
    
    public static Vector vector = new Vector();
    private static login_jp instance = null;
    
    public static login_jp getInstance() {
      if(instance == null) {
         instance = new login_jp();
      }
      return instance;
    }
    
    public login_jp(){
        Organizer();
    }
    void Organizer(){
        setMainLoginPanel();
        setLogin();
        setVectorPlaneMemberID();
        listener();
    }
    void setMainLoginPanel(){
        this.setBounds(0,30,1100,670);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
    }
    public void setStock_icon(){
        out_of_stock_list o = out_of_stock_list.getInstance();
        o.setIcon();
    }
    public void clearUser_list(){
        username_tf.removeAllItems();
    }
    public void addUser_data(String username){
        username_tf.addItem(username);
    }
    void setLogin(){
        login_jp = new JPanel();
        login_jp.setOpaque(false);
        login_jp.setBounds(620,80,400,450);
        login_jp.setLayout(null);
//        login_jp.setBorder(BorderFactory.createTitledBorder(null, "Login", TitledBorder.LEFT,
//                TitledBorder.TOP, new Font("Segoe UI",Font.BOLD,25), Color.BLACK));
        this.add(login_jp);
        
        try{
            BufferedImage bimg = ImageIO.read(new File("Skins\\C_extras\\icon.png"));
            icon.setBounds(0,100,bimg.getWidth(),bimg.getHeight());
            this.add(icon);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Icon error: "+ex);
        }
        
        username_jl = new JLabel("Username");
        username_jl.setBounds(10,25,70,30);
        username_jl.setFont(new Font("Tahoma", Font.PLAIN, 14));
//        login_jp.add(username_jl);

        username_tf = new JComboBox();
        username_tf.setBounds(110,157,220,30);
        username_tf .setOpaque(false);
        username_tf .setEditable(true);
        username_tf.setUI(new BasicComboBoxUI());
        username_tf.setBorder(BorderFactory.createEmptyBorder());
        username_tf.setBackground(new Color(5, 5, 5, 5));
        username_tf.setFocusable(false);
        username_tf.setFont(new Font("Tahoma", Font.PLAIN, 15));
        login_jp.add(username_tf);

        password_jl = new JLabel("Password");
        password_jl.setBounds(10,65,70,30);
        password_jl.setFont(new Font("Tahoma", Font.PLAIN, 20));
//        login_jp.add(password_jl);
        
        password_tf = new JPasswordField("Password....");
        password_tf.setBounds(110,213,190,30);
        password_tf.setForeground(Color.GRAY);
        password_tf.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        password_tf.setFont(new Font("Tahoma", Font.PLAIN, 15));
        password_tf.setOpaque(false);
        password_tf.setHorizontalAlignment(JTextField.CENTER);
//        password_tf.setEchoChar((char) 0);
        login_jp.add(password_tf);
        
        CHECKER.setBounds(310,213,500,30);
        CHECKER.setOpaque(false);
        login_jp.add(CHECKER);
        
        login = new JButton(new ImageIcon("Skins/C_buttons/LOGIN_1_BUTTON.png"));
        login.setBounds(53,262,297,35);
        login.setOpaque(false);
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.setContentAreaFilled(false);
        login.setFocusable(false);
        login.setBorderPainted(false);
        login_jp.add(login);
        
        forgot_password = new JButton("Forgot Password?");
        forgot_password.setBounds(53,305,200,30);
        forgot_password.setOpaque(false);
        forgot_password.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgot_password.setContentAreaFilled(false);
        forgot_password.setFocusable(false);
        forgot_password.setBorderPainted(false);
        login_jp.add(forgot_password);
//        username = new JTextField("Username");
//        username.setBounds()
        
        JLabel login_user = new JLabel(new ImageIcon("Skins/C_background/login_info_2.png"));
        login_user.setBounds(0,0,400,450);
        login_jp.add(login_user);
        
        ImageIcon LOGIN_BG_II = new ImageIcon("Skins/C_background/LOGIN_BG.png");
        JLabel BG = new JLabel(LOGIN_BG_II);
        BG.setBounds(0,0,1100,670);
        this.add(BG);
    }
	public void setVectorPlaneMemberID()
	{
//        for(int x=0;x<dh.getAccount_size();x++){
//            user_jcb.addItem(dh.getUsername(x));
//        }
            int set = user_jcb.getSelectedIndex();
			for(int a=0;a<dh.getAccount_size();a++)
		{
			vector.add(dh.getUsername(a));
		}
        }
    void listener(){
        password_tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                if(dh.setIndex_accounts(username_tf.getSelectedItem().toString(),
                        password_tf.getText())==true){
                String x = String.valueOf(username_tf.getSelectedItem().toString());
                dh.setUserLogin(x);
                System.out.println("Welcome "+x);
                
                password_tf.setText(null);
                
                setVisible(false);
                List_Panel lp = List_Panel.getInstance();
                lp.setButtons(dh.getUsert_type());
                
                Sale_Panel sp = Sale_Panel.getInstance();
                sp.setVisible(true);
                List_Panel lpxd = List_Panel.getInstance();
                lpxd.setVisible(false);
                Logs_Panel lpx = Logs_Panel.getInstance();
                lpx.setVisible(false);
                
                if(dh.getUsert_type().equalsIgnoreCase("admin")){
                    Settings s = Settings.getInstance();
                    s.setButtons(true);
                }else{
                    Settings s = Settings.getInstance();
                    s.setButtons(false);
                }
                
                SecondMainPanel smp = SecondMainPanel.getInstance();
                smp.setButtons(dh.getUsert_type());
                smp.setVisible(true);                
                
                MainFrame mf = MainFrame.getInstance();
                mf.setUser("Welcome "+dh.getUser());
                mf.setShorcuts(true);
                //Scan for expired products
                if(dh.getTotal_expiredProduct() == 0){
                }else{
                    String setMessage = "";
                    for(int xx=0;xx<dh.setSize_expired();xx++){
                        setMessage = setMessage+dh.getExpired_product(xx)+"\n"+
                                "expired today";
                    }
                    JOptionPane.showMessageDialog(null,setMessage);
                  }
                setStock_icon();
                mf.setTop_settings(dh.getUsert_type());
                }else{
                    password_tf.setText(null);
                    JOptionPane.showMessageDialog(null,"Invalid account. Please try again.");
                }
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        CHECKER.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(CHECKER.isSelected()){
                    password_tf.setEchoChar((char) 0);
                }else{
//                    if(password_tf.getText().equals("Password....")){
//                    }else{
                        password_tf.setEchoChar('\u2022');
//                    }
                }
            }
        });
        password_tf.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            if(password_tf.getText().equals("Password....")){
            password_tf.setText(null);
//            password_tf.setEchoChar('\u2022');
            }else{
            password_tf.setText(password_tf.getText());
//            password_tf.setEchoChar('\u2022');
            }
            password_tf.setForeground(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
            password_tf.setForeground(Color.GRAY);
            if(password_tf.getText().isEmpty()){
            password_tf.setText("Password....");
            password_tf.setEchoChar((char) 0);
            password_tf.setForeground(Color.GRAY);
            }else{
            password_tf.setText(password_tf.getText());
            password_tf.setForeground(Color.BLACK);
            }
            }
        });
        forgot_password.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame mf = MainFrame.getInstance();
//                mf.setFront(false);
                Forgot_password fp = new Forgot_password();
//                fp.centreWindow(mf);
//                fp.setVisible(true);
                
                Expired_List exp = Expired_List.getInstance();
//                exp.emptyModel();
                fp.centreWindow(mf);
                fp.setVisible(true);
                mf.setFront(false);
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                forgot_password.setForeground(Color.BLUE);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                forgot_password.setForeground(Color.BLACK);
            }
        });
        login.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(dh.setIndex_accounts(username_tf.getSelectedItem().toString(),
                        password_tf.getText())==true){
                String x = String.valueOf(username_tf.getSelectedItem().toString());
                dh.setUserLogin(x);
                System.out.println("Welcome "+x);
                
                password_tf.setText(null);
                
                setVisible(false);
                List_Panel lp = List_Panel.getInstance();
                lp.setButtons(dh.getUsert_type());
                
                Sale_Panel sp = Sale_Panel.getInstance();
                sp.setVisible(true);
                List_Panel lpxd = List_Panel.getInstance();
                lpxd.setVisible(false);
                Logs_Panel lpx = Logs_Panel.getInstance();
                lpx.setVisible(false);
                
//                if(dh.getUsert_type().equalsIgnoreCase("admin")){
//                    Settings s = Settings.getInstance();
//                    s.setButtons(true);
//                }else{
//                    Settings s = Settings.getInstance();
//                    s.setButtons(false);
//                }
                
                SecondMainPanel smp = SecondMainPanel.getInstance();
                smp.setButtons(dh.getUsert_type());
                smp.setVisible(true);                
                
                MainFrame mf = MainFrame.getInstance();
                mf.setUser("Welcome "+dh.getUser());
                mf.setShorcuts(true);
                //Scan for expired products
                Expired_List exp = Expired_List.getInstance();
                exp.emptyModel();
                if(dh.getTotal_expiredProduct() == 0){
                }else{
                    for(int indexExpire=0;indexExpire<dh.setSize_expired();indexExpire++){
                        exp.addData(dh.getExpired_product(indexExpire));
                    }
                        exp.centreWindow(mf);
                        exp.setVisible(true);
                        exp.centreWindow(mf);
                        exp.setVisible(true);
                        mf.setFront(false);
                  }
                setStock_icon();
                mf.setTop_settings(dh.getUsert_type());
                }else{
                    password_tf.setText(null);
                    JOptionPane.showMessageDialog(null,"Invalid account. Please try again.");
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
                ImageIcon login_ii = new ImageIcon("Skins/C_buttons/LOGIN_2_BUTTON.png");
                login.setIcon(login_ii);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon login_ii = new ImageIcon("Skins/C_buttons/LOGIN_1_BUTTON.png");
                login.setIcon(login_ii);
            }
        });
    }
}
