package Frames;

import DataBase.SQLiteDB;
import DataBase.SQLiteDB_action;
import DataHolder.DataHolder;
import LoginPanel.login_jp;
import Panels.Admin_mainPanel;
import Panels.List_Panel;
import Panels.Logs_Panel;
import Panels.Sale_Panel;
import Panels.SecondMainPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import msinventorysystem.MainFrame;
import msinventorysystem.ZipCompress;
import msinventorysystem.ZipDecompress;

public class Settings extends JPanel{
    
    JButton NEW_ACCOUNT,ACCOUNT_LIST,CHANGE_PASSWORD,LOG_OUT,DOWNLOAD_DATA,
            UPLOAD_DATA;
    
    Font SETFONT = new Font("SansSerif", Font.BOLD, 15);
        
    private static Settings instance = null;
    
    public static Settings getInstance() {
      if(instance == null) {
         instance = new Settings();
      }
      return instance;
    }        
    Settings(){
        this.setLayout(null);
        this.setBounds(850,70,200,200);
        this.setVisible(false);
//        this.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
//        this.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        Organizer();
    }
    public void centreWindow(Window frame) {
        int x = frame.getX();
        int y = frame.getY();
        this.setBounds(x+460, y+26,200,140);
    }
    void Organizer(){
        Components();
        Listener();
    }
    
    void Components(){
        NEW_ACCOUNT = new JButton("New Account");
        NEW_ACCOUNT.setBounds(0,0,200,30);
        NEW_ACCOUNT.setOpaque(false);
        NEW_ACCOUNT.setCursor(new Cursor(Cursor.HAND_CURSOR));
        NEW_ACCOUNT.setContentAreaFilled(false);
        NEW_ACCOUNT.setBorderPainted(false);
        NEW_ACCOUNT.setFont(SETFONT);
        NEW_ACCOUNT.setForeground(Color.BLACK);
        this.add(NEW_ACCOUNT);
        
        ACCOUNT_LIST = new JButton("Account List");
        ACCOUNT_LIST.setBounds(0,30,200,30);
        ACCOUNT_LIST.setOpaque(false);
        ACCOUNT_LIST.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ACCOUNT_LIST.setContentAreaFilled(false);
        ACCOUNT_LIST.setBorderPainted(false);
        ACCOUNT_LIST.setFont(SETFONT);
        ACCOUNT_LIST.setForeground(Color.BLACK);
        this.add(ACCOUNT_LIST);
        
        CHANGE_PASSWORD = new JButton("Change password");
        CHANGE_PASSWORD.setBounds(0,60,200,30);
        CHANGE_PASSWORD.setOpaque(false);
        CHANGE_PASSWORD.setCursor(new Cursor(Cursor.HAND_CURSOR));
        CHANGE_PASSWORD.setContentAreaFilled(false);
        CHANGE_PASSWORD.setForeground(Color.BLACK);
        CHANGE_PASSWORD.setFont(SETFONT);
        CHANGE_PASSWORD.setBorderPainted(false);
        this.add(CHANGE_PASSWORD);
        
        DOWNLOAD_DATA = new JButton("Download Data");
        DOWNLOAD_DATA.setBounds(0,90,200,30);
        DOWNLOAD_DATA.setOpaque(false);
        DOWNLOAD_DATA.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DOWNLOAD_DATA.setContentAreaFilled(false);
        DOWNLOAD_DATA.setForeground(Color.BLACK);
        DOWNLOAD_DATA.setFont(SETFONT);
        DOWNLOAD_DATA.setBorderPainted(false);
        this.add(DOWNLOAD_DATA);
        
        UPLOAD_DATA = new JButton("Upload Data");
        UPLOAD_DATA.setBounds(0,120,200,30);
        UPLOAD_DATA.setOpaque(false);
        UPLOAD_DATA.setCursor(new Cursor(Cursor.HAND_CURSOR));
        UPLOAD_DATA.setContentAreaFilled(false);
        UPLOAD_DATA.setForeground(Color.BLACK);
        UPLOAD_DATA.setFont(SETFONT);
        UPLOAD_DATA.setBorderPainted(false);
        this.add(UPLOAD_DATA);

        LOG_OUT = new JButton("Log-out");
        LOG_OUT.setBounds(0,150,200,30);
        LOG_OUT.setOpaque(false);
        LOG_OUT.setCursor(new Cursor(Cursor.HAND_CURSOR));
        LOG_OUT.setContentAreaFilled(false);
        LOG_OUT.setForeground(Color.BLACK);
        LOG_OUT.setFont(SETFONT);
        LOG_OUT.setBorderPainted(false);
        this.add(LOG_OUT);

//        ImageIcon PANEL_II = new ImageIcon("Skins/C_background/settings_bg.png");
//        JLabel PANELS_BG = new JLabel(PANEL_II);
//        PANELS_BG.setBounds(0,0,200,140);
//        this.add(PANELS_BG);
    }
    public void setButtons(boolean x){
        NEW_ACCOUNT.setVisible(x);
        ACCOUNT_LIST.setVisible(x);
        DOWNLOAD_DATA.setVisible(x);
        UPLOAD_DATA.setVisible(x);
        if(x == false){
            CHANGE_PASSWORD.setBounds(0,20,200,30);
            LOG_OUT.setBounds(0,50,200,30);
        }else{
            CHANGE_PASSWORD.setBounds(0,60,200,30);
            LOG_OUT.setBounds(0,150,200,30);
        }
    }
    void Listener(){
//    this.addFocusListener( new FocusListener() {
//      private boolean gained = false;
//      @Override
//      public void focusGained( FocusEvent e ) {
//        gained = true;
//      }
//
//      @Override
//      public void focusLost( FocusEvent e ) {
//        if ( gained ){
//          setVisible(false);
//        }
//      }
//    } );
        NEW_ACCOUNT.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);
                NewAccount n = NewAccount.getInstance();
                n.centreWindow(mf);
                n.setVisible(true);
                //Set the other frame visible false
                ChangePassword cp = ChangePassword.getInstance();
                NewAccount na = NewAccount.getInstance();
                UpdateProduct up = UpdateProduct.getInstance();
                NewProduct np = NewProduct.getInstance();
                View_Accounts va = View_Accounts.getInstance();
                
