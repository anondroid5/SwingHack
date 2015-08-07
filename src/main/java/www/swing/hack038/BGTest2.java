package www.swing.hack038;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BGTest2 {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("HACK #38 Transparent Window");
        frame.setUndecorated(true);
        TransparentBackground bg = new TransparentBackground(frame);
        bg.setLayout(new BorderLayout());
        
        JPanel panel = new JPanel() {
            public void paintComponent(Graphics g) {
                g.setColor(Color.blue);
                Image img = new ImageIcon("./images038/mp3.png").getImage();
                g.drawImage(img,0,0,null);
            }
        };
        panel.setOpaque(false);
        bg.add("Center",panel);
        
        
        frame.getContentPane().add("Center",bg);
        frame.pack();
        frame.setSize(200,200);
        frame.setLocation(500,500);
        frame.setVisible(true);
    }
}