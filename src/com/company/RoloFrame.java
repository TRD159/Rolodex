package com.company;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class RoloFrame extends JFrame{
    JList clist;
    String[] ex = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};

    JScrollPane scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    public RoloFrame(String name) {
        super(name);

        clist = new JList<>(ex);
        clist.setVisibleRowCount(2);
        clist.setLayoutOrientation(JList.VERTICAL);

        scroll.setViewportView(clist);
        scroll.setBounds(0, 0, 180, 100);

        setSize(600, 400);

        setDefaultCloseOperation(3);

        setLayout(null);

        add(scroll);

        setVisible(true);
    }
}
