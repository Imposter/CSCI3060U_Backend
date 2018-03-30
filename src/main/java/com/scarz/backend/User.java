package com.scarz.backend;

/**
 * Used to store users read by UserFile
 */
public class User {
    private String mName;
    private int mUserType;
    private double mCredits;

    /**
     * Initializes instance with specified information
     * @param name User name
     * @param type User account type
     * @param credits Amount of credits
     */
    public User(String name, int type, double credits) {
        mName = name;
        mUserType = type;
        mCredits = credits;
    }

    /**
     * Gets user's name
     * @return User name
     */
    public String getName() {
        return mName;
    }

    /**
     * Gets user's type
     * @return User account type
     */
    public int getUserType() {
        return mUserType;
    }

    /**
     * Returns the user's amount of credits
     * @return Amount of credits
     */
    public double getCredits() {
        return mCredits;
    }

    /**
     * Sets the credits for the user
     * @param credits Amount of credits for user
     */
    public void setCredits(double credits) {
        mCredits = credits;
    }
}