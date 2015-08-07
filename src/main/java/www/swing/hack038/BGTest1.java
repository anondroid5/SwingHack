package www.swing.hack038;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BGTest1 {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("HACK #38 Transparent Window");
        TransparentBackground bg = new TransparentBackground(frame);
        bg.setLayout(new BorderLayout());
        JButton button = new JButton("This is a button");
        bg.add("North",button);
        JLabel label = new JLabel("This is a label");
        bg.add("South",label);
        frame.getContentPane().add("Center",bg);
        frame.pack();
        frame.setSize(150,100);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}