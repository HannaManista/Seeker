package com.ahks.seekerApp.model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *  <code>SeekerModel</code> is a dispatcher of table and list model. The class has methods for text file use.
 */
public class SeekerModel {

    /**
     * Table model
     */
    private MyTableModel tableModel = new MyTableModel();

    /**
     * List model
     */
    private MyListModel listModel = new MyListModel();

    /**
     * <code>tableModel</code> getter
     * @return  table model
     */
    public MyTableModel getTableModel() {
        return tableModel;
    }

    /**
     * <code>listModel</code> getter
     * @return list model
     */
    public MyListModel getListModel() { return listModel; }

    /**
     *  Method enables reading the contain of the text file
     * @param fullPath Absolute file path
     * @return file text
     * @throws IOException input/output exception
     */
    public String readFile(String fullPath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fullPath))) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                // add a line of text
                stringBuilder.append(line);
                // add new line separator
                stringBuilder.append(ls);
            }
            return stringBuilder.toString();
        }
    }

}
