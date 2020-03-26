package Panels;

import java.awt.Color;
import javax.swing.*;

public class Admin_mainPanel extends JPanel{
    
    private static Admin_mainPanel instance = null;
    
    public static Admin_mainPanel getInstance() {
      if(instance == null) {
         instance = new Admin_mainPanel();
      }
      return instance;
    }

    Admin_mainPanel(){
        Organizer();
    }
    void Organizer(){
        setAdmin_mainPanel();
        components();
        listener();
    }
    void setAdmin_mainPanel(){
        this.setBounds(0,0,900,600);
        this.setLayout(null);
        this.setBackground(Color.PINK);
        this.setVisible(false);
    }
    void components(){
        
    }
    
    void listener(){
        
    }
    
}
