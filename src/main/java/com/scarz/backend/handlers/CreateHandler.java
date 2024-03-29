package com.scarz.backend.handlers;

import com.scarz.backend.User;
import com.scarz.backend.UserFile;
import com.scarz.backend.transactions.BasicTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;

/**
 * Handles operations for transaction type create
 */
public class CreateHandler implements IHandler {
    private UserFile mUserFile;

    /**
     * Initializes handler with required files
     * @param userFile User accounts file
     */
    public CreateHandler(UserFile userFile) {
        mUserFile = userFile;
    }

    /**
     * Gets transaction type
     * @return Transaction type
     */
    @Override
    public int getType() {
        return TransactionType.CREATE;
    }

    /**
     * Gets transaction type in its string representation
     * @return Transaction type in string form
     */
    @Override
    public String getName() {
        return "create";
    }

    /**
     * Handles the transaction and applies changes to user file and item file
     * @param t Transaction to apply
     * @return Whether the transaction was applied successfully or not
     */
    @Override
    public boolean handle(Transaction t) {
        BasicTransaction transaction = (BasicTransaction) t;

        // Check if the user already exists
        User user = mUserFile.getUserByName(transaction.getUserName());
        if (user != null) {
            System.out.printf("ERROR: [%s] User %s already exists!\r\n", getName(), transaction.getUserName());
            return false;
        }

        // Create user
        user = new User(transaction.getUserName(), transaction.getUserType(), transaction.getCredits());
        mUserFile.addUser(user);

        System.out.printf("[%s] Created user %s\r\n", getName(), transaction.getUserName());

        return true;
    }
}
