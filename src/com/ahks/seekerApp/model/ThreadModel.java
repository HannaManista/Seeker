package com.ahks.seekerApp.model;

import java.util.concurrent.Callable;

public class ThreadModel implements Callable<Integer> {

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

    public TextFile getTf() {
        return tf;
    }

    @Override
    public Integer call() {
        Thread.currentThread().setName(this.getName());
        System.out.println("Thread test: "+this.name);
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return tf.searchPhrase(path, phrase);

    }
}
