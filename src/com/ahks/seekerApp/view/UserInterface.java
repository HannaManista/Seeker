package com.ahks.seekerApp.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface extends JPanel {

    private JFrame frame;
    private JLabel label;
    private JTextArea textAreaR;
    private JTextField searchField;
    private JButton addFileBtn;
    private JButton searchBtn;
    private JMenuBar menuBar;
    private JMenu menu;
    private JScrollPane scrollPaneLD;
    private JScrollPane scrollPaneLU;
    private JScrollPane scrollPaneR;

    int FRAME_WIDTH = 600;
    int FRAME_HEIGHT = 500;
    int STANDARD_GAP = FRAME_WIDTH/20;
    int BUTTON_WIDTH = 100;
    int BUTTON_HEIGHT = 20;

    public UserInterface() {
        this.initializeView();
    }

    public JButton getAddFileBtn() {
        return addFileBtn;
    }

    public JButton getSearchBtn() {
        return searchBtn;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JTextArea getTextAreaR() {
        return textAreaR;
    }

    public void initializeActionListener(ActionListener actionListener){
        addFileBtn.addActionListener(actionListener);
        searchBtn.addActionListener(actionListener);
    }

    public JScrollPane getScrollPaneR() {
        return scrollPaneR;
    }

    private void initializeView() {

        this.frame = new JFrame();
        this.textAreaR = new JTextArea();
        this.addFileBtn = new JButton("Add File");
        this.menuBar = new JMenuBar();
        this.menu = new JMenu("Seeker");
        this.label = new JLabel("Text");
        this.searchField = new JTextField("Search word");
        this.searchBtn = new JButton("Search");
        this.scrollPaneLD = new JScrollPane();
        this.scrollPaneLU = new JScrollPane();
        this.scrollPaneR = new JScrollPane();


        scrollPaneR.setBounds(230, 2*STANDARD_GAP, 340, 350);
        scrollPaneLU.setBounds(STANDARD_GAP, 40, 170, 130);
        scrollPaneLD.setBounds(STANDARD_GAP, 210, 170, 195);
        searchField.setBounds(STANDARD_GAP, 180, BUTTON_WIDTH, BUTTON_HEIGHT);
        addFileBtn.setBounds(STANDARD_GAP*2, STANDARD_GAP/2, BUTTON_WIDTH, BUTTON_HEIGHT );
        searchBtn.setBounds(STANDARD_GAP+BUTTON_WIDTH,180,BUTTON_WIDTH/2, BUTTON_HEIGHT);
        label.setBounds(230, STANDARD_GAP, BUTTON_WIDTH, BUTTON_HEIGHT );
        frame.setBounds(300, 300, FRAME_WIDTH, FRAME_HEIGHT);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        frame.add(scrollPaneR);
        frame.add(scrollPaneLU);
        frame.add(scrollPaneLD);
        frame.add(addFileBtn);
        frame.add(searchBtn);
        frame.add(label);
        frame.add(searchField);

        setVisible(true);
        searchBtn.setEnabled(false);
        searchField.setEnabled(false);
        scrollPaneR.add(textAreaR);
        textAreaR.requestFocus();


        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
    }
}



