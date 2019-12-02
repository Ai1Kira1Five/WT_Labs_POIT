package com.Bank.application.controller.serialize.xml;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The interface Ixml serialize.
 *
 * @param <T> the type parameter
 */
public interface IXMLSerialize<T> {
    /**
     * Xml deserialize array list.
     *
     * @param filePath the file path
     * @return the array list
     * @throws IOException the io exception
     */
    ArrayList<T> xmlDeserialize(String filePath) throws IOException;

    /**
     * Xml serialize.
     *
     * @param filePath the file path
     * @param list     the list
     */
    void xmlSerialize(String filePath, ArrayList<T> list) throws IOException;
}
