package www.swing.hack001;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class ImageTest {
    
    public static void main(String[] args) {
        
        // イメージファイルを読み込む
        ImagePanel panel = new ImagePanel(new ImageIcon("./images001/background.png").getImage());
        ImageLabel label = new ImageLabel(new ImageIcon("./images001/reactor.png"));
        
        label.setLocation(29,37);
        panel.add(label);
        
        final ImageButton button = new ImageButton("./images001/button.png");
        button.setPressedIcon(new ImageIcon("./images001/button-down.png"));
        button.setRolloverIcon(new ImageIcon("./images001/button-over.png"));
        button.setSelectedIcon(new ImageIcon("./images001/button-sel.png"));
        button.setRolloverSelectedIcon(new ImageIcon("./images001/button-sel-over.png"));
        button.setDisabledIcon(new ImageIcon("./images001/button-disabled.png"));
        button.setDisabledSelectedIcon(new ImageIcon("./images001/button-disabled-selected.png"));
        
        button.setLocation(60,74);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                button.setSelected(!button.isSelected());
                System.out.println("selecting");
            }
        });
        //button.setSelected(true);
        //button.setDisabled(false);
        panel.add(button);
        
        final JCheckBox checkbox = new JCheckBox("Disable");
        checkbox.setLocation(70,150);
        checkbox.setOpaque(false);
        checkbox.setSize(checkbox.getPreferredSize());
        panel.add(checkbox);
        checkbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                button.setEnabled(!checkbox.isSelected());
            }
        });
        
        JFrame frame = new JFrame("Hack #01: Image Components");
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
