package msinventorysystem;

import DataBase.SQLiteDB;
import DataBase.SQLiteDB_action;
import DataHolder.DataHolder;
import Frames.Background;
import Frames.ChangePassword;
import Frames.NewAccount;
import Frames.NewProduct;
import Frames.Settings;
import Frames.UpdateLogoAndIcon;
import Frames.View_Accounts;
import Frames.out_of_stock_list;
import LoginPanel.login_jp;
import Panels.Admin_mainPanel;
import Panels.List_Panel;
import Panels.Logs_Panel;
import Panels.Sale_Panel;
import Panels.SecondMainPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.filechooser.FileFilter;

public class MainFrame extends JFrame{

static String setFrame_type = null;
    
static int lastX, lastY;
JButton MINIMIZE,EXIT,x,BACKGROUND, DATABASE, MANAGE_ACCOUNTS, MY_ACCOUNT,
        SETTINGS;
Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
int screenWidth = screenSize.width;
int screenHeight = screenSize.height;
JLabel user;
JLabel shortcuts;
JPanel TOL_PANEL = new JPanel(); 

final JPopupMenu data_menu = new JPopupMenu();
final JPopupMenu manage_accounts_menu = new JPopupMenu();
final JPopupMenu my_account = new JPopupMenu();
final JPopupMenu settings = new JPopupMenu();
        
JMenuItem log_out,change_password,account_list,new_account,upload_data,
          download_data,days_expired_limit,show_background,help,about_us,
          update_icon;
JMenu printer_menu;
        
static boolean setFocus= true;

    private final String FILENAME = "Data/bgScanner.dat";

    DataHolder dh = DataHolder.getInstance();        
    private static MainFrame instance = null;
    
