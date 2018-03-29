package com.scarz.backend.transactions;

import com.scarz.backend.Config;
import com.scarz.backend.utility.ISerializer;
import com.scarz.backend.utility.io.MemoryStream;
import com.scarz.backend.utility.io.StringStream;

/**
 * Transaction for bids containing relevant information about the bid
 */
public class BidTransaction extends Transaction {
    private String mItemName;
    private String mBuyerUserName;
    private String mSellerUserName;
    private double mNewBid;

    /**
     * Initializes transaction with bid information
     */
    public BidTransaction(String itemName, String sellerUserName, String buyerUserName,double newBid) {
        super(TransactionType.BID);

        mItemName = itemName;
        mBuyerUserName = buyerUserName;
        mSellerUserName = sellerUserName;
        mNewBid = newBid;
    }
    /**
     * Gets item's name
     * @return Item's name
     */
    public String getItemName() {return mItemName;}

    /**
     * Gets buyer's username
     * @return Buyer's username
     */
    public String getBuyerUserName() {return mBuyerUserName;}

    /**
     * Gets the seller's username
     * @return Seller's username
     */
    public String getSellerUserName() {return mSellerUserName;}

    /**
     * Gets the new bid on the item
     * @return New bid
     */
    public double getNewBid() {return mNewBid;}

    /**
     * Serializer for serializing and deserializing BidTransaction
     */
    public static class Serializer implements ISerializer<BidTransaction> {
        /**
         * Serializes transaction into a string
         * @param bidTransaction Transaction to serialize
         * @return String representation of transaction
         * @throws Exception Thrown when serialization is not supported, or has failed
         */
        @Override
        public String serialize(BidTransaction bidTransaction) throws UnsupportedOperationException {
            // Not needed
            throw new UnsupportedOperationException("Not implemented");
        }

        /**
         * Deserializes string into transaction
         * @param serializedData Data to deserialize into a transaction
         * @return Transaction from data
         * @throws Exception Thrown when deserialization is not supported, or has failed
         */
        @Override
        public BidTransaction deserialize(String serializedData) throws Exception {
            // Create stream from line
            StringStream stream = new StringStream(new MemoryStream(serializedData.getBytes()));

            // Ignore type
            stream.readString(2);
            stream.read();

            // Read item name
            String itemName = stream.readString(Config.ITEM_NAME_LENGTH).trim();
            stream.read();

            // Read seller name
            String sellerUserName = stream.readString(Config.USERNAME_LENGTH).trim();
            stream.read();

            // Read buyer name
            String buyerUserName = stream.readString(Config.USERNAME_LENGTH).trim();
            stream.read();

            // Read minimum bid
            double newBid = Double.parseDouble(stream.readString(Config.ITEM_PRICE_LENGTH));

            return new BidTransaction(itemName, sellerUserName, buyerUserName, newBid);
        }
    }
}
