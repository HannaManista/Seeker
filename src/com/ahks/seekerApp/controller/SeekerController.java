package com.ahks.seekerApp.controller;

import com.ahks.seekerApp.model.SeekerModel;
import com.ahks.seekerApp.view.UserInterface;

public class SeekerController {
    //Listenery

    UserInterface ui;
    SeekerModel sm;

    public SeekerController(UserInterface ui, SeekerModel sm) {
        this.ui = ui;
        this.sm = sm;
    }
}
