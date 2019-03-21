package com.ahks.seekerApp.controller;

import com.ahks.seekerApp.concurrency.SeekerThread;
import com.ahks.seekerApp.view.UserInterface;
import com.ahks.seekerApp.view.FileChooser;
import com.ahks.seekerApp.model.TextFile;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.*;

/**
 * <code></>SeekerController</code> receives events and invoke appropriate methods
 */
public class SeekerController implements ActionListener, MouseListener {

    private UserInterface ui;
    private FileChooser fc;

    public SeekerController(UserInterface ui) {
        ui.initializeActionListener(this);
        ui.initializeMouseListener(this);
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

//        addFileBtn controller
        if (source == ui.getAddFileBtn()) {
            fc = new FileChooser();
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
                    ui.getTextAreaR().setText(new TextFile().readFile(paths[paths.length - 1]));
                    int ind = ui.getTableModel().getRowCount() - 1;
                    ui.getTable().setRowSelectionInterval(ind, ind);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(null, "No selection");
            }

            ui.getSearchField().setEnabled(true);
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

//        confirm search
        if (source == ui.getSearchBtn()) {
            long startTime = System.currentTimeMillis();
//            start threads
            seek(ui.getThreadCountField());
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("TIME: " + elapsedTime);
            ui.getTimeLabel().setText("TIME: " + elapsedTime);
            highlighting();
            ui.getTableModel().fireTableDataChanged();
        }
    }

    public void mouseClicked(MouseEvent event) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();
        if (source == ui.getTable()) {
            printSelectedText();
            if (ui.getTableModel().getFileArray().get(ui.getTable().getSelectedRow()).getResults() != null) {
                highlighting();
            }
        }
        if (source == ui.getSearchField()) {
            ui.getAddStringBtn().setEnabled(true);
            ui.getSearchBtn().setEnabled(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void printSelectedText() {
        int row = ui.getTable().getSelectedRow();
        String fullpath = ui.getTableModel().getRowPath(row);
        TextFile tf = new TextFile();
        try {
            ui.getTextAreaR().setText(tf.readFile(fullpath));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    //    function defining searched words highlighting
    private void highlighting() {
        try {
            Highlighter highlighter = ui.getTextAreaR().getHighlighter();
            Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.pink);
            String str = ui.getTextAreaR().getText().toLowerCase();
            for (int i = 0; i < ui.getListModel().getSize(); i++) {
                String phrase = ui.getListModel().getElementAt(i);
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

    //    searching phrases in files using multiple threads
    private void seek(int nThreads) {
        System.out.println("Thread pool: " + nThreads);
//        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newWorkStealingPool(nThreads);
        // for every seeked phrase created are thread for each one file
        int tableSize = ui.getTableModel().getRowCount();
        int phrasesCount = ui.getListModel().getSize();
//        list for saving lists of results of matching
//        ArrayList<ArrayList<Future<Integer>>> listOfLists = new ArrayList<>();
//        iterating over each file
        for (int i = 0; i < tableSize; i++) {
//            listOfLists.add(new ArrayList<>());
//            iterating over each phrase inserted
            int[] results = new int[phrasesCount];
            for (int j = 0; j < phrasesCount; j++) {
                Callable threadModel = new SeekerThread(ui.getListModel().getElementAt(j), ui.getTableModel().getRowPath(i), i + "-" + j);
                Future<Integer> element = executorService.submit(threadModel);
                try {
                    results[j] = element.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println(executorService.getPoolSize());
            }
            ui.getTableModel().getFileArray().get(i).setResults(results);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        System.out.println("Executor service shutdown");

    }
}
