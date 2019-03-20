package com.ahks.seekerApp.model;


/**
 * metody, logika
 */
public class SeekerModel {

    private TextFile textFile = new TextFile();
    private TableModel tableModel = new TableModel();
    private MyListModel listModel = new MyListModel();


    public TextFile getTextFile() {
        return textFile;
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public MyListModel getListModel() { return listModel; }
}
