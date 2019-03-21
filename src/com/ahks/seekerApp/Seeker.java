package com.ahks.seekerApp;

import com.ahks.seekerApp.controller.SeekerController;
import com.ahks.seekerApp.view.UserInterface;

import javax.swing.*;

public class Seeker {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserInterface ui = new UserInterface();
            new SeekerController(ui);
        });
    }
}