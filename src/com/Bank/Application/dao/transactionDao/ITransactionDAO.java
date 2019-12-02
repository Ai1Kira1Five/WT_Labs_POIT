package com.Bank.application.dao.transactionDao;

import com.Bank.application.entity.Transaction;

import java.io.IOException;
import java.util.ArrayList;

public interface ITransactionDAO {
    void delete(int index) throws IOException;

    void insert(Transaction transaction) throws IOException;

    ArrayList<Transaction> getTransactions() throws IOException;
}
