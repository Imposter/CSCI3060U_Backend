package com.scarz.backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Current users file used to get users by name
 */
public class UserFile extends File {
    private List<User> mUsers;

    /**
     * Initializes file with specified file path
     * @param name File path
     */
    public UserFile(String name) {
        super(name);

        mUsers = new ArrayList<>();
    }

    /**
     * Opens the user file and reads all the users into memory
     * @throws IOException Thrown when an error occurs while reading the file
     */
    public void open() throws IOException {
        // TODO: Open file in read mode, read all items, close handle
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Writes the updated user list to the file and closes the stream
     * @throws IOException Thrown when an error occurs while writing the file
     */
    @Override
    public void close() throws IOException {
        // TODO: Open file in write mode, write all lines, close handle
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Gets a list of all available users currently present in the system
     * @return All user accounts present in the user file
     */
    public List<User> getUsers() {
        return mUsers;
    }

    /**
     * Returns a user in the file with the specified name
     * @param name Username to search
     * @return User with specified name, if any
     */
    public User getUserByName(String name) {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Adds a user to the list of current users
     * @param user User to add
     */
    public void addUser(User user) {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Removes a user from the list of current users
     * @param item User to remove
     */
    public void removeUser(Item item) {
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented");
    }
}
