package com.Bank.migrateXMLtoSQL;

public class MigrationData {
    String type;
    String table;
    String[] fields;
    String types;
    public MigrationData(String type, String table, String[] fields, String types) {
        this.type = type;
        this.table = table;
        this.fields = fields;
        this.types = types;
    }
}
