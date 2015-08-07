package www.swing.hack078;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ComponentGlassPane extends JComponent {
    
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("HACK #78: Component Boundary Glasspane");
        
        Container root = frame.getContentPane();
        root.setLayout(new BoxLayout(root,BoxLayout.Y_AXIS));
        final JButton activate = new JButton("Show component boundaries");
        root.add(activate);
        root.add(new JLabel("Juice Settings"));
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.add(new JLabel("Flavor"));
        panel.add(new JTextField("          "));
        root.add(panel);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        final ComponentGlassPane glass = new ComponentGlassPane(frame);
        frame.setGlassPane(glass);
        
        activate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                glass.setVisible(true);
            }
        });
        
    }
    
    
    private JFrame frame;
    private Point cursor;
    public ComponentGlassPane(JFrame frame) {
        this.frame = frame;
        cursor = new Point();
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent evt) {
                cursor = new Point(evt.getPoint());
                ComponentGlassPane.this.repaint();
            }
        });
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                ComponentGlassPane.this.setVisible(false);
            }
        });
    }
    
    public void paint(Graphics g) {
        Container root = frame.getContentPane();
        rPaint(root,g);
    }
    
    private void rPaint(Component comp, Graphics g) {
        int x = comp.getX();
        int y = comp.getY();
        g.translate(x,y);
        cursor.translate(-x,-y);
        
        int w = comp.getWidth();
        int h = comp.getHeight();
        
        // 背景を描画
        g.setColor(new Color(1.0f, 0.5f, 0.5f, 0.3f));
        g.fillRect(0,0,w,h);
        g.setColor(Color.red);
        g.drawRect(0,0,w,h);
        
        // このコンポーネント上にマウスカーソルがある場合
        if(comp.contains(cursor)) {
            // テキストの描画
            String cls_name = comp.getClass().getName();
            Graphics2D g2 = (Graphics2D)g;
            Font fnt = g.getFont();
            FontMetrics fm = g.getFontMetrics();
            int text_width = fm.stringWidth(cls_name);
            int text_height = fm.getHeight();
            int text_ascent = fm.getAscent();
            
            // テキストの背景を描画
            g.setColor(new Color(1f,1f,1f,0.7f));
            g.fillRect(0,0,text_width,text_height);
            g.setColor(Color.white);
            g.drawRect(0,0,text_width,text_height);
            
            // テキストを描く
            g.setColor(Color.black);
            g.drawString(cls_name, 0, 0+text_ascent);
        }
        
        if(comp instanceof Container) {
            Container cont = (Container)comp;
            for(int i=0; i<cont.getComponentCount(); i++) {
                Component child = cont.getComponent(i);
                rPaint(child,g);
            }
        }
        
        cursor.translate(x,y);
        g.translate(-x,-y);
    }
    
}



