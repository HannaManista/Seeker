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

    @Override
    public Integer call() {
        Thread.currentThread().setName(this.getName());
        System.out.println("Thread '" + this.name + "' test   ->   phrase: " + this.phrase + " | path: " + this.path);

//        calling a method searching files for inserted phrase
        return this.tf.searchPhrase(this.path, this.phrase);

    }
}
