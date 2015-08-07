package www.swing.hack065;

import javax.swing.JFrame;

/**
 * Mac環境のみ
 */
public class MenuNameDemo {
    
    static {
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "MyDemo");
    }
    
    public static void main(String[] args) {
        // meaningless - just want to keep the app
        // hanging around
        JFrame frame = new JFrame("HACK #65: Empty JFrame");
        frame.setVisible(true);
    }    
}
