package com.scarz.backend.handlers;

import com.scarz.backend.transactions.Transaction;

/**
 * Interface used to implement transaction handlers for the main application
 */
public interface IHandler {
    /**
     * Identify the transaction type that handler is used for
     * @return Handler's transaction type
     */
    int getType();

    /**
     * Identify the transaction command name that the handler is used for
     * @return Handler's command name
     */
    String getName();

    /**
     * Handles the transaction and applies changes to user file and item file
     * @param t Transaction to apply
     * @return Whether the transaction was applied successfully or not
     */
    boolean handle(Transaction t);
}
