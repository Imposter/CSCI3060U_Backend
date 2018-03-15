package com.scarz.backend.transactions;

import com.scarz.backend.utility.ISerializer;

/**
 * Transaction for advertisements containing relevant information about the advertisement
 */
public class AdvertiseTransaction extends Transaction {
    private String mItemName;
    private String mSellerUserName;
    private int mDaysToAuction;
    private double mMinBid;

    /**
     * Initializes transaction with advertise type
     * @param type Refund type
     */
    public AdvertiseTransaction (int type) {
        super (type);
    }

    /**
     * Gets item name
     * @return Item's name
     */
    public String getItemName() {
        return mItemName;
    }

    /**
     * Gets seller's username
     * @return Seller's username
     */
    public String getSellerUserName() {
        return mSellerUserName;
    }

    /**
     * Gets days left to auction
     * @return Days left to auction's end
     */
    public int getDaysToAuction() {
        return mDaysToAuction;
    }

    /**
     * Gets the item's minimum bid
     * @return Item's minimum bid
     */
    public double getMinBid() {
        return mMinBid;
    }

    /**
     * Serializer for serializing and deserializing AdvertiseTransaction
     */
    public class Serializer implements ISerializer<AdvertiseTransaction> {
        /**
         * Serializes transaction into a string
         * @param advertiseTransaction Transaction to serialize
         * @return String representation of transaction
         * @throws UnsupportedOperationException Thrown when serialization is not supported
         */
        @Override
        public String serialize(AdvertiseTransaction advertiseTransaction) throws UnsupportedOperationException {
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
        public AdvertiseTransaction deserialize(String serializedData) throws UnsupportedOperationException {
            // TODO: Implement
            throw new UnsupportedOperationException("Not implemented");
        }
    }
}
