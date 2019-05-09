package com.abedafnan.hw5;

import com.abedafnan.hw4.excercise4.Account;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class exercise2 {

    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();

        readAccount(accounts);

        // Sort accounts based on the account balance
        Collections.sort(accounts, new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                if (o1.getAccBalance() < o2.getAccBalance())
                    return 1;
                else if (o1.getAccBalance() > o2.getAccBalance())
                    return -1;
                return 0;
            }
        });

        accounts.forEach(acc -> System.out.println(acc + "\n"));
    }

    private static void readAccount(ArrayList<Account> accounts) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("account"));
            while (true) {
                Account account = (Account)inputStream.readObject();
                accounts.add(account);
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
