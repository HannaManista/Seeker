package com.ahks.seekerApp.view;

import com.ahks.seekerApp.controller.SeekerController;
import com.ahks.seekerApp.model.TableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class UserInterface extends JFrame {

    private TableModel tableModel;
    private JFrame frame;
    private JLabel label;
    private JTable table;
    private JTextArea textAreaR;
    private JTextField searchField;
    private JButton addFileBtn;
    private JButton searchBtn;
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

    public JTable getTable() {
        return table;
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public void initializeActionListener(ActionListener actionListener){
        addFileBtn.addActionListener(actionListener);
        searchBtn.addActionListener(actionListener);
    }
    public void initializeMouseListener(MouseListener mouseListener){
        table.addMouseListener(mouseListener);
        searchField.addMouseListener(mouseListener);
    }

    private void initializeView() {

        tableModel = new TableModel();
        this.frame = new JFrame();
        this.textAreaR = new JTextArea();
        this.addFileBtn = new JButton("Add File");
        this.label = new JLabel("Text");
        this.searchField = new JTextField("Search word");
        this.searchBtn = new JButton("Search");
        this.scrollPaneLD = new JScrollPane();
        this.scrollPaneLU = new JScrollPane();
        this.scrollPaneR = new JScrollPane();
        this.table = new JTable(tableModel);

        scrollPaneR.setBounds(230, 2*STANDARD_GAP, 340, 350);
        scrollPaneLU.setBounds(STANDARD_GAP, 40, 170, 130);
        scrollPaneLD.setBounds(STANDARD_GAP, 210, 170, 195);
        table.setBounds(0, 0, 170, 130);
        searchField.setBounds(STANDARD_GAP, 180, BUTTON_WIDTH, BUTTON_HEIGHT);
        addFileBtn.setBounds(STANDARD_GAP*2, STANDARD_GAP/2, BUTTON_WIDTH, BUTTON_HEIGHT );
        searchBtn.setBounds(STANDARD_GAP+BUTTON_WIDTH,180,70, BUTTON_HEIGHT);
        label.setBounds(230, STANDARD_GAP, BUTTON_WIDTH, BUTTON_HEIGHT );
        frame.setBounds(300, 300, FRAME_WIDTH, FRAME_HEIGHT);

        setVisible(true);
        searchBtn.setEnabled(false);
        searchField.setEnabled(false);

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
        frame.add(label);
        frame.add(searchField);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
    }
}



