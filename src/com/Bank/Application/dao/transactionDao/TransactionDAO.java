package com.Bank.application.dao.transactionDao;

import com.Bank.application.controller.serialize.serialize;
import com.Bank.application.entity.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static com.Bank.application.controller.Controller.*;
import static com.Bank.application.controller.DataManager.updateData;
import static com.Bank.application.dao.DaoFactory.getTransactionDAO;

public class TransactionDAO implements ITransactionDAO {

    private static serialize<Transaction> serializer = new serialize<Transaction>();
    private static String filePath = getDatabasePath();

    public void save(ArrayList<Transaction> vehicles){
        serializer.serialize(filePath, vehicles);
    }

    public void delete(int index) throws IOException {
        ArrayList<Transaction> transactions = getTransactions();
        if (transactions != null){
            transactions.remove(index-1);
            deleteTransactionByID(index-1);
            save(transactions);
        }
    }

    public void insert(Transaction transaction) throws IOException {
        ArrayList<Transaction> transactions = getTransactions();
        if(transactions == null){
            transactions = new ArrayList<Transaction>();
        }
        transactions.add(transaction);
        updateData(transactions);
        save(transactions);
    }

    public ArrayList<Transaction> getTransactions() throws IOException {
        return serializer.deserialize(filePath);
    }

    public ArrayList<Transaction> getTransactionByBank(String bank) throws IOException {
        ArrayList<Transaction> transactions = getTransactionDAO().getTransactions();
        ArrayList<Transaction> resTransactions = new ArrayList<Transaction>();

        for(Transaction transaction : transactions){
            if(transaction.getSourceBank() == bank) resTransactions.add(transaction);
        }
        return resTransactions;
    }

    private static String getDatabasePath(){
        return new File("").getAbsolutePath()+"\\data\\trans.txt";
    }
}
