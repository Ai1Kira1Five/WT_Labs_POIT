package com.Bank.migrateXMLtoSQL;

import com.Bank.application.dao.transactionDao.TransactionDAO;
import com.Bank.application.entity.Transaction;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MigrationMain {
    private static String dbTableName =     "transactions";
    private static String URL =             "jdbc:mysql://localhost:3308/wt_lab_poit" +
                                            "?verifyServerCertificate=false" +
                                            "&useSSL=false" +
                                            "&requireSSL=false" +
                                            "&useLegacyDatetimeCode=false" +
                                            "&amp" +
                                            "&serverTimezone=UTC";

    private static Logger                   logger;
    private static Connection               connection;
    private static Statement                statement;

    private static String xsdFile =         getXSDPath();
    private static String xmlFile =         getXMLPath();

    private static TransactionDAO dao = new TransactionDAO();

    private enum menuTree {
        VALIDATE,
        CONNECT,
        MIGRATE,
        CLOSE
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("=======================\nStarting migration util\n=======================");

        PropertyConfigurator.configure("log4j.properties");
        logger = Logger.getLogger(MigrationMain.class);

        loopMenu();
    }

    private static void loopMenu() throws IOException, ClassNotFoundException {
        boolean flag = true;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        menuTree arg = null;

        while (flag) {
            boolean inFlag = true;
            while (inFlag) {
                System.out.println("What do you want to do?\n" +
                        "1 - Validate XML;\n" +
                        "2 - Connect to MySQL DB\n" +
                        "3 - Migrate data from xml to DB\n" +
                        "4 - Close connection and close util");

                String answer = in.readLine();

                switch (answer) {
                    case "1":
                        arg = menuTree.VALIDATE;
                        inFlag = false;
                        break;
                    case "2":
                        arg = menuTree.CONNECT;
                        inFlag = false;
                        break;
                    case "3":
                        arg = menuTree.MIGRATE;
                        inFlag = false;
                        break;
                    case "4":
                        arg = menuTree.CLOSE;
                        inFlag = false;
                        break;
                    default:
                        System.out.println("Incorrect answer, retype please!");
                        break;
                }
            }
            switch (arg) {
                case VALIDATE:
                    if (validateXMLSchema(xsdFile, xmlFile))
                        System.out.println("Validation okay");
                    else
                        System.out.println("XML file incorrect");
                    break;
                case CONNECT:
                    System.out.println("Input user: ");
                    String user = in.readLine();
                    System.out.println("Input password: ");
                    String pass = in.readLine();

                    if (createConnection(user, pass)) {
                        System.out.println("Connection ok");
                    } else {
                        System.out.println("Troubles with connection");
                    }
                    break;
                case MIGRATE:
                    migrateXMLtoDB();
                    break;
                case CLOSE:
                    closeConnection();
                    flag = false;
                    break;
            }
        }
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static void migrateXMLtoDB() throws IOException {
        String db               = "CREATE DATABASE IF NOT EXISTS " + "wt_lab_poit";
        String dbTable          = "CREATE TABLE IF NOT EXISTS " + dbTableName;
        String dbTableInsert    = "INSERT INTO " + dbTableName;
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

    private static String getXSDPath(){
        return new File("").getAbsolutePath()+"\\data\\trans.xsd";
    }
    private static String getXMLPath(){
        return new File("").getAbsolutePath()+"\\data\\trans.xml";
    }
}
