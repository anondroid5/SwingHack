package www.swing.hack020;

/*
    live collections aware combo box
 
    create demo prog that loads up a set of combo boxes
    load in any list or iterator or set
    load in a map and track keys vs values
 
 
    this is really two hacks?
 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class ListComboBoxModel implements ComboBoxModel, ActionListener {
    
    protected List data;
    protected Object selected;
    protected List<ListDataListener> listeners;
    
    ListComboBoxModel() {
        this.listeners = new ArrayList<ListDataListener>();
        data = new ArrayList();
    }
    
    public ListComboBoxModel(List<String> list) {
        this();
        this.data = list;
        if(list.size() > 0) {
            selected = list.get(0);
        }
    }
    
    public void setSelectedItem(Object item) {
        this.selected = item;
    }
    public Object getSelectedItem() {
        return this.selected;
    }
    
    public Object getElementAt(int index) {
        return data.get(index);
    }
    
    public int getSize() {
        return data.size();
    }
    
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }
    public void removeListDataListener(ListDataListener l) {
        this.listeners.remove(l);
    }
    
    public void actionPerformed(ActionEvent evt) {
        if(evt.getActionCommand().equals("update")) {
            this.fireUpdate();
        }
    }
    
    public void fireUpdate() {
        ListDataEvent le = new ListDataEvent(this,
                ListDataEvent.CONTENTS_CHANGED,
                0,
                data.size());
        for(int i=0; i<listeners.size(); i++) {
            ListDataListener l = listeners.get(i);
            l.contentsChanged(le);
        }
    }
    
}