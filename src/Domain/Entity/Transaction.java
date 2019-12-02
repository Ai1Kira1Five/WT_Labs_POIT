package Domain.Entity;

import java.util.UUID;

/**
 * The type Transaction.
 */
public class Transaction {
    private String id;
    private String sourceBank;
    private String destinationBank;
    private String date;
    private TransactionType transType;
    private Operation operation;

    /**
     * Instantiates a new Transaction.
     *
     * @param operation the operation
     */
    public Transaction(Operation operation){
        this.id = getUniqueId();
        this.operation = operation;
    }

    /**
     * Instantiates a new Transaction.
     *
     * @param id              the id
     * @param sourceBank      the source bank
     * @param destinationBank the destination bank
     * @param date            the date
     * @param transType       the trans type
     * @param operation       the operation
     */
    public Transaction(String id,
                       String sourceBank,
                       String destinationBank,
                       String date,
                       TransactionType transType,
                       Operation operation){
        this.id = id;
        this.sourceBank = sourceBank;
        this.destinationBank = destinationBank;
        this.date = date;
        this.transType = transType;
        this.operation = operation;
    }

    private Transaction(String sourceBank,
                        String destinationBank,
                        String date,
                        TransactionType transType,
                        Operation operation){
        this.id = getUniqueId();
        this.sourceBank = sourceBank;
        this.destinationBank = destinationBank;
        this.date = date;
        this.transType = transType;
        this.operation = operation;
    }

    private static String getUniqueId(){
        return UUID.randomUUID().toString();
    }

    /**
     * Set source bank.
     *
     * @param sourceBank the source bank
     */
    public void setSourceBank(String sourceBank){
        this.sourceBank = sourceBank;
    }

    /**
     * Set destination bank.
     *
     * @param destinationBank the destination bank
     */
    public void setDestinationBank(String destinationBank){
        this.destinationBank = destinationBank;
    }

    /**
     * Set trans type.
     *
     * @param transType the trans type
     */
    public void setTransType(TransactionType transType){
        this.transType = transType;
    }

    /**
     * Set date.
     *
     * @param date the date
     */
    public void setDate(String date){
        this.date = date;
    }

    /**
     * Set card.
     *
     * @param card the card
     */
    public void setCard(String card){
        //System.out.println(this.operation);
        this.operation.setCard(card);
    }

    /**
     * Set card cash amount.
     *
     * @param cashAmount the cash amount
     */
    public void setCardCashAmount(int cashAmount){
        this.operation.setAmountOfCash(cashAmount);
    }

    /**
     * Get source bank string.
     *
     * @return the string
     */
    public String getSourceBank(){return sourceBank;}

    /**
     * Get destination bank string.
     *
     * @return the string
     */
    public String getDestinationBank(){return destinationBank;}

    /**
     * Get date string.
     *
     * @return the string
     */
    public String getDate(){return date;}

    /**
     * Get transaction type transaction type.
     *
     * @return the transaction type
     */
    public TransactionType getTransactionType(){return transType;}

    /**
     * Get operation operation.
     *
     * @return the operation
     */
    public Operation getOperation(){return operation;}

    /**
     * Get id string.
     *
     * @return the string
     */
    public String getId(){return id;}
}
