package com.scarz.backend;

public class Config {
    // Maximum size of username string
    public static final int USERNAME_LENGTH = 15;

    // Maximum size of user type string
    public static final int USER_TYPE_LENGTH = 2;

    // Maximum size of item name string
    public static final int ITEM_NAME_LENGTH = 25;

    // Maximum amount of credits
    public static final double CREDITS_MAX  = 999999.99;

    // Maximum credits allowed to be added
    public static final double CREDITS_SESSION_MAX = 1000.00;

    // Maximum amount of characters in a credit string
    public static final int CREDITS_LENGTH = 9;

    // Maximum item price
    public static final double ITEM_PRICE_MAX = 999.99;

    // Maximum item price length
    public static final int ITEM_PRICE_LENGTH = 6;

    // Maximum days to auction
    public static final int ITEM_AUCTION_MAX = 100;

    // Maximum days to auction length
    public static final int ITEM_AUCTION_LENGTH = 3;
}
