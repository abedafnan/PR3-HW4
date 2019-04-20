package com.abedafnan.hw4.excercise3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class MainFrame extends JFrame {
    JPanel topPanel;
    JLabel label;
    JTextField pathFiled;
    JButton textButton;
    JButton binaryButton;

    public MainFrame(String title) {
        super(title);
        this.setLayout(new FlowLayout());
        this.setSize(350, 150);
        this.setLocationRelativeTo(null);

        topPanel = new JPanel();
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        label = new JLabel("Path: ");
        pathFiled = new JTextField(20);
        topPanel.add(label);
        topPanel.add(pathFiled);
        this.add(topPanel);

        textButton = new JButton("Convert to Text");
        textButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertToText();
            }
        });
        binaryButton = new JButton("Convert to Binary");
        binaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertToBinary();
            }
        });
        this.add(textButton);
        this.add(binaryButton);
    }

    private boolean checkValidation(String type) {
        String path = pathFiled.getText();
        String fileExtension = path.substring(path.length()-3);

        // Check if the file extension is correct
        if ((type.equals("txt") && !fileExtension.equals("txt")) ||
                (type.equals("dat") && !fileExtension.equals("dat"))) {
            JOptionPane.showMessageDialog(this,
                    "File extension is wrong", "Wrong Path", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        // Check if the the path field is empty
        if (path.equals("")) {
            JOptionPane.showMessageDialog(this,
                    "Insert the file path", "Empty Input", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private void convertToText() {
        if (!checkValidation("dat")) {
            return;
        }
        String path = pathFiled.getText();
        String binaryContent = readBinaryFile(path);

        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        try {
            File file = new File("output.txt");
            outputStream = new FileOutputStream(file);
            printWriter = new PrintWriter(outputStream);

            printWriter.write(binaryContent);
            JOptionPane.showMessageDialog(this,
                    "File converted..", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException exception) {
            exception.printStackTrace();

        } finally {

            try {
                printWriter.close();
                outputStream.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        }
    }

    /**
     *
     * @param path the path of the file to be converted
     * @return the content of the binary file
     */
    private String readBinaryFile(String path) {
        int i;
        String content = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);

            while ((i = dataInputStream.read()) != -1)
                content += (char) i;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    private void convertToBinary() {
        if (!checkValidation("txt")) {
            return;
        }
        String path = pathFiled.getText();
        String textContent = readTextFile(path);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("output.dat");
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

            dataOutputStream.writeChars(textContent);
            JOptionPane.showMessageDialog(this,
                    "File converted..", "Success", JOptionPane.INFORMATION_MESSAGE);

            dataOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param path of the text file to be read
     * @return the content of the text file
     */
    private String readTextFile(String path) {
        String fileContent = "";
        try {
            File file = new File(path);
            FileInputStream inputStream = new FileInputStream(file);

            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                fileContent = fileContent + scanner.nextLine() + "\n";
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return fileContent;
    }
}
