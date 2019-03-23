package com.ahks.seekerApp.concurrency;

import java.util.concurrent.Callable;

/**
 * <code></>SeekerThread</code> is a class implementing Callable interface. <code>call()</code> method returns
 * <code>Integer</code>
 */
public class SeekerThreadRunnable implements Runnable {

    private String phrase;
    private String text;
    private String threadName;
    private String fileName;

    private String getName() {
        return threadName;
    }

    public SeekerThreadRunnable(String phrase, String text, String threadName, String fileName) {
        this.phrase = phrase;
        this.text = text;
        this.threadName = threadName;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(this.getName());
        System.out.println("Thread '" + this.threadName + "' test   ->   phrase: " + this.phrase + " | path: " + this.fileName);

//        calling a method searching files for inserted phrase
//        return this.searchPhrase(this.text, this.phrase);

    }


//    method enables searching given phrase in the text
    private int searchPhrase(String text, String phrase) {

        int phraseCounter = 0;
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
