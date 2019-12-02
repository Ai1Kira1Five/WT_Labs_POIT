package com.Bank.application.entity;

import java.io.Serializable;
import java.util.List;

/**
 * The type Bank.
 */
public class Bank implements Serializable {
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
