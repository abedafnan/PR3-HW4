package com.abedafnan.hw4.excercise4;

import java.io.Serializable;

public class Account implements Serializable {
    int accNo;
    String accName;
    double accBalance;

    public Account(int accNo, String accName, double accBalance) {
        this.accNo = accNo;
        this.accName = accName;
        this.accBalance = accBalance;
    }

    public int getAccNo() {
        return accNo;
    }

    public String getAccName() {
        return accName;
    }

    public double getAccBalance() {
        return accBalance;
    }

    @Override
    public String toString() {
        return new StringBuffer("Account No : ").append(this.accNo)
                .append("\nAccount Name : ").append(this.accName)
                .append("\nAccount Balance : ").append(this.accBalance).toString();
    }
}
