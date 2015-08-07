package www.swing.hack044;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class Demo extends JFrame {

	private static final long serialVersionUID = -6239873030350458159L;
	protected AnimatedPanel animated;
    protected InfiniteProgressPanel glassPane;
    protected CardLayout carder;
    protected JPanel cardPane;
    
    public Demo() {
        super("Infinite Progress Demo");
        
        build();
        
        pack();
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    protected void build() {
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.CENTER, buildTabbedPane());
    }
    
    protected Container buildTabbedPane() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Animated", buildAnimatedPanel());
        tabbedPane.add("Infinite", buildInfinitePanel());
        return tabbedPane;
    }
    
    protected Container buildAnimatedPanel() {
        carder = new CardLayout();
        cardPane = new JPanel(carder);
        
        JPanel pane = new JPanel(new BorderLayout());
        JTable table = new JTable(new CountTableModel());
        JScrollPane scrollPane = new JScrollPane(table);
        pane.add(BorderLayout.CENTER, scrollPane);
        
        JPanel buttons = new JPanel();
        JButton button = new JButton("Start");
        buttons.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                carder.show(cardPane, "animation");
                animated.start();
                
                Thread performer = new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ie) { }
                        
                        animated.stop();
                        carder.show(cardPane, "form");
                    }
                }, "Performer");
                performer.start();
            }
        });
        pane.add(BorderLayout.SOUTH, buttons);
        
        // animated = new AnimatedPanel("Waiting in vain...", UIHelper.readImageIcon("images/network.png"));
        ImageIcon icon = UIHelper.readImageIcon("images/network.png");
        animated = new AnimatedPanel("Waiting in vain...", icon);
        
        animated.setFont(animated.getFont().deriveFont(Font.BOLD, 16));
        
        cardPane.add("form", pane);
        cardPane.add("animation", animated);
        return cardPane;
    }
    
    protected Container buildInfinitePanel() {
        JPanel pane = new JPanel(new BorderLayout());
        
        glassPane = new InfiniteProgressPanel();
        setGlassPane(glassPane);
        
        JTable table = new JTable(new CountTableModel());
        JScrollPane scrollPane = new JScrollPane(table);
        pane.add(BorderLayout.CENTER, scrollPane);
        
        JPanel buttons = new JPanel();
        JButton button = new JButton("Start");
        buttons.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                glassPane.start();
                Thread performer = new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException ie) { }
                        glassPane.stop();
                    }
                }, "Performer");
                performer.start();
            }
        });
        pane.add(BorderLayout.SOUTH, buttons);
        
        return pane;
    }
    
    private class CountTableModel extends AbstractTableModel {

		private static final long serialVersionUID = -4142954205238902883L;
		public int getColumnCount() { return 10; }
        public int getRowCount() { return 10; }
        public Object getValueAt(int row, int col) { return new Integer((row + 1) * (col + 1)); }
    }
    
    public static void main(String[] args) {
        Demo d = new Demo();
        d.setVisible(true);
    }
}