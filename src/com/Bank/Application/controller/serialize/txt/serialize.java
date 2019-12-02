package com.Bank.application.controller.serialize.txt;

import java.io.*;
import java.util.ArrayList;

public class serialize<T> implements ISerialize<T> {
    /**
     * Deserialize array list.
     *
     * @param filePath the file path
     * @return the array list
     */
    @SuppressWarnings("unchecked")
    public ArrayList<T> deserialize(String filePath) throws IOException {
        ArrayList<T> list = null;

        FileInputStream file = new FileInputStream(filePath);
        try
        {
            if(file.available() == 0){
                System.out.println("File is empty...");
                list = new ArrayList<T>();
            }else{
                try{
                    ObjectInputStream in =  new ObjectInputStream(file);
                    list = (ArrayList<T>) in.readObject();
                    in.close();
                } catch(IOException ex) {
                    System.out.println(ex.getMessage());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        } finally {
            file.close();
        }
        return list;
    }

    /**
     * Serialize.
     *
     * @param filePath the file path
     * @param list     the list
     */
    public void serialize(String filePath, ArrayList<T> list){
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(list);

            out.close();
            file.close();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
