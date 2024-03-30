package org.v1.bank;

import org.v1.customer.Customer;
import org.v1.customer.CustomerHandler;
import org.v1.transaction.Transaction;
import org.v1.transaction.TransactionHandler;

import java.util.Scanner;

public class AccountActionHandler {
    public static void transferMoney() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter From Customer Id: ");
        int custId = sc.nextInt();

        System.out.println("Enter Password: ");
        String pass = sc.next();

        if (!authenticate(custId, pass)) {
            return;
        } else {
            Customer fromC = Bank.customerMap.get(custId);
            System.out.println("Enter Amount to Transfer: ");
            double amount = sc.nextDouble();
            if (amount <= 0) {
                System.out.println("Transfer Failed! Invalid Amount");
                return;
            } else if (fromC.getBalance() - amount < 1000) {
                System.out.println("Transfer Failed! Low balance");
                return;
            } else {
                System.out.println("Enter To Customer Id: ");
                int toCustId = sc.nextInt();
                Customer toC = Bank.customerMap.get(toCustId);
                if (toC == null) {
                    System.out.println("fails! No customer found");
                    return;
                } else {
                    fromC.setBalance(fromC.getBalance() - amount);
                    toC.setBalance(toC.getBalance() + amount);
                }

                System.out.println("Amount Withdrawn Successfully!");

            }
        }
    }

    public static void withdraw() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Customer Id: ");
        int custId = sc.nextInt();

        System.out.println("Enter Password: ");
        String pass = sc.next();

        if (!authenticate(custId, pass)) {
            return;
        } else {
            Customer c = Bank.customerMap.get(custId);
            System.out.println("Enter Amount to withdraw: ");
            double amount = sc.nextDouble();
            if (c.getBalance() - amount < 1000 || amount <= 0) {
                System.out.println("withdraw Failed!");
                return;
            } else {
                c.setBalance(c.getBalance() - amount);
                System.out.println("Amount Withdrawn Successfully!");

                TransactionHandler handler = new TransactionHandler();
                int transacId = handler.getTransactionID(custId);

                Transaction transaction = new Transaction(
                        ++transacId,
                        "Withdraw",
                        amount,
                        c.getBalance()
                );
                handler.writeTransaction(custId,transaction);
            }
        }
    }

    public static void deposit() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Customer Id: ");
        int custId = sc.nextInt();

        System.out.println("Enter Password: ");
        String pass = sc.next();

        if (!authenticate(custId, pass)) {
            return;
        } else {
            Customer c = Bank.customerMap.get(custId);
            System.out.println("Enter Amount to deposit: ");
            double amount = sc.nextDouble();
            if (amount <= 0) {
                System.out.println("Deposit Failed!");
                return;
            } else {
                c.setBalance(c.getBalance() + amount);
                System.out.println("Amount Deposited Successfully!");

                TransactionHandler handler = new TransactionHandler();
                int transacId = handler.getTransactionID(custId);

                Transaction transaction = new Transaction(
                        ++transacId,
                        "Deposit",
                        amount,
                        c.getBalance()
                );
                handler.writeTransaction(custId,transaction);
            }
        }
    }

    static boolean authenticate(int custId, String pass) {
        Customer c = Bank.customerMap.get(custId);
        if (c == null) {
            System.out.println("InValid user: No matched user");
            return false;
        } else {
            pass = CustomerHandler.getInstance().encryptPass(pass);
            System.out.println(c.getPassword() + "   " + pass);
            if (pass.equals(c.getPassword())) {
                System.out.println("Valid user");
                return true;
            }
        }
        System.out.println("InValid user");
        return false;
    }

}
