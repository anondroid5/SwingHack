package www.swing.hack047;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TestCompletableJTextField extends JPanel
        implements ActionListener {
    
	private static final long serialVersionUID = -7164308061965747562L;
	CompletableJTextField completableField;
    JTextField completionField;
    
    public TestCompletableJTextField() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        completableField = new CompletableJTextField(75);
        add(completableField);
        JPanel bottom = new JPanel();
        bottom.add(new JLabel("Completion:"));
        completionField = new JTextField(40);
        completionField.addActionListener(this);
        bottom.add(completionField);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this);
        bottom.add(addButton);
        add(bottom);
    }
    
    public void actionPerformed(ActionEvent e) {
        completableField.addCompletion(completionField.getText());
        completionField.setText("");
    }
    
    public static void main(String[] main) {
        JFrame f = new JFrame("HACK #47: Completions...");
        f.getContentPane().add(new TestCompletableJTextField());
        f.pack();
        f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    
    
}