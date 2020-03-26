package Panels;

import DataHolder.DataHolder;
import Frames.Settings;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class SecondMainPanel extends JPanel{
    ImageIcon SALE_II;
    JButton LIST_JB,LOGS_JB,SETTINGS,SALE_JB;
    JLabel TITLE,TIME,DAY,MONTH_DAY;
    
    static String panel_viewer = "pos";
    
    Font SETFONT = new Font("SansSerif", Font.BOLD, 17);
    
    static String setChoose = "sale";
    Timer timer;
//    static boolean settings_Checker = false;
    //SET CLASSES
    DataHolder dh = DataHolder.getInstance();
    private static SecondMainPanel instance = null;    
    
      public static SecondMainPanel getInstance() {
      if(instance == null) {
         instance = new SecondMainPanel();
      }
      return instance;
    }
        SecondMainPanel(){
            this.setBounds(0,0,1100,600);
            this.setLayout(null);
            this.setVisible(false);
//            this.setBackground(Color.WHITE);
            this.setBackground(new Color(66,161, 255));
            Organizer();
        }
    public void setButtons_icon(String set){
        if(set.equals("pos")){
                ImageIcon LOGS_II = new ImageIcon("Skins/C_buttons/LOGS_1_BUTTON.png");
                ImageIcon SALE_II = new ImageIcon("Skins/C_buttons/SALE_2_BUTTON.png");
                ImageIcon LIST_II = new ImageIcon("Skins/C_buttons/LIST_1_BUTTON.png");
                LOGS_JB.setIcon(LOGS_II);
                SALE_JB.setIcon(SALE_II);
                LIST_JB.setIcon(LIST_II);
        }else if(set.equals("products_list")){
                ImageIcon LOGS_II = new ImageIcon("Skins/C_buttons/LOGS_1_BUTTON.png");
                ImageIcon SALE_II = new ImageIcon("Skins/C_buttons/SALE_1_BUTTON.png");
                ImageIcon LIST_II = new ImageIcon("Skins/C_buttons/LIST_2_BUTTON.png");
                LOGS_JB.setIcon(LOGS_II);
                SALE_JB.setIcon(SALE_II);
                LIST_JB.setIcon(LIST_II);
        }else{
                ImageIcon LOGS_II = new ImageIcon("Skins/C_buttons/LOGS_2_BUTTON.png");
                ImageIcon SALE_II = new ImageIcon("Skins/C_buttons/SALE_1_BUTTON.png");
                ImageIcon LIST_II = new ImageIcon("Skins/C_buttons/LIST_1_BUTTON.png");
                LOGS_JB.setIcon(LOGS_II);
                SALE_JB.setIcon(SALE_II);
                LIST_JB.setIcon(LIST_II);
        }
            panel_viewer = set;
    }
    void Organizer(){
        Components(); 
        timerSet();
        Listener();
    }
    void timerSet(){
           timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            //Set time
            DateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Date time = new Date();
            TIME.setText(timeFormat.format(time).toString());
            //Set current date
            DateFormat dateFormat = new SimpleDateFormat("MMMMM  dd");
            Date date = new Date();
            MONTH_DAY.setText(dateFormat.format(date));
            //Set current day
            DateFormat dayFormat = new SimpleDateFormat("EEEE");
            Date day = new Date();
            DAY.setText(dayFormat.format(day));
        }
    });
            timer.start();
    }
    void setEnabledIfSubFrameExists(){
        LIST_JB.setEnabled(false);
        LOGS_JB.setEnabled(false);
        SALE_JB.setEnabled(false);
    }
    void Components(){
//        ImageIcon TITLE_II = new ImageIcon("Skins/C_background/"
//                + "SECONDPANEL_TITLE.png");
//        TITLE = new JLabel(TITLE_II);
//        TITLE.setBounds(40,40,400,100);
//        this.add(TITLE);
        
        ImageIcon SALE_II = new ImageIcon("Skins/C_buttons/SALE_2_BUTTON.png");
        SALE_JB = new JButton(SALE_II);
        SALE_JB.setBounds(0,30,150,70);
        SALE_JB.setOpaque(false);
        SALE_JB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        SALE_JB.setContentAreaFilled(false);
        SALE_JB.setFocusable(false);
        SALE_JB.setBorderPainted(false);
        this.add(SALE_JB);

        ImageIcon LIST_II = new ImageIcon("Skins/C_buttons/LIST_1_BUTTON.png");
        LIST_JB = new JButton(LIST_II);
        LIST_JB.setBounds(150,30,150,70);
        LIST_JB.setOpaque(false);
        LIST_JB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        LIST_JB.setContentAreaFilled(false);
        LIST_JB.setFocusable(false);
        LIST_JB.setBorderPainted(false);
        this.add(LIST_JB);
        
        ImageIcon LOGST_II = new ImageIcon("Skins/C_buttons/LOGS_1_BUTTON.png");
        LOGS_JB = new JButton(LOGST_II);
        LOGS_JB.setBounds(300,30,150,70);
        LOGS_JB.setOpaque(false);
        LOGS_JB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        LOGS_JB.setContentAreaFilled(false);
        LOGS_JB.setFocusable(false);
        LOGS_JB.setBorderPainted(false);
        this.add(LOGS_JB);
        
        TIME = new JLabel("8:00");
        TIME.setBounds(820,40,150,50);
        TIME.setFont(new Font("Segoe UI", Font.PLAIN, 50));
        TIME.setForeground(Color.WHITE);
        TIME.setOpaque(false);
        this.add(TIME);
        
        DAY = new JLabel("Day");
        DAY.setBounds(940,40,150,30);
        DAY.setFont(new Font("Segoe UI", Font.PLAIN, 25));
        DAY.setForeground(Color.WHITE);
        this.add(DAY);
        
        MONTH_DAY = new JLabel("March 2");
        MONTH_DAY.setBounds(940,65,150,30);
        MONTH_DAY.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        MONTH_DAY.setForeground(Color.WHITE);
        this.add(MONTH_DAY);

        ImageIcon SETTINGS_II = new ImageIcon("Skins/C_buttons/SETTINGS_1.png");
        SETTINGS = new JButton(SETTINGS_II);
        SETTINGS.setBounds(950,40,100,30);
        SETTINGS.setOpaque(false);
        SETTINGS.setCursor(new Cursor(Cursor.HAND_CURSOR));
        SETTINGS.setContentAreaFilled(false);
        SETTINGS.setFocusable(false);
        SETTINGS.setBorderPainted(false);
//        this.add(SETTINGS);
        
//        ImageIcon BACKGROUND_II = new ImageIcon("Skins/C_background/SECOND_PANEL_BG.png");
//        JLabel BG = new JLabel(BACKGROUND_II);
//        BG.setBounds(0,0,900,600);
//        this.add(BG);
        
    }
    public void setButtons(String userType){
        String x = dh.getUsert_type();
//        if(userType.equals("user")){
//            LOGS_JB.setVisible(false);
//        }else{
//            LOGS_JB.setVisible(true);
//        }
    }
    public void Listener(){
       SETTINGS.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//             if(settings_Checker == true){
//             Settings s = Settings.getInstance();
//             s.setVisible(false);
//                settings_Checker = false;
//             }else{
//             MainFrame mf = MainFrame.getInstance();
//             Settings s = Settings.getInstance();
//             s.setVisible(true);
//             s.centreWindow(mf);
//                 settings_Checker = true;
//             }
//            int option = JOptionPane.showConfirmDialog(null, 
//            "Are you sure to log-out?", null, JOptionPane.YES_NO_OPTION);
//
//            if (option == 0) { //The ISSUE is here
//                login_jp lp = login_jp.getInstance();
//                lp.setVisible(true);
//                setVisible(false);
//                MainFrame mf = MainFrame.getInstance();
//                mf.setMenu_visible(false);
//            } else {
//               System.out.print("no");
//            }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon SETTINGS_II = new ImageIcon("Skins/C_buttons/SETTINGS_2.png");
                SETTINGS.setIcon(SETTINGS_II);
                Settings s = Settings.getInstance();
                s.setVisible(true);
//                ImageIcon LOG_OUT_II = new ImageIcon("Skins/C_buttons/LOG_OUT_2_BUTTON.png");
//                LOG_OUT_JB.setIcon(LOG_OUT_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
//                ImageIcon LOG_OUT_II = new ImageIcon("Skins/C_buttons/LOG_OUT_1_BUTTON.png");
//                LOG_OUT_JB.setIcon(LOG_OUT_II);
                ImageIcon SETTINGS_II = new ImageIcon("Skins/C_buttons/SETTINGS_1.png");
                SETTINGS.setIcon(SETTINGS_II);
                Settings s = Settings.getInstance();
                s.setVisible(false);
            }
        });
        SALE_JB.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sale_Panel sp = Sale_Panel.getInstance();
                sp.setVisible(true);
                List_Panel lp = List_Panel.getInstance();
                lp.setVisible(false);
                Logs_Panel lpx = Logs_Panel.getInstance();
                lpx.setVisible(false);
