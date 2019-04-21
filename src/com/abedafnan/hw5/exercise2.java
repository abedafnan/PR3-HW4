package com.abedafnan.hw5;

import com.abedafnan.hw4.excercise4.Account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class exercise2 {

    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();

        // Test accounts
        Account acc1 = new Account(0, "test", 35);
        Account acc2 = new Account(1, "test2", 22);
        Account acc3 = new Account(2, "test3", 42);
        accounts.add(acc1);
        accounts.add(acc2);
        accounts.add(acc3);

        //TODO: Read Account objects from file and add them to the list

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
}
