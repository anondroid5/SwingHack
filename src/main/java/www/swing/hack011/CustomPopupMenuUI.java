package www.swing.hack011;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.*;

public class CustomPopupMenuUI extends BasicPopupMenuUI {
    public static ComponentUI createUI(JComponent c) {
        return new CustomPopupMenuUI();
    }
    
    public Popup getPopup(JPopupMenu popup, int x, int y) {
        Popup pp = super.getPopup(popup,x,y);
        JPanel panel = (JPanel)popup.getParent();
        panel.setBorder(new ShadowBorder(3,3));
        panel.setOpaque(false);
        return pp;
    }
}

class ShadowBorder extends AbstractBorder {
	
	private static final long serialVersionUID = 7548676154981828359L;
	int xoff, yoff;
    Insets insets;
    public ShadowBorder(int x, int y) {
        this.xoff = x;
        this.yoff = y;
        insets = new Insets(0,0,xoff,yoff);
    }
    
    public Insets getBorderInsets( Component c ) {
        return insets;
    }
    
    public void paintBorder(Component comp, Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.black);
        g.translate(x,y);
        g.fillRect(width-xoff, yoff, xoff, height-yoff);
        g.fillRect(xoff, height-yoff, width-xoff, yoff);
        g.translate(-x,-y);
    }
}
