package com.Bank.application.dao;

import com.Bank.application.dao.transactionDao.TransactionDAO;

public class DaoFactory {
    private static TransactionDAO transactionDAO = new TransactionDAO();

    public static TransactionDAO getTransactionDAO(){return transactionDAO;}
}
