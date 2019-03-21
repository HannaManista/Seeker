package com.ahks.seekerApp.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFile {
    private String fileName;
    private String fullPath;
    private int[] results = null;


    public TextFile(String name, String fullPath) {
        this.fileName = name;
        this.fullPath = fullPath;
    }

    public TextFile() { }

//    method enables reading the contain of the text file
    public String readFile(String fullPath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fullPath));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }

//    method enables searching given phrase in the text
    public int searchPhrase(String fullPath, String phrase) {

        int phraseCounter = 0;
        try {
            String text = this.readFile(fullPath).toLowerCase();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phraseCounter;
    }

    public String getName() {
        return fileName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public int[] getResults() {
        return results;
    }

    public void setResults(int[] results) {
        this.results = results;
    }
}
