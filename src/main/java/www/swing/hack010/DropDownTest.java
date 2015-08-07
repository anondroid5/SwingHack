package www.swing.hack010;

import java.awt.BorderLayout;
import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DropDownTest extends JPanel {
    
	private static final long serialVersionUID = -7545375567273661450L;

	public static void main(String[] args) {
        
        final JButton status = new JButton("Color");
        final JPanel panel = new ColorSelectionPanel();
        final DropDownComponent dropdown = new DropDownComponent(status,panel);
        panel.addPropertyChangeListener("selectedColor",new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                dropdown.hidePopup();
                status.setBackground((Color)evt.getNewValue());
            }
        });
        
        JFrame frame = new JFrame("HACK #10: Drop Down Test");
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(  ));
        frame.getContentPane().add("North",dropdown);
        frame.getContentPane().add("Center",new JLabel("Drop Down Test"));
        frame.pack();
        frame.setSize(300,300);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
    
}