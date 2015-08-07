package www.swing.hack040;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolTip;
import javax.swing.RepaintManager;
import javax.swing.ToolTipManager;


public class ToolTipsHack {
    
    public static void main(String[] args) {
        RepaintManager.setCurrentManager(new FullRepaintManager());
        System.out.println(" light = " + ToolTipManager.sharedInstance().isLightWeightPopupEnabled());
        ToolTipManager.sharedInstance().setLightWeightPopupEnabled(true);
        System.out.println(" light = " + ToolTipManager.sharedInstance().isLightWeightPopupEnabled());
        
        JButton button;
        
        
        JFrame frame = new JFrame("HACK #40: Tool Tips Hack");
        BoxLayout layout =  new BoxLayout(
                frame.getContentPane(),
                BoxLayout.Y_AXIS);
        frame.getContentPane().setLayout(layout);
        
        button = new CustomJButton();
        button.setText("Open");
        button.setToolTipText("Open an existing file");
        frame.getContentPane().add(button);
        
        button = new CustomJButton();
        button.setText("Save");
        button.setToolTipText("Save the currently open file");
        frame.getContentPane().add(button);
        
        
        frame.getContentPane().add(new JLabel("a label"));
        frame.getContentPane().add(new JLabel("a label"));
        frame.getContentPane().add(new JLabel("a label"));
        
        
        frame.pack();
        frame.setSize(400,400);
        frame.setVisible(true);
        System.out.println(" light = " + ToolTipManager.sharedInstance().isLightWeightPopupEnabled());
    }
    
    
}

class CustomJButton extends JButton {
	
	private static final long serialVersionUID = -9042201326785054965L;
	JToolTip _tooltip;
    
    public CustomJButton() {
        _tooltip = new CustomToolTip();
        _tooltip.setComponent(this);
        System.out.println("tooltip = " + _tooltip);
        System.out.println(" tooltip = " + _tooltip.isLightweight());
    }
    
    public JToolTip createToolTip() {
        return _tooltip;
    }
    
}

class CustomToolTip extends JToolTip {

	private static final long serialVersionUID = -2136259869121572140L;
	public CustomToolTip() {
        super();
        this.setOpaque(false);
        System.out.println("light = " + this.isLightweight());
        System.out.println(" light  x = " + ToolTipManager.sharedInstance().isLightWeightPopupEnabled());
    }
    
    public Dimension getPreferredSize() {
        Dimension dim = super.getPreferredSize();
        return new Dimension((int)dim.getWidth()+20,(int)dim.getHeight()+20);
    }
    public void paintComponent(Graphics g) {
        // set the parent opaque
        Component parent = this.getParent();
        if(parent != null) {
            if(parent instanceof JComponent) {
                JComponent jparent = (JComponent)parent;
                if(jparent.isOpaque()) {
                    jparent.setOpaque(false);
                }
            }
        }
        
        // create a round rectangle
        Shape round = new RoundRectangle2D.Float(4,4,
                this.getWidth()-1-8,
                this.getHeight()-1-8,
                15,15);
        
        // draw the white background
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.white);
        g2.fill(round);
        
        // draw the gray border
        g2.setColor(Color.gray);
        g2.setStroke(new BasicStroke(5));
        g2.draw(round);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_DEFAULT);
        
        // draw the text
        String text = this.getComponent().getToolTipText();
        if(text != null) {
            FontMetrics fm = g2.getFontMetrics();
            int h = fm.getAscent();
            g2.setColor(Color.black);
            g2.drawString(text,10,(this.getHeight()+h)/2);
        }
        
    }
    
    public void setToolTipText(String str) {
        super.setToolTipText(str);
        System.out.println("set tooltip text called");
    }
}