package com.ahks.seekerApp;

import com.ahks.seekerApp.controller.SeekerController;
import com.ahks.seekerApp.model.SeekerModel;
import com.ahks.seekerApp.view.FileChooser;
import com.ahks.seekerApp.view.UserInterface;
import com.ahks.seekerApp.model.TextFile;
import com.ahks.seekerApp.model.TableModel;

import javax.swing.*;

public class Seeker {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserInterface ui = new UserInterface();
            SeekerModel sm = new SeekerModel();
            TextFile tf = new TextFile();
            //TableModel tm = new TableModel();
            SeekerController pc = new SeekerController(ui, sm, tf);
        });
    }
}