    public static MainFrame getInstance() {
      if(instance == null) {
         instance = new MainFrame();
      }
      return instance;
    }        
    public void setBackground(String x){
        BACKGROUND.setText(x);
    }
    public void setUtility(String set){
        if(set.equalsIgnoreCase("new_event")){
            
        }else{
            
        }
    }
    public void setTop_settings(String type){
        if(type.equalsIgnoreCase("admin")){
            DATABASE.setVisible(true);
            MANAGE_ACCOUNTS.setVisible(true);
            MY_ACCOUNT.setVisible(true);
            SETTINGS.setVisible(true);
            
            DATABASE.setVisible(true);
            MANAGE_ACCOUNTS.setVisible(true);
            MY_ACCOUNT.setBounds(340,5,120,20);
        }else{
            DATABASE.setVisible(false);
            MANAGE_ACCOUNTS.setVisible(false);
            MY_ACCOUNT.setVisible(true);
            MY_ACCOUNT.setBounds(90,5,90,20);
            SETTINGS.setVisible(true);
        }
    }
    public void setShorcuts(boolean isShow){
        shortcuts.setVisible(isShow);
    }
    public MainFrame(){
        dh.setFunction_use("pos");
        this.setUndecorated(true);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        this.setLayout(null);
        this.setTitle("Business Inventory System");
        this.setSize(1100,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.yellow);
        this.setResizable(false);
        
        List<Image> icons = new ArrayList<Image>();
        icons.add(new ImageIcon("Skins/C_extras/FRAME_ICO.png").getImage());
        icons.add(new ImageIcon("Skins/C_extras/TASKBAR_ICO.png").getImage());
        this.setIconImages(icons);
        
        setTop_panel();
                
        //Set connection
//        setConnection s = setConnection.getInstance();
        //Set SQLite Connection
        SQLiteDB SQLite = SQLiteDB.getInstance();
        //Add components from other class
        Settings s = Settings.getInstance();
        this.add(s);

        
        out_of_stock_list o =  out_of_stock_list.getInstance();
//        MainFrame mf = MainFrame.getInstance();
//        o.centreWindow(mf);
        o.setVisible(false);
        this.add(o);
        
        List_Panel lp = List_Panel.getInstance();
        this.add(lp);
        
        Logs_Panel lp2 = Logs_Panel.getInstance();
//        lp2.setVisible(true);
        this.add(lp2);
        
        Sale_Panel sp = Sale_Panel.getInstance();
        this.add(sp);
        
        login_jp ljp = login_jp.getInstance();
        ljp.setVisible(true);
        this.add(ljp);
        
        Admin_mainPanel amp = Admin_mainPanel.getInstance();
        this.add(amp);
        
        SecondMainPanel smp = SecondMainPanel.getInstance();
//        smp.setVisible(true);
        this.add(smp);
        
        SQLite.retrieveUsername();
        
        dh.print();
                
        this.setVisible(true);
        ActionListener actListner = new ActionListener() {

        @Override

        public void actionPerformed(ActionEvent event) {
            dh.checkSubFrameIsVisible();
            System.out.println("Checking visibility of sub frames");
            sp.checkBoxUnusable();
        }
                 };
          Timer timer = new Timer(500, actListner);
          timer.start();
    }
    public void setUser(String x){
//        user.setText(x);
    }
    void setTop_panel(){
        TOL_PANEL.setBounds(0,0,1100,30);
        TOL_PANEL.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(189,189,189)));  
        TOL_PANEL.setLayout(null);
        TOL_PANEL.setBackground(Color.WHITE);
        this.add(TOL_PANEL);
        
        JLabel setVersion = new JLabel("Ver 3.1");
        Font SETFONT = new Font("Tahoma", Font.ITALIC, 10);
        setVersion.setFont(SETFONT);
        setVersion.setBounds(40,0,70,30);
        TOL_PANEL.add(setVersion);
        
        AbstractBorder brdr = new TextBubbleBorder(Color.LIGHT_GRAY,1,16,0);
        download_data = new JMenuItem("Download Data");
        data_menu.add(download_data);
        ImageIcon download_ico=new ImageIcon("Skins/C_extras/file (1).png");
        download_data.setIcon(download_ico);
        download_data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dh.frameSetVisibleFalse();
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
                }catch(Exception exd){
            }

                if(!f.getSelectedFile().equals(null)){
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
            }
        });
        
        upload_data = new JMenuItem("Upload Data");
        data_menu.add(upload_data);
        ImageIcon upload_ico=new ImageIcon("Skins/C_extras/file (2).png");
        upload_data.setIcon(upload_ico);
        upload_data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dh.frameSetVisibleFalse();
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
        
        int result = fileChooser.showOpenDialog(upload_data);
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
        });

        Font font = new Font("Tahoma", Font.BOLD, 12);
        
        DATABASE = new JButton("Database");
        DATABASE.setBounds(90,5,90,20);
        DATABASE.setOpaque(false);
        DATABASE.setCursor(new Cursor(Cursor.HAND_CURSOR));
        DATABASE.setContentAreaFilled(false);
        DATABASE.setFont(font);
        DATABASE.setForeground(Color.GRAY);
        DATABASE.setFocusable(false);
        DATABASE.setBorderPainted(false);
        TOL_PANEL.add(DATABASE);
        
        DATABASE.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                data_menu.show(DATABASE, DATABASE.getBounds().x-80, DATABASE.getBounds().y
                   + DATABASE.getBounds().height);
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                DATABASE.setBorderPainted(true);
                DATABASE.setBorder(brdr);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                DATABASE.setBorderPainted(false);
                DATABASE.setBorder(null);
           }
        });
        
        new_account = new JMenuItem("New Account");
        manage_accounts_menu.add(new_account);
        ImageIcon new_account_icon=new ImageIcon("Skins/C_extras/user (3).png");
        new_account.setIcon(new_account_icon);
        new_account.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dh.frameSetVisibleFalse();
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);
                NewAccount n = NewAccount.getInstance();
                n.centreWindow(mf);
                n.setVisible(true);
                //Set the other frame visible false
                ChangePassword cp = ChangePassword.getInstance();
                NewAccount na = NewAccount.getInstance();
//                UpdateProduct up = UpdateProduct.getInstance();
                NewProduct np = NewProduct.getInstance();
                View_Accounts va = View_Accounts.getInstance();
                
                va.setVisible(false);
                np.setVisible(false);
                cp.setVisible(false);
//                up.setVisible(false);
            }
        });
        
        account_list = new JMenuItem("Account List");
        manage_accounts_menu.add(account_list);
        ImageIcon account_list_icon=new ImageIcon("Skins/C_extras/employee.png");
        account_list.setIcon(account_list_icon);
        account_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dh.frameSetVisibleFalse();
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);
                View_Accounts va = View_Accounts.getInstance();
                va.centreWindow(mf);
                va.setVisible(true);
                
                ChangePassword cp = ChangePassword.getInstance();
                NewAccount na = NewAccount.getInstance();
//                UpdateProduct up = UpdateProduct.getInstance();
                NewProduct np = NewProduct.getInstance();
                
                na.setVisible(false);
                np.setVisible(false);
                cp.setVisible(false);
//                up.setVisible(false);
            }
        });
        
        MANAGE_ACCOUNTS = new JButton("Manage Accounts");
        MANAGE_ACCOUNTS.setBounds(185,5,150,20);
        MANAGE_ACCOUNTS.setOpaque(false);
        MANAGE_ACCOUNTS.setCursor(new Cursor(Cursor.HAND_CURSOR));
        MANAGE_ACCOUNTS.setContentAreaFilled(false);
        MANAGE_ACCOUNTS.setFocusable(false);
        MANAGE_ACCOUNTS.setFont(font);
        MANAGE_ACCOUNTS.setForeground(Color.GRAY);
        MANAGE_ACCOUNTS.setBorderPainted(false);
        TOL_PANEL.add(MANAGE_ACCOUNTS);
        
        MANAGE_ACCOUNTS.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                manage_accounts_menu.show(MANAGE_ACCOUNTS, MANAGE_ACCOUNTS.getBounds().x-170,
                    MANAGE_ACCOUNTS.getBounds().y+DATABASE.getBounds().height);
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                MANAGE_ACCOUNTS.setBorderPainted(true);
                MANAGE_ACCOUNTS.setBorder(brdr);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                MANAGE_ACCOUNTS.setBorderPainted(false);
                MANAGE_ACCOUNTS.setBorder(null);
           }
        });

        change_password = new JMenuItem("Change Password");
        my_account.add(change_password);
        ImageIcon change_password_icon=new ImageIcon("Skins/C_extras/shuffle.png");
        change_password.setIcon(change_password_icon);
        change_password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dh.frameSetVisibleFalse();
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);
                ChangePassword cp = ChangePassword.getInstance();
                cp.centreWindow(mf);
                cp.setVisible(true);

                NewAccount na = NewAccount.getInstance();
//                UpdateProduct up = UpdateProduct.getInstance();
                NewProduct np = NewProduct.getInstance();
                View_Accounts va = View_Accounts.getInstance();
                
                na.setVisible(false);
                va.setVisible(false);
                np.setVisible(false);
