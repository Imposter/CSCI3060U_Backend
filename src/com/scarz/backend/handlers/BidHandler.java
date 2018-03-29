package com.scarz.backend.handlers;

import com.scarz.backend.Item;
import com.scarz.backend.ItemFile;
import com.scarz.backend.User;
import com.scarz.backend.UserFile;
import com.scarz.backend.transactions.BidTransaction;
import com.scarz.backend.transactions.Transaction;
import com.scarz.backend.transactions.TransactionType;

/**
 * Handles operations for transaction type bid
 */
public class BidHandler implements IHandler {
    private UserFile mUserFile;
    private ItemFile mItemFile;

    /**
     * Initializes handler with required files
     * @param userFile User accounts file
     * @param itemFile Available items file
     */
    public BidHandler(UserFile userFile, ItemFile itemFile) {
        mUserFile = userFile;
        mItemFile = itemFile;
    }

    /**
     * Gets transaction type
     * @return Transaction type
     */
    @Override
    public int getType() {
        return TransactionType.BID;
    }

    /**
     * Gets transaction type in its string representation
     * @return Transaction type in string form
     */
    @Override
    public String getName() {
        return "bid";
    }

    /**
     * Handles the transaction and applies changes to user file and item file
     * @param t Transaction to apply
     * @return Whether the transaction was applied successfully or not
     */
    @Override
    public boolean handle(Transaction t) {
        BidTransaction transaction = (BidTransaction)t;

        // Check if buyer exists
        User buyer = mUserFile.getUserByName(transaction.getBuyerUserName());
        if (buyer == null) {
            System.out.printf("ERROR: [%s] Buyer %s does not exist!\r\n", getName(), transaction.getBuyerUserName());
            return false;
        }

        // Check if seller exists
        User seller = mUserFile.getUserByName(transaction.getSellerUserName());
        if (seller == null) {
            System.out.printf("ERROR: [%s] Seller %s does not exist!\r\n", getName(), transaction.getSellerUserName());
            return false;
        }

        Item item = mItemFile.getItemByUserAndName(transaction.getSellerUserName(), transaction.getItemName());

        // Check if item exists
        if (item == null) {
            System.out.printf("ERROR: [%s] Item %s by user %s does not exist!\r\n", getName(), transaction.getItemName(),
                    transaction.getSellerUserName());
            return false;
        }

        // Check if there was a previous bidder, if there was, refund his money
        if (!item.getBidderUserName().isEmpty()) {
            User previousBidder = mUserFile.getUserByName(item.getBidderUserName());
            previousBidder.setCredits(previousBidder.getCredits() + item.getCurrentBid());
        }

        // Update listing
        item.setBidderUserName(buyer.getName());
        item.setCurrentBid(transaction.getNewBid());

        // Take away credits from user
        buyer.setCredits(buyer.getCredits() - transaction.getNewBid());

        System.out.printf("[%s] Bid on item %s by %s for %.2f!\r\n", getName(), transaction.getItemName(),
                transaction.getBuyerUserName(), transaction.getNewBid());

        return true;
    }
}
