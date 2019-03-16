package com.ahks.seekerApp.model;

import com.ahks.seekerApp.view.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextFile {
    private String fileName;
    private String path;
    private int results;
    private File file;
    private String fullPath;

    public TextFile(String name, String path) {
        this.fileName = name;
        this.path = path;
        this.results = 0;
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

    public String readFile(String filePath) throws IOException {
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
    public String getName() {
        return fileName;
    }

    public void setName(String name) {
        this.fileName = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }
}
