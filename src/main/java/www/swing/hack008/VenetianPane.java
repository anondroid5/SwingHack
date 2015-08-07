package www.swing.hack008;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;


public class VenetianPane extends TransitionTabbedPane {

	private static final long serialVersionUID = -8901078470150748983L;
	public VenetianPane() {
        super();
        this.setAnimationLength(20);
    }
    public void paintTransition(Graphics2D g2, int step,
            Rectangle size, Image prev) {
        
        int length = getAnimationLength();
        int half = length/2;
        
        // create a blind
        Rectangle blind = new Rectangle();
        
        // calculate the fade out part
        if(step >= 0 && step < half) {
            // draw the saved version of the old tab component
            if(prev != null) {
                g2.drawImage(prev,(int)size.getX(),(int)size.getY(),null);
            }
            // calculate the growing blind
            blind = new Rectangle(
                    (int)size.getX(),
                    (int)size.getY(),
                    step,
                    (int)size.getHeight());
        }
        
        
        // calculate the fade in part
        if(step >= half && step < length) {
            // calculate the shrinking blind
            blind = new Rectangle(
                    (int)size.getX(),
                    (int)size.getY(),
                    length-step,
                    (int)size.getHeight());
            blind.translate(step-half,0);
        }
        
        // draw the blinds
        for(int i=0; i<size.getWidth()/half; i++) {
            g2.setColor(Color.white);
            g2.fill(blind);
            blind.translate(half,0);
        }
        
    }
}