package com.Bank.application.controller.serialize.xml;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class XMLSerialize<T> implements IXMLSerialize{
    @Override
    public ArrayList<T> xmlDeserialize(String filePath) throws IOException {
        ArrayList<T> list = null;
        FileInputStream file = new FileInputStream(filePath);
        try{
            if(file.available() == 0){
                System.out.println("File is empty...");
                list = new ArrayList<T>();
            } else {
                XMLDecoder decoder = new XMLDecoder(file);
                list = (ArrayList<T>) decoder.readObject();
            }
        } catch (Exception ex) {
            System.out.println("XML Deserializer exception: " + ex.toString());
        } finally {
            file.close();
        }
        return list;
    }

    @Override
    public void xmlSerialize(String filePath, ArrayList list) throws IOException{
        FileOutputStream fos = new FileOutputStream(filePath);
        try{

            XMLEncoder encoder = new XMLEncoder(fos);

            encoder.setExceptionListener(e -> System.out.println("Encoder exception: " + e.toString()));

            encoder.writeObject(list);
            encoder.close();
        } catch (Exception ex){
            System.out.println("Serialize FileWriter exception: " + ex.toString());
        } finally {
            fos.close();
        }
    }
}
