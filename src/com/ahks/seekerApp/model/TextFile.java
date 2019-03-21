package com.ahks.seekerApp.model;

public class TextFile {

    /**
     * File name
     */
    private String fileName;

    /**
     * File path
     */
    private String fullPath;

    /**
     * An array of primitive integer represents found results respectively to seeked phrases in JList
     */
    private int[] results = null;

    /**
     * TextFile contructor
     * @param name file name
     * @param fullPath absolute file path
     */
    public TextFile(String name, String fullPath) {
        this.fileName = name;
        this.fullPath = fullPath;
    }

    /**
     * File name getter
     * @return file name
     */
    public String getName() {
        return fileName;
    }

    /**
     * Absolute path getter
     * @return absolute path
     */
    public String getFullPath() {
        return fullPath;
    }

    /**
     * Results getter
     * @return results
     */
    public int[] getResults() {
        return results;
    }

    /**
     * Results setter
     * @param results
     */
    public void setResults(int[] results) {
        this.results = results;
    }
}
