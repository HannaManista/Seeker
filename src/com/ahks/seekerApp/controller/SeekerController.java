package com.ahks.seekerApp.controller;

import com.ahks.seekerApp.model.SeekerModel;
import com.ahks.seekerApp.model.TableModel;
import com.ahks.seekerApp.view.UserInterface;
import com.ahks.seekerApp.view.FileChooser;
import com.ahks.seekerApp.model.TextFile;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeekerController implements ActionListener, MouseListener {
    UserInterface ui;
    SeekerModel sm;
    FileChooser fc;
    TextFile tf;

    public SeekerController(UserInterface UI, SeekerModel SM, TextFile TF) {
        UI.initializeActionListener(this);
        this.ui = UI;
        this.sm = SM;
        this.tf = TF;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == ui.getAddFileBtn()) {
            fc = new FileChooser();
            ui.getSearchBtn().setEnabled(true);
            ui.getSearchField().setEnabled(true);
        }

        if (fc.getChooser().showOpenDialog(fc) == JFileChooser.APPROVE_OPTION) {
            String path;
            String name;
            path = tf.readFilePath(fc);
            name = tf.readFileName(fc);
            try {
                ui.getTextAreaR().setText(null);
                ui.getTableModel().addPath(name, path);
                ui.getTextAreaR().append(tf.readFile(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
//         if(source == ui.getTableModel().addTableModelListener(new TableModelListener()) {
//            if(source == ui.getTable().getMouseListeners()){
//
//
//            }
        } else {
            System.out.println("No Selection ");
        }
    }

    public void mouseClicked(MouseEvent event){
        System.out.println("mouseClicked!");
        ui.getTextAreaR().setText(null);
        int row = ui.getTable().rowAtPoint(event.getPoint());
        int col = ui.getTable().columnAtPoint(event.getPoint());
        String path = ui.getTableModel().getValueAt(row,col).toString();
        System.out.println(path);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
