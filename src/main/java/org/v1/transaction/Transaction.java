package org.v1.transaction;

public class Transaction {
    int tid;
    String type;
    double amount;
    double balance;

    public Transaction(int tid, String type, double amount, double balance) {
        this.tid = tid;
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        String SPACE = " ";
        return tid + SPACE + type + SPACE + amount + SPACE + balance;
    }
}
