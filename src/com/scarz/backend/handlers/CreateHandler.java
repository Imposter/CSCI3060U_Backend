package com.scarz.backend.handlers;

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
        BasicTransaction transaction = (BasicTransaction)t;

        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented");
    }
}