                va.setVisible(false);
                np.setVisible(false);
                cp.setVisible(false);
                up.setVisible(false);
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                NEW_ACCOUNT.setBackground(new Color(189,195,199));
                NEW_ACCOUNT.setOpaque(true);
                setVisible(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                NEW_ACCOUNT.setBackground(null);
                NEW_ACCOUNT.setOpaque(false);
                setVisible(false);
            }
        });
        DOWNLOAD_DATA.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            JFileChooser f = new JFileChooser();
            f.setFileFilter(new FileFilter() {
              public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".zip")
                    || f.isDirectory();
              }

              public String getDescription() {
                return "ZIP Files";
              }
            });
                try{
            f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
            f.showSaveDialog(null);

            System.out.println(f.getCurrentDirectory());
            System.out.println(f.getSelectedFile());
                }catch(Exception exd){
            }

            ZipCompress.compress("Data");
                try{
                   File afile =new File("Data.zip");

                   if(afile.renameTo(new File(f.getSelectedFile()+"\\Data.zip"))){
                        System.out.println("File is moved successful!");
                   }else{
                        System.out.println("File is failed to move!");
                   }
                   
            JOptionPane.showMessageDialog(null, "Data successfully "
                                              + "downloaded at "
                                              +f.getSelectedFile()
                                              ,null,JOptionPane.PLAIN_MESSAGE);
            
                }catch(Exception ex){
                        ex.printStackTrace();
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
                DOWNLOAD_DATA.setBackground(new Color(189,195,199));
                DOWNLOAD_DATA.setOpaque(true);
                setVisible(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                DOWNLOAD_DATA.setBackground(null);
                DOWNLOAD_DATA.setOpaque(false);
                setVisible(false);
            }
        });
        UPLOAD_DATA.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setFileFilter(new FileFilter() {
              public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".zip")
                    || f.isDirectory();
              }

              public String getDescription() {
                return "ZIP Files";
              }
            });
        File selectedFile = null;
        
        int result = fileChooser.showOpenDialog(UPLOAD_DATA);
        if (result == JFileChooser.APPROVE_OPTION) {
         selectedFile = fileChooser.getSelectedFile();
         System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }
            String INPUT_ZIP_FILE = selectedFile.getAbsolutePath().toString();
            String OUTPUT_FOLDER = "Data";
            ZipDecompress unZip = new ZipDecompress();
            unZip.unZipIt(INPUT_ZIP_FILE,OUTPUT_FOLDER);
            JOptionPane.showMessageDialog(null, "Data successfully uploaded."
                                              ,null,JOptionPane.PLAIN_MESSAGE);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "No file selected!");
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
                UPLOAD_DATA.setBackground(new Color(189,195,199));
                UPLOAD_DATA.setOpaque(true);
                setVisible(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                UPLOAD_DATA.setBackground(null);
                UPLOAD_DATA.setOpaque(false);
                setVisible(false);
            }
        });
        ACCOUNT_LIST.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);
                View_Accounts va = View_Accounts.getInstance();
                va.centreWindow(mf);
                va.setVisible(true);
                
                ChangePassword cp = ChangePassword.getInstance();
                NewAccount na = NewAccount.getInstance();
                UpdateProduct up = UpdateProduct.getInstance();
                NewProduct np = NewProduct.getInstance();
                
                na.setVisible(false);
                np.setVisible(false);
                cp.setVisible(false);
                up.setVisible(false);
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                ACCOUNT_LIST.setBackground(new Color(189,195,199));
                ACCOUNT_LIST.setOpaque(true);
                setVisible(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ACCOUNT_LIST.setBackground(null);
                ACCOUNT_LIST.setOpaque(false);
                setVisible(false);
            }
        });
        CHANGE_PASSWORD.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);
                ChangePassword cp = ChangePassword.getInstance();
                cp.centreWindow(mf);
                cp.setVisible(true);

                NewAccount na = NewAccount.getInstance();
                UpdateProduct up = UpdateProduct.getInstance();
                NewProduct np = NewProduct.getInstance();
                View_Accounts va = View_Accounts.getInstance();
                
                na.setVisible(false);
                va.setVisible(false);
                np.setVisible(false);
                up.setVisible(false);
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                CHANGE_PASSWORD.setBackground(new Color(189,195,199));
                CHANGE_PASSWORD.setOpaque(true);
                setVisible(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                CHANGE_PASSWORD.setBackground(null);
                CHANGE_PASSWORD.setOpaque(false);
                setVisible(false);
            }
        });
        LOG_OUT.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            int option = JOptionPane.showConfirmDialog(null, 
            "Are you sure to log-out?", null, JOptionPane.YES_NO_OPTION);

            if (option == 0) { //The ISSUE is here
                login_jp lp = login_jp.getInstance();
                lp.setVisible(true);
                
        List_Panel lpx = List_Panel.getInstance();
        lpx.setVisible(false);
        lpx.setText();
        Logs_Panel lp2 = Logs_Panel.getInstance();
        lp2.setVisible(false);
        
        Sale_Panel sp = Sale_Panel.getInstance();
        sp.setVisible(false);
        
        Admin_mainPanel amp = Admin_mainPanel.getInstance();
        amp.setVisible(false);
        
        SecondMainPanel smp = SecondMainPanel.getInstance();
        smp.setVisible(false);
        
        MainFrame mf = MainFrame.getInstance();
        mf.setUser("");
        DataHolder dh = DataHolder.getInstance();
        dh.setLogin(false);
                    SQLiteDB_action sqlite = SQLiteDB_action.getInstance();
                    sqlite.clear_Cart();
                    dh.clear_all_cartData();
                    SQLiteDB retrieve = SQLiteDB.getInstance();
                    retrieve.retrieveData();
                    sp.filter_cart_table();
                    sp.filterTable();
                    sp.setTotal();
                    dh.clearAll_product();
            } else {
               System.out.print("no");
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
                LOG_OUT.setBackground(new Color(189,195,199));
                LOG_OUT.setOpaque(true);
                setVisible(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                LOG_OUT.setBackground(null);
                LOG_OUT.setOpaque(false);
                setVisible(false);
            }
        });
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                setVisible(true);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setVisible(false);
            }
        });
    }
}