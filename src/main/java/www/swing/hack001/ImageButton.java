package www.swing.hack001;

import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {

	private static final long serialVersionUID = 2836706151496449139L;

	public ImageButton(String img) {
        this(new ImageIcon(img));
    }
    
    public ImageButton(ImageIcon icon) {
        setIcon(icon);
        setMargin(new Insets(0,0,0,0));
        setIconTextGap(0);
        setBorderPainted(false);
        setBorder(null);
        setText(null);
        setSize(icon.getImage().getWidth(null),
                icon.getImage().getHeight(null));
    }
    
}
