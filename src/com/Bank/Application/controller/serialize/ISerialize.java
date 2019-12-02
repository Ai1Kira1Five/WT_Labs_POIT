package com.Bank.application.controller.serialize;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface ISerialize<T> {
    /**
     * Deserialize array list.
     *
     * @param filePath the file path
     * @return the array list
     */
    ArrayList<T> deserialize(String filePath) throws IOException;

    /**
     * Serialize.
     *
     * @param filePath the file path
     * @param list     the list
     */
    void serialize(String filePath, ArrayList<T> list);
}
