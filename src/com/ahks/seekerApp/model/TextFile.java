package com.ahks.seekerApp.model;

import com.ahks.seekerApp.view.FileChooser;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class TextFile {
    private String fileName;
    private String fullPath;
    private File file;
    private ArrayList<Integer> results;


    public TextFile(String name, String fullPath, ArrayList<Integer> results) {
        this.fileName = name;
        this.fullPath = fullPath;
        this.results = results;
    }

    public TextFile() { }
//
//    public String readFilePath(FileChooser fc) {
//        file = fc.getChooser().getSelectedFile();
//        fullPath = file.getAbsolutePath();
//        return fullPath;
//    }

//
//    method enables reading the contain of the text file
    public String readFile(String fullPath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fullPath));
        String line = null;
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
            System.out.println("length of the text "+text.length());
            if (text != null) {
                for (int i = 0; i < words.length; i++) {
                    if (words[i].contains(phrase)) {
                        phraseCounter++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error in calculating matching phrases in text");
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

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public ArrayList<Integer> getResults() {
        return results;
    }

}
