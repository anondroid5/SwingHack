package www.swing.hack010;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.*;
import javax.swing.plaf.metal.*;

public class DropDownComponent extends JComponent
        implements ActionListener, AncestorListener {
    
	private static final long serialVersionUID = 6047640793208629939L;
	protected JComponent drop_down_comp;
    protected JComponent visible_comp;
    protected JButton arrow;
    protected JWindow popup;
    
    public DropDownComponent(JComponent vcomp, JComponent ddcomp) {
        drop_down_comp = ddcomp;
        visible_comp = vcomp;
        
        arrow = new JButton(new MetalComboBoxIcon());
        Insets insets = arrow.getMargin();
        arrow.setMargin( new Insets( insets.top, 1, insets.bottom, 1 ) );
        setupLayout();
        
        arrow.addActionListener(this);
        addAncestorListener(this);
    }
    
    protected void setupLayout() {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gbl);
        
        c.weightx = 1.0;  c.weighty = 1.0;
        c.gridx = 0;  c.gridy = 0;
        c.fill = c.BOTH;
        gbl.setConstraints(visible_comp,c);
        add(visible_comp);
        
        c.weightx = 0;
        c.gridx++;
        gbl.setConstraints(arrow,c);
        add(arrow);
    }
    
    
    public void actionPerformed(ActionEvent evt) {
        // build popup window
        popup = new JWindow(getFrame(null));
        popup.getContentPane().add(drop_down_comp);
        popup.addWindowFocusListener(new WindowAdapter() {
            public void windowLostFocus(WindowEvent evt) {
                popup.setVisible(false);
            }
        });
        popup.pack();
        
        // show the popup window
        Point pt = visible_comp.getLocationOnScreen();
        System.out.println("pt = " + pt);
        pt.translate(visible_comp.getWidth()-popup.getWidth(),visible_comp.getHeight());
        //pt.translate(0,visible_comp.getHeight());
        System.out.println("pt = " + pt);
        popup.setLocation(pt);
        popup.toFront();
        popup.setVisible(true);
        popup.requestFocusInWindow();
    }
    
    protected Frame getFrame(Component comp) {
        if(comp == null) {
            comp = this;
        }
        if(comp.getParent() instanceof Frame) {
            return (Frame)comp.getParent();
        }
        return getFrame(comp.getParent());
    }
    
    public void ancestorAdded(AncestorEvent event){
        hidePopup();
    }
    
    public void ancestorRemoved(AncestorEvent event){
        hidePopup();
    }
    
    public void ancestorMoved(AncestorEvent event){
        if (event.getSource() != popup) {
            hidePopup();
        }
    }
    
    public void hidePopup() {
        if(popup != null && popup.isVisible()) {
            popup.setVisible(false);
        }
    }
}