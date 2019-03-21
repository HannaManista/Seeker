package com.ahks.seekerApp.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <code></>MyTableModel</code> represents a table model
 */
public class MyTableModel extends AbstractTableModel {

    /**
     *  A list of <code>TextFile</code> objects, which contains: file name, path and result count.
     */
    private List<TextFile> fileArray = new ArrayList<>();

    /**
     * Column names
     */
    private String[] columnNames = {"Name", "Path", "Matched Results"};

    /**
     * Method add file to <code>fileArray</code> in TextFile object.
     * @param fileName text file name
     * @param filePath text file path
     */
    public void addFileToArray(String fileName, String filePath) {
        fileArray.add(new TextFile(fileName, filePath));
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return fileArray.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return fileArray.get(row).getName();
            case 1:
                return fileArray.get(row).getFullPath();
            case 2:
                if (fileArray.get(row).getResults() == null)
                    return "";
                else
                    return Arrays.toString(fileArray.get(row).getResults());
            default:
                return null;
        }
    }

    /**
     * Method return a path of text file at index <code>row</code>
     * @param row index of text file in <code>fileArray</code>
     * @return path of text file
     */
    public String getRowPath(int row) {
        return fileArray.get(row).getFullPath();
    }

    /**
     * Method return <code>fileArray</code> array with TextFiles objects
     * @return array of text files
     */
    public List<TextFile> getFileArray() {
        return fileArray;
    }
}
