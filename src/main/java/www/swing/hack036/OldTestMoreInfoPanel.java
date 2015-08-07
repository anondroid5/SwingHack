package www.swing.hack036;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class OldTestMoreInfoPanel {
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JLabel label =
                new JLabel("This is a simple JLabel as the basic contents");
        JTextArea area =
                new JTextArea("These are the contents of the more info " +
                "component.  They're made visible by clicking " +
                "on the little triangle component, which " +
                "catches the event and makes the component " +
                "visible or invisible, based on its previous " +
                "state",
                5, 35);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        JScrollPane scroller =
                new JScrollPane(area,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        MoreInfoPanel mip = new MoreInfoPanel(label, scroller);
        frame.getContentPane().add(mip);
        frame.pack();
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}