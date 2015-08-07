package www.swing.hack047;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CompletableJTextField extends JTextField
        implements ListSelectionListener {
    
	private static final long serialVersionUID = 3563500563523604047L;
	Completer completer;
    JList completionList;
    DefaultListModel completionListModel;
    JScrollPane listScroller;
    JWindow listWindow;
    
    public CompletableJTextField(int col) {
        super(col);
        completer = new Completer();
        completionListModel = new DefaultListModel();
        completionList = new JList(completionListModel);
        completionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        completionList.addListSelectionListener(this);
        listScroller =
                new JScrollPane(completionList,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        listWindow = new JWindow();
        listWindow.getContentPane().add(listScroller);
    }
    
    public void addCompletion(String s) {
        completer.addCompletion(s); }
    
    public void removeCompletion(String s) {
        completer.removeCompletion(s); }
    
    public void clearCompletions(String s ) {
        completer.clearCompletions(); }
    
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) { return; }
        if (completionList.getModel().getSize() == 0) {return;}
        listWindow.setVisible(false);
        
        /*
        String completionString =
            (String) completionList.getSelectedValue();
        // this causes an IllegalStateException:
        // "Attempt to mutate in notification"
        setText (completionString);
         */
        
        final String completionString =
                (String) completionList.getSelectedValue();
        Thread worker = new Thread() {
            public void run() {
                setText(completionString);
            }
        };
        SwingUtilities.invokeLater(worker);
    }
    
    /** inner class does the matching of the JTextField's
     * document to completion strings kept in an ArrayList
     */
    class Completer implements DocumentListener {
        private Pattern pattern;
        private ArrayList<String> completions;
        public Completer() {
            completions = new ArrayList<String>();
            getDocument().addDocumentListener(this);
        }
        
        public void addCompletion(String s) {
            completions.add(s);
            buildAndShowPopup();
        }
        
        public void removeCompletion(String s) {
            completions.remove(s);
            buildAndShowPopup();
        }
        
        public void clearCompletions() {
            completions.clear();
            buildPopup();
            listWindow.setVisible(false);
        }
        
        private void buildPopup() {
            completionListModel.clear();
            System.out.println("buildPopup for " + completions.size() + " completions");
            pattern = Pattern.compile(getText() + ".+");
            
            for(String completion: completions) {
                Matcher matcher = pattern.matcher(completion);
                 if (matcher.matches()) {
                    System.out.println("matched "+ completion);
                    completionListModel.add(completionListModel.getSize(), completion);
                } else {
                    System.out.println("pattern " + pattern.pattern() +
                                        " does not match " + completion);
                }
            }
 
//            Iterator<String> it = completions.iterator();           
//            while (it.hasNext()) {
//                // check if match
//                String completion = it.next();
//                Matcher matcher = pattern.matcher(completion);
//                if (matcher.matches()) {
//                    // add if match
//                    System.out.println("matched "+ completion);
//                    completionListModel.add(completionListModel.getSize(),
//                            completion);
//                } else {
//                    System.out.println("pattern " +
//                            pattern.pattern() +
//                            " does not match " +
//                            completion);
//                }
//            }
 
        }
        
        private void showPopup() {
            if (completionListModel.getSize() == 0) {
                listWindow.setVisible(false);
                return;
            }
            // figure out where the text field is,
            // and where its bottom left is
            java.awt.Point los = getLocationOnScreen();
            int popX = los.x;
            int popY = los.y + getHeight();
            listWindow.setLocation(popX, popY);
            listWindow.pack();
            listWindow.setVisible(true);
        }
        
        private void buildAndShowPopup() {
            if (getText().length() < 1)
                return;
            buildPopup();
            showPopup();
        }
        
        // DocumentListener implementation
        public void insertUpdate(DocumentEvent e) { buildAndShowPopup(); }
        public void removeUpdate(DocumentEvent e) { buildAndShowPopup(); }
        public void changedUpdate(DocumentEvent e) { buildAndShowPopup(); }
        
    }
    
}