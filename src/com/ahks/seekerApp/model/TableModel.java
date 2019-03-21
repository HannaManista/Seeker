package com.ahks.seekerApp.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableModel extends AbstractTableModel {

    private List<TextFile> fileArray = new ArrayList<>();
    private String[] columnNames = {"Name","Path","Matched Results"};


   public TableModel() {
   }

//   adding file to table model
    public void addFileToArray(String fileName, String filePath) {
       fileArray.add(new TextFile(fileName, filePath));
//       resultsArray.add(results);
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
//   returns value at certain row and column of the table model
   public Object getValueAt(int row, int col) {
       switch (col) {
           case 0:
               return fileArray.get(row).getName();
           case 1:
               return fileArray.get(row).getFullPath();
           case 2:
               if(fileArray.get(row).getResults() == null)
                   return "";
               else
                   return Arrays.toString(fileArray.get(row).getResults());
           default:
               return null;
       }
   }

    public String getRowPath(int row){
       return fileArray.get(row).getFullPath();
   }

    public List<TextFile> getFileArray() {
        return fileArray;
    }
}
