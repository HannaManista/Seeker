package com.ahks.seekerApp.model;

import java.io.File;

import static java.lang.Thread.sleep;

public class ThreadModel implements Runnable{

    private String phrase;
    private String path;
    private String name;

    public ThreadModel(String phrase, String path, String name) {
        this.phrase = phrase;
        this.path = path;
        this.name =name;
    }

    @Override
    public void run() {
        System.out.println("Thread test: "+this.name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //wywołać metodę wyszukującą

    }
}
