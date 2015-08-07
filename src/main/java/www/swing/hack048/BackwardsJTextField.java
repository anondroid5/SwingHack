package www.swing.hack048;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.Document;

public class BackwardsJTextField extends JTextField {
    
	private static final long serialVersionUID = 1866266141042182292L;


	public BackwardsJTextField() { super(); }
    public BackwardsJTextField(Document doc, String text, int columns) {
        super(doc, text, columns);
    }
    public BackwardsJTextField(int columns) { super(columns); }
    public BackwardsJTextField(String text) { super(text);}
    public BackwardsJTextField(String text, int columns) {
        super(text, columns);
    }
    
    public void paint(Graphics g) {
        if (g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D) g;
            AffineTransform flipTrans = new AffineTransform();
            double widthD = (double) getWidth();
            flipTrans.setToTranslation(widthD, 0);
            flipTrans.scale(-1.0, 1);
            g2.transform(flipTrans);
            super.paint(g);
        } else {
            super.paint(g);
        }
    }
    
    
    public static void main(String[] args) {
        BackwardsJTextField field =
                new BackwardsJTextField("Through the looking glass", 50);
        JFrame frame = new JFrame("HACK #48: Backwards Text");
        frame.getContentPane().add(field);
        frame.pack();
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}