//                up.setVisible(false);
            }
        });
        
        log_out = new JMenuItem("Log-out");
        my_account.add(log_out);
        ImageIcon log_out_icon=new ImageIcon("Skins/C_extras/logout.png");
        log_out.setIcon(log_out_icon);
        log_out.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dh.frameSetVisibleFalse();
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
                    
            DATABASE.setVisible(false);
            MANAGE_ACCOUNTS.setVisible(false);
            MY_ACCOUNT.setVisible(false);
            SETTINGS.setVisible(false);
        setShorcuts(false);
            } else {
               System.out.print("no");
               }
            }
        });

        MY_ACCOUNT = new JButton("My Account");
        MY_ACCOUNT.setBounds(340,5,120,20);
        MY_ACCOUNT.setOpaque(false);
        MY_ACCOUNT.setCursor(new Cursor(Cursor.HAND_CURSOR));
        MY_ACCOUNT.setFont(font);
        MY_ACCOUNT.setForeground(Color.GRAY);
        MY_ACCOUNT.setContentAreaFilled(false);
        MY_ACCOUNT.setFocusable(false);
        MY_ACCOUNT.setBorderPainted(false);
        TOL_PANEL.add(MY_ACCOUNT);
        
        MY_ACCOUNT.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(dh.getUsert_type().equalsIgnoreCase("admin")){
                    my_account.show(MY_ACCOUNT, MY_ACCOUNT.getBounds().x-285,
                        MY_ACCOUNT.getBounds().y+MY_ACCOUNT.getBounds().height);
                }else{
                    my_account.show(MY_ACCOUNT, MY_ACCOUNT.getBounds().x-80, MY_ACCOUNT.getBounds().y
                       + MY_ACCOUNT.getBounds().height);
                }
                                dh.frameSetVisibleFalse();
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                MY_ACCOUNT.setBorderPainted(true);
                MY_ACCOUNT.setBorder(brdr);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                MY_ACCOUNT.setBorderPainted(false);
                MY_ACCOUNT.setBorder(null);
           }
        });
        
        SETTINGS = new JButton("Settings");
        SETTINGS.setBounds(460,5,120,20);
        SETTINGS.setOpaque(false);
        SETTINGS.setCursor(new Cursor(Cursor.HAND_CURSOR));
        SETTINGS.setFont(font);
        SETTINGS.setForeground(Color.GRAY);
        SETTINGS.setContentAreaFilled(false);
        SETTINGS.setFocusable(false);
        SETTINGS.setBorderPainted(false);
        TOL_PANEL.add(SETTINGS);
        
        SETTINGS.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(dh.getUsert_type().equalsIgnoreCase("admin")){
                    settings.show(SETTINGS, SETTINGS.getBounds().x-400,
                        SETTINGS.getBounds().y+SETTINGS.getBounds().height);
                }else{
                    settings.show(SETTINGS, SETTINGS.getBounds().x-80, SETTINGS.getBounds().y
                       + SETTINGS.getBounds().height);
                }
                                dh.frameSetVisibleFalse();
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                SETTINGS.setBorderPainted(true);
                SETTINGS.setBorder(brdr);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                SETTINGS.setBorderPainted(false);
                SETTINGS.setBorder(null);
           }
        });
        
        printer_menu = new JMenu("Printer");
        settings.add(printer_menu);
        
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        System.out.println("Number of print services: " + printServices.length);
        int incrementID = 0;
        
        for (PrintService printer : printServices){
            JMenuItem newMenuItem = new MenuSettingsPopUpItem(incrementID,printer.getName());
            printer_menu.add(newMenuItem);
            incrementID++;
        }
        
        show_background = new JMenuItem("Show/Hide Background");
        settings.add(show_background);
        
        show_background.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dh.getBackground().equals("true")){
                    Background b = Background.getInstance();
                    b.setVisible(false);
                    dh.setBackground("false");
                    setBGdata("false");
//                    BACKGROUND.setText("Show background");
                }else{
                    setBGdata("true");
                    Background b = Background.getInstance();
                    b.setVisible(true);
                    dh.setBackground("true");
//                BACKGROUND.setText("Closed background");
                }
            }
        });
        
        settings.addSeparator();

        update_icon = new JMenuItem("Update logo and icon");
        settings.add(update_icon);

        update_icon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);
                new UpdateLogoAndIcon().centreWindow(mf);
            }
        });
        
        help = new JMenuItem("Help");
        settings.add(help);

        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Howdy, this is a dummy text! No message here!"
                );
            }
        });
        
        about_us = new JMenuItem("About us");
        settings.add(about_us);
        
        about_us.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Howdy, this is a dummy text! No message here!"
                );
            }
        });
        
        DATABASE.setVisible(false);
        MANAGE_ACCOUNTS.setVisible(false);
        MY_ACCOUNT.setVisible(false);
        SETTINGS.setVisible(false);
            
        user = new JLabel();
        user.setBounds(200,5,500,25);
