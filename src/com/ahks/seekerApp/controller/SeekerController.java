package com.ahks.seekerApp.controller;

import com.ahks.seekerApp.model.SeekerModel;
import com.ahks.seekerApp.model.TableModel;
import com.ahks.seekerApp.view.UserInterface;
import com.ahks.seekerApp.view.FileChooser;
import com.ahks.seekerApp.model.TextFile;

import java.io.IOException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeekerController implements ActionListener {
    UserInterface ui;
    SeekerModel sm;
    FileChooser fc;
    TextFile tf;

    public SeekerController(UserInterface UI, SeekerModel SM, TextFile TF) {
        UI.initializeActionListener(this);
        this.ui = UI;
        this.sm = SM;
        this.tf = TF;
    }

    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == ui.getAddFileBtn()) {
            fc = new FileChooser();
            ui.getSearchBtn().setEnabled(true);
            ui.getSearchField().setEnabled(true);
        }

        if (fc.getChooser().showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
            String path;
            String name;
            path = tf.readFilePath(fc);
            name = tf.readFileName(fc);
            try {
                ui.getTableModel().addPath(name, path);
                ui.getTextAreaR().append(tf.readFile(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No Selection ");
        }
    }
}
