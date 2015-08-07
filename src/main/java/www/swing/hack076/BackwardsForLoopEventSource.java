package www.swing.hack076;

import java.util.ArrayList;
import java.util.EventObject;

public class BackwardsForLoopEventSource
        extends TestEventSource {
    
    ArrayList<TestEventListener> listeners = new ArrayList<TestEventListener>();
    
    public void addListener(TestEventListener l) {
        listeners.add(l);
    }
    
    public void removeListener(TestEventListener l) {
        listeners.remove(l);
    }
    
    public void fireEvent(EventObject o) {
        for (int i=listeners.size()-1; i>=0; i--) {
            TestEventListener l = (TestEventListener) listeners.get(i);
            l.handleEvent(o);
        }
    }
    
    public static void main(String[] args) {
        BackwardsForLoopEventSource bfles =
                new BackwardsForLoopEventSource();
        bfles.test();
    }
}
