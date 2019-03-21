package com.ahks.seekerApp.concurrency;

import com.ahks.seekerApp.model.TextFile;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * <code></>SeekerThread</code> is a class implementing Callable interface. <code>call()</code> method returns
 * <code>Integer</code>
 */
public class SeekerThread implements Callable<Integer> {

    private String phrase;
    private String path;
    private String name;

    public String getName() {
        return name;
    }

    public SeekerThread(String phrase, String path, String name) {
        this.phrase = phrase;
        this.path = path;
        this.name =name;
    }

    @Override
    public Integer call() {
        Thread.currentThread().setName(this.getName());
        System.out.println("Thread '" + this.name + "' test   ->   phrase: " + this.phrase + " | path: " + this.path);

//        calling a method searching files for inserted phrase
        return this.searchPhrase(this.path, this.phrase);

    }


    //    method enables searching given phrase in the text
    public int searchPhrase(String text, String phrase) {

        int phraseCounter = 0;
//            String text = this.readFile(fullPath).toLowerCase();
            if (text != null && !text.isEmpty()) {
                int p0 = -1;
                do{
                    p0 = text.indexOf(phrase, p0+1);
                    if(p0>-1) {
                        phraseCounter++;
                        p0++;
                    }
                }while(p0 >= 0 && p0<=text.length());
            }
        return phraseCounter;
    }
}
