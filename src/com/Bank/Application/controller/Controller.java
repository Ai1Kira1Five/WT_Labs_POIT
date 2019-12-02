package com.Bank.application.controller;

import com.Bank.application.dao.transactionDao.ITransactionDAO;
import com.Bank.application.dao.transactionDao.TransactionDAO;
import com.Bank.application.entity.Operation;
import com.Bank.application.entity.Transaction;
import com.Bank.application.presentation.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * Delete transaction.
     *
     * @throws IOException the io exception
     */
    public static void deleteTransaction() throws IOException {
        List<Transaction> transactions = dao.getTransactions();

        int index = View.getDeleteIndex();

        dao.delete(index);

    }

    public static void deleteTransactionByID(int index) throws IOException {
        List<Transaction> transactions;
        transactions = DataManager.Decode();
        transactions.remove( index );
        updateData(transactions);
    }

    /**
     * Update transaction.
     *
     * @throws IOException the io exception
     */
    public static void updateTransaction() throws IOException {
        List<Transaction> transactions;
        transactions = DataManager.Decode();

        int transactionId = View.getUpdateIndex() - 1;
        Transaction gettedTransaction = transactions.get(transactionId);

        View.getUpdatedTransaction(gettedTransaction);

        updateData(transactions);
    }

    /**
     * Compare transactions.
     *
     * @throws IOException the io exception
     */
    public static void compareTransactions() throws IOException{
        List<Transaction> transactions = DataManager.Decode();
        View.showSortMenu();

        sort(transactions);
        View.showTransactions(transactions);
    }

}
