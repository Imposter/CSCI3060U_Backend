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
        switch (type) {
            case BUY:
                return "BS";
            case SELL:
                return "SS";
            case FULL:
                return "FS";
            case ADMIN:
                return "AA";
        }

        return "";
    }

    /**
     * Converts a specified user type string to its integer representation
     * @param type User type in short form
     * @return User type in integer representation
     */
    public static int getUserTypeFromString(String type) {
        switch (type) {
            case "BS":
                return BUY;
            case "SS":
                return SELL;
            case "FS":
                return FULL;
            case "AA":
                return ADMIN;
        }

        return NONE;
    }
}
