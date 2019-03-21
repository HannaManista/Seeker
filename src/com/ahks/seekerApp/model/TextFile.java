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
    public Integer searchPhrase(String fullPath, String phrase) {

        int phraseCounter = 0;
        try {
            String text = this.readFile(fullPath).toLowerCase();
            String[] words = text.split("\\s+");
            if (text != null) {
                for (int i = 0; i < words.length; i++) {
                    if (words[i].contains(phrase)) {
                        phraseCounter++;
                    }
                }
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
