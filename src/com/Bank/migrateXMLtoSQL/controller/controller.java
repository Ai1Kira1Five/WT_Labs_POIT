package com.Bank.migrateXMLtoSQL.controller;

import com.Bank.application.dao.transactionDao.TransactionDAO;
import com.Bank.application.entity.Transaction;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.Bank.migrateXMLtoSQL.MigrationMain.logger;
import static com.Bank.migrateXMLtoSQL.data.migrationData.*;

public class controller {
    private static TransactionDAO dao = new TransactionDAO();

    public static void migrateXMLtoDB() throws IOException {
        String db               = "CREATE DATABASE IF NOT EXISTS "  + dbName;
        String dbTable          = "CREATE TABLE IF NOT EXISTS "     + dbTableName;
        String dbTableInsert    = "INSERT INTO "                    + dbTableName;

        ArrayList<Transaction> transactions = dao.getTransactionsFromXML();

        try{
            statement = connection.createStatement();
            statement.executeUpdate(db);
        } catch (SQLException e) {
            logger.error(e.getErrorCode() + ": " + e.getMessage());
            try{
                statement.clearBatch();
            } catch (SQLException ex){
                logger.error(ex.getErrorCode() + ": " + ex.getMessage());
            }
        }

        try{
            statement = connection.createStatement();
            statement.executeUpdate(dbTable + " (" +
                                    "idNo INT(64) primary key AUTO_INCREMENT," +
                                    "destinationBank VARCHAR(10)," +
                                    "sourceBank VARCHAR(10)," +
                                    "cardType VARCHAR(10)," +
                                    "transType VARCHAR(255)," +
                                    "dateTrans VARCHAR(10)," +
                                    "cashTrans INT(64) NOT NULL)");
        } catch (SQLException e){
            logger.error(e.getErrorCode() + ": " + e.getMessage());
            try{
                statement.clearBatch();
            } catch (SQLException ex){
                logger.error(ex.getErrorCode() + ": " + ex.getMessage());
            }
        }

        for(Transaction transaction : transactions){
            try{
                statement = connection.createStatement();
                statement.executeUpdate(dbTableInsert +
                                        "(destinationBank, sourceBank, cardType, transType, dateTrans, cashTrans)" +
                                        " VALUES " +
                                        "('" + transaction.getDestinationBank() +
                                        "','" + transaction.getSourceBank() +
                                        "','" + transaction.getOperation().getCard() +
                                        "','" + transaction.getTransactionType().toString() +
                                        "','" + transaction.getDate() +
                                        "'," + transaction.getOperation().getAmountOfCash() + ")");

            } catch (SQLException e){
                logger.error(e.getErrorCode() + ": " + e.getMessage());
                try{
                    statement.clearBatch();
                } catch (SQLException ex){
                    logger.error(ex.getErrorCode() + ": " + ex.getMessage());
                }
            }
        }
    }

    public static boolean createConnection(String user, String password) throws ClassNotFoundException {
        boolean result;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(URL, user, password);
            statement = connection.createStatement();
            result = true;
        } catch (SQLException ex) {
            logger.error(ex.getErrorCode() + ": " + ex.getMessage());
            result = false;
        }
        return result;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            logger.error(ex.getErrorCode() + ": " + ex.getMessage());
        }
    }
}
