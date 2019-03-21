package com.ahks.seekerApp.view;

import com.ahks.seekerApp.model.MyListModel;
import com.ahks.seekerApp.model.SeekerModel;
import com.ahks.seekerApp.model.TableModel;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class UserInterface extends JPanel{

//    declaration of used components
    private JButton addFileBtn;
    private JTable table;
    private JButton searchBtn;
    private JList list;
    private JTextField searchField;
    private JButton addStringBtn;
    private JTextArea textAreaR;
    private JPanel panel;

//    declaration of used models
    private TableModel tableModel;
    private MyListModel listModel;

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

    public TableModel getTableModel() {
        return tableModel;
    }

    public MyListModel getListModel() { return listModel; }

//    initialization of actionListseners and mouseListeners
    public void initializeActionListener(ActionListener actionListener){
        addFileBtn.addActionListener(actionListener);
        addStringBtn.addActionListener(actionListener);
        searchBtn.addActionListener(actionListener);
    }
    public void initializeMouseListener(MouseListener mouseListener){
        table.addMouseListener(mouseListener);
        searchField.addMouseListener(mouseListener);
    }

//    method initializaing the user interface components
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

        this.table.setModel(tableModel);
        this.list.setModel(listModel);

        searchBtn.setEnabled(false);
        searchField.setEnabled(false);
        addStringBtn.setEnabled(false);
    }
}
