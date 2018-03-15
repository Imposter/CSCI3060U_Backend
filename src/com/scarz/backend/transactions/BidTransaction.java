package com.scarz.backend.transactions;

import com.scarz.backend.utility.ISerializer;

/**
 * Transaction for Bids containing relevant information about the bid
 */
public class BidTransaction extends Transaction {
    private String mItemName;
    private String mBuyerUserName;
    private String mSellerUserName;
    private double mNewBid;

    /**
     * Initializes transaction with Bid type
     * @param type Refund type
     */
    public BidTransaction (int type) { super (type);}
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
         * @throws UnsupportedOperationException Thrown when serialization is not supported
         */
        @Override
        public String serialize(BidTransaction bidTransaction) throws UnsupportedOperationException {
            // TODO: Implement
            throw new UnsupportedOperationException("Not implemented");
        }

        /**
         * Deserializes string into transaction
         * @param serializedData Data to deserialize into a transaction
         * @return Transaction from data
         * @throws UnsupportedOperationException Thrown when deserialization is not supported
         */
        @Override
        public BidTransaction deserialize(String serializedData) throws UnsupportedOperationException {
            // TODO: Implement
            throw new UnsupportedOperationException("Not implemented");
        }
    }
}
