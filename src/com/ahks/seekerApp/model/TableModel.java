package com.ahks.seekerApp.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {

    private ArrayList<TextFile> fileArray;
    private String[] columnNames = {"Name","Path"};

   public TableModel() {
       fileArray = new ArrayList<TextFile>();
   }

   public void addPath(String fileName, String filePath) {
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
       return 2;
   }

   @Override
   public Object getValueAt(int row, int col) {
       switch (col) {
           case 0:
               return fileArray.get(row).getName();
           case 1:
               return fileArray.get(row).getFullPath();
           default:
               return null;
       }
   }
   public String getRowPath(int row){
       return fileArray.get(row).getFullPath();
   }

   // Stringi mają zostać czy zmienić typ? Potrzebne do wątków
    public String[] getTextFilesPaths () {
        int nRow = this.getRowCount();
        String[] filePath = new String[nRow];
        for (int i = 0 ; i < nRow ; i++)
            filePath[i] = (String) this.getValueAt(i,1);
        return filePath;
    }
}
