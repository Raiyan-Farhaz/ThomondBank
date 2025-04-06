package model;

import java.time.LocalDate;

public abstract class Account {
    private int id;
    private int custNo;
    private double balance;
    private LocalDate dateCreated;

    public Account(int id, int custNo, double balance) {
        this.id = id;
        this.custNo = custNo;
        this.balance = balance;

    }

    public int getId() {
        return id;
    }
    public LocalDate getDateCreated() {
        return dateCreated;
    }
    public int getCustNo() {
        return custNo;
    }
    public double getBalance() {
        return balance;
    }
    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        this.balance += amount;
    }

    public abstract void withdraw(double amount);
}


