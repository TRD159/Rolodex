package com.company;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class RoloFrame extends JFrame{
    JList clist;
    ArrayList<String> ex = new ArrayList<>() {
        {
            //add("B, A");
            //add("F, E");
        }
    };

    ArrayList<Contact> contacts = new ArrayList<>(){
        {
            //add(new Contact("A", "B", "C", "D"));
            //add(new Contact("E", "F", "G", "H"));
        }
    };

    JScrollPane scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    JButton btn_save = new JButton("Save");
    JButton btn_new = new JButton("New");
    JButton btn_del = new JButton("Delete Contact");

    JLabel lbl_name = new JLabel("Name: ");
    JTextField txt_namef = new JTextField();
    JTextField txt_namel = new JTextField();

    JLabel lbl_phone = new JLabel("Phone Number: ");
    JTextField txt_phone = new JTextField();

    JLabel lbl_address = new JLabel("Address: ");
    JTextArea txt_address = new JTextArea();

    public RoloFrame(String name) {
        super(name);

        clist = new JList<>() {
            {
                setListData(ex.toArray());
            }
        };
        clist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clist.setLayoutOrientation(JList.VERTICAL);

        contacts.sort(new Sortr());
        Collections.sort(ex);

        scroll.setViewportView(clist);
        scroll.setBounds(0, 0, 200, 300);

        btn_save.setBounds(203, 245, 145, 50);
        btn_new.setBounds(351, 245, 145, 50);

        lbl_name.setBounds(200, 0, 400, 50);
        lbl_name.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        txt_namef.setBounds(275, 15, 200, 25);
        txt_namel.setBounds(275, 45, 200, 25);

        lbl_phone.setBounds(200, 56, 400, 50);
        lbl_phone.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        txt_phone.setBounds(375, 71, 100, 25);

        lbl_address.setBounds(200, 95, 400, 25);
        lbl_address.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        txt_address.setBounds(300, 100, 175, 75);

        txt_address.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt_phone.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt_namel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt_namef.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setPreferredSize(new Dimension(500, 300));
        pack();

        setSize(new Dimension(500 + getInsets().left + getInsets().right, 300 + getInsets().top + getInsets().bottom));

        setDefaultCloseOperation(3);

        btn_save.addActionListener(e -> save());

        clist.addListSelectionListener(e -> switc());

        setLayout(null);

        add(scroll);

        add(btn_save);
        add(btn_new);

        add(lbl_name);
        add(txt_namef);
        add(txt_namel);

        add(lbl_phone);
        add(txt_phone);

        add(lbl_address);

        add(txt_address);

        setVisible(true);
    }

    public void nu() {
        txt_namef.setText("");
        txt_namel.setText("");

        txt_phone.setText("");

        txt_address.setText("");
    }

    public void save() {
        if(txt_namel.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Last name needs to be filled out", "ALERT", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(clist.getSelectedIndex() == -1) {
            contacts.add(new Contact(txt_namef.getText(), txt_namel.getText(), txt_phone.getText(), txt_address.getText()));
            ex.add(contacts.get(ex.size()).toString());
        } else {
            contacts.set(clist.getSelectedIndex(), new Contact(txt_namef.getText(), txt_namel.getText(), txt_phone.getText(), txt_address.getText()));
            ex.set(clist.getSelectedIndex(), contacts.get(clist.getSelectedIndex()).toString());
        }

        Collections.sort(ex);
        contacts.sort(new Sortr());

        nu();
    }

    public void switc() {

        Contact c = contacts.get(clist.getSelectedIndex());

        txt_namef.setText(c.getNamef());
        txt_namel.setText(c.getNamel());

        txt_phone.setText(c.getNum());

        txt_address.setText(c.getAdd());
    }
}
