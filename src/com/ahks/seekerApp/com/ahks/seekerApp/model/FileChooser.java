package com.ahks.seekerApp.com.ahks.seekerApp.model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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
                break;

//            // only folders are able to select
//            case 'd':
//                this.chooser.setCurrentDirectory(new java.io.File("."));
//                this.chooser.setDialogTitle("Select the destination directory");
//                this.chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//                // disable the "All files" option.
//                this.chooser.setAcceptAllFileFilterUsed(false);
//                if (this.chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
//                    this.directory = new String[] { this.chooser.getSelectedFile() + "" };
//                    System.out.println("getCurrentDirectory(): " + this.chooser.getCurrentDirectory());
//                    System.out.println("getSelectedFile() : " + directory);
//                } else {
//                    System.out.println("No Selection ");
//                }
//                break;
//        }
    }

    /**
     *
     * @returnReturns the one-dimensional array of paths
     */
    public String[] getDirectory() {
        return this.directory;
    }

//    /**
//     * @param c JFileChooser
//     * @return
//     */
//    public static boolean disableCreateNewFolder(Container c) {
//        Component[] comps = c.getComponents();
//        boolean gotIt = false;
//        for (int i = 0; i < comps.length; i++) {
//            if (comps[i] instanceof JButton) {
//                JButton b = (JButton) comps[i];
//                String ttText = b.getToolTipText();
//                if (ttText != null && ttText.equals("Create New Folder")) {
//                    b.setEnabled(false);
//                }
//                gotIt = true;
//            }
//            if (comps[i] instanceof Container) {
//                gotIt = disableCreateNewFolder((Container) comps[i]);
//            }
//
//            if (gotIt) {
//                break;
//            }
//        }
//
//        return gotIt;
//    }
}
