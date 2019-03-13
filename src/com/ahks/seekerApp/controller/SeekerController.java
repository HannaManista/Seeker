package com.ahks.seekerApp.controller;

import com.ahks.seekerApp.model.SeekerModel;
import com.ahks.seekerApp.view.UserInterface;
import com.ahks.seekerApp.view.FileChooser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.*;
import java.nio.file.Files;
import java.util.List;

import javax.swing.*;
import javax.swing.plaf.FileChooserUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SeekerController implements ActionListener {
    UserInterface ui;
    SeekerModel sm;
    FileChooser fc;

    public void actionPerformed(ActionEvent event){
        Object source = event.getSource();

        if(source == ui.getAddFileBtn()){
            fc = new FileChooser();
            ui.getSearchBtn().setEnabled(true);
            ui.getSearchField().setEnabled(true);
        }

//        if(source == ui.getSearchBtn()){
//
//        }

        if(fc.getChooser().showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
            fc.getPath();
            File file = fc.getChooser().getSelectedFile();
            String fullPath = file.getAbsolutePath();
            String text = new String();
            try {
                text = this.readFile(fullPath);

            } catch (IOException e) {
                e.printStackTrace();
            }
            ui.getTextAreaR().append(text);
        }
        else {
            System.out.println("No Selection ");
        }
    }

    private String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }



    public SeekerController(UserInterface UI, SeekerModel SM) {
        UI.initializeActionListener(this);
        this.ui = UI;
        this.sm = SM;
    }
}
