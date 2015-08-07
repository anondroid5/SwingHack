package www.swing.hack019;

import java.awt.Component;
import java.lang.reflect.Method;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

public class GenericListCellRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 2509249078379566868L;
	protected String method;
    public GenericListCellRenderer(String method) {
        super();
        this.method = method;
    }
    
    
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
        
        JLabel label = (JLabel)super.getListCellRendererComponent(
                list,value,index, isSelected, cellHasFocus);
        
        try {
            // Method meth = value.getClass().getMethod(method, (Class[])null);
             Method meth = value.getClass().getMethod(method);
            if(meth != null) {
                Object retval = meth.invoke(value);
                label.setText(""+retval);
            }
        } catch (Exception ex) {
            System.out.println("got an execption: " + ex);
            ex.printStackTrace();
        }
        return label;        
    }
    
    
    public static void main(String[] args) {
        String[] data = { "Proton", "Neutron",  "Electron" };
        JList list = new JList(data);
        
//        GenericListCellRenderer renderer =
//                new GenericListCellRenderer("toString");
//        GenericListCellRenderer renderer =
//                new GenericListCellRenderer("length");
        GenericListCellRenderer renderer =
            new GenericListCellRenderer("hashCode");
        list.setCellRenderer(renderer);
        
        JFrame frame = new JFrame("HACK #19: Cell Renderer Hack");
        frame.getContentPane().add(list);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
