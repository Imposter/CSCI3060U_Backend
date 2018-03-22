package com.scarz.backend.transactions;

import com.scarz.backend.Config;
import com.scarz.backend.utility.ISerializer;
import com.scarz.backend.utility.io.MemoryStream;
import com.scarz.backend.utility.io.StringStream;

/**
 * Transaction for advertisements containing relevant information about the advertisement
 */
public class AdvertiseTransaction extends Transaction {
    private String mItemName;
    private String mSellerUserName;
    private int mDaysToAuction;
    private double mMinBid;

    /**
     * Initializes transaction with advertisement information
     */
    public AdvertiseTransaction(String itemName, String sellerUserName, int daysToAuction, double minBid) {
        super(TransactionType.ADVERTISE);

        mItemName = itemName;
        mSellerUserName = sellerUserName;
        mDaysToAuction = daysToAuction;
        mMinBid = minBid;
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
    public static class Serializer implements ISerializer<AdvertiseTransaction> {
        /**
         * Serializes transaction into a string
         * @param advertiseTransaction Transaction to serialize
         * @return String representation of transaction
         * @throws Exception Thrown when serialization is not supported, or has failed
         */
        @Override
        public String serialize(AdvertiseTransaction advertiseTransaction) throws Exception {
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
        public AdvertiseTransaction deserialize(String serializedData) throws Exception {
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

            // Read days to auction
            int daysToAuction = Integer.parseInt(stream.readString(Config.ITEM_AUCTION_LENGTH));
            stream.read();

            // Read minimum bid
            double minBid = Double.parseDouble(stream.readString(Config.ITEM_PRICE_LENGTH));

            return new AdvertiseTransaction(itemName, sellerUserName, daysToAuction, minBid);
        }
    }
}
