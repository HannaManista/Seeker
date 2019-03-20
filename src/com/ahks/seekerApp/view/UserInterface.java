package com.ahks.seekerApp.view;

import com.ahks.seekerApp.model.SeekerModel;
import com.ahks.seekerApp.model.TableModel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class UserInterface extends JPanel{

    private JButton addFileBtn;
    private JTable table;
    private JButton searchBtn;
    private JList list;
    private JTextField searchField;
    private JButton addStringBtn;
    private JTextArea textAreaR;
    private JPanel panel;

    private TableModel tableModel;
    private ListModel listModel;

    private JScrollPane scrollPaneLD;
    private JScrollPane scrollPaneLU;
    private JScrollPane scrollPaneR;

    public UserInterface() {
        initializeView();
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

    public JList<String> getList() { return list; }

    public TableModel getTableModel() {
        return tableModel;
    }

    public ListModel getListModel() { return listModel; }

    public void initializeActionListener(ActionListener actionListener){
        addFileBtn.addActionListener(actionListener);
        addStringBtn.addActionListener(actionListener);
        searchBtn.addActionListener(actionListener);
        System.out.println("test");
    }
    public void initializeMouseListener(MouseListener mouseListener){
        table.addMouseListener(mouseListener);
        searchField.addMouseListener(mouseListener);
    }

    private void initializeView() {

        JFrame frame = new JFrame("Your window name");
        frame.setTitle("Thread app");
        frame.setContentPane(panel);
        System.out.println(frame.getContentPane().getComponentCount());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        SeekerModel sm = new SeekerModel();
        this.tableModel = sm.getTableModel();
        this.listModel = sm.getListModel();

        this.textAreaR = new JTextArea();
        this.addFileBtn = new JButton("Add File");
        this.searchBtn = new JButton("Search phrases");
        this.searchField = new JTextField();
        this.addStringBtn = new JButton("Add Phrase");
//        this.scrollPaneLD = new JScrollPane();
//        this.scrollPaneLU = new JScrollPane();
//        this.scrollPaneR = new JScrollPane();
        this.table = new JTable(tableModel);
//        this.list = new JList<>();
//        this.list.setModel(listModel);
//        this.list.repaint();
//
//        scrollPaneR.setBounds(230, 55, 340, 350);
//        scrollPaneLU.setBounds(STANDARD_GAP, 40, 170, 130);
//        scrollPaneLD.setBounds(STANDARD_GAP, 210, 170, 195);
//        table.setBounds(0, 0, 170, 130);
//        list.setBounds(STANDARD_GAP, 210, 150, 195);
//        searchField.setBounds(STANDARD_GAP, 180, BUTTON_WIDTH, BUTTON_HEIGHT);
//        addFileBtn.setBounds(STANDARD_GAP*2, STANDARD_GAP/2, BUTTON_WIDTH, BUTTON_HEIGHT );
//        addStringBtn.setBounds(STANDARD_GAP+BUTTON_WIDTH,180,70, BUTTON_HEIGHT);
//        label.setBounds(230, STANDARD_GAP, BUTTON_WIDTH, BUTTON_HEIGHT );
//        searchBtn.setBounds(190, 410, 2*BUTTON_WIDTH, 2*BUTTON_HEIGHT);
//        frame.setBounds(300, 300, FRAME_WIDTH, FRAME_HEIGHT);

        searchBtn.setEnabled(false);
        searchField.setEnabled(false);
        addStringBtn.setEnabled(false);

//        scrollPaneR.getViewport().add(textAreaR);
//        scrollPaneLU.add(table);
//        scrollPaneLD.add(list);
        textAreaR.requestFocus();

//        frame.add(scrollPaneR);
//        frame.add(list);
//        frame.add(scrollPaneLU);
//        frame.add(scrollPaneLD);
        tableModel.addPath("xd", "lol");
        searchField.setText("kokos");

    }
}
