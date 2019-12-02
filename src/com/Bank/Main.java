package com.Bank;

import java.io.IOException;
import static com.Bank.application.presentation.View.mainMenuLoop;

/**
 * The type Main.
 */
public class Main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {
        Main myProg = new Main();
        myProg.start();
    }

    public void start() throws IOException{
        mainMenuLoop();
    }
}
