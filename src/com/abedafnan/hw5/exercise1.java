package com.abedafnan.hw5;

import java.util.LinkedList;
import java.util.Random;

public class exercise1 {

    public static void main(String[] args) {

        // Create a LinkedList of integers
        LinkedList<Integer> integersList = new LinkedList<>();

        // Insert 25 random integers in the list
        for (int i = 0; i < 25; i++) {
            Random random = new Random();
            integersList.add(random.nextInt(101));
        }

        // Sort & print the content of the list
        System.out.println("Sorted List:");
        integersList.stream()
                .sorted()
                .forEach(num -> System.out.print(num + " "));

        // Calculate the sum of the elements
        System.out.print("\n\nThe sum of elements = ");
        int sum = integersList.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);

        // Calculate the floating-point average of the elements
        System.out.print("The avg of elements = ");
        double avg = integersList.stream().mapToDouble(Integer::doubleValue).average().getAsDouble();
        System.out.println(avg);

    }
}
