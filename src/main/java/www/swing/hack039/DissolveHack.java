package www.swing.hack039;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class DissolveHack {
    
    public static void main(String[] args) {
        
        final JFrame frame = new JFrame("HACK #39: Dissolve Hack");
        JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new Dissolver().dissolveExit(frame);
            }
        });
        
        frame.getContentPane().add(quit);
        frame.pack();
        frame.setLocation(300,300);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
}


class Dissolver extends JComponent implements Runnable {
    
	private static final long serialVersionUID = -3555148647090125032L;

	public Dissolver() {
    }
    public void dissolveExit(JFrame frame) {
        try {
            this.frame = frame;
            Robot robot = new Robot();
            
            // cap screen w/ frame to frame buffer
            Rectangle frame_rect = frame.getBounds();
            frame_buffer = robot.createScreenCapture(frame_rect);
            
            // hide frame
            frame.setVisible(false);
            
            // cap screen w/o frame
            Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle screen_rect = new Rectangle(0,0,screensize.width, screensize.height);
            screen_buffer = robot.createScreenCapture(screen_rect);
            
            // create big window w/o decorations
            fullscreen = new Window(new JFrame());
            fullscreen.setSize(screensize);
            fullscreen.add(this);
            this.setSize(screensize);
            fullscreen.setVisible(true);
            // start animation
            new Thread(this).start();
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
    
    Frame frame;
    Window fullscreen;
    BufferedImage frame_buffer;
    BufferedImage screen_buffer;
    int count;
    
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        // draw the screen, offset in case the window isn't at 0,0
        g.drawImage(screen_buffer,-fullscreen.getX(),-fullscreen.getY(),null);
        
        // draw the frame
        Composite old_comp = g2.getComposite();
        Composite fade = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f-((float)count)/20f);
        g2.setComposite(fade);
        g2.drawImage(frame_buffer,frame.getX(),frame.getY(),null);
        g2.setComposite(old_comp);
    }
    
    public void run() {
        try {
            count = 0;
            Thread.currentThread().sleep(100);
            for(int i=0; i<20; i++) {
                count = i;
                fullscreen.repaint();
                Thread.currentThread().sleep(100);
            }
        } catch (InterruptedException ex) {
        }
        System.exit(0);
    }
    
}


class SpinDissolver2 extends Dissolver {
    
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        // draw the screen, offset in case the window isn't at 0,0
        g.drawImage(screen_buffer,-fullscreen.getX(),-fullscreen.getY(),null);
        
        // save the current transform
        AffineTransform old_trans = g2.getTransform();
        
        // move to the upper left hand corner of the frame
        g2.translate(frame.getX(), frame.getY());
        
        // move the frame off towards the left
        g2.translate(-((count+1) * (frame.getX()+frame.getWidth())/20),0);
        
        // shrink the frame
        float scale = 1f / ((float)count+1);
        g2.scale(scale,scale);
        
        // rotate around the center
        g2.rotate(((float)count)/3.14/1.3, frame.getWidth()/2, frame.getHeight()/2);
        
        // finally draw the frame
        g2.drawImage(frame_buffer,0,0,null);
        
        // restore the current transform
        g2.setTransform(old_trans);
    }
}