package Frames;

import DataHolder.DataHolder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.*;
import msinventorysystem.MainFrame;
import msinventorysystem.StringToDimension;
import msinventorysystem.get_settings;

public class Expired_List extends JFrame{
 
    static int lastX, lastY;
    DataHolder dh = DataHolder.getInstance();
    
    JList jl;
    DefaultListModel<String> model = new DefaultListModel<String>();
    JPanel top_panel, bottom_panel;
    
    private static Expired_List instance = null;
    
    public static Expired_List getInstance() {
      if(instance == null) {
         instance = new Expired_List();
      }
      return instance;
    }
    
    public void centreWindow(Window frame) {
        int x = frame.getX();
        int y = frame.getY();
        this.setLocation(x+400,y+250);
    }
    
    public void addData(String data){
        model.addElement(data);
    }
    
    public void emptyModel(){
        model.clear();
    }
    public Expired_List(){
        //Set icon for frame
        java.util.List<Image> icons = new ArrayList<Image>();
        icons.add(new ImageIcon("Skins/C_extras/FRAME_ICO.png").getImage());
        icons.add(new ImageIcon("Skins/C_extras/TASKBAR_ICO.png").getImage());
        this.setIconImages(icons);
        
        this.setSize(new StringToDimension().convertToDimension(new get_settings().getSettings(0)));
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setLayout(null);
	this.setLayout(new BorderLayout());
        dh.addFrameList(this);
        this.setFocusable(true);
        
        jl = new JList();
        jl.setModel(model);
        this.add(jl);
        
	add(jl, BorderLayout.CENTER);

        FlowLayout flow_top_layout = new FlowLayout(FlowLayout.LEFT);
        top_panel = new JPanel(); 
        Color c = new Color(217, 217, 217);
        top_panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,c));
        top_panel.setLayout(flow_top_layout);
//        top_panel.setBackground(Color.red);
        
        top_panel.add(new JLabel(new ImageIcon("Skins\\C_extras\\expired_products.png")));
        top_panel.add(new JLabel("Expired today"));
        add(top_panel, BorderLayout.NORTH);
        
        FlowLayout flow_bottom_layout = new FlowLayout(FlowLayout.RIGHT);
        bottom_panel = new JPanel();
        bottom_panel.setLayout(flow_bottom_layout);
//        bottom_panel.setBackground(Color.red);
        
        add(bottom_panel, BorderLayout.SOUTH);
        
        JButton view_jb = new JButton("View");
        bottom_panel.add(view_jb);
        
        view_jb.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainFrame mf = MainFrame.getInstance();
                mf.setFront(false);
                dh.frameSetVisibleFalse();
                
                ExpireAndOutOfStockListFrame eos = new ExpireAndOutOfStockListFrame();
                eos.centreWindow(mf);
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
        
        JButton okay_jb = new JButton("OK");
        bottom_panel.add(okay_jb);
        
        okay_jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mf = MainFrame.getInstance();
                mf.requestFocus();
                mf.toFront();                
                dh.frameSetVisibleFalse();
            }
        });
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
        
        //This will update the run.settings file
        this.addComponentListener(new ComponentAdapter() 
        {  
                public void componentResized(ComponentEvent evt) {
                    Component c = (Component)evt.getSource();
                    new get_settings().setSettings(0, c.getWidth()+","+c.getHeight());
                }
        });
     }
    public static void main(String [] args){
        Expired_List exp = new Expired_List();
        exp.addData("Tester");
        exp.addData("Tester");
        exp.setVisible(true);
    }
}