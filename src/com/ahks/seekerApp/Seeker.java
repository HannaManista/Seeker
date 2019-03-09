package com.ahks.seekerApp;

import javax.swing.*;

public class Seeker {
    private JPanel panelMain;
    private JLabel label1;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Seeker");
        frame.setContentPane(new Seeker().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
