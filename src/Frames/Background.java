package Frames;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Background extends JFrame{
    
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    JPanel jp;
        	 int IMG_WIDTH  = 0;
	int IMG_HEIGHT = 0;
    private static Background instance = null;
    public static Background getInstance() {
      if(instance == null) {
         instance = new Background();
      }
      return instance;
    }        
    Background(){
        IMG_WIDTH = (int) dim.getWidth();
        IMG_HEIGHT = (int) dim.getHeight();
        resizer();
    this.setBounds(100, 100, (int) dim.getWidth(), (int) dim.getHeight());
    this.setLocationRelativeTo(null);
    this.setUndecorated(true);
    this.setLayout(null);
    this.setType(Type.UTILITY);
    Organizer();
    this.setVisible(false);
    }
    private BufferedImage resizeImage(BufferedImage originalImage, int type){
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();

	return resizedImage;
    }

    private  BufferedImage resizeImageWithHint(BufferedImage originalImage, int type){

	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();
	g.setComposite(AlphaComposite.Src);

	g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	g.setRenderingHint(RenderingHints.KEY_RENDERING,
	RenderingHints.VALUE_RENDER_QUALITY);
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);

	return resizedImage;
    }
    public void resizer(){
	try{

		BufferedImage originalImage = ImageIO.read(new File("skins/C_background/bacgrpund.png"));
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();

		BufferedImage resizeImageJpg = resizeImage(originalImage, type);
		ImageIO.write(resizeImageJpg, "jpg", new File("skins/C_background/bacgrpundx.png"));

		BufferedImage resizeImagePng = resizeImage(originalImage, type);
		ImageIO.write(resizeImagePng, "png", new File("skins/C_background/bacgrpundx.png"));

		BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type);
		ImageIO.write(resizeImageHintJpg, "jpg", new File("skins/C_background/bacgrpundx.png"));

		BufferedImage resizeImageHintPng = resizeImageWithHint(originalImage, type);
		ImageIO.write(resizeImageHintPng, "png", new File("skins/C_background/bacgrpundx.png"));

	}catch(IOException e){
		System.out.println(e.getMessage());
	}

    }
    void Organizer(){
        Components();
        Listener();
    }
    void Components(){
        jp = new JPanel();
        jp.setBounds(0,0, (int) dim.getWidth(), (int) dim.getHeight());
        jp.setBackground(Color.WHITE);
        this.add(jp);
        
        ImageIcon BG_II = new ImageIcon("Skins/C_background/bacgrpundx.png");
        JLabel BG = new JLabel(BG_II);
        BG.setBounds(0,0,(int) dim.getWidth(), (int) dim.getHeight());
        jp.add(BG);
    }
    void Listener(){
//        jp.addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                MainFrame m = MainFrame.getInstance();
//                m.setFocusable(true);
//                m.toFront();
//            }
//            @Override
//            public void mousePressed(MouseEvent e) {
//            }
//            @Override
//            public void mouseReleased(MouseEvent e) {
//            }
//            @Override
//            public void mouseEntered(MouseEvent e) {
//            }
//            @Override
//            public void mouseExited(MouseEvent e) {
//            }
//        });
    }
    public static void main(String [] args){
        Background b = Background.getInstance();
    }
}