//        user.setForeground(Color.WHITE);
        TOL_PANEL.add(user);
        
        shortcuts = new JLabel("<html>(<b>F1</b>) View POS    "
                                           + "(<b>F2</b>) View Product List    "
                                           + "(<b>F3</b>) View Logs List");
        shortcuts.setBounds(620,5,500,25);
        shortcuts.setVisible(false);
//        shortcuts.setForeground(Color.WHITE);
        TOL_PANEL.add(shortcuts);
        
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
            MainFrame m = MainFrame.getInstance();
            m.setLocation(getLocationOnScreen().x + x - lastX,
            getLocationOnScreen().y + y - lastY);
            lastX = x;
            lastY = y;
        }
    };

        TOL_PANEL.addMouseListener(ma);
        TOL_PANEL.addMouseMotionListener(ma);    

        ImageIcon MINIMIZE_II = new ImageIcon("Skins/C_buttons/minimize_1.png");
        MINIMIZE  = new JButton(MINIMIZE_II);
        MINIMIZE.setBounds(1037,0,30,30);
        MINIMIZE.setOpaque(false);
        MINIMIZE.setCursor(new Cursor(Cursor.HAND_CURSOR));
        MINIMIZE.setContentAreaFilled(false);
        MINIMIZE.setFocusable(false);
        MINIMIZE.setBorderPainted(false);
        TOL_PANEL.add(MINIMIZE);
        
        ImageIcon HEADER_II = new ImageIcon("Skins/C_extras/header_icon.png");
        JLabel HEADER = new JLabel(HEADER_II);
        HEADER.setBounds(5,3,30,30);
        TOL_PANEL.add(HEADER);
        
        BACKGROUND = new JButton("Close background");
        BACKGROUND.setBounds(887,0,150,30);
        BACKGROUND.setOpaque(false);
        BACKGROUND.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        BACKGROUND.setForeground(Color.WHITE);
        BACKGROUND.setContentAreaFilled(false);
        BACKGROUND.setFocusable(false);
        BACKGROUND.setBorderPainted(false);
