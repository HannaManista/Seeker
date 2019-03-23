package com.ahks.seekerApp.controller;

import com.ahks.seekerApp.concurrency.SeekerCallable;
import com.ahks.seekerApp.concurrency.SeekerRunnable;
import com.ahks.seekerApp.model.SeekerModel;
import com.ahks.seekerApp.view.UserInterface;
import com.ahks.seekerApp.view.FileChooser;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.*;

/**
 * <code></>SeekerController</code> receives events and invoke appropriate methods
 */
public class SeekerController implements ActionListener, ListSelectionListener {

    private UserInterface ui;
    private SeekerModel sm;


    public SeekerController(UserInterface ui, SeekerModel sm) {
        ui.initializeActionListener(this);
        this.sm = sm;
        this.ui = ui;
    }

    /*
    Table controller
     */
    @Override
    public void valueChanged(ListSelectionEvent event) {
        int selectedRow = ui.getTable().getSelectedRow();
        if (selectedRow > -1) {
            printSelectedText();
            if (ui.getTableModel().getFileArray().get(selectedRow).getResults() != null) {
                highlighting();
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        /*
        Buttons controller
         */
        if (source == ui.getAddFileBtn()) {
            FileChooser fc = new FileChooser();
            if (fc.getChooser().showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
                fc.getPath();
                String[] paths = fc.getDirectory();
                String[] names = fc.getNames();
                try {
                    ui.getTextAreaR().setText("");
                    ui.getTextAreaR().setEditable(false);
//                    adding file name and file path to table
                    for (int i = 0; i < paths.length; i++)
                        ui.getTableModel().addFileToArray(names[i], paths[i]);
                    ui.getTextAreaR().setText(sm.readFile(paths[paths.length - 1]));
                    int ind = ui.getTableModel().getRowCount() - 1;
                    ui.getTable().setRowSelectionInterval(ind, ind);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(null, "No selection");
            }
            ui.getSearchField().setEnabled(true);
            ui.getAddStringBtn().setEnabled(true);
            ui.getSearchBtn().setEnabled(true);
        }

//        Adding phrases to the list
        if (source == ui.getAddStringBtn()) {
            String phrase = ui.getSearchField().getText();
            if (!(phrase.equals("") || phrase.isEmpty())) {
                ui.getListModel().add(phrase);
            } else {
                JOptionPane.showMessageDialog(null, "Please insert a phrase");
            }
            ui.getSearchField().setText("");
        }

//        Confirm search
        if (source == ui.getSearchBtn()) {
            /*
            Fixed Callable
             */
            long startTime = System.currentTimeMillis();
//             Start threads
            seekFixedCall(ui.getThreadCountField());
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("TIME: " + elapsedTime);
            ui.getFixedCallLabel().setText("" + elapsedTime);

            // UI update
            highlighting();
            ui.getTableModel().fireTableDataChanged();

            /*
            Fixed Runnable
             */
            startTime = System.currentTimeMillis();
//             Start threads
            seekFixedRunnable(ui.getThreadCountField());
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            ui.getFixedRunLabel().setText("" + elapsedTime);

            /*
            Cached Runnable
             */
            int[] i = new int[2];
            startTime = System.currentTimeMillis();
//             Start threads
            i[1] = seekCachedRunnable();
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            ui.getCachedRunLabel().setText("" + elapsedTime);

            /*
            Cached Callable
             */
            startTime = System.currentTimeMillis();
//             Start threads
            i[0] = seekCachedCallable();
            System.out.println("test");
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            ui.getCachedCallLabel().setText("" + elapsedTime);
            ui.getCachedThreadsLabel().setText("(max) " + i[0] + " | " + i[1]);
        }
    }

    /**
     * Printing of selected file in table
     */
    private void printSelectedText() {
        int row = ui.getTable().getSelectedRow();
        String fullpath = ui.getTableModel().getRowPath(row);
        try {
            ui.getTextAreaR().setText(sm.readFile(fullpath));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Highlighting of searched phrases
     */
    private void highlighting() {
        try {
            Highlighter highlighter = ui.getTextAreaR().getHighlighter();
            Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.pink);
            String str = ui.getTextAreaR().getText().toLowerCase();
            for (int i = 0; i < ui.getListModel().getSize(); i++) {
                String phrase = ui.getListModel().getElementAt(i).toLowerCase();
                int p0 = -1;
                do {
                    p0 = str.indexOf(phrase, p0 + 1);
                    int p1 = p0 + phrase.length();
                    if (p0 >= 0)
                        highlighter.addHighlight(p0, p1, painter);
                } while (p0 >= 0 && p0 < str.length());
            }
        } catch (BadLocationException ble) {
            ble.printStackTrace();
        }
    }

    /**
     * Searching of phrases in files using concurrency
     * @param nThreads threads count
     */
    private void seekFixedCall(int nThreads) {
        System.out.println("Thread pool: " + nThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
//         for every seeked phrase created are thread for each one file
        int tableSize = ui.getTableModel().getRowCount();
        int phrasesCount = ui.getListModel().getSize();

//        iterating over each file
        for (int i = 0; i < tableSize; i++) {
//            iterating over each phrase inserted
            int[] results = new int[phrasesCount];
            for (int j = 0; j < phrasesCount; j++) {
                String text = null;
                try {
                    text = sm.readFile(ui.getTableModel().getRowPath(i)).toLowerCase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                System.out.println(ui.getListModel().getSize() + " + " +   ui.getListModel().getElementAt(j));
//                System.out.println(ui.getTableModel().getFileArray().get(j).getName());
                Callable threadModel = new SeekerCallable(ui.getListModel().getElementAt(j).toLowerCase(), text, i + "-" + j, ui.getTableModel().getFileArray().get(i).getName());
                Future<Integer> element = executorService.submit(threadModel);
                try {
                    results[j] = element.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
//                Print if newCachedThreadPool is set
//                System.out.println(executorService.getPoolSize());
            }
            ui.getTableModel().getFileArray().get(i).setResults(results);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) { }
        System.out.println("Executor service shutdown");

    }
    /**
     * Searching of phrases in files using concurrency
     * @param nThreads threads count
     */
    private void seekFixedRunnable(int nThreads) {
        System.out.println("Thread pool: " + nThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
//        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newWorkStealingPool(nThreads);
//         for every seeked phrase created are thread for each one file
        int tableSize = ui.getTableModel().getRowCount();
        int phrasesCount = ui.getListModel().getSize();

//        iterating over each file
        for (int i = 0; i < tableSize; i++) {
//            iterating over each phrase inserted
            int[] results = new int[phrasesCount];
            for (int j = 0; j < phrasesCount; j++) {
                String text = null;
                try {
                    text = sm.readFile(ui.getTableModel().getRowPath(i)).toLowerCase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                System.out.println(ui.getListModel().getSize() + " + " +   ui.getListModel().getElementAt(j));
//                System.out.println(ui.getTableModel().getFileArray().get(j).getName());
                Runnable threadModel = new SeekerRunnable(ui.getListModel().getElementAt(j).toLowerCase(), text, i + "-" + j, ui.getTableModel().getFileArray().get(i).getName());
                executorService.submit(threadModel);
//                Print if newCachedThreadPool is set
//                System.out.println(executorService.getPoolSize());
            }
            ui.getTableModel().getFileArray().get(i).setResults(results);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) { }
        System.out.println("Executor service shutdown");

    }

    private int seekCachedCallable() {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//         for every seeked phrase created are thread for each one file
        int tableSize = ui.getTableModel().getRowCount();
        int phrasesCount = ui.getListModel().getSize();
        int maxThreadCount = 0;
//        iterating over each file
        for (int i = 0; i < tableSize; i++) {
//            iterating over each phrase inserted
            int[] results = new int[phrasesCount];
            for (int j = 0; j < phrasesCount; j++) {
                String text = null;
                try {
                    text = sm.readFile(ui.getTableModel().getRowPath(i)).toLowerCase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Callable threadModel = new SeekerCallable(ui.getListModel().getElementAt(j).toLowerCase(), text, i + "-" + j, ui.getTableModel().getFileArray().get(i).getName());
                executorService.submit(threadModel);
//                Print if newCachedThreadPool is set
                System.out.println(executorService.getPoolSize());
                if(maxThreadCount < executorService.getPoolSize())
                    maxThreadCount = executorService.getPoolSize();
                Future<Integer> element = executorService.submit(threadModel);
                try {
                    results[j] = element.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            ui.getTableModel().getFileArray().get(i).setResults(results);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) { }
        System.out.println("Executor service shutdown");
        return maxThreadCount;

    }

    private int seekCachedRunnable() {

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//         for every seeked phrase created are thread for each one file
        int tableSize = ui.getTableModel().getRowCount();
        int phrasesCount = ui.getListModel().getSize();
        int maxThreadCount = 0;
//        iterating over each file
        for (int i = 0; i < tableSize; i++) {
//            iterating over each phrase inserted
            int[] results = new int[phrasesCount];
            for (int j = 0; j < phrasesCount; j++) {
                String text = null;
                try {
                    text = sm.readFile(ui.getTableModel().getRowPath(i)).toLowerCase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Runnable threadModel = new SeekerRunnable(ui.getListModel().getElementAt(j).toLowerCase(), text, i + "-" + j, ui.getTableModel().getFileArray().get(i).getName());
                executorService.submit(threadModel);
                System.out.println(executorService.getPoolSize());
                if(maxThreadCount < executorService.getPoolSize())
                    maxThreadCount = executorService.getPoolSize();
            }
            ui.getTableModel().getFileArray().get(i).setResults(results);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) { }
        System.out.println("Executor service shutdown");
        return maxThreadCount;
    }

}
