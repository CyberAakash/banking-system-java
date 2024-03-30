package org.v1;

import org.v1.bank.AccountActionHandler;
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
//        System.out.println(Arrays.toString(Bank.customers.toArray()));
//        System.out.println(Bank.customers.get(0).getName());

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
                    AccountActionHandler.deposit();

                    break;
                case 3:
//                  //Withdraw
                    AccountActionHandler.withdraw();
                    break;
                case 4:
//                  //Tranfer Money
                    AccountActionHandler.transferMoney();
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
}
