package www.swing.hack055;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*
 * ColorChooserDemo2.java
 *
 * Created on 2006/02/23, 11:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author anondroid5
 */
public class ColorChooserDemo2 extends JFrame {
    
	private static final long serialVersionUID = 6524522492586864184L;
	JPanel image_panel;
    Dimension screen_size;
    JComponent comp = null;
    Image background_image = null;
    Robot robot;
    JLabel label;
    
    
    /** Creates a new instance of ColorChooserDemo2 */
    public ColorChooserDemo2(JComponent comp) {
        this.comp = comp;
    }
    
    // update both the display label and the component that was passed in
    public void setSelectedColor(Color color) {
        comp.setBackground(color);
        label.setBackground(color);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("HACK #55: Color Chooser Hack(JDK1.5)");
        final JButton button = new JButton("Click to choose a color");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JFrame frame = new ColorChooserDemo(button);
                frame.setVisible(true);
            }
        });
        
        frame.getContentPane().add(button);
        frame.pack();
        frame.setVisible(true);
    }  
}
