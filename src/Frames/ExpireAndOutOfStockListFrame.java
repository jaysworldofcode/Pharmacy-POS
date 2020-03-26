package Frames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import msinventorysystem.ExpiredTableDataAndUI;
import msinventorysystem.MainFrame;
import msinventorysystem.OutOfStackTableDataAndUI;
import msinventorysystem.StringToDimension;
import msinventorysystem.get_settings;

 public class ExpireAndOutOfStockListFrame extends JFrame{
     
     JPanel mainPanel,OutOfStackPanel,ExpiredPanel;
     
    public ExpireAndOutOfStockListFrame(){
        Organizer();
    }
    void Organizer(){
        Components();
        Listener();
    }
    void Components(){
       setSize(new StringToDimension().convertToDimension(new get_settings().getSettings(1)));
       setVisible(true);
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);
        
        List<Image> icons = new ArrayList<Image>();
        icons.add(new ImageIcon("Skins/C_extras/FRAME_ICO.png").getImage());
        icons.add(new ImageIcon("Skins/C_extras/TASKBAR_ICO.png").getImage());
        this.setIconImages(icons);
        
        OutOfStackPanel  = new JPanel();  
        OutOfStackPanel.setLayout(new BorderLayout());
        OutOfStackPanel.add(new OutOfStackTableDataAndUI(), BorderLayout.CENTER);
        
        ExpiredPanel  = new JPanel();  
        ExpiredPanel.setLayout(new BorderLayout());
        ExpiredPanel.add(new ExpiredTableDataAndUI(), BorderLayout.CENTER);

        JTabbedPane tp = new JTabbedPane();  
        tp.add("Out of stock",OutOfStackPanel);  
        tp.setIconAt(0, new ImageIcon("Skins\\C_extras\\no_stock_log.png"));

        tp.add("Expired",ExpiredPanel);  
        tp.setIconAt(1, new ImageIcon("Skins\\C_extras\\expired_products.png"));

        tp.setFont( new Font( "Dialog", Font.BOLD, 15 ) );
        mainPanel.add(tp, BorderLayout.CENTER);
    }
    public void centreWindow(Window frame) {
        this.setVisible(true);
        int x = frame.getX();
        int y = frame.getY();
        this.setLocation(x+260, y+50); 
    }
    void Listener(){
        addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosing(WindowEvent e)
                {
                    System.out.println("Closed");
                    MainFrame mf = MainFrame.getInstance();
                    mf.setFront(true);
                    mf.requestFocus();
                    mf.toFront();
                }
        });
        //This will update the run.settings file
        this.addComponentListener(new ComponentAdapter() 
        {  
                public void componentResized(ComponentEvent evt) {
                    Component c = (Component)evt.getSource();
                    new get_settings().setSettings(1, c.getWidth()+","+c.getHeight());
                }
        });
    }
    public static void main(String [] args){
        ExpireAndOutOfStockListFrame eos = new ExpireAndOutOfStockListFrame();
    }
}