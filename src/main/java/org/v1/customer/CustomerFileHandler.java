package org.v1.customer;

import org.v1.bank.Bank;

import java.io.*;
import java.util.Map;

public class CustomerFileHandler {
    private static final String FILENAME = "customer_db.txt";

    public static CustomerFileHandler customerFileHandler;

    public static CustomerFileHandler getInstance() {
        if(customerFileHandler == null) {
            customerFileHandler = new CustomerFileHandler();
        }
        return customerFileHandler;
    }

    public void initialize() throws IOException {
        File file = new File(FILENAME);
        BufferedReader reader = new BufferedReader(
                new FileReader(file)
        );

        String customerInfo = reader.readLine();

        do {
            Customer customer = castStringToCustomer(customerInfo);
            Bank.customers.add(customer);
            Bank.customerMap.put(customer.custId, customer);
            customerInfo = reader.readLine();
        } while (customerInfo != null);
        reader.close();
        Bank.refCustomer = Bank.customers.get(Bank.customers.size() - 1);
        Bank.refCustId = Bank.refCustomer.getCustId();
        Bank.refAccountId = Bank.refCustomer.getAccountId();

    }

    private Customer castStringToCustomer(String customerInfo) {
        String[] info = customerInfo.split(" ");
        Customer c = new Customer(
                Integer.parseInt(info[0]),
                Long.parseLong(info[1]),
                info[2],
                info[3],
                Double.parseDouble(info[4])
        );
        return c;
    }

    public void addCustomerToFile(Customer customer) throws IOException {
        File file = new File(FILENAME);
        FileWriter writer = new FileWriter(file,true);
        writer.write("\n"+customer.toString());
        writer.close();
    }

    public void addCustomerMapToFile() throws IOException {
        File file = new File(FILENAME);
        FileWriter writer = new FileWriter(file);
        for(Map.Entry<Integer,Customer> entry: Bank.customerMap.entrySet()) {
            Customer c = entry.getValue();
            writer.write(c.toString()+"\n");
        }
        writer.close();
    }
}
