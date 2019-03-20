package com.ahks.seekerApp.model;

import com.ahks.seekerApp.view.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TextFile {
    private String fileName;
    private String fullPath;
    private File file;

    public TextFile(String name, String fullPath) {
        this.fileName = name;
        this.fullPath = fullPath;

    }

    public TextFile() {}

    public String readFilePath(FileChooser fc){
        file = fc.getChooser().getSelectedFile();
        fullPath = file.getAbsolutePath();
        return fullPath;
    }
    public String readFileName(FileChooser fc){
        file = fc.getChooser().getSelectedFile();
        fileName = file.getName();
        return fileName;
    }

    public String readFile(String fullPath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fullPath));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }

    public void searchPhrase(String fullPath, String phrase) {

        int phraseCounter = 0;
        try {
            String text = this.readFile(fullPath).toLowerCase();
            String[] words = text.split("\\s+");
            if (text != null) {
                for (int i = 0; i < words.length; i++) {
                    if (words[i].equals(phrase)) {
                        phraseCounter++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Błąd w obliczaniu zgodniści wyrazów i fraz");
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "found: " + phraseCounter + " results.");
    }

    public String getName() {
        return fileName;
    }

    public void setName(String name) {
        this.fileName = name;
    }

//    public ArrayList<Integer> getResults() {
//        return results;
//    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

//    public ArrayList<String> getPhraseList() {
//        return phraseList;
//    }
}
