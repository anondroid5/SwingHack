package www.swing.hack005;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JTextField;

// put a texture in the background

public class WatermarkTextField extends JTextField {
    BufferedImage img;
    TexturePaint texture;
    public WatermarkTextField(File file) throws IOException {
        super();
        img = ImageIO.read(file);
        Rectangle rect = new Rectangle(0,0,
                img.getWidth(null),img.getHeight(null));
        texture = new TexturePaint(img, rect);
        setOpaque(false);
    }
    
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setPaint(texture);
        g.fillRect(0,0,getWidth(),getHeight());
        super.paintComponent(g);
    }
    
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("HACK #05: Watermark JTextField Hack");
        
        JTextField textfield = new WatermarkTextField(new File("./images005/red.png"));
        textfield.setText("A Text Field");
        frame.getContentPane().add(textfield);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }
    
}
