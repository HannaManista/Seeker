package com.ahks.seekerApp.controller;

import com.ahks.seekerApp.model.SeekerModel;
import com.ahks.seekerApp.view.UserInterface;
import com.ahks.seekerApp.view.FileChooser;

import javax.swing.*;
import javax.swing.plaf.FileChooserUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        if(source == ui.getSearchBtn()){

        }
    }


    public SeekerController(UserInterface UI, SeekerModel SM) {
        UI.initializeActionListener(this);
        this.ui = UI;
        this.sm = SM;
    }
}
