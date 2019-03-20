package com.ahks.seekerApp.model;

import javax.swing.*;
import javax.xml.soap.Text;
import java.io.File;

import static java.lang.Thread.sleep;

public class ThreadModel implements Runnable{

    private TextFile tf = new TextFile();
    private String phrase;
    private String path;
    private String name;

    public String getName() {
        return name;
    }

    public ThreadModel(String phrase, String path, String name) {
        this.phrase = phrase;
        this.path = path;
        this.name =name;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(this.getName());
        System.out.println("Thread test: "+this.name);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        tf.searchPhrase(path, phrase);

    }
}
