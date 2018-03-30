package com.scarz.backend.tests;

import com.scarz.backend.User;
import com.scarz.backend.UserType;
import junit.framework.TestCase;

public class UserTest extends TestCase {
    public void testGetName() {
        User user = new User("buyuser", UserType.BUY, 100.00);
        assertEquals("buyuser", user.getName());
    }

    public void testGetUserType() {
        User user = new User("buyuser", UserType.BUY, 100.00);
        assertEquals(UserType.BUY, user.getUserType());
    }

    public void testGetCredits() {
        User user = new User("buyuser", UserType.BUY, 100.00);
        assertEquals(100.00, user.getCredits());
    }

    public void testSetCredits() {
        User user = new User("buyuser", UserType.BUY, 100.00);
        user.setCredits(120.00);
        assertEquals(120.00, user.getCredits());
    }
}