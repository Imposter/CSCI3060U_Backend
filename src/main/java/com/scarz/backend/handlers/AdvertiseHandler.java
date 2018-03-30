package com.scarz.backend.handlers;

import com.scarz.backend.Item;
import com.scarz.backend.ItemFile;
import com.scarz.backend.User;
import com.scarz.backend.UserFile;
import com.scarz.backend.transactions.AdvertiseTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;

/**
 * Handles operations for transaction type advertise
 */
public class AdvertiseHandler implements IHandler {
    private UserFile mUserFile;
    private ItemFile mItemFile;

    /**
     * Initializes handler with required files
     * @param itemFile Available items file
     */
    public AdvertiseHandler(UserFile userFile, ItemFile itemFile) {
        mUserFile = userFile;
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

        // Get user
        User user = mUserFile.getUserByName(transaction.getSellerUserName());

        // Check if user exists
        if (user == null) {
            System.out.printf("ERROR: [%s] User %s does not exist!\r\n", getName(), transaction.getSellerUserName());
            return false;
        }

        Item item = mItemFile.getItemByUserAndName(transaction.getSellerUserName(), transaction.getItemName());

        // Check if item exists
        if (item != null) {
            System.out.printf("ERROR: [%s] Item %s by user %s already exists!\r\n", getName(), transaction.getItemName(),
                    transaction.getSellerUserName());
            return false;
        }

        // Store in item list
        item = new Item(transaction.getItemName(), transaction.getSellerUserName(), "",
                transaction.getDaysToAuction(), transaction.getMinBid(), true);
        mItemFile.addItem(item);

        System.out.printf("[%s] Posted listing %s by %s for %.2f, available for %d days!\r\n", getName(),
                transaction.getItemName(), user.getName(), transaction.getMinBid(), transaction.getDaysToAuction());

        return true;
    }
}
