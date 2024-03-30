package org.v1;

import org.v1.bank.Bank;
import org.v1.customer.Customer;
import org.v1.customer.CustomerFileHandler;
import org.v1.customer.CustomerHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        Initializing Customers
        try {
            CustomerFileHandler.getInstance().initialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Arrays.toString(Bank.customers.toArray()));
        System.out.println(Bank.customers.get(0).getName());

        printMenu();


    }

    static void printMenu() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Choose from the Menu: ");
            System.out.println("1. Add Customer\n2. Deposit\n3. Withdraw\n4. Tranfer Money\n5. Print Customer\n6. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    //        Creating New Customers
                    CustomerHandler.getInstance().addCustomer();
                    System.out.println(Bank.customers.get(Bank.customers.size() - 1).getName());
                    break;
                case 2:
//                  //Deposit
                    deposit();

                    break;
                case 3:
//                  //Withdraw
                    withdraw();
                    break;
                case 4:
//                  //Tranfer Money
                    transferMoney();
                    break;
                case 5:
//                    Print Map
                    printMap();
                    break;
                case 6:
                    try {
                        CustomerFileHandler.getInstance().addCustomerMapToFile();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.exit(0);
                    break;
                default:
//                  //Invalid
                    System.out.println("Oops! Inavild Option");
                    break;
            }
        } while (true);
    }

    private static void printMap() {
        System.out.println("Customer list");
        for(Map.Entry<Integer,Customer> entry: Bank.customerMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        }
    }

    private static void transferMoney() {
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

    private static void withdraw() {
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
            }
        }
    }

    private static void deposit() {
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
