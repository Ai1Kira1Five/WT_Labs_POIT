package com.Bank.Application.Services;

import Domain.Entity.Operation;
import Domain.Entity.Transaction;
import Domain.Entity.TransactionType;

import java.io.*;
import java.util.*;

/**
 * The type Data manager.
 */
public class DataManager {
    /**
     * Update data.
     *
     * @param transactions the transactions
     * @throws IOException the io exception
     */
    public static void updateData(List<Transaction> transactions) throws IOException {
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

        FileWriter csvWriter = new FileWriter("operations.csv");
        csvWriter.append("Id");
        csvWriter.append(";");
        csvWriter.append("Source");
        csvWriter.append(";");
        csvWriter.append("Destination");
        csvWriter.append(";");
        csvWriter.append("Date");
        csvWriter.append(";");
        csvWriter.append("TransactionType");
        csvWriter.append(";");
        csvWriter.append("Card");
        csvWriter.append(";");
        csvWriter.append("AmountOfCash");
        csvWriter.append("\n");

        for (List<String> rowData : rows) {
            csvWriter.append(String.join(";", rowData));
            csvWriter.append("\n");
        }
        //TODO: finally
        csvWriter.flush();
        csvWriter.close();
    }

    /**
     * Decode list.
     *
     * @return the list
     * @throws IOException the io exception
     */
    public static List<Transaction> Decode() throws IOException {
        //TODO: constants
        BufferedReader csvReader = new BufferedReader(new FileReader("operations.csv"));
        String row;
        int iteration = 0;
        List<Transaction> transactions = new ArrayList<>();
        while ((row = csvReader.readLine()) != null) {
            if(iteration == 0) {
                iteration++;
                continue;
            }
            String[] data = row.split(";");
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
    //TODO: layers refactor
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
