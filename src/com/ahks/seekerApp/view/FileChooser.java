package com.ahks.seekerApp.view;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.Arrays;

/**
 * <code>FileChooser</code> sets file explorer adapted to text files
 */
public class FileChooser extends JFrame {

    /**
     * Files chooser
     */
    private JFileChooser chooser = new JFileChooser();

    /**
     * Files paths
     */
    private String[] directory = null;

    /**
     * Files names
     */
    private String[] names = null;

    /**
     * <code>FileChooser</code> no arg constructor
     */
    public FileChooser() {
        this.chooser.setCurrentDirectory(new java.io.File("."));
        this.chooser.setDialogTitle("Select the destination directory");
        this.chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.chooser.setMultiSelectionEnabled(true);
        // Disable to creation new folder
        disableCreateNewFolder(chooser);
        // Removal of option: "All files"
        this.chooser.setAcceptAllFileFilterUsed(false);
        // Possible format in FileChooser is ".txt"
        this.chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text", "txt"));
    }

    /**
     * Gets paths of selected files
     */
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

    /**
     * File chooser getter
     *
     * @return file chooser
     */
    public JFileChooser getChooser() {
        return chooser;
    }

    /**
     * Paths getter
     *
     * @return paths
     */
    public String[] getDirectory() {
        return directory;
    }

    /**
     * File names getter
     *
     * @return file names
     */
    public String[] getNames() {
        return names;
    }

    /**
     * Disable option to create new folder
     *
     * @param c chooser
     * @return true if successful
     */
    private static boolean disableCreateNewFolder(Container c) {
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
        return "FileChooser{" + "directory=" + Arrays.toString(directory) + '}';
    }
}




