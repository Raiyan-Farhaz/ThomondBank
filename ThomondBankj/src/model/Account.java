package model;

import java.time.LocalDate;

public class Account {
    private int id;
    private int custNo;
    private double balance;
    private LocalDate dateCreated;

    public Account(int id, int custNo, double balance, LocalDate dateCreated) {
        this.id = id;
        this.custNo = custNo;
        this.balance = balance;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }



    public int getCustNo() {
        return custNo;
    }

    public void setCustNo(int custNo) {
        this.custNo = custNo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

}
