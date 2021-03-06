package com.ahks.seekerApp;

import com.ahks.seekerApp.controller.SeekerController;
import com.ahks.seekerApp.model.SeekerModel;
import com.ahks.seekerApp.view.UserInterface;

import javax.swing.*;

public class Seeker {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SeekerModel sm = new SeekerModel();
            UserInterface ui = new UserInterface(sm);
            new SeekerController(ui, sm);
        });
    }
}