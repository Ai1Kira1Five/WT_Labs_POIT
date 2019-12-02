package com.Bank.application.controller;

import com.Bank.application.entity.Transaction;
import java.io.*;
import java.util.*;

/**
 * The type Data manager.
 */
public class DataManager {
    /**
     * Sort list.
     *
     * @param transactions the transactions
     * @return the list
     * @throws IOException the io exception
     */
    public static ArrayList<Transaction> sort(ArrayList<Transaction> transactions) throws IOException{
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
