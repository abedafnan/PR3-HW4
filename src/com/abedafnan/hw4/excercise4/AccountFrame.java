package com.abedafnan.hw4.excercise4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AccountFrame extends JFrame {
    JPanel bottomPanel;
    JPanel centerPanel;
    JButton writeButton;
    JButton readButton;
    JTextField nameFiled;
    JTextField noField;
    JTextField balanceField;
    JLabel nameLabel;
    JLabel noLabel;
    JLabel balanceLabel;

    public AccountFrame(String title) {
        super(title);
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel = new JPanel(new GridLayout(3, 3, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        nameFiled = new JTextField(30);
        noField = new JTextField(30);
        balanceField = new JTextField(30);
        nameLabel = new JLabel("Name:");
        noLabel = new JLabel("No:");
        balanceLabel = new JLabel("Balance:");
        centerPanel.add(noLabel);
        centerPanel.add(noField);
        centerPanel.add(nameLabel);
        centerPanel.add(nameFiled);
        centerPanel.add(balanceLabel);
        centerPanel.add(balanceField);

        writeButton = new JButton("Write");
        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });
        readButton = new JButton("Read");
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readAccount();
            }
        });
        bottomPanel.add(writeButton);
        bottomPanel.add(readButton);

        this.add(centerPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void createAccount() {
        String noString = noField.getText();
        String name = nameFiled.getText();
        String balanceString =  balanceField.getText();

        // Check if the account no. and balance contain letter
        if (noString.matches("[0-9]*[a-zA-Z]+") || balanceString.matches("[0-9]*[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(this, "the number and balance " +
                    "must contain only numbers", "Wrong Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // parse the no. and balance
        int no = Integer.parseInt(noString);
        double balance = Double.parseDouble(balanceString);

        // Create & write account to the file
        Account account = new Account(no, name, balance);
        writeAccount(account);

        // Show message dialog on success and clear fields
        JOptionPane.showMessageDialog(this,
                "Account Registered..", "Success", JOptionPane.INFORMATION_MESSAGE);
        noField.setText("");
        nameFiled.setText("");
        balanceField.setText("");
    }

    private void writeAccount(Account account) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("account"));
            outputStream.writeObject(account);

        } catch (IOException ex) {
            System.out.println("Error on creating stream");
        }
    }

    public void readAccount() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("account"));
            while (true) {
                Account account = (Account)inputStream.readObject();
                System.out.println(account + "\n");
            }

        } catch (EOFException e) {
            System.out.println("End of File");
        } catch (IOException e) {
            System.out.println("Error Opening file");
        } catch (ClassNotFoundException e) {
            System.out.println("Error reading object");
        }
    }

}

