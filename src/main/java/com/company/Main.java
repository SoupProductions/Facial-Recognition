package com.company;

import com.github.sarxos.webcam.Webcam;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;

public class Main {

    public static void main(String[] args) {
        WebcamStream stream = new WebcamStream();
    }
}

class WebcamStream extends JFrame implements Runnable {

    private Thread thread;
    JPanel webcamPanel;

    Webcam webcam;
    ImageIcon icon = new ImageIcon();
    JLabel imageLabel = new JLabel(icon);

    public WebcamStream() {
        setLayout(new BorderLayout());
        webcamPanel = new JPanel();
        add(webcamPanel, BorderLayout.CENTER);



        webcam = Webcam.getDefault();
        webcam.open();

        setSize(webcam.getViewSize());
        setVisible(true);
        start();
    }

    public void run() {
        icon.setImage(webcam.getImage());
        repaint();
        try {
            Thread.sleep(1000/60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start () {
        System.out.println(new Timestamp(System.currentTimeMillis()) + " Starting " +  this.getClass().getName() + "!");
        if (thread == null) {
            thread = new Thread (this, "ClockRunnable");
            thread.start();
        }
    }

}
