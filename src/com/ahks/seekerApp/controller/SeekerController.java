package com.ahks.seekerApp.controller;

import com.ahks.seekerApp.model.SeekerModel;
import com.ahks.seekerApp.model.ThreadModel;
import com.ahks.seekerApp.view.UserInterface;
import com.ahks.seekerApp.view.FileChooser;
import com.ahks.seekerApp.model.TextFile;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SeekerController implements ActionListener, MouseListener {
    private UserInterface ui;
    private SeekerModel sm;
    private FileChooser fc;
    private TextFile tf;

    public SeekerController(UserInterface ui, SeekerModel sm) {
        ui.initializeActionListener(this);
        ui.initializeMouseListener(this);
        this.ui = ui;
        this.sm = sm;
        this.tf = sm.getTextFile();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == ui.getAddFileBtn()) {
            fc = new FileChooser();
            if(fc.getChooser().showOpenDialog(fc) == JFileChooser.APPROVE_OPTION){
                //
                String fullpath;
                //
                fullpath = tf.readFilePath(fc);
                fc.getPath();
                String [] paths = fc.getDirectory();
                String [] names = fc.getNames();
                try {
                    ui.getTextAreaR().setText("");
                    for(int i = 0 ; i<paths.length ; i++)
                        ui.getTableModel().addPath(names[i], paths[i]);
                    //
                    ui.getTextAreaR().setText(tf.readFile(fullpath));
                    //
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                JOptionPane.showMessageDialog(null,"No selection");
            }

            ui.getSearchField().setEnabled(true);
        }

        // Adding phrases to the list
        if(source == ui.getAddStringBtn()){
            String phrase = ui.getSearchField().getText();
            if(!(phrase.equals("") || phrase.isEmpty())) {
                ui.getListModel().add(phrase);
            } else {
                JOptionPane.showMessageDialog(null, "Please insert a phrase");
            }
        }
        if (source == ui.getSearchBtn()) {
            long startTime = System.currentTimeMillis();
            seek();
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            System.out.println("TIME: " + elapsedTime);
//            System.out.println(results.toString());
//            tf.getPhraseList().clear();
//            tf.getResults().clear();
        }
    }

    public void mouseClicked(MouseEvent event){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();

        if(source == ui.getTable()) {
            try {
                int row = ui.getTable().rowAtPoint(e.getPoint());
                String fullpath = ui.getTableModel().getRowPath(row);
                tf.setFullPath(fullpath);
                ui.getTextAreaR().setText(tf.readFile(fullpath));

                Highlighter highlighter = ui.getTextAreaR().getHighlighter();
                Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.pink);
                String str = ui.getTextAreaR().getText();
                int p0=-1;
                do{
                    p0 = str.indexOf("kot", p0+1);
                    int p1 = p0 + "kot".length();
                    if(p0>=0)
                        highlighter.addHighlight(p0, p1, painter);
                    System.out.println(p0);
                }while(p0>=0 && p0<str.length());
            } catch (Exception ignore) {
                JOptionPane.showMessageDialog(null,"Chosen place is out of bounds");
                ignore.printStackTrace();
            }
        }
        if(source == ui.getSearchField()){
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

    public void seek() {

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        // for every seeked phrase created are thread for each one file
        int tableSize = ui.getTableModel().getRowCount();
        int phrasesCount = ui.getListModel().getSize();
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < phrasesCount; j++) {
                Runnable threadModel = new ThreadModel(ui.getListModel().getElementAt(j), ui.getTableModel().getRowPath(i), i + "-" + j);
                executorService.execute(threadModel);
            }
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}
