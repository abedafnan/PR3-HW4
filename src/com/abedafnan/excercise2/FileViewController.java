package com.abedafnan.excercise2;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.Optional;
import java.util.Scanner;

public class FileViewController {

    @FXML
    TextArea fileArea;
    String filePath;

    public void open() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        filePath = selectedFile.getPath();
        fileArea.setEditable(true);

        if (selectedFile != null) {
            try {
                Scanner scanner = new Scanner(selectedFile);
                while (scanner.hasNextLine()) {
                    String s = scanner.nextLine();
                    fileArea.appendText(s);
                }
                scanner.close();
            } catch (IOException ioexception) {
                fileArea.setText("File Not Found!");
            }
        }
    }

    public void save() {
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;
        try {
            File newFile = new File(filePath);
            outputStream = new FileOutputStream(newFile);
            printWriter = new PrintWriter(outputStream);
            printWriter.write(fileArea.getText());
            System.out.println("File Saved");

        } catch (FileNotFoundException exception) {
            JOptionPane.showMessageDialog(null,
                    "Cannot Find Your File", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {

            try {
                printWriter.close();
                outputStream.close();
            } catch (Exception exception) {
                System.out.println("An Error Occurred!");
            }

        }
    }

    public void close() {
        fileArea.setText("");
        fileArea.setEditable(false);
    }

    public void exit() {
        System.exit(0);
    }

    public void chooseFont() {
        String[] fontSizes = {"10", "15", "20", "25", "30"};
        JComboBox<String> fontSizesCombo = new JComboBox<>(fontSizes);
        JOptionPane.showMessageDialog(null,
                fontSizesCombo, "Choose Font Size", JOptionPane.QUESTION_MESSAGE);
        int selectedSize = Integer.parseInt((String)fontSizesCombo.getSelectedItem());

        fileArea.setFont(new Font("Sans Serif", selectedSize));
    }

    public void chooseColor() {
        // Setup the choice dialog data
        String[] colorsList = {"Red", "Green", "Blue", "Black"};
        ChoiceDialog<String> choiceDialog = new ChoiceDialog(colorsList[3], colorsList);
        choiceDialog.setTitle("Color Selection");
        choiceDialog.setContentText("Available Colors");
        choiceDialog.setHeaderText("Select the color from list");

        // Wait for button press to get selected data
        Optional<String> result = choiceDialog.showAndWait();
        if (result.isPresent()) {
            // Get the selected color
            String selectedColor = choiceDialog.getSelectedItem();
            selectedColor = selectedColor.toLowerCase();

            // Use the selected value to change the text color
            switch (selectedColor) {
                case "red":
                    fileArea.setStyle("-fx-text-fill: #f00;");
                    break;
                case "blue":
                    fileArea.setStyle("-fx-text-fill: #00f;");
                    break;
                case "green":
                    fileArea.setStyle("-fx-text-fill: #0f0;");
                    break;
                case "black":
                    fileArea.setStyle("-fx-text-fill: #000;");
                    break;
            }
        }
    }
}
