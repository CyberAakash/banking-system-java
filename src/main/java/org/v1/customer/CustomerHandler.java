package org.v1.customer;

import org.v1.bank.Bank;

import java.io.IOException;
import java.util.Scanner;

public class CustomerHandler {

    public static CustomerHandler customerHandler;

    public static CustomerHandler getInstance() {
        if (customerHandler == null) {
            customerHandler = new CustomerHandler();
        }
        return customerHandler;
    }

    public void addCustomer() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter you name: ");
        String name = sc.nextLine();

        System.out.println("Enter Password");
        String pass = sc.nextLine();

        System.out.println("Retype Password");
        String repass = sc.nextLine();

        if (name == "" || pass == "" || repass == "") {
            System.out.println("Account creation failed!");
            return;
        }
        if (!pass.equals(repass)) {
            System.out.println("Account creation failed! Password Mismatch");
            return;
        }
        if (!validPass(pass)) {
            System.out.println("Account creation failed! Invalid Password");
            return;
        }
        pass = encryptPass(pass);

        Bank.refCustId++;
        Bank.refAccountId++;
        Customer c = new Customer(
                Bank.refCustId,
                Bank.refAccountId,
                name,
                pass,
                Bank.INITIAL_BALANCE
        );

        Bank.refCustomer = c;
        Bank.customers.add(c);
        Bank.customerMap.put(c.custId,c);
        try {
            CustomerFileHandler.getInstance().addCustomerToFile(c);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String encryptPass(String pass) {
        int len = pass.length();
        StringBuilder encryptedPass = new StringBuilder();
        for (int i = 0; i < len; i++) {
            switch (pass.charAt(i)) {
                case 'Z':
                    encryptedPass.append('A');
                    break;
                case 'z':
                    encryptedPass.append('a');
                    break;
                case '9':
                    encryptedPass.append('0');
                    break;
                default:
                    encryptedPass.append((char) (pass.charAt(i) + 1));
                    break;
            }
        }
        return encryptedPass.toString();
    }

    private boolean validPass(String pass) {
        for (char c : pass.toCharArray()) {
            if ((c < 65 && c > 90) || (c < 97 && c > 122) || (c < 48 && c > 57)) {
                return false;
            }
        }
        return true;
    }
}
