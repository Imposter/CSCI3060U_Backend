package com.scarz.backend;

/**
 * Used to store items read by ItemFile
 */
public class Item {
    private String mName;
    private String mSellerUserName;
    private String mBidderUserName;
    private int mDaysToAuction;
    private double mCurrentBid;
    private boolean mNewItem;

    /**
     * Initializes item with information provided
     * @param itemName Item name
     * @param sellerName Seller name
     * @param bidderName Bidder name
     * @param daysLeft Days left to auction
     * @param currentBid Current bid
     */
    public Item(String itemName, String sellerName, String bidderName, int daysLeft, double currentBid, boolean newItem) {
        mName = itemName;
        mSellerUserName = sellerName;
        mBidderUserName = bidderName;
        mDaysToAuction = daysLeft;
        mCurrentBid = currentBid;
        mNewItem = newItem;
    }

    /**
     * Gets item's name
     * @return Item's name
     */
    public String getName() {
        return mName;
    }

    /**
     * Gets seller's username
     * @return Seller's username
     */
    public String getSellerUserName() {
        return mSellerUserName;
    }

    /**
     * Gets bidder's username
     * @return Bidder's username
     */
    public String getBidderUserName() {
        return mBidderUserName;
    }

    /**
     * Gets the days left to auction
     * @return Days left to auction's end
     */
    public int getDaysToAuction() {
        return mDaysToAuction;
    }

    /**
     * Sets the days left to auction
     * @param days Days left to auction
     */
    public void setDaysToAuction(int days) {
        mDaysToAuction = days;
    }

    /**
     * Gets current highest bidder's bid
     * @return Highest bid
     */
    public double getCurrentBid() {
        return mCurrentBid;
    }

    /**
     * Sets the current highest bidder
     * @param bidder Highest bidder
     */
    public void setBidderUserName(String bidder) {
        mBidderUserName = bidder;
    }

    /**
     * Sets the highest bidder's bid
     * @param bid Highest bid
     */
    public void setCurrentBid(double bid) {
        mCurrentBid = bid;
    }

    /**
     * Is the item new to the system
     * @return New item
     */
    public boolean isNewItem() {
        return mNewItem;
    }
}


