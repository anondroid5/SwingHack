package www.swing.hack039;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import javax.swing.JButton;
import javax.swing.JFrame;

public class SpinDissolver extends Dissolver {

	private static final long serialVersionUID = 8850909143866245640L;

	public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        // draw the screen, offset in case the window isn't at 0,0
        g.drawImage(screen_buffer,-fullscreen.getX( ),
                -fullscreen.getY( ),null);
        // save the current transform
        AffineTransform old_trans = g2.getTransform( );
        // move to the upper-lefthand corner of the frame
        g2.translate(frame.getX(), frame.getY( ));
        // move the frame off toward the left
        g2.translate(-((count+1) * (frame.getX()+frame.getWidth( ))/20),0);
        // shrink the frame
        float scale = 1f / ((float)count+1);
        g2.scale(scale,scale);
        // rotate around the center
        g2.rotate(((float)count)/3.14/1.3,
                frame.getWidth()/2, frame.getHeight( )/2);
        // finally draw the frame
        g2.drawImage(frame_buffer,0,0,null);
        // restore the current transform
        g2.setTransform(old_trans);
    }
    
public static void main(String[] args) {
        
        final JFrame frame = new JFrame("HACK #39: Dissolve Hack");
        JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new SpinDissolver().dissolveExit(frame);
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