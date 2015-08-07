package www.swing.hack079;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ComponentEvent;
import java.util.HashMap;

public class ComponentMap  extends HashMap implements AWTEventListener {
    
    public ComponentMap() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        tk.addAWTEventListener(this,
            AWTEvent.COMPONENT_EVENT_MASK);
    }
    
    public void eventDispatched(AWTEvent evt) {
        try {
            ComponentEvent ce = (ComponentEvent)evt;
            this.put(ce.getComponent().getName(), ce.getComponent());
            //super.put(ce.getComponent().getName(), ce.getComponent());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}