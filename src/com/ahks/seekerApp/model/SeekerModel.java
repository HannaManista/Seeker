package com.ahks.seekerApp.model;


/**
 * metody, logika
 */
public class SeekerModel {

    private MyTableModel tableModel = new MyTableModel();
    private MyListModel listModel = new MyListModel();

    public MyTableModel getTableModel() {
        return tableModel;
    }

    public MyListModel getListModel() { return listModel; }
}
