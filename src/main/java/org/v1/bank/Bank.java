package org.v1.bank;

import org.v1.customer.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    public static List<Customer> customers = new ArrayList<>();
    public static Map<Integer,Customer> customerMap = new HashMap<>();
    public static Customer refCustomer;
    public static int refCustId;
    public static long refAccountId;
    public static final double INITIAL_BALANCE = 10000;


}
