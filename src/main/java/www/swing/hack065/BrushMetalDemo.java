package www.swing.hack065;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Mac環境のみ
 */
public class BrushMetalDemo {
    
    static {
        System.setProperty("apple.awt.brushMetalLook", "true");
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("HACK #65: Brushed Metal");
        frame.getContentPane().add(new JLabel("It's so shiny!"));
        frame.setSize(200, 200);
        frame.setVisible(true);
    }
    
}
