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
            btnSearchListener();

            ui.getSearchBtn().setEnabled(true);
            ui.getSearchField().setEnabled(true);
        }
        if(source == ui.getSearchBtn()){
            String text = ui.getSearchField().getText();
            System.out.println(text);
            // TODO: search method calling
        }

        if (fc.getChooser().showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
            String fullpath;
            String name;
            fullpath = tf.readFilePath(fc);
            name = tf.readFileName(fc);
            try {
                ui.getTextAreaR().setText("");
                ui.getTableModel().addPath(name, fullpath);
                ui.getTextAreaR().setText(tf.readFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No Selection ");
        }
    }


    public void mouseClicked(MouseEvent event){

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();

        if(source == ui.getTable()) {
            try {
                System.out.println(source.toString());
                int row = ui.getTable().rowAtPoint(e.getPoint());
                String fullpath = ui.getTableModel().getRowPath(row);
                tf.setFullPath(fullpath);
                ui.getTextAreaR().setText(tf.readFile());
            } catch (Exception ignore) {
                System.out.println("Chosen space is out of bounds");
            }
        }
        if(source == ui.getSearchField()){
            System.out.println(source.toString());
            ui.getSearchField().setText("");
            // TODO: comparing methods
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
//        sm.createSeekingThreads(ui.getList(), ui.getTableModel().getTextFilesPaths());
//        test skasowac
        String [] test = {"a", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "b"};
        sm.createSeekingThreads(test, test);
    }

}
