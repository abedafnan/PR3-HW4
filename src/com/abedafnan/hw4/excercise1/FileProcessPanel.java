package com.abedafnan.hw4.excercise1;

import javax.swing.*;
import java.awt.*;

public class FileProcessPanel extends JPanel {
    public JTextArea area;
    private JScrollPane scrollPane;

    public FileProcessPanel() {
        this.setLayout(new GridLayout(1,1));

        // Create the TextArea
        area = new JTextArea();
        area.setText("Open a text file..");
        area.setEditable(false);
        area.setLineWrap(true);

        // Create the ScrollPane
        scrollPane = new JScrollPane(area);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);
    }

}