//        TOL_PANEL.add(BACKGROUND);
        
        BACKGROUND.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                                                        if(dh.getBackground().equals("true")){
                                                            Background b = Background.getInstance();
                                                            b.setVisible(false);
                                                            dh.setBackground("false");
                                                            setBGdata("false");
                                                            BACKGROUND.setText("Show background");
                                                        }else{
                                                            setBGdata("true");
                                                            Background b = Background.getInstance();
                                                            b.setVisible(true);
                                                            dh.setBackground("true");
                                                            BACKGROUND.setText("Closed background");
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
                BACKGROUND.setForeground(Color.blue);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                BACKGROUND.setForeground(Color.black);
            }
        });
        
        MINIMIZE.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                  setState(JFrame.ICONIFIED);            
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon MINIMIZE_II = new ImageIcon("Skins/C_buttons/minimize_2.png");
                MINIMIZE.setIcon(MINIMIZE_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon MINIMIZE_II = new ImageIcon("Skins/C_buttons/minimize_1.png");
                MINIMIZE.setIcon(MINIMIZE_II);
            }
        });
        
        ImageIcon EXIT_II = new ImageIcon("Skins/C_buttons/exit_1.png");
        EXIT = new JButton(EXIT_II);
        EXIT.setBounds(1069,0,30,30);
        EXIT.setOpaque(false);
        EXIT.setCursor(new Cursor(Cursor.HAND_CURSOR));
        EXIT.setContentAreaFilled(false);
        EXIT.setFocusable(false);
        EXIT.setBorderPainted(false);
        TOL_PANEL.add(EXIT);
        
        EXIT.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon EXIT_II = new ImageIcon("Skins/C_buttons/exit_2.png");
                EXIT.setIcon(EXIT_II);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon EXIT_II = new ImageIcon("Skins/C_buttons/exit_1.png");
                EXIT.setIcon(EXIT_II);
            }
        });
        this.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }
            @Override
            public void windowLostFocus(WindowEvent e) {
                if(setFocus == true){
                toFront();
                }else{
                }
            }
        });
        TOL_PANEL.setVisible(true);
    }
    public void setFront(boolean x){
        setFocus = x;
    }
    public void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y-20);
    }
    public void setBGdata(String x){
        try{
              String verify, putData;
              File file = new File("Data/bgScanner.dat");
              file.createNewFile();
              FileWriter fw = new FileWriter(file);
              BufferedWriter bw = new BufferedWriter(fw);
              bw.write(x);
              bw.flush();
              bw.close();
          }catch(IOException e){
          e.printStackTrace();
          }
    }
    class RoundedCornerBorder extends AbstractBorder {
  @Override public void paintBorder(
      Component c, Graphics g, int x, int y, int width, int height) {
    Graphics2D g2 = (Graphics2D)g.create();
    g2.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    int r = 12;
    int w = width  - 1;
    int h = height - 1;
    Area round = new Area(new RoundRectangle2D.Float(x, y, w, h, r, r));
    Container parent = c.getParent();
    if(parent!=null) {
      g2.setColor(parent.getBackground());
      Area corner = new Area(new Rectangle2D.Float(x, y, width, height));
      corner.subtract(round);
      g2.fill(corner);
    }
    g2.setPaint(c.getForeground());
    g2.draw(round);
    g2.dispose();
  }
  @Override public Insets getBorderInsets(Component c) {
    return new Insets(4, 8, 4, 8);
  }
  @Override public Insets getBorderInsets(Component c, Insets insets) {
    insets.left = insets.right = 8;
    insets.top = insets.bottom = 4;
    return insets;
  }
}
class TextBubbleBorder extends AbstractBorder {

    private Color color;
    private int thickness = 4;
    private int radii = 8;
    private int pointerSize = 7;
    private Insets insets = null;
    private BasicStroke stroke = null;
    private int strokePad;
    private int pointerPad = 4;
    RenderingHints hints;

    TextBubbleBorder(
            Color color) {
        new TextBubbleBorder(color, 4, 8, 7);
    }

    TextBubbleBorder(
            Color color, int thickness, int radii, int pointerSize) {
        this.thickness = thickness;
        this.radii = radii;
        this.pointerSize = pointerSize;
        this.color = color;

        stroke = new BasicStroke(thickness);
        strokePad = thickness / 2;

        hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int pad = radii + strokePad;
        int bottomPad = pad + pointerSize + strokePad;
        insets = new Insets(pad, pad, bottomPad, pad);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return insets;
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        return getBorderInsets(c);
    }

    @Override
    public void paintBorder(
            Component c,
            Graphics g,
            int x, int y,
            int width, int height) {

        Graphics2D g2 = (Graphics2D) g;

        int bottomLineY = height - thickness - pointerSize;

        RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
                0 + strokePad,
                0 + strokePad,
                width - thickness,
                bottomLineY,
                radii,
                radii);

        Polygon pointer = new Polygon();

        // left point
        pointer.addPoint(
                strokePad + radii + pointerPad,
                bottomLineY);
        // right point
        pointer.addPoint(
                strokePad + radii + pointerPad + pointerSize,
                bottomLineY);
        // bottom point
        pointer.addPoint(
                strokePad + radii + pointerPad + (pointerSize / 2),
                height - strokePad);

        Area area = new Area(bubble);
        area.add(new Area(pointer));

        g2.setRenderingHints(hints);

        Area spareSpace = new Area(new Rectangle(0, 0, width, height));
        spareSpace.subtract(area);
        g2.setClip(spareSpace);
        g2.clearRect(0, 0, width, height);
        g2.setClip(null);

        g2.setColor(color);
        g2.setStroke(stroke);
        g2.draw(area);
        }
    }
}