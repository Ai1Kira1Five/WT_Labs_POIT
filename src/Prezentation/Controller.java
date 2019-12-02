package Prezentation;

import Domain.Entity.Operation;
import Domain.Entity.Transaction;
import com.Bank.Application.Services.DataManager;

import java.io.IOException;
import java.util.List;

import static com.Bank.Application.Services.DataManager.*;

/**
 * The type Controller.
 */
public class Controller {

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
        List<Transaction> transactions;
        transactions = Decode();

        View.showTransactions(transactions);
    }

    /**
     * Add new transaction.
     *
     * @throws IOException the io exception
     */
    public static void addNewTransaction() throws IOException {
        List<Transaction> transactions;
        transactions = DataManager.Decode();

        Operation newOperation = new Operation();
        Transaction newTransaction = new Transaction(newOperation);
        View.getNewTransaction(newTransaction);

        transactions.add(newTransaction);
        updateData(transactions);
    }

    /**
     * Delete transaction.
     *
     * @throws IOException the io exception
     */
    public static void deleteTransaction() throws IOException {
        List<Transaction> transactions;
        transactions = DataManager.Decode();

        int index = View.getDeleteIndex();

        transactions.remove( index - 1);
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
    public  static void compareTransactions() throws IOException{
        List<Transaction> transactions = DataManager.Decode();
        View.showSortMenu();

        sort(transactions);
        View.showTransactions(transactions);
    }

}
