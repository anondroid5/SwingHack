package www.swing.hack007;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;

// put a texture in the background
// put a translucent image in the foreground

// put a yellow gradient inthe background
// put a translucent sun in the upper right

public class ScrollPaneWatermark extends JViewport {
	
	private static final long serialVersionUID = -5626675840496268953L;
	BufferedImage fgimage, bgimage;
    TexturePaint texture;
    
    public ScrollPaneWatermark(){
        super();
    }
    
    public void setBackgroundTexture(URL url) throws IOException {
        bgimage = ImageIO.read(url);
        Rectangle rect = new Rectangle(0,0,
                bgimage.getWidth(null),bgimage.getHeight(null));
        texture = new TexturePaint(bgimage, rect);
    }
    
    public void setForegroundBadge(URL url) throws IOException {
        fgimage = ImageIO.read(url);
    }
    
    public void paintComponent(Graphics g) {
        // do the superclass behavior first
        super.paintComponent(g);
        
        // paint the texture
        if(texture != null) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setPaint(texture);
            g.fillRect(0,0,getWidth(),getHeight());
        }
    }
    
    public void paintChildren(Graphics g) {
        super.paintChildren(g);
        if(fgimage != null) {
            g.drawImage(fgimage,
                    getWidth()-fgimage.getWidth(null), 0,
                    null);
        }
    }
    
    public void setView(JComponent view) {
        view.setOpaque(false);
        super.setView(view);
    }
    
    /*
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("HACK #07: Scroll Pane Watermark Hack");
        
        JTextArea ta = new JTextArea();
        ta.setText(fileToString(new File("resources/alice.txt")));
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        ta.setOpaque(false);
        
        ScrollPaneWatermark viewport = new ScrollPaneWatermark();
        viewport.setBackgroundTexture(new File("images/clouds.jpg").toURL());
        viewport.setForegroundBadge(new File("images/flyingsaucer.png").toURL());
        viewport.setView(ta);
        viewport.setOpaque(false);
        
        JScrollPane scroll = new JScrollPane();
        scroll.setViewport(viewport);
        
        frame.getContentPane().add(scroll);
        frame.pack();
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }
     */
    
    public static String fileToString( File file )
    throws FileNotFoundException, IOException {
        FileReader reader = new FileReader( file );
        StringWriter writer = new StringWriter();
        char[] buf = new char[1000];
        while ( true ) {
            int n = reader.read( buf, 0, 1000 );
            if ( n == -1 ) {
                break;
            }
            writer.write( buf, 0, n );
        }
        return writer.toString();
    }
}
