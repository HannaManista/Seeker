package com.ahks.seekerApp.model;

import javax.swing.*;

public class ListModel extends AbstractListModel {
    String[] strings = new String[10000];

    public ListModel() {
        for (int i = 0; i < 10000; i++) {
            strings[i] = "bob" + i;
        }
    }

    public int getSize() {
        return strings.length;
    }

    public Object getElementAt(int index) {
        return strings[index];
    }
}
