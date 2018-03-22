package com.scarz.backend;

/**
 * User type, used to determine type of user
 */
public class UserType {
    public static final int NONE = -1;
    public static final int BUY = 0;
    public static final int SELL = 1;
    public static final int FULL = 2;
    public static final int ADMIN = 3;

    /**
     * Converts a specified user type to its string representation
     * @param type User type
     * @return User type in string representation
     */
    public static String getUserTypeString(int type) {
        if (type == BUY) {
            return "BS";
        } else if (type == SELL) {
            return "SS";
        } else if (type == FULL) {
            return "FS";
        } else if (type == ADMIN) {
            return "AA";
        }

        return "";
    }

    /**
     * Converts a specified user type to its long string representation
     * @param type User type
     * @return User type in long string representation
     */
    public static String getUserTypeLongString(int type) {
        if (type == BUY) {
            return "buy-standard";
        } else if (type == SELL) {
            return "sell-standard";
        } else if (type == FULL) {
            return "full-standard";
        } else if (type == ADMIN) {
            return "admin";
        }

        return "";
    }

    /**
     * Converts a specified user type string to its integer representation
     * @param type User type in short form
     * @return User type in integer representation
     */
    public static int getUserTypeFromString(String type) {
        if (type.equals("BS")) {
            return BUY;
        } else if (type.equals("SS")) {
            return SELL;
        } else if (type.equals("FS")) {
            return FULL;
        } else if (type.equals("AA")) {
            return ADMIN;
        }

        return NONE;
    }

    /**
     * Converts a user type from string form to its integer representation
     * @param type User type in long form
     * @return User type in integer representation
     */
    public static int getUserTypeFromLongString(String type) {
        if (type.equals("buy-standard")) {
            return BUY;
        } else if (type.equals("sell-standard")) {
            return SELL;
        } else if (type.equals("full-standard")) {
            return FULL;
        } else if (type.equals("admin")) {
            return ADMIN;
        }

        return NONE;
    }
}
