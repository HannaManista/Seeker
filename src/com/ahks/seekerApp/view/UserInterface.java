package com.ahks.seekerApp.view;

import com.ahks.seekerApp.model.SeekerModel;
import com.ahks.seekerApp.model.TableModel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class UserInterface extends JFrame {

    private TableModel tableModel;
    private JFrame frame;
    private JLabel label;
    private JTable table;
    private JTextArea textAreaR;
    private JTextField searchField;
    private JButton addFileBtn;
    private JButton searchBtn;
    private JButton addStringBtn;
    private JScrollPane scrollPaneLD;
    private JScrollPane scrollPaneLU;
    private JScrollPane scrollPaneR;

    private List<String> phraseList = new ArrayList();

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

    public JButton getAddStringBtn() {
        return addStringBtn;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public JTextArea getTextAreaR() {
        return textAreaR;
    }

    public JTable getTable() {
        return table;
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public List<String> getPhraseList() {
        return phraseList;
    }

    public void initializeActionListener(ActionListener actionListener){
        addFileBtn.addActionListener(actionListener);
        addStringBtn.addActionListener(actionListener);
        searchBtn.addActionListener(actionListener);
    }
    public void initializeMouseListener(MouseListener mouseListener){
        table.addMouseListener(mouseListener);
        searchField.addMouseListener(mouseListener);
    }


    private void initializeView() {

        this.tableModel = new SeekerModel().getTableModel();
        this.frame = new JFrame();
        this.textAreaR = new JTextArea();
        this.addFileBtn = new JButton("Add File");
        this.searchBtn = new JButton("Search phrases");
        this.label = new JLabel("Text");
        this.searchField = new JTextField();
        this.addStringBtn = new JButton("Add Phrase");
        this.scrollPaneLD = new JScrollPane();
        this.scrollPaneLU = new JScrollPane();
        this.scrollPaneR = new JScrollPane();
        this.table = new JTable(tableModel);

        scrollPaneR.setBounds(230, 55, 340, 350);
        scrollPaneLU.setBounds(STANDARD_GAP, 40, 170, 130);
        scrollPaneLD.setBounds(STANDARD_GAP, 210, 170, 195);
        table.setBounds(0, 0, 170, 130);
        searchField.setBounds(STANDARD_GAP, 180, BUTTON_WIDTH, BUTTON_HEIGHT);
        addFileBtn.setBounds(STANDARD_GAP*2, STANDARD_GAP/2, BUTTON_WIDTH, BUTTON_HEIGHT );
        addStringBtn.setBounds(STANDARD_GAP+BUTTON_WIDTH,180,70, BUTTON_HEIGHT);
        label.setBounds(230, STANDARD_GAP, BUTTON_WIDTH, BUTTON_HEIGHT );
        searchBtn.setBounds(190, 410, 2*BUTTON_WIDTH, 2*BUTTON_HEIGHT);
        frame.setBounds(300, 300, FRAME_WIDTH, FRAME_HEIGHT);

        searchBtn.setEnabled(false);
        searchField.setEnabled(false);
        addStringBtn.setEnabled(false);

        scrollPaneR.getViewport().add(textAreaR);
        scrollPaneLU.add(table);
        textAreaR.requestFocus();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.add(scrollPaneR);
        frame.add(scrollPaneLU);
        frame.add(scrollPaneLD);
        frame.add(addFileBtn);
        frame.add(searchBtn);
        frame.add(addStringBtn);
        frame.add(label);
        frame.add(searchField);
        frame.setVisible(true);
    }
}



