package com.Bank;

import Domain.Entity.Bank;
import Domain.Entity.Transaction;
import Prezentation.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Main.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException{
        List<Transaction> transactions = new ArrayList<>();
        Bank bank = new Bank(transactions);

        boolean work = true;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while (work) {
            Controller.showMainMenu();

            String result = in.readLine();
            switch (result) {
                case "1":
                    Controller.showTransactions();
                    break;
                case "2":
                    Controller.addNewTransaction();
                    break;
                case "3":
                    Controller.deleteTransaction();
                    break;
                case "4":
                    Controller.updateTransaction();
                    break;
                case "5":
                    Controller.compareTransactions();
                    break;
                case "0":
                    work = false;
                    break;
            }
        }
    }
}
