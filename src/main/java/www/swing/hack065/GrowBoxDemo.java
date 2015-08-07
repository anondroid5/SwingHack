package www.swing.hack065;

import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * Mac環境のみ
 */
public class GrowBoxDemo {
    
    static {
        System.setProperty("apple.awt.showGrowBox", "true");
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("HACK #65: Grow Box Demo");
        JTextField field =
                new JTextField("Intruder Alert! Intruder Alert!");
        frame.getContentPane().add(field, BorderLayout.CENTER);
        frame.getContentPane().add(Box.createVerticalStrut(15),
                BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
    
}
