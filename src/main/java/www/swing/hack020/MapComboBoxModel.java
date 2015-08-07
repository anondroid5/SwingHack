package www.swing.hack020;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapComboBoxModel extends ListComboBoxModel {
    
    protected Map<String, String> map_data;
    protected List<String> index;
    
    public MapComboBoxModel() {
        this.map_data = new HashMap<String, String>();
        index = new ArrayList<String>();
    }
    
    public MapComboBoxModel(Map<String, String> map) {
        this.map_data = map;
        buildIndex();
        if(index.size() > 0) {
            selected = index.get(0);
        }
    }
    
    protected void buildIndex() {
        index = new ArrayList<String>(map_data.keySet());
    }
    
    
    public Object getElementAt(int i) {
        return index.get(i);
    }
    
    public int getSize() {
        return map_data.size();
    }
    
    
    public void actionPerformed(ActionEvent evt) {
        if(evt.getActionCommand().equals("update")) {
            buildIndex();
            fireUpdate();
        }
    }
    
    public Object getValue(Object selectedItem) {
        return map_data.get(selectedItem);
    }
    public Object getValue(int selectedItem) {
        return getValue(index.get(selectedItem));
    }
}