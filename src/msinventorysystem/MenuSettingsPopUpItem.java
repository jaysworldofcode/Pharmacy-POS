package msinventorysystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

public class MenuSettingsPopUpItem extends JMenuItem{

    int id = 0;
    
    public MenuSettingsPopUpItem(int id,String setName){
        System.out.println("Printer adding "+setName);
        this.id = id;
        this.setText(setName);
        
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePrinterIndex(id);
            }
        });
    }
}