package www.swing.hack050;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RepaintManager;

public class AntiAliasedPanel extends JPanel {
    
	private static final long serialVersionUID = -2831183626721786641L;

	public void paintChildren(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        super.paintChildren(g2);
    }
    
    public static void main(String[] args) {
        RepaintManager.setCurrentManager(new FullRepaintManager());
        JPanel panel = new AntiAliasedPanel();
        JFrame frame = new JFrame("HACK #50: Anti-Aliased text");
        frame.getContentPane().add(panel);
        
        JLabel label = new JLabel("This is anti-aliased text");
        label.setFont(label.getFont().deriveFont(40f));
        panel.add(label);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}