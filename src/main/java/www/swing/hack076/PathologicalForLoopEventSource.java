package www.swing.hack076;

import java.util.ArrayList;
import java.util.EventObject;

public class PathologicalForLoopEventSource
        extends TestEventSource {
    
    ArrayList<TestEventListener> listeners = new ArrayList<TestEventListener>();
    
    public void addListener(TestEventListener l) {
        listeners.add(l);
    }
    
    public void removeListener(TestEventListener l) {
        listeners.remove(l);
    }
    
    public void fireEvent(EventObject o) {
        for (int i=0; i<listeners.size(); i++) {
            TestEventListener l = (TestEventListener) listeners.get(i);
            l.handleEvent(o);
        }
    }
    
    public static void main(String[] args) {
        PathologicalForLoopEventSource pfles =
                new PathologicalForLoopEventSource();
        pfles.test();
    }
    
}
