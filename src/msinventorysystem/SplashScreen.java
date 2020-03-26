package msinventorysystem;

import DataHolder.DataHolder;
import Frames.Background;
import Panels.List_Panel;
import Panels.Logs_Panel;
import Panels.Sale_Panel;
import Panels.SecondMainPanel;
import de.ksquared.system.keyboard.GlobalKeyListener;
import de.ksquared.system.keyboard.KeyAdapter;
import de.ksquared.system.keyboard.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class SplashScreen extends JFrame{
    
  public static Timer t;
  private static int cnt;
  JProgressBar PROGRESS_BAR = new JProgressBar();
  JLabel LOADING = new JLabel();

  SplashScreen(){
        this.setLayout(null); 
        this.setSize(600,360);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);

        List<Image> icons = new ArrayList<Image>();
        icons.add(new ImageIcon("Skins/C_extras/FRAME_ICO.png").getImage());
        icons.add(new ImageIcon("Skins/C_extras/TASKBAR_ICO.png").getImage());
        this.setIconImages(icons);
        
        Organizer();
    }
    void Organizer(){
        Components();
        count();
    }
public static void centreWindow(Window frame) {
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
    frame.setLocation(x, y);
}
    void Components(){
        PROGRESS_BAR.setBounds(0,310,600,10);
        this.add(PROGRESS_BAR);
        
        LOADING = new JLabel("Please wait....");
        LOADING.setBounds(450,320,150,30);
        LOADING.setFont(new Font("Tahoma", Font.PLAIN, 14));
        LOADING.setForeground(Color.WHITE);
        this.add(LOADING);
        
        ImageIcon BACKGROUND_II = new ImageIcon("Skins/C_Background/SPLASH_SCREEN.png");
        JLabel BACKGROUND_BG = new JLabel(BACKGROUND_II);
        BACKGROUND_BG.setBounds(0,0,600,400);
        this.add(BACKGROUND_BG);
        
    }
    public void count(){
        ActionListener actListner = new ActionListener() {

@Override
public void actionPerformed(ActionEvent event) {

                cnt += 1;

            if(cnt == 0){
                LOADING.setText("Loading panels....");
                PROGRESS_BAR.setValue(0);
              }else if(cnt == 1){
                  LOADING.setText("Loading database....");
                PROGRESS_BAR.setValue(25);
              }else if(cnt == 2){
                  LOADING.setText("Loading files....");
                PROGRESS_BAR.setValue(50);
              }else if(cnt == 3){
                  LOADING.setText("Loading list....");
                PROGRESS_BAR.setValue(75);
              }else if(cnt == 4){
                  PROGRESS_BAR.setValue(100);
                  LOADING.setText("Successfully loaded....");
                  setVisible(false);
                  MainFrame i = MainFrame.getInstance();
                  i.centreWindow(i);
              }
            }
         };
  Timer timer = new Timer(1000, actListner);
  timer.start();
    }
    public static void main(String [] args) throws FileNotFoundException{
//        Product_info pi = new Product_info("Paracetamol","Drugman","14.00","140mg","fern-c");
//        pi.setVisible(true);
        System.out.println("/* Inventory System Running */");
                            try {
UIManager.put("TitledBorder.border", new LineBorder(new Color(200,200,200), 1));
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
//            UIManager.put( "ScrollBar.minimumThumbSize", new Dimension( 10, 10 ) ); 
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }   
//        SplashScreen ss = new SplashScreen();
//        ss.centreWindow(ss);
//        ss.setVisible(true);
                   
                  Background b = Background.getInstance();
                  MainFrame i = MainFrame.getInstance();
                  i.centreWindow(i);
                  //Get background show
                  File file = new File("Data/bgScanner.dat");
                  Scanner scnr = new Scanner(file);
                  DataHolder dh = DataHolder.getInstance();  
                    while(scnr.hasNextLine()){
                       String line = scnr.nextLine();
                       dh.setBackground(line);
                    }
                    
                    if(dh.getBackground().equals("true")){
                        b.setVisible(true);
                        i.setBackground("Closed background");
                    }else{
                        b.setVisible(false);
                        i.setBackground("Show background");
                    }
                    	new GlobalKeyListener().addKeyListener(new KeyAdapter() {
			@Override public void keyPressed(KeyEvent event) { 
                        }
			@Override public void keyReleased(KeyEvent event) {
                                                                           String s = event.toString();
//				System.out.println(s.substring(0, s.length() - 5));
                                                                           DataHolder dh = DataHolder.getInstance();
                                                                           Sale_Panel sp = Sale_Panel.getInstance();
                                                                           List_Panel list_p = List_Panel.getInstance();
                                                                           Logs_Panel logs_p = Logs_Panel.getInstance();
                                                                           SecondMainPanel spx = SecondMainPanel.getInstance();
                                                                           if(dh.viewLogin()==true){
                                                                               if(s.substring(0, s.length() - 5).toString().equals("112")){
                                                                                   sp.setVisible(true);
                                                                                   list_p.setVisible(false);
                                                                                   logs_p.setVisible(false);
                                                                                   spx.setButtons_icon("pos");
                                                                                   dh.setFunction_use("pos");
                                                                               }else if(s.substring(0, s.length() - 5).toString().equals("113")){
                                                                                   list_p.setVisible(true);
                                                                                   sp.setVisible(false);
                                                                                   logs_p.setVisible(false);
                                                                                   spx.setButtons_icon("products_list");
                                                                                   dh.setFunction_use("products_list");
                                                                               }else if(s.substring(0, s.length() - 5).toString().equals("114")){
                                                                                   logs_p.setVisible(true);
                                                                                   list_p.setVisible(false);
                                                                                   sp.setVisible(false);
                                                                                   spx.setButtons_icon("logs");
                                                                                   dh.setFunction_use("logs");
                                                                               }else if(s.substring(0, s.length() - 5).toString().equals("115")){
                                                                                   System.out.println("F4 is pressed!");
                                                                                   if(dh.getFunction_use().equals("pos")){
                                                                                       sp.setCash_out();
                                                                                   }
                                                                               }else if(s.substring(0, s.length() - 5).toString().equals("116")){
                                                                                   System.out.println("F4 is pressed!");
                                                                                   if(dh.getFunction_use().equals("pos")){
                                                                                       sp.clearCart();
                                                                                   }
                                                                               }
                                                                           }
				if(event.getVirtualKeyCode()==KeyEvent.VK_ADD
				&& event.isCtrlPressed())
					System.out.println("CTRL+ADD was just released (CTRL is still pressed)");
			}
		});
//		while(true)
//			try { Thread.sleep(100); }
//			catch(InterruptedException e) { e.printStackTrace(); }

    }
}