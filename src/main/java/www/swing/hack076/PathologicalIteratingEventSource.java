package www.swing.hack076;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;

public class PathologicalIteratingEventSource
        extends TestEventSource {
    
    ArrayList<TestEventListener> listeners = new ArrayList<TestEventListener>();
    
    public void addListener(TestEventListener l) {
        listeners.add(l);
    }
    
    public void removeListener(TestEventListener l) {
        listeners.remove(l);
    }
    
    public void fireEvent(EventObject o) {
//        for(TestEventListener l : listeners) {
//            l.handleEvent(o);
//        }
          
        Iterator<TestEventListener> it = listeners.iterator();
        while (it.hasNext()) {
            TestEventListener l = it.next();
            l.handleEvent(o);
        }
    }
    
    public static void main(String[] args) {
        PathologicalIteratingEventSource pies =
                new PathologicalIteratingEventSource();
        pies.test();
    }
    
    
}
