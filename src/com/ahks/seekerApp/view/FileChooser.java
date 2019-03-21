package com.ahks.seekerApp.view;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.Arrays;

/**
 * <code>FileChooser</code> set file explorer adapted to text files.
 *
 */
public class FileChooser extends JFrame {

    private JFileChooser chooser = new JFileChooser();
    private String[] directory = null;
    private String[] names = null;

    public FileChooser() {
        this.chooser.setCurrentDirectory(new java.io.File("."));
        this.chooser.setDialogTitle("Select the destination directory");
        this.chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.chooser.setMultiSelectionEnabled(true);
        //disable to creation new folder
        disableCreateNewFolder(chooser);
        // Removal of option: "All files"
        this.chooser.setAcceptAllFileFilterUsed(false);
        // Possible format in FileChooser is ".txt"
        this.chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text", "txt"));
    }

    //Getting path
    public void getPath() {
        int i = this.chooser.getSelectedFiles().length;
        int j = 0;
        directory = new String[i];
        names = new String[i];
        for (File dir : this.chooser.getSelectedFiles()) {
            directory[j] = dir + "";
            names[j] = dir.getName();
            j++;
        }
    }

    public JFileChooser getChooser() {
        return chooser;
    }

    public String[] getDirectory() {
        return directory;
    }

    public String[] getNames() {
        return names;
    }

    private static boolean disableCreateNewFolder(@NotNull Container c) {
        Component[] comps = c.getComponents();
        boolean gotIt = false;
        for (Component comp : comps) {
            if (comp instanceof JButton) {
                JButton b = (JButton) comp;
                String ttText = b.getToolTipText();
                if (ttText != null && ttText.equals("Create New Folder")) {
                    b.setEnabled(false);
                }
                gotIt = true;
            }
            if (comp instanceof Container) {
                gotIt = disableCreateNewFolder((Container) comp);
            }
            if (gotIt) {
                break;
            }
        }
        return gotIt;
    }

    @Override
    public String toString() {
        return "FileChooser{" +
                "directory=" + Arrays.toString(directory) +
                '}';
    }
}




