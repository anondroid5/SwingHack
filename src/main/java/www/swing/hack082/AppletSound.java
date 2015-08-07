package www.swing.hack082;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AppletSound extends Applet
        implements ActionListener {
    
    JButton fileButton, loadButton, playButton, loopButton, stopButton;
    JLabel urlLabel;
    JTextField urlField;
    AudioClip clip;
    
    
    public AppletSound() {
        setLayout(new GridLayout(2,1));
        // first row layout
        JPanel topPanel = new JPanel();
        urlLabel = new JLabel("URL:");
        topPanel.add(urlLabel);
        urlField = new JTextField(25);
        urlField.addActionListener(this);
        topPanel.add(urlField);
        loadButton = new JButton("Load");
        loadButton.addActionListener(this);
        topPanel.add(loadButton);
        fileButton = new JButton("File");
        fileButton.addActionListener(this);
        topPanel.add(fileButton);
        add(topPanel);
        
        // second row layout
        JPanel bottomPanel = new JPanel();
        playButton = new JButton("Play");
        playButton.addActionListener(this);
        bottomPanel.add(playButton);
        stopButton = new JButton("Stop");
        stopButton.addActionListener(this);
        bottomPanel.add(stopButton);
        loopButton = new JButton("Loop");
        loopButton.addActionListener(this);
        bottomPanel.add(loopButton);
        add(bottomPanel);
    }
    
    public void stop() {
        clip.stop();
    }
    
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == fileButton) {
            JFileChooser chooser = new JFileChooser();
            int pick = chooser.showOpenDialog(this);
            if (pick == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = chooser.getSelectedFile();
                    urlField.setText(file.toURL().toString());
                } catch (MalformedURLException murle) {
                    murle.printStackTrace();
                }
            }
        } else if (source == loadButton ) {
            try {
                System.out.println("field: " + urlField.getText());
                URL clipURL = new URL(urlField.getText());
                System.out.println("loading " + clipURL);
                clip = getAudioClip(clipURL);
                System.out.println("got clip");
            } catch (MalformedURLException murle) {
                murle.printStackTrace();
            }
        } else if (source == playButton ) {
            clip.play();
        } else if (source == stopButton ) {
            clip.stop();
        } else if (source == loopButton ) {
            clip.loop();
        }
    }
    
    public static void main(String args[]) {
        JFrame f = new JFrame("Sound Applet as Java Application");
        f.getContentPane().add(new AppletSound());
        f.pack();
        f.setVisible(true);
    }
    
}
