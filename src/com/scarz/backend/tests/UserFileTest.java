package com.scarz.backend.tests;

import com.scarz.backend.User;
import com.scarz.backend.UserFile;
import com.scarz.backend.UserType;
import junit.framework.TestCase;

import java.util.List;

public class UserFileTest extends TestCase {
    public static final String USERS_FILE = "test_current_users.txt";

    public void testOpen() throws Exception {
        // Create files
        TestHelper.createFiles();

        // Open file
        UserFile file = TestHelper.getUserFile();
        file.open();
    }

    public void testClose() throws Exception {
        // Create files
        TestHelper.createFiles();

        // Open file
        UserFile file = TestHelper.getUserFile();
        file.open();

        // Close file
        file.close();
    }

    public void testGetUsers() throws Exception {
        // Create files
        TestHelper.createFiles();

        // Open file
        UserFile file = TestHelper.getUserFile();
        file.open();

        // Get users
        List<User> users = file.getUsers();
        assertEquals(users.size(), 6);

        // Close file
        file.close();
    }

    public void testGetUserByName() throws Exception {
        // Create files
        TestHelper.createFiles();

        // Open file
        UserFile file = TestHelper.getUserFile();
        file.open();

        // Get user by name
        User user = file.getUserByName("selluser");
        assertNotNull(user);
        assertEquals("selluser", user.getName());
        assertEquals(UserType.SELL, user.getUserType());
        assertEquals(10099.99, user.getCredits());

        // Close file
        file.close();
    }

    public void testAddUser() throws Exception {
        // Create files
        TestHelper.createFiles();

        // Open file
        UserFile file = TestHelper.getUserFile();
        file.open();

        // Add user
        file.addUser(new User("tempuser", UserType.FULL, 9999.99));

        // Get user by name
        User user = file.getUserByName("tempuser");
        assertNotNull(user);
        assertEquals("tempuser", user.getName());
        assertEquals(UserType.FULL, user.getUserType());
        assertEquals(9999.99, user.getCredits());

        // Close file
        file.close();
    }

    public void testRemoveUser() throws Exception {
        // Create files
        TestHelper.createFiles();

        // Open file
        UserFile file = TestHelper.getUserFile();
        file.open();

        // Remove user
        User user = file.getUserByName("selluser");
        file.removeUser(user);

        // Get user by name
        user = file.getUserByName("tempuser");
        assertNull(user);

        // Close file
        file.close();
    }
}