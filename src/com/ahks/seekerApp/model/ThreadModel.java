package com.ahks.seekerApp.model;

import java.io.File;

public class ThreadModel implements Runnable{

    private TextFile textFile;

    public ThreadModel(TextFile textFile) {
        this.textFile = textFile;
    }

    @Override
    public void run() {
        //seek the string in the file >SeekerModel
        //
    }
}
