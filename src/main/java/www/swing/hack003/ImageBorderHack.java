package www.swing.hack003;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageBorderHack {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hack #03: Image Border");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        JButton button = new JButton("Image Border Test");
        panel.add(button);
        
//        Image upperLeft = new ImageIcon(
//                ImageBorderHack.class.getResource("/upper_left.png")).getImage();
//        Image upper = new ImageIcon(
//                ImageBorderHack.class.getResource("/upper.png")).getImage();
//        Image upperRight = new ImageIcon(
//                ImageBorderHack.class.getResource("/upper_right.png")).getImage();
//        Image leftCenter = new ImageIcon(
//                ImageBorderHack.class.getResource("/left_center.png")).getImage();
//        Image rightCenter = new ImageIcon(
//                ImageBorderHack.class.getResource("/right_center.png")).getImage();
//        Image buttomLeft = new ImageIcon(
//                ImageBorderHack.class.getResource("/bottom_left.png")).getImage();
//        Image buttomCenter = new ImageIcon(
//                ImageBorderHack.class.getResource("/bottom_center.png")).getImage();
//        Image buttomRight = new ImageIcon(
//                ImageBorderHack.class.getResource("/bottom_right.png")).getImage();
//        //
//        ImageBorder image_border = new ImageBorder(upperLeft,
//                                                     upper,
//                                                     upperRight,                
//                                                     leftCenter,
//                                                     rightCenter,
//                                                     buttomLeft,
//                                                     buttomCenter,
//                                                     buttomRight);

        ImageBorder image_border = new ImageBorder(
                new ImageIcon("./images003/upper_left.png").getImage(),
                new ImageIcon("./images003/upper.png").getImage(),
                new ImageIcon("./images003/upper_right.png").getImage(),
                //
                new ImageIcon("./images003/left_center.png").getImage(),
                new ImageIcon("./images003/right_center.png").getImage(),
                //
                new ImageIcon("./images003/bottom_left.png").getImage(),
                new ImageIcon("./images003/bottom_center.png").getImage(),
                new ImageIcon("./images003/bottom_right.png").getImage()
                );
        
        panel.setBorder(image_border);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }
    
}


