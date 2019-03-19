package com.ahks.seekerApp.controller;

import com.ahks.seekerApp.model.SeekerModel;
import com.ahks.seekerApp.model.ThreadModel;
import com.ahks.seekerApp.view.UserInterface;
import com.ahks.seekerApp.view.FileChooser;
import com.ahks.seekerApp.model.TextFile;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
                String fullpath;
                String name;
                fullpath = tf.readFilePath(fc);
                name = tf.readFileName(fc);
                try {
                    ui.getTextAreaR().setText("");
                    ui.getTableModel().addPath(name, fullpath);
                    ui.getTextAreaR().setText(tf.readFile(fullpath));
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
                ui.getPhraseList().add(phrase);
//                tf.getPhraseList().add(phrase);
            } else {
                JOptionPane.showMessageDialog(null, "Please insert a phrase");
            }
        }
        if (source == ui.getSearchBtn()) {

            seek();

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
            } catch (Exception ignore) {
                JOptionPane.showMessageDialog(null,"Chosen place is out of bounds");
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

//  Po przycisnieciu btnSearch uruchamiana jest funkcja.
    public void seek() {
//        test skasowac
//        String [] phrases, paths, test = {"a", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b"};
//        phrases = test; //ui.getList()
//        paths = test; // ui.getTableModel().getTextFilesPaths()

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        // for every seeked phrase created are thread for each one file

        int tableSize = ui.getTableModel().getRowCount();
        int phrasesCount = ui.getPhraseList().size();

        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < phrasesCount; j++) {
                Runnable threadModel = new ThreadModel(ui.getPhraseList().get(j), ui.getTableModel().getRowPath(i), i + "-" + j);
                executorService.execute(threadModel);
            }
        }
    }
}
