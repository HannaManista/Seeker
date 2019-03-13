package com.ahks.seekerApp.model;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private String[] columnNames = { "File name:", "Matched results:" };

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        return null;
    }
}
