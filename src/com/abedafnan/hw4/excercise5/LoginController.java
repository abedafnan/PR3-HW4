package com.abedafnan.hw4.excercise5;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class LoginController {
    @FXML
    Button okButton;
    @FXML
    Button exitButton;
    @FXML
    TextField nameField;
    @FXML
    PasswordField passField;
    @FXML
    TextField addNameField;
    @FXML
    PasswordField addPassField;
    @FXML
    Button addButton;
    @FXML
    Button cancelButton;
    @FXML
    VBox mainPanel;
    @FXML
    GridPane optionsPanel;
    @FXML
    VBox addPanel;

    /**
     * For testing use name: test, password: testtest
     */
    public void login() {
        // Check if the name and password fields are empty
        if (nameField.getText().equals("") || passField.getText().equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Please enter your name and password", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Prepare file for reading
            File file = new File("passes.txt");
            FileInputStream inputStream = new FileInputStream(file);
            Scanner scanner = new Scanner(inputStream);

            String retrievedPass = getMD5Hash(passField.getText());

            while (scanner.hasNextLine()) {
                String userInfo = scanner.nextLine();
                String[] userInfoArray = userInfo.split(" ");

                // Change the scene if the login info is correct
                if (userInfoArray[0].equals(nameField.getText()) && userInfoArray[1].equals(retrievedPass)) {
                    Stage stage = (Stage) mainPanel.getScene().getWindow();
                    stage.setScene(loadScene("options.fxml"));
                    return;
                }
            }

            // Display dialog if the info is wrong
            JOptionPane.showMessageDialog(null,
                    "Name or Password is Incorrect", "Error", JOptionPane.ERROR_MESSAGE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // load the new scene to add student
    public void addStudent() {
        Stage stage = (Stage) optionsPanel.getScene().getWindow();
        stage.setScene(loadScene("add.fxml"));
    }

    public void add() {
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;

        String name = addNameField.getText();
        String pass = getMD5Hash(addPassField.getText());
        String content = "\n" + name + " " + pass;
        try {
            File file = new File("passes.txt");
            outputStream = new FileOutputStream(file, true);
            printWriter = new PrintWriter(outputStream);
            printWriter.write(content);

            // Display dialog when the student is added
            JOptionPane.showMessageDialog(null,
                    "Your information is successfully added", "Success", JOptionPane.INFORMATION_MESSAGE);

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

    // Cancelling the add operation will bring back the options scene
    public void cancel() {
        Stage stage = (Stage) addPanel.getScene().getWindow();
        stage.setScene(loadScene("options.fxml"));
    }

    public void exit() {
        System.exit(0);
    }

    /**
     * NOTE: This might not work in some JDK versions,
     * because of the class DatatypeConverter which is deprecated
     * (try editing/removing VM options)
     *
     * @param string is the text to be converted
     * @return the MD5 hash of the string
     */
    public String getMD5Hash(String string) {
        String md5Hash = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            byte[] digest = md.digest();
            md5Hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return md5Hash;
    }

    /**
     *
     * @param resource of the fxml file to be loaded
     * @return the new scene to be displayed
     */
    private Scene loadScene(String resource) {
        Scene scene = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resource));
            Parent parent = loader.load();
            scene = new Scene(parent);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return scene;
    }
}
