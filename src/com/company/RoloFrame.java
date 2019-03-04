package com.company;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class RoloFrame extends JFrame{
    JList clist;
    ArrayList<String> ex = new ArrayList<>() {
        {

        }
    };

    ArrayList<Contact> contacts = new ArrayList<>();

    JScrollPane scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    JButton btn_save = new JButton("Save");
    JButton btn_new = new JButton("New");

    JLabel lbl_name = new JLabel("Name: ");
    JTextField txt_name = new JTextField();

    JLabel lbl_phone = new JLabel("Phone Number: ");
    JTextField txt_phone = new JTextField();

    JLabel lbl_address = new JLabel("Address: ");
    JTextField txt_address = new JTextField();

    public RoloFrame(String name) {
        super(name);

        clist = new JList<>() {
            {
                setListData(ex.toArray());
            }
        };
        clist.setLayoutOrientation(JList.VERTICAL);

        scroll.setViewportView(clist);
        scroll.setBounds(0, 0, 200, 300);

        btn_save.setBounds(203, 245, 145, 50);
        btn_new.setBounds(351, 245, 145, 50);

        lbl_name.setBounds(200, 0, 400, 50);
        lbl_name.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        txt_name.setBounds(275, 15, 200, 25);

        lbl_phone.setBounds(200, 25, 400, 50);
        lbl_phone.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        txt_phone.setBounds(375, 40, 100, 25);

        setPreferredSize(new Dimension(500, 300));
        pack();

        setSize(new Dimension(500 + getInsets().left + getInsets().right, 300 + getInsets().top + getInsets().bottom));

        setDefaultCloseOperation(3);

        setLayout(null);

        add(scroll);

        add(btn_save);
        add(btn_new);

        add(lbl_name);
        add(txt_name);

        add(lbl_phone);
        add(txt_phone);

        setVisible(true);
    }
}
