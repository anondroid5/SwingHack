package www.swing.hack042;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SheetTest extends Object
        implements PropertyChangeListener {
    
    JOptionPane optionPane;
    AniSheetableJFrame frame;
    
    public static void main(String[] args) {
        new SheetTest();
    }
    
    public SheetTest() {
        frame = new AniSheetableJFrame("HACK #42: Animating a sheet dialog");
        // put an image in the frame's content pane
        ImageIcon icon = new ImageIcon("./images042/keagy-lunch.png");
        JLabel label = new JLabel(icon);
        frame.getContentPane().add(label);
        // build JOptionPane dialog and hold onto it
        optionPane = new JOptionPane("Do you want to save?",
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION);
        frame.pack();
        frame.setVisible(true);
        optionPane.addPropertyChangeListener(this);
        // pause for effect, then show the sheet
        try {Thread.sleep(1000);} catch (InterruptedException ie) {}
        JDialog dialog =
                optionPane.createDialog(frame, "irrelevant");
        frame.showJDialogAsSheet(dialog);
        
    }
    
    public void propertyChange(PropertyChangeEvent pce) {
        if (pce.getPropertyName().equals(JOptionPane.VALUE_PROPERTY)) {
            System.out.println("Selected option " +
                    pce.getNewValue());
            frame.hideSheet();
        }
    }
    
}