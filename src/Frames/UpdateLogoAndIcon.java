package Frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import msinventorysystem.CopyFile;
import msinventorysystem.MainFrame;
import msinventorysystem.UpdateImage;

public class UpdateLogoAndIcon extends JFrame{
    
    JPanel MainPanel = new JPanel();
    JLabel updatedLogo;
    JButton updateButtonIcon;
    
    public UpdateLogoAndIcon(){
        MainPanel.setLayout(new BoxLayout(MainPanel, BoxLayout.Y_AXIS));
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                            MainFrame mf = MainFrame.getInstance();
                            mf.setFront(true);
            }
        });
        
        MainPanel.add(new JLabel("<html><br><br></html>"));
        
        updatedLogo = new JLabel(new ImageIcon("Skins\\C_extras\\fixedIcon.png"));
        updatedLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        MainPanel.add(updatedLogo);
        
        JLabel note = new JLabel("<html><br><br>"
                + "Note: Image must be .png file format.<br><br></html>"
                , SwingConstants.CENTER);
        note.setAlignmentX(Component.CENTER_ALIGNMENT);
        MainPanel.add(note);
        
        updateButtonIcon = new JButton("Update Logo");
        updateButtonIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
        MainPanel.add(updateButtonIcon);

        updateButtonIcon.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showOpenDialog((Component)e.getSource());
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        
                        if(getFileExtension(file).toUpperCase().equals(".PNG")){
                            new CopyFile().setLogo(file,new File(
                                    "Skins\\C_extras\\icon.png"
                            ));
                            updateImageSmallToBeCheck();
                            updatedLogo.setIcon(new ImageIcon("Skins\\C_extras\\fixedIcon.png"));
                            updatedLogo.repaint();
                            MainFrame mf = MainFrame.getInstance();
                            mf.setFront(true);
                            setVisible(false);
                        }else{
                            JOptionPane.showMessageDialog(null, 
                                "Please provide png file.");
                        }
                        
                    }else {
                        System.out.println("File access cancelled by user.");
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
        
        setSize(300,300);
        add(MainPanel);
        setVisible(true);
    }
    public void centreWindow(Window frame) {
        int x = frame.getX();
        int y = frame.getY();
        this.setLocation(x+250, y+200);
    }
    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }
    void updateImageSmallToBeCheck(){
        try{
            File outputfile = new File("Skins\\C_extras\\fixedIcon.png");
            ImageIO.write(new UpdateImage().scaleImage(50,50,"Skins\\C_extras\\icon.png"), "png", outputfile);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error set size in updating"
                    + " logo and icon: "+ex);
        }
    }
    public static void main(String [] args){
        UpdateLogoAndIcon upl = new UpdateLogoAndIcon();
    }
}
