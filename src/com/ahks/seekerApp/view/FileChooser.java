package com.ahks.seekerApp.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileChooser extends JFrame {

    private JFileChooser chooser = new JFileChooser();
    private String[] directory = null;

    public FileChooser() {

        this.chooser.setCurrentDirectory(new java.io.File("."));
        this.chooser.setDialogTitle("Select the destination directory");
//                this.chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.chooser.setMultiSelectionEnabled(true);
        //delete the folder creation option
//                disableCreateNewFolder(chooser);
        // Removal of options "All files"
        this.chooser.setAcceptAllFileFilterUsed(false);
        // Possible format in FileChooser is ".txt"
        this.chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text", "txt"));


        //Getting path
        if (this.chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            int i = this.chooser.getSelectedFiles().length;
            int j = 0;
            directory = new String[i];
            for (File dir : this.chooser.getSelectedFiles()) {
                directory[j] = dir + "";
                j++;
            }
            System.out.println("getCurrentDirectory(): " + this.chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : " + directory);
        } else {
            System.out.println("No Selection ");
        }
    }

    public JFileChooser getChooser() {
        return chooser;
    }

    public void setChooser(JFileChooser chooser) {
        this.chooser = chooser;
    }

    public String[] getDirectory() {
        return directory;
    }

    public void setDirectory(String[] directory) {
        this.directory = directory;
    }
}
