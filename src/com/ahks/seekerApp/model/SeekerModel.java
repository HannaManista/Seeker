package com.ahks.seekerApp.model;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * metody, logika
 */
public class SeekerModel {

    private TextFile textFile = new TextFile();
    private TableModel tableModel = new TableModel();


    public TextFile getTextFile() {
        return textFile;
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    public void createSeekingThreads(String[] phrases, String[] paths) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        // for every seeked phrase created are thread for each one file
        int i=0,j =0;
        for (String phrase : phrases) {
            i++;
            j=0;
            for (String path : paths) {
                j++;
                Runnable threadModel = new ThreadModel(phrase, path,i+"-"+j);
                executorService.execute(threadModel);
            }
        }
    }
}
