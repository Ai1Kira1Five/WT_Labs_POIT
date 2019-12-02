package com.Bank.application.dao.transactionDao;

import com.Bank.application.controller.serialize.txt.serialize;
import com.Bank.application.controller.serialize.xml.XMLSerialize;
import com.Bank.application.entity.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import static com.Bank.application.controller.Controller.*;

public class TransactionDAO implements ITransactionDAO {

    private static serialize<Transaction> serializer = new serialize<Transaction>();
    private static XMLSerialize<Transaction> xmlSerialize = new XMLSerialize<Transaction>();
    private static String filePath = getDatabasePath();
    private static String xmlFilePath = getXMLDatabasePath();

    public void save(ArrayList<Transaction> transactions){
        serializer.serialize(filePath, transactions);
    }

    public void saveToXML(ArrayList<Transaction> transactions) throws IOException { xmlSerialize.xmlSerialize(xmlFilePath, transactions); }

    public void delete(int index) throws IOException {
        ArrayList<Transaction> transactions = getTransactions();
        if (transactions != null){
            transactions.remove(index - 1);
            deleteTransactionByID(index - 1);
            save(transactions);
        }
    }

    public void deleteFromXML(int index) throws IOException {
        ArrayList<Transaction> transactions = getTransactionsFromXML();
        if (transactions != null){
            transactions.remove(index - 1);
            deleteTransactionByIDFromXML(index - 1);
            saveToXML(transactions);
        }
    }

    public void insert(Transaction transaction) throws IOException {
        ArrayList<Transaction> transactions = getTransactions();
        if(transactions == null){
            transactions = new ArrayList<Transaction>();
        }
        transactions.add(transaction);
        save(transactions);
    }

    public void insertToXML(Transaction transaction) throws IOException {
        ArrayList<Transaction> transactions = getTransactionsFromXML();
        if(transactions == null){
            transactions = new ArrayList<Transaction>();
        }
        transactions.add(transaction);
        saveToXML(transactions);
    }

    public ArrayList<Transaction> getTransactions() throws IOException {
        return serializer.deserialize(filePath);
    }

    public ArrayList<Transaction> getTransactionsFromXML() throws IOException {
        return xmlSerialize.xmlDeserialize(xmlFilePath);
    }

    private static String getDatabasePath(){
        return new File("").getAbsolutePath()+"\\data\\trans.txt";
    }

    private static String getXMLDatabasePath(){
        return new File("").getAbsolutePath()+"\\data\\trans.xml";
    }
}
