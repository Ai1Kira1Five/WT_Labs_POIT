package com.Bank.migrateXMLtoSQL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;

import static com.Bank.migrateXMLtoSQL.view.view.loopMenu;

public class MigrationMain {
    public static Logger logger;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("=======================\nStarting migration util\n=======================");

        PropertyConfigurator.configure("log4j.properties");
        logger = Logger.getLogger(MigrationMain.class);

        loopMenu();
    }
}
