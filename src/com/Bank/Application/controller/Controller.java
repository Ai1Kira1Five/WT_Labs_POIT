package com.Bank.application.controller;

import com.Bank.application.dao.transactionDao.TransactionDAO;
import com.Bank.application.entity.Operation;
import com.Bank.application.entity.Transaction;
import com.Bank.application.presentation.View;

import java.io.IOException;
import java.util.ArrayList;

import static com.Bank.application.controller.DataManager.*;

/**
 * The type Controller.
 */
public class Controller {
    static TransactionDAO dao = new TransactionDAO();
    /**
     * Show main menu.
     */
    public static void showMainMenu() {
        View.showMainMenu();
    }

    /**
     * Show transactions.
     *
     * @throws IOException the io exception
     */
    public static void showTransactions() throws IOException {
        ArrayList<Transaction> transactions = dao.getTransactions();
        View.showTransactions(transactions);
    }

    public static void showTransactionsFromXML() throws IOException {
        ArrayList<Transaction> transactions = dao.getTransactionsFromXML();
        View.showTransactions(transactions);
    }

    /**
     * Add new transaction.
     *
     * @throws IOException the io exception
     */
    public static void addNewTransaction() throws IOException {
        Operation newOperation = new Operation();
        Transaction newTransaction = new Transaction(newOperation);
        View.getNewTransaction(newTransaction);
        dao.insert(newTransaction);
    }

    public static void addNewTransactionToXML() throws IOException {
        Operation newOperation = new Operation();
        Transaction newTransaction = new Transaction(newOperation);
        View.getNewTransaction(newTransaction);
        dao.insertToXML(newTransaction);
    }

    /**
     * Delete transaction.
     *
     * @throws IOException the io exception
     */
    public static void deleteTransaction() throws IOException {
        dao.delete(View.getDeleteIndex());
    }

    public static void deleteTransactionFromXML() throws IOException {
        dao.delete(View.getDeleteIndex());
    }

    public static void deleteTransactionByID(int index) throws IOException {
        ArrayList<Transaction> transactions = dao.getTransactions();
        transactions.remove( index );
        dao.save(transactions);
    }

    public static void deleteTransactionByIDFromXML(int index) throws IOException {
        ArrayList<Transaction> transactions = dao.getTransactionsFromXML();
        transactions.remove( index );
        dao.saveToXML(transactions);
    }

    /**
     * Update transaction.
     *
     * @throws IOException the io exception
     */
    public static void updateTransaction() throws IOException {
        ArrayList<Transaction> transactions = dao.getTransactions();

        int transactionId = View.getUpdateIndex() - 1;
        Transaction getTransaction = transactions.get(transactionId);

        View.getUpdatedTransaction(getTransaction);

        dao.save(transactions);
    }

    public static void updateTransactionInXML() throws IOException {
        ArrayList<Transaction> transactions = dao.getTransactionsFromXML();

        int transactionId = View.getUpdateIndex() - 1;
        Transaction getTransaction = transactions.get(transactionId);

        View.getUpdatedTransaction(getTransaction);

        dao.saveToXML(transactions);
    }

    /**
     * Compare transactions.
     *
     * @throws IOException the io exception
     */
    public static void compareTransactions() throws IOException{
        ArrayList<Transaction> transactions = dao.getTransactions();
        View.showSortMenu();
        sort(transactions);
        View.showTransactions(transactions);
        dao.save(transactions);
    }

    public static void compareTransactionsInXML() throws IOException{
        ArrayList<Transaction> transactions = dao.getTransactionsFromXML();
        View.showSortMenu();
        sort(transactions);
        View.showTransactions(transactions);
        dao.saveToXML(transactions);
    }

}
