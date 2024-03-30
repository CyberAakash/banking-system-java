package org.v1.customer;

public class Customer {
    int custId;
    long accountId;
    String name;
    String password;
    double balance;

    Customer(int custId, long accountId, String name, String password, double balance) {
        this.custId = custId;
        this.accountId = accountId;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public int getCustId() {
        return custId;
    }

    public long getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return custId + " " + accountId + " " + name + " " + password + " " + balance;
    }
}
