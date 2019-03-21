package com.ahks.seekerApp.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <code></>MyListModel</code> represents a list model
 */
public class MyListModel extends AbstractListModel {

    List<String> strings;

    public MyListModel() {
        strings = new ArrayList();
    }

    public int getSize() {
        return strings.size();
    }

    public String getElementAt(int index) {
        return strings.get(index);
    }

    public void add(String element) {
        if (strings.add(element)) {
            fireContentsChanged(this, 0, getSize());
        }
    }
}
