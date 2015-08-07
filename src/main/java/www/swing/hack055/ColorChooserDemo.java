package www.swing.hack055;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorChooserDemo extends JFrame
        implements MouseListener, MouseMotionListener {
    
	private static final long serialVersionUID = -4067506448470474658L;
	JPanel image_panel;
    Dimension screen_size;
    JComponent comp = null;
    Image background_image = null;
    Robot robot;
    JLabel label;
    
    public ColorChooserDemo(JComponent comp) {
        // get the screen dimensions
        screen_size = Toolkit.getDefaultToolkit().getScreenSize();
        
        // setup the frame (this)
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.comp = comp;
        this.setUndecorated(true);
        this.setSize(screen_size.width, screen_size.height);
        
        // set up the panel that holds the screenshot
        image_panel = new JPanel() {

			private static final long serialVersionUID = -4668020613102075761L;

			public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background_image,0,0,null);
            }
        };
        image_panel.setPreferredSize(screen_size);
        image_panel.setLayout(null);
        this.getContentPane().add(image_panel);
        
        // setup the display label
        label = new JLabel("Selected Color");
        label.setOpaque(true);
        label.setSize(100,100);
        image_panel.add(label);
        label.setLocation((int)screen_size.getWidth()/2 - 50,
                (int)screen_size.getHeight()/2 - 50);
    }
    
//    public void show() {
//        try {
//            // make the screenshot before showing the frame
//            Rectangle rect = new Rectangle(0,0,
//                    (int)screen_size.getWidth(),
//                    (int)screen_size.getHeight());
//            this.robot = new Robot();
//            background_image = robot.createScreenCapture(rect);
//            super.setVisible(true);
//        } catch (AWTException ex) {
//            System.out.println("exception creating screenshot:");
//            ex.printStackTrace();
//        }
//    }
    
    public void setVisible(boolean visible) {
        if(visible) {
            try {
                // make the screenshot before showing the frame
                Rectangle rect = new Rectangle(0,0,
                        (int)screen_size.getWidth(),
                        (int)screen_size.getHeight());
                this.robot = new Robot();
                background_image = robot.createScreenCapture(rect);
                super.setVisible(true);
            } catch (AWTException ex) {
                System.out.println("exception creating screenshot:");
                ex.printStackTrace();
            }
        } else {
            super.setVisible(false);
        }
    }
    
    
    // update both the display label and the component that was passed in
    public void setSelectedColor(Color color) {
        comp.setBackground(color);
        label.setBackground(color);
    }
    // update the selected color on mouse press, dragged, and release
    public void mousePressed(MouseEvent evt) {
        setSelectedColor(robot.getPixelColor(evt.getX(), evt.getY()));
    }
    public void mouseDragged(MouseEvent evt) {
        setSelectedColor(robot.getPixelColor(evt.getX(), evt.getY()));
    }
    // for released we want to hide the frame as well
    public void mouseReleased(MouseEvent evt) {
        setSelectedColor(robot.getPixelColor(evt.getX(),evt.getY()));
        this.setVisible(false);
    }
    
    
    // no-ops for the rest of the mouse event listener
    public void mouseClicked(MouseEvent evt) { }
    public void mouseEntered(MouseEvent evt) { }
    public void mouseExited(MouseEvent evt) { }
    public void mouseMoved(MouseEvent evt) { }
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("HACK #55: Color Chooser Hack");
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


