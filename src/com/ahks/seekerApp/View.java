package com.ahks.seekerApp;

import javax.swing.*;

public class View extends JPanel{
    private JPanel panelMain;
    private JLabel label1;
    private JTable table;
    private JFrame frame;


    public View() {
        this.initializeUI();
    }

    private void initializeUI() {
        this.frame = new JFrame("View");
        this.frame.setContentPane(this.panelMain);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.pack();
        this.frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new View());
    }
}
