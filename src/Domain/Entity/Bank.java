package Domain.Entity;

import java.util.List;

/**
 * The type Bank.
 */
public class Bank {
    /**
     * The Transactions.
     */
    List<Transaction> transactions;

    /**
     * Instantiates a new Bank.
     *
     * @param transactions the transactions
     */
    public Bank(List<Transaction> transactions){
        this.transactions = transactions;
    }
}