//                setVisible(false);
                setChoose = "sale";
//                if(setChoose.equals("sale")){
                ImageIcon LOGS_II = new ImageIcon("Skins/C_buttons/LOGS_1_BUTTON.png");
                ImageIcon SALE_II = new ImageIcon("Skins/C_buttons/SALE_2_BUTTON.png");
                ImageIcon LIST_II = new ImageIcon("Skins/C_buttons/LIST_1_BUTTON.png");
                LOGS_JB.setIcon(LOGS_II);
                SALE_JB.setIcon(SALE_II);
                LIST_JB.setIcon(LIST_II);
                dh.setFunction_use("pos");
//                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(setChoose.equals("sale")){
                ImageIcon SALE_II = new ImageIcon("Skins/C_buttons/SALE_3_BUTTON.png");
                SALE_JB.setIcon(SALE_II);
                }else{
                ImageIcon SALE_II = new ImageIcon("Skins/C_buttons/SALE_4_BUTTON.png");
                SALE_JB.setIcon(SALE_II);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(setChoose.equals("sale")){
                ImageIcon SALE_II = new ImageIcon("Skins/C_buttons/SALE_2_BUTTON.png");
                SALE_JB.setIcon(SALE_II);
                }else{
                ImageIcon SALE_II = new ImageIcon("Skins/C_buttons/SALE_1_BUTTON.png");
                SALE_JB.setIcon(SALE_II);
                }
//                ImageIcon SALE_II = new ImageIcon("Skins/C_buttons/SALE_1_BUTTON.png");
//                SALE_JB.setIcon(SALE_II);
            }
        });
        LOGS_JB.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
