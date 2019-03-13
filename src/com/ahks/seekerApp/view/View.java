package com.ahks.seekerApp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JPanel implements ActionListener {

    private JFrame frame;
    private JLabel label;
    private JTextPane textpaneR;
    private JTextField searchfield;
    private JButton addFileBtn;
    private JButton searchBtn;
    private JMenuBar menuBar;
    private JMenu menu;
    private JScrollPane scrollPaneLD;
    private JScrollPane scrollPaneLU;

    int FRAME_WIDTH = 600;
    int FRAME_HEIGHT = 500;
    int STANDARD_GAP = FRAME_WIDTH/20;
    int BUTTON_WIDTH = 100;
    int BUTTON_HEIGHT = 20;

    public View() {
        this.initializeView();
    }
    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> {
        View view = new View();
        //});
    }

    private void initializeView() {

        this.frame = new JFrame();
        this.textpaneR = new JTextPane();
        this.addFileBtn = new JButton("Add File");
        this.menuBar = new JMenuBar();
        this.menu = new JMenu("Seeker");
        this.label = new JLabel("Text");
        this.searchfield = new JTextField("Search word");
        this.searchBtn = new JButton("Search");
        this.scrollPaneLD = new JScrollPane();
        this.scrollPaneLU = new JScrollPane();


        textpaneR.setBounds(230, 2*STANDARD_GAP, 340, 350);
        scrollPaneLU.setBounds(STANDARD_GAP, 40, 170, 130);
        scrollPaneLD.setBounds(STANDARD_GAP, 210, 170, 195);
        searchfield.setBounds(STANDARD_GAP, 180, BUTTON_WIDTH, BUTTON_HEIGHT);
        addFileBtn.setBounds(STANDARD_GAP*2, STANDARD_GAP/2, BUTTON_WIDTH, BUTTON_HEIGHT );
        searchBtn.setBounds(STANDARD_GAP+BUTTON_WIDTH,180,BUTTON_WIDTH/2, BUTTON_HEIGHT);
        label.setBounds(230, STANDARD_GAP, BUTTON_WIDTH, BUTTON_HEIGHT );
        frame.setBounds(300, 300, FRAME_WIDTH, FRAME_HEIGHT);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        frame.add(textpaneR);
        frame.add(scrollPaneLU);
        frame.add(scrollPaneLD);
        frame.add(addFileBtn);
        frame.add(searchBtn);
        frame.add(label);
        frame.add(searchfield);

        addFileBtn.addActionListener(this);
        searchBtn.addActionListener(this);

        setVisible(true);
        searchBtn.setEnabled(false);
        searchfield.setEnabled(false);
        textpaneR.requestFocus();


        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
    }

    @Override
    public void actionPerformed(ActionEvent event){
        Object source = event.getSource();

        if(source == addFileBtn){
            searchBtn.setEnabled(true);
            searchfield.setEnabled(true);
        }

        if(source == searchBtn){

        }
    }

}



