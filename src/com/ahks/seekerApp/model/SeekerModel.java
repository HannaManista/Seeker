package com.ahks.seekerApp.model;


/**
 * metody, logika
 */
public class SeekerModel {

    private TextFile textFile = new TextFile();
    private TableModel tableModel = new TableModel();
    private ListModel listModel = new ListModel();


    public TextFile getTextFile() {
        return textFile;
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public ListModel getListModel() { return listModel; }
}
