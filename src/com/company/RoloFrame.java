package com.company;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class RoloFrame extends JFrame {
    JList clist;
    ArrayList<String> ex;

    ArrayList<Contact> contacts;

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

    //JOptionPane save = new JOptionPane();

    int s = -1;

    public RoloFrame(String name) {
        super(name);

        ex = new ArrayList<>() {
            {
                //add("B, A");
                //add("F, E");
            }
        };

        contacts = new ArrayList<>();

        setDefaultCloseOperation(0);

        WindowListener wList = new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    FileInputStream exfin = new FileInputStream("Ex.ser");
                    FileInputStream cfin = new FileInputStream("Cont.ser");

                    ObjectInputStream exin = new ObjectInputStream(exfin);
                    ObjectInputStream cin = new ObjectInputStream(cfin);

                    ex = (ArrayList<String>) exin.readObject();
                    contacts = (ArrayList<Contact>) cin.readObject();

                    clist.setListData(ex.toArray());

                    exin.close();
                    cin.close();

                    exfin.close();
                    cfin.close();
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    FileOutputStream exfos = new FileOutputStream("Ex.ser");
                    FileOutputStream cfos = new FileOutputStream("Cont.ser");

                    ObjectOutputStream exos = new ObjectOutputStream(exfos);
                    ObjectOutputStream cos = new ObjectOutputStream(cfos);

                    exos.writeObject(ex);
                    cos.writeObject(contacts);

                    exos.close();
                    cos.close();

                    cfos.close();
                    exfos.close();

                    System.exit(0);
                } catch (Exception x) {
                    x.printStackTrace();
                    System.exit(0);
                }
            }
        };

        addWindowListener(wList);

        clist = new JList<>();

        clist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clist.setLayoutOrientation(JList.VERTICAL);

        contacts.sort(new Sortr());
        Collections.sort(ex);

        scroll.setViewportView(clist);
        scroll.setBounds(0, 0, 200, 300);

        btn_save.setBounds(203, 245, 145, 50);
        btn_new.setBounds(351, 245, 145, 50);
        btn_del.setBounds(203, 190, 293, 50);

        lbl_name.setBounds(200, 0, 400, 50);
        lbl_name.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        txt_namef.setBounds(275, 15, 200, 25);
        txt_namel.setBounds(275, 45, 200, 25);

        lbl_phone.setBounds(200, 56, 400, 50);
        lbl_phone.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        txt_phone.setBounds(375, 72, 100, 25);

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


        btn_save.addActionListener(e -> save());
        btn_new.addActionListener(e -> nu());
        btn_del.addActionListener(e -> del());

        clist.addListSelectionListener(e -> switc());

        setLayout(null);

        add(scroll);

        add(btn_save);
        add(btn_new);
        add(btn_del);

        btn_del.setVisible(false);

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

        clist.clearSelection();

        btn_del.setVisible(false);
    }

    public void del() {
        ex.remove(clist.getSelectedIndex());
        contacts.remove(clist.getSelectedIndex());

        nu();

        clist.setListData(ex.toArray());
        scroll.setViewportView(clist);
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

        clist.setListData(ex.toArray());
        scroll.setViewportView(clist);
    }

    public void switc() {
        if(clist.getSelectedIndex() == -1) {
            nu();
            return;
        }
        //System.out.print(clist.getSelectedIndex());

        int n = clist.getSelectedIndex();

        if(s != -1/* && !(txt_namef.getText().equals(contacts.get(s).getNamef()) && txt_namel.getText().equals(contacts.get(s).getNamel()) && txt_phone.getText().equals(contacts.get(s).getNum()) && txt_address.getText().equals(contacts.get(s).getAdd()))*/) {
            Contact oc = contacts.get(s);
            Contact nc = new Contact(txt_namef.getText(), txt_namel.getText(), txt_phone.getText(), txt_address.getText());

            if(!oc.equals(nc)) {
                int o = JOptionPane.showConfirmDialog(this, "Save Changes?", "Hey!", JOptionPane.YES_NO_CANCEL_OPTION);

                switch (o) {
                    case JOptionPane.YES_OPTION:
                        save();
                        break;
                    case JOptionPane.NO_OPTION:
                        break;
                    case JOptionPane.CANCEL_OPTION:
                        return;
                }
            }

        }
        s = clist.getSelectedIndex();
        //clist.setSelectedIndex(s);

        Contact c = contacts.get(s);

        txt_namef.setText(c.getNamef());
        txt_namel.setText(c.getNamel());

        txt_phone.setText(c.getNum());

        txt_address.setText(c.getAdd());

        btn_del.setVisible(true);
    }
}
