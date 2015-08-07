package www.swing.hack066;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * Mac環境のみ
 */
public class MacITunes implements ActionListener {
    public static void main(String[] args) {
        JFrame frame = new JFrame("HACK #66: Mac iTunes Hack");
        JButton button = new JButton("Play/Pause");
        button.addActionListener(new MacITunes());
        
        frame.getContentPane().add(button);
        frame.pack();
        frame.setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent evt) {
        try {
            Runtime rt = Runtime.getRuntime();
            String[] args = { "osascript", "-e","tell app \"iTunes\" to playpause"};
//			String[] args = { "osascript", "-e","tell app \"iTunes\" to artist of current track as string"};
            System.out.println("running: " + args[0] + " " + args[1] + " " + args[2]);
            final Process proc = rt.exec(args);
            
                        /*
                        new Thread(new Runnable() {
                                public void run() {
                                        printStream(proc.getErrorStream());
                                }
                        }).start();
                        new Thread(new Runnable() {
                                public void run() {
                                        printStream(proc.getInputStream());
                                }
                        }).start();
                         */
            
            InputStream in = proc.getInputStream();
//            String str = new DataInputStream(in).readLine();
            
            /* JDK1.4 */
//            DataInputStream dis = new DataInputStream(in);
//            String str = dis.readLine();
            /* JDK1.5 */
            BufferedReader d = new BufferedReader(new InputStreamReader(in)); 
            String str = d.readLine();
            
            System.out.println("got: " + str);
            
        } catch (IOException ex) {
            System.out.println("exception : " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public static void printStream(InputStream stream) {
        try {
            byte[] buff = new byte[256];
            while(true) {
                int n = stream.read(buff);
                if(n == -1) {
                    break;
                }
                System.out.println(new String(buff,0,n));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
