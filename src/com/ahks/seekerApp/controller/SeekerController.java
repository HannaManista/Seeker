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
          
//          test skasowac
            //btnSearchListener();
            ui.getSearchField().setEnabled(true);
        }
        if(source == ui.getAddStringBtn()){
            if(!ui.getSearchField().getText().equals("")) {
                String inputSearch = ui.getSearchField().getText();
                ui.getSearchBtn().setEnabled(true);
                tf.getPhraseList().add(inputSearch);
            }
            else{
                ui.getPopUpFrame().setVisible(true);
                JOptionPane.showMessageDialog(ui.getPopUpFrame(),"Please insert a phrase");
            }
        }
        if(source == ui.getSearchBtn()){
            int index = 1;  // TODO: thread chooses index of the word in list
            String inputPhrase = tf.getPhraseList().get(index);
            String fullPath = tf.readFilePath(fc);
            tf.searchPhrase(inputPhrase, fullPath);
            tf.getPhraseList().clear();
            tf.getResults().clear();
        }

        if (fc.getChooser().showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
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
            ui.getPopUpFrame().setVisible(true);
            JOptionPane.showMessageDialog(ui.getPopUpFrame(),"No selection");
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
                ui.getPopUpFrame().setVisible(true);
                JOptionPane.showMessageDialog(ui.getPopUpFrame(),"Chosen place is out of bounds");
            }
        }
        if(source == ui.getSearchField()){
            ui.getSearchField().setText("");
            ui.getAddStringBtn().setEnabled(true);
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
    void btnSearchListener(){
//        test skasowac
        String [] phrases, paths, test = {"a", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b"};
        phrases = test; //ui.getList()
        paths = test; // ui.getTableModel().getTextFilesPaths()

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        // for every seeked phrase created are thread for each one file
        int i = 0, j = 0;
        for (String phrase : phrases) {
            i++;
            j = 0;
            for (String path : paths) {
                j++;
                Runnable threadModel = new ThreadModel(phrase, path, i + "-" + j);
                executorService.execute(threadModel);
            }
        }
    }

}
