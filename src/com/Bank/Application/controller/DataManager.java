package com.Bank.application.controller;

import com.Bank.application.entity.Operation;
import com.Bank.application.entity.Transaction;
import com.Bank.application.entity.TransactionType;

import java.io.*;
import java.util.*;

/**
 * The type Data manager.
 */
public class DataManager {
    private static String split = ";";
    private static String csvFileName = "operations.csv";

    /**
     * Update data.
     *
     * @param transactions the transactions
     * @throws IOException the io exception
     */
    public static void updateData(List<Transaction> transactions) throws IOException {
        FileWriter csvWriter = new FileWriter(csvFileName);
        try{
            List<List<String>> rows = new LinkedList<>(Arrays.asList());
            for (Transaction transaction : transactions) {
                rows.add(Arrays.asList( transaction.getId(),
                        transaction.getSourceBank(),
                        transaction.getDestinationBank(),
                        transaction.getDate(),
                        transaction.getTransactionType().name(),
                        transaction.getOperation().getCard(),
                        Integer.toString(transaction.getOperation().getAmountOfCash())
                        )
                );
            }
            csvWriter.append("Id");
            csvWriter.append(split);
            csvWriter.append("Source");
            csvWriter.append(split);
            csvWriter.append("Destination");
            csvWriter.append(split);
            csvWriter.append("Date");
            csvWriter.append(split);
            csvWriter.append("TransactionType");
            csvWriter.append(split);
            csvWriter.append("Card");
            csvWriter.append(split);
            csvWriter.append("AmountOfCash");
            csvWriter.append("\n");

            for (List<String> rowData : rows) {
                csvWriter.append(String.join(split, rowData));
                csvWriter.append("\n");
            }
        }
        finally {
            csvWriter.flush();
            csvWriter.close();
        }
    }

    /**
     * Decode list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public static List<Transaction> Decode() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(csvFileName));
        String row;
        int iteration = 0;
        List<Transaction> transactions = new ArrayList<>();
        while ((row = csvReader.readLine()) != null) {
            if(iteration == 0) {
                iteration++;
                continue;
            }
            String[] data = row.split(split);
            transactions.add(new Transaction(data[0], data[1], data[2], data[3], TransactionType.valueOf(data[4]),new Operation(data[5], Integer.valueOf(data[6]))));
        }
        csvReader.close();
        return transactions;
    }

    /**
     * Sort list.
     *
     * @param transactions the transactions
     * @return the list
     * @throws IOException the io exception
     */
    public static List<Transaction> sort(List<Transaction> transactions) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String answer = in.readLine();

        switch (answer){
            case "1":
                Comparator<Transaction> compare1 = Comparator.comparing(obj -> obj.getId());
                Collections.sort(transactions, compare1);
                break;
            case "2":
                Comparator<Transaction> compare2 = Comparator.comparing(obj -> obj.getOperation().getAmountOfCash());
                Collections.sort(transactions, compare2);
                break;
            case "0":
                break;
        }

        return transactions;
    }
}
