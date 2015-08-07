package www.swing.hack001;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel {
    
	private static final long serialVersionUID = 1488931007153403233L;

	public ImageLabel(String img) {
        this(new ImageIcon(img));
    }
    
    public ImageLabel(ImageIcon icon) {
        setIcon(icon);
        setIconTextGap(0);
        setBorder(null);
        setText(null);
        setOpaque(false);
        setSize(icon.getImage().getWidth(null),
                icon.getImage().getHeight(null));
    }
    
}
