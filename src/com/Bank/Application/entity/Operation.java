package com.Bank.application.entity;

import java.io.Serializable;

/**
 * The type Operation.
 */

public class Operation implements Serializable {
    private String card;
    private int amountOfCash;

    /**
     * Instantiates a new Operation.
     */
    public Operation(){}

    /**
     * Instantiates a new Operation.
     *
     * @param card         the card
     * @param amountOfCash the amount of cash
     */
    public Operation(String card, int amountOfCash){
        this.card = card;
        this.amountOfCash = amountOfCash;
    }

    /**
     * Get card string.
     *
     * @return the string
     */
    public String getCard(){return card;}

    /**
     * Get amount of cash int.
     *
     * @return the int
     */
    public int getAmountOfCash(){return amountOfCash;}

    /**
     * Set card.
     *
     * @param card the card
     */
    public void setCard(String card){
        this.card = card;
    }

    /**
     * Sets amount of cash.
     *
     * @param amountOfCash the amount of cash
     */
    public void setAmountOfCash(int amountOfCash) {
        this.amountOfCash = amountOfCash;
    }
}
