package com.abedafnan.hw5;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

public class exercise3 {

    public static void main(String[] args) {
        HashMap<String, Integer> wordsCount = new HashMap<>();
        HashMap<Character, Integer> charsCount = new HashMap<>();

        String fileContent = readFile();

        // Print the content of the file
        System.out.println("File Content: ");
        System.out.println(fileContent);

        // Count and print the occurrence of each word in the file content
        String[] contentArray = fileContent.split(" ");
        Arrays.stream(contentArray).forEach(element -> {
            if (!wordsCount.containsKey(element)) {
                wordsCount.put(element, 1);
            } else {
                int newCount = wordsCount.get(element) + 1;
                wordsCount.replace(element, newCount);
            }
        });
        System.out.printf("\n%-10s %10s\n----------------------------", "Word", "Count");
        wordsCount.forEach((key, val) -> System.out.printf("\n%-10s %10d", key, val));

        // Count and print the occurrence of each letter in the file content
        Character[] contentCharArray = fileContent.chars()
                .mapToObj(c -> (char)c)
                .toArray(Character[]::new);
        Stream.of(contentCharArray).forEach(character -> {
            if (!charsCount.containsKey(character)) {
                charsCount.put(character, 1);
            } else {
                int newCount = charsCount.get(character) + 1;
                charsCount.replace(character, newCount);
            }
        });
        System.out.printf("\n\n%-10s %10s\n----------------------------", "Letter", "Count");
        charsCount.forEach((key, val) -> System.out.printf("\n%-10s %10d", key, val));

    }

    /**
     *
     * @return The content of the file to be read as a string
     */
    public static String readFile() {
        String fileContent = "";
        try {
            File file = new File("exercise3.txt");
            FileInputStream inputStream = new FileInputStream(file);

            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNext()) {
                fileContent = fileContent + scanner.next() + " ";
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return fileContent;
    }

}
