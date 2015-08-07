package www.swing.hack028;

import java.io.File;
import javax.swing.JFileChooser;

public class DisplayShortcutTest {
    public static void main(String[] args) throws Exception {
        JFileChooser chooser = new JFileChooser(new File("./Test"));
        chooser.setFileView(new ShortcutFileView());
        chooser.showOpenDialog(null);
    }
}