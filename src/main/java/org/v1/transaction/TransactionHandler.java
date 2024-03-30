package org.v1.transaction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TransactionHandler {
    public void writeTransaction(int custId, Transaction transaction) {
        String FILENAME = custId + ".txt";

        File file = new File(FILENAME);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file, true);
            writer.write(transaction.toString()+"\n");
            writer.close();
        } catch (IOException e) {
//                throw new RuntimeException(e);
            System.out.println("Transaction creation Failed!");
        }
    }

    public int getTransactionID(int custId) {
        String FILENAME = custId + ".txt";
        try {
            File file = new File(FILENAME);
            if (!file.exists()) {
                return 0;
            }
            Scanner sc = new Scanner(file);
            String transacInfo = "";
            while (sc.hasNext()) {
                transacInfo = sc.nextLine();
            }
            sc.close();
            Transaction transaction = casrStringToTransaction(transacInfo);
            return transaction.tid;
        } catch (IOException e) {
            System.out.println("Exeption: " + e);
        }
        return 0;
    }

    private Transaction casrStringToTransaction(String info) {
        String[] transac = info.split(" ");
        Transaction transaction = new Transaction(
                Integer.parseInt(transac[0]),
                transac[1],
                Double.parseDouble(transac[2]),
                Double.parseDouble(transac[3])
        );
        return transaction;
    }

}
