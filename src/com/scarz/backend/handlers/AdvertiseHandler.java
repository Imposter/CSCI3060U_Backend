package com.scarz.backend.handlers;

import com.scarz.backend.ItemFile;
import com.scarz.backend.transactions.AdvertiseTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;

/**
 * Handles operations for transaction type advertise
 */
public class AdvertiseHandler implements IHandler {
    private ItemFile mItemFile;

    /**
     * Initializes handler with required files
     * @param itemFile Available items file
     */
    public AdvertiseHandler(ItemFile itemFile) {
        mItemFile = itemFile;
    }

    /**
     * Gets transaction type
     * @return Transaction type
     */
    @Override
    public int getType() {
        return TransactionType.ADVERTISE;
    }

    /**
     * Gets transaction type in its string representation
     * @return Transaction type in string form
     */
    @Override
    public String getName() {
        return "advertise";
    }

    /**
     * Handles the transaction and applies changes to user file and item file
     * @param t Transaction to apply
     * @return Whether the transaction was applied successfully or not
     */
    @Override
    public boolean handle(Transaction t) {
        AdvertiseTransaction transaction = (AdvertiseTransaction)t;

        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented");
    }
}