//                if(dh.getUsert_type().equals("admin")){
                if(dh.check_if_array_is_empty()==false){
                    JOptionPane.showMessageDialog(null, "Finish all transaction first.");
                }else{
                Sale_Panel sp = Sale_Panel.getInstance();
                sp.setVisible(false);
                List_Panel lp = List_Panel.getInstance();
                lp.setVisible(false);
                Logs_Panel lpx = Logs_Panel.getInstance();
                lpx.setVisible(true);
                lpx.setUserPrivelege(dh.getUsert_type());
//                setVisible(false);
                setChoose = "logs";
                ImageIcon LOGS_II = new ImageIcon("Skins/C_buttons/LOGS_2_BUTTON.png");
                ImageIcon SALE_II = new ImageIcon("Skins/C_buttons/SALE_1_BUTTON.png");
                ImageIcon LIST_II = new ImageIcon("Skins/C_buttons/LIST_1_BUTTON.png");
                LOGS_JB.setIcon(LOGS_II);
                SALE_JB.setIcon(SALE_II);
                LIST_JB.setIcon(LIST_II);
                dh.setFunction_use("logs");
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
                if(setChoose.equals("logs")){
                ImageIcon LOGST_II = new ImageIcon("Skins/C_buttons/LOGS_3_BUTTON.png");
                LOGS_JB.setIcon(LOGST_II);
                }else{
                ImageIcon LOGST_II = new ImageIcon("Skins/C_buttons/LOGS_4_BUTTON.png");
                LOGS_JB.setIcon(LOGST_II);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(setChoose.equals("logs")){
                ImageIcon LOGST_II = new ImageIcon("Skins/C_buttons/LOGS_2_BUTTON.png");
                LOGS_JB.setIcon(LOGST_II);
                }else{
                ImageIcon LOGST_II = new ImageIcon("Skins/C_buttons/LOGS_1_BUTTON.png");
                LOGS_JB.setIcon(LOGST_II);
                }
//                ImageIcon LOGST_II = new ImageIcon("Skins/C_buttons/LOGS_1_BUTTON.png");
//                LOGS_JB.setIcon(LOGST_II);
            }
        });
        LIST_JB.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(dh.check_if_array_is_empty()==false){
                    JOptionPane.showMessageDialog(null, "Finish all transaction first.");
                }else{
                    Sale_Panel sp = Sale_Panel.getInstance();
                    sp.setVisible(false);
                    List_Panel lp = List_Panel.getInstance();
                    lp.setVisible(true);
                    Logs_Panel lpx = Logs_Panel.getInstance();
                    lpx.setVisible(false);
                setChoose = "list";
                ImageIcon LOGS_II = new ImageIcon("Skins/C_buttons/LOGS_1_BUTTON.png");
                ImageIcon SALE_II = new ImageIcon("Skins/C_buttons/SALE_1_BUTTON.png");
                ImageIcon LIST_II = new ImageIcon("Skins/C_buttons/LIST_2_BUTTON.png");
                LOGS_JB.setIcon(LOGS_II);
                SALE_JB.setIcon(SALE_II);
                LIST_JB.setIcon(LIST_II);
                dh.setFunction_use("products_list");
                }
//                setVisible(false);
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(setChoose.equals("list")){
                ImageIcon LIST_II = new ImageIcon("Skins/C_buttons/LIST_3_BUTTON.png");
                LIST_JB.setIcon(LIST_II);
                }else{
                ImageIcon LIST_II = new ImageIcon("Skins/C_buttons/LIST_4_BUTTON.png");
                LIST_JB.setIcon(LIST_II);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(setChoose.equals("list")){
                ImageIcon LIST_II = new ImageIcon("Skins/C_buttons/LIST_2_BUTTON.png");
                LIST_JB.setIcon(LIST_II);
                }else{
                ImageIcon LIST_II = new ImageIcon("Skins/C_buttons/LIST_1_BUTTON.png");
                LIST_JB.setIcon(LIST_II);
                }
            }
        });
    }
}