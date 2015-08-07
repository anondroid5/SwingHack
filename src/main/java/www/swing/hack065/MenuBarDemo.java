package www.swing.hack065;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * Mac環境のみ
 */
public class MenuBarDemo {
    
    static {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("HACK #65: Menu Bar Demo");
        JMenuBar bar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");
        bar.add(fileMenu);
        bar.add(editMenu);
        bar.add(helpMenu);
        frame.setSize(300, 150);
        frame.setJMenuBar(bar);
        frame.setVisible(true);
    }
    
}
