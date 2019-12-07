package com.Bank.migrateXMLtoSQL.data;

import java.io.File;
import java.sql.Connection;
import java.sql.Statement;

public class migrationData {
    public static String dbName =           "wt_lab_poit";
    public static String dbTableName =      "transactions";
    public static String URL =              "jdbc:mysql://localhost:3308/" +
                                            dbName +
                                            "?verifyServerCertificate=false" +
                                            "&useSSL=false" +
                                            "&requireSSL=false" +
                                            "&useLegacyDatetimeCode=false" +
                                            "&amp" +
                                            "&serverTimezone=UTC";

    public enum menuTree {
        VALIDATE,
        CONNECT,
        MIGRATE,
        CLOSE
    }

    public static Connection connection;
    public static Statement statement;

    public static String xsdFile = getXSDPath();
    public static String xmlFile = getXMLPath();

    private static String getXSDPath(){
        return new File("").getAbsolutePath()+"\\data\\trans.xsd";
    }
    private static String getXMLPath(){
        return new File("").getAbsolutePath()+"\\data\\trans.xml";
    }
}
