package www.swing.hack008;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class TabFadeTest {
    
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("HACK #08: Fade Tabs");
             
        // JTabbedPane tab = new InOutPane(); // Tab transition at mid-point
        JTabbedPane tab = new VenetianPane();
        
        tab.addTab("t1",new JButton("Test Button 1"));
        tab.addTab("t2",new JButton("Test Button 2"));
        
        frame.getContentPane().add(tab);
        frame.pack();
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
    
}