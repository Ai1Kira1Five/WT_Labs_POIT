package com.Bank.xsdValidator;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;


public class XSDValidation {
    private static String xsdPath = getXSDPath();
    private static String xmlPath = getXMLPath();

    public static void main(String[] args) {
        System.out.println("XML file validates against XSD schema? " + validateXMLSchema(xsdPath, xmlPath));
    }

    public static String validateXMLSchema(String xsdPath, String xmlPath){
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return "NOPE, you're lozer";
        }
        return "YEAP, it's fine";
    }

    private static String getXSDPath(){
        return new File("").getAbsolutePath()+"\\data\\trans.xsd";
    }
    private static String getXMLPath(){
        return new File("").getAbsolutePath()+"\\data\\trans.xml";
    }
}
