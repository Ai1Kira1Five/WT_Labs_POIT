package com.Bank.migrateXMLtoSQL;

import com.Bank.application.dao.transactionDao.TransactionDAO;
import com.Bank.application.entity.Transaction;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class MigrationMain {
    private static String URL = "jdbc:mysql://localhost:3306/lab2";
    private static String USER = "root";
    private static String PASSWORD = "root";

    private static Logger logger;
    private static Connection connection;
    private static Statement statement;

    private static String xsdFile = getXSDPath();
    private static String xmlFile = getXMLPath();

    private static TransactionDAO dao = new TransactionDAO();

    public static MigrationData data = new MigrationData("Transaction", "transactions", new String[]{"sourceBank", "destinationBank", "date", "transactionType", "cash"}, "id int, sourceBank varchar(20), destinationBank varchar(20), date varchar(20), transactionType varchar(20), cash int");

    public static void main(String[] args) throws IOException {
        ArrayList<Transaction> transactions = dao.getTransactionsFromXML();
        PropertyConfigurator.configure("log4j.properties");
        logger = Logger.getLogger(MigrationMain.class);

        try{
            File sqlFile = new File("trans.sql");
            if(!sqlFile.exists()){
                sqlFile.createNewFile();
            }

            if(validateXMLSchema(xsdFile, xmlFile)){
                System.out.println("XSD validation good");
                FileWriter w = new FileWriter(sqlFile);
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document d = builder.parse(sqlFile);
                d.getDocumentElement().normalize();
                w.write("CREATE TABLE " + data.table + "\t(" + data.types + ")\n");

                w.write('\n');
                w.write(XMLToSQL(d.getElementsByTagName(data.type), data.table, data.fields));

                w.flush();
                w.close();
            }
        } catch (SAXException ex) {
            System.err.println(ex.toString());
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }

    }

    static String XMLToSQL(NodeList nodes, String table, String[] fields) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < nodes.getLength(); i++) {
            result.append("INSERT INTO\t").append(table).append("\t(id");
            for (int j = 0; j < fields.length; j++) {
                result.append(',').append(fields[j]);
            }
            result.append(")\tVALUES\t(");
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element elem = (Element) node;
                result.append(elem.getAttribute("id"));
                for (String field : fields) {
                    result.append(',');
                    result.append(elem.getElementsByTagName(field).item(0).getTextContent());
                }
            }
            result.append(");\n");
        }
        return result.toString();
    }

    public static boolean validateXMLSchema(String xsdPath, String xmlPath){
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }

    private static String getXSDPath(){
        return new File("").getAbsolutePath()+"\\data\\trans.xsd";
    }
    private static String getXMLPath(){
        return new File("").getAbsolutePath()+"\\data\\trans.xml";
    }
}
