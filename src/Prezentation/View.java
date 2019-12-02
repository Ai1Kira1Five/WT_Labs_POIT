package Prezentation;

import Domain.Entity.Transaction;
import Domain.Entity.TransactionType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * The type View.
 */
public class View {

    /**
     * Show main menu.
     */
    public static void showMainMenu() {
        System.out.println("==============================================");
        System.out.println("Main menu:");
        System.out.println("1. Show transactions.");
        System.out.println("2. Add new transaction");
        System.out.println("3. Delete transaction");
        System.out.println("4. Update transaction");
        System.out.println("5. Sort transactions");
        System.out.println("0. Exit.");
    }

    /**
     * Show transactions.
     *
     * @param transactions the transactions
     */
    public static void showTransactions(List<Transaction> transactions) {
        int index = 1;
        for(Transaction transaction: transactions) {
            System.out.println(index + " From: " + transaction.getSourceBank() +
                    " To: " + transaction.getDestinationBank() +
                    " Date: " + transaction.getDate() +
                    " Transaction type: " + transaction.getTransactionType() +
                    " Card: " + transaction.getOperation().getCard() +
                    " Cash amount: " + transaction.getOperation().getAmountOfCash());
            index += 1;
        }
    }

    /**
     * Show sort menu.
     */
    public static void showSortMenu(){
        System.out.println("Sorting by:");

        System.out.println("1. ID");
        System.out.println("2. Amount of cash");
        System.out.println("0. Exit.");
    }

    /**
     * Gets new transaction.
     *
     * @param transaction the transaction
     * @throws IOException the io exception
     */
    public static void getNewTransaction(Transaction transaction) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter source bank");
        transaction.setSourceBank(in.readLine());

        System.out.println("Enter destination bank");
        transaction.setDestinationBank(in.readLine());

        System.out.println("Enter date");
        transaction.setDate(in.readLine());

        transaction.setTransType(TransactionType.TRANSACTION);

        System.out.println("Enter type of card");
        transaction.setCard(in.readLine());

        System.out.println("Enter amount of cash");
        transaction.setCardCashAmount(Integer.valueOf(in.readLine()));
    }

    /**
     * Gets delete index.
     *
     * @return the delete index
     * @throws IOException the io exception
     */
    public static int getDeleteIndex() throws IOException {
        System.out.println("What transaction you prefer to delete?");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        return Integer.valueOf(in.readLine());
    }

    /**
     * Gets update index.
     *
     * @return the update index
     * @throws IOException the io exception
     */
    public static int getUpdateIndex() throws IOException {
        System.out.println("What transaction you prefer to update?");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        return Integer.valueOf(in.readLine());
    }

    /**
     * Gets updated transaction.
     *
     * @param transaction the transaction
     * @throws IOException the io exception
     */
    public static void getUpdatedTransaction(Transaction transaction) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("What you want to update?");

        System.out.println("1. Source bank");
        System.out.println("2. Destination bank");
        System.out.println("3. Date");
        System.out.println("4. Status(1:TRANSACTION, 2:PAYMENT, 3:WITHDRAWAL)");
        System.out.println("5. Type of card");
        System.out.println("6. Amount of cash");
        System.out.println("0. Exit.");

        String answer = in.readLine();

        switch (answer) {
            case "1":
                System.out.println("Input new source bank");
                transaction.setSourceBank(in.readLine());
                break;
            case "2":
                System.out.println("Input new destination bank");
                transaction.setDestinationBank(in.readLine());
                break;
            case "3":
                System.out.println("Input new date: ");
                transaction.setDate(in.readLine());
                break;
            case "4":
                System.out.println("Input new type of operation: ");
                String newStatus = in.readLine();
                switch (newStatus) {
                    case "1":
                        transaction.setTransType(TransactionType.TRANSACTION);
                        break;
                    case "2":
                        transaction.setTransType(TransactionType.PAYMENT);
                        break;
                    case "3":
                        transaction.setTransType(TransactionType.WITHDRAWAL);
                        break;
                }
                break;
            case "5":
                System.out.println("Input new card type: ");
                transaction.setCard(in.readLine());
                break;
            case "6":
                System.out.println("Input new amount of cash: ");
                transaction.setCardCashAmount(Integer.valueOf(in.readLine()));
                break;
            case "0":
                break;
        }
    }

}
