package www.swing.hack037;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;


public class MiniMizeHack implements MouseListener, ActionListener {
    
    public static void main(String[] args) {
        MiniMizeHack mini = new MiniMizeHack();
        mini.frame.pack();
        mini.frame.setSize(300,300);
        mini.frame.setVisible(true);
    }
    
    public JFrame frame;
    public JPanel panel;
    public JPopupMenu popup;
    public JMenuBar menubar;
    public JLabel top;
    public JLabel bottom;
    Dimension normal_size;
    
    public MiniMizeHack() {
        top = new JLabel(new ImageIcon("./images037/image.png"));
        bottom = new JLabel("further info");
        
        frame = new JFrame("HACK #37: Mini Mize Hack");
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add("Center",bottom);
        panel.add("North",top);
        frame.getContentPane().add(panel);
        
        
        menubar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menu.add(new JMenuItem("Open"));
        menu.add(new JMenuItem("Quit"));
        menubar.add(menu);
        
        JMenu window = new JMenu("Window");
        JMenuItem mini = new JMenuItem("Minimize");
        mini.addActionListener(this);
        window.add(mini);
        menubar.add(window);
        frame.setJMenuBar(menubar);
        
        
        popup = new JPopupMenu();
        JMenuItem restore = new JMenuItem("Restore");
        restore.addActionListener(this);
        popup.add(restore);
    }
    
    public void actionPerformed(ActionEvent evt) {
        if(bottom.isVisible()) {
            switchToMini();
        } else {
            switchToNormal();
        }
    }
    
    public void switchToMini() {
        // nuke the old frame and build a new one
        Point location = frame.getLocation();
        normal_size = frame.getSize();
        frame.setVisible(false);
        frame = new JFrame();
        frame.setUndecorated(true);
        frame.getContentPane().add(panel);
        
        // hide the extra components
        bottom.setVisible(false);
        
        // add the popup
        panel.addMouseListener(this);
        
        // stay on top
        //frame.setAlwaysOnTop(true);
        
        // show the frame again
        frame.pack();
        frame.setLocation(location);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public void switchToNormal() {
        // nuke the old frame and build a new one
        Point location = frame.getLocation();
        frame.setVisible(false);
        frame = new JFrame();
        frame.setUndecorated(false);
        frame.getContentPane().add(panel);
        
        // show the extra components
        bottom.setVisible(true);
        frame.setJMenuBar(menubar);
        
        // hide the popup
        panel.removeMouseListener(this);
        
        // turn off stay on top
        //frame.setAlwaysOnTop(false);
        
        // show the frame again
        frame.pack();
        frame.setSize(normal_size);
        frame.setLocation(location);
        frame.setVisible(true);
    }
    
    
    public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
    }
    
    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }
    public void mouseExited(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popup.show(e.getComponent(),
                    e.getX(), e.getY());
        }
    }
}