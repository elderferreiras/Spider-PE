package br.ufpa.spider.pe.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 

public class Progress extends JDialog {

    JProgressBar current;
    JTextArea out;
    JButton find;
    Thread runner;
    int num = 0;

    public Progress(String string) {  
    	setUndecorated(true);
        JPanel pane = new JPanel();
        pane.setLayout(new FlowLayout());
        current = new JProgressBar();
        current.setStringPainted(true);
        current.setString(string);
        current.setIndeterminate(true);
        pane.add(current);
        setContentPane(pane);
    }
}