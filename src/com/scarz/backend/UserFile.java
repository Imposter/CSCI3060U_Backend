package com.scarz.backend;

import com.scarz.backend.utility.StringUtility;
import com.scarz.backend.utility.io.MemoryStream;
import com.scarz.backend.utility.io.StringStream;

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
     * @throws Exception Thrown when an error occurs while reading the file
     */
    public void open() throws Exception {
        // Open in read mode
        openRead();

        // Read all users
        List<String> lines = readLines();
        for (String line : lines) {
            // End of file user
            if (line.equals("END"))
                break;

            // Create stream from line
            StringStream stream = new StringStream(new MemoryStream(line.getBytes()));

            // Read name
            String name = stream.readString(Config.USERNAME_LENGTH).trim();
            stream.read();

            // Read type
            int type = UserType.getUserTypeFromString(stream.readString(Config.USER_TYPE_LENGTH));
            stream.read();

            // Read credits
            double credits = Double.parseDouble(stream.readString(Config.CREDITS_LENGTH));

            mUsers.add(new User(name, type, credits));
        }

        // Close file handle
        closeRead();
    }

    /**
     * Writes the updated user list to the file and closes the stream
     * @throws IOException Thrown when an error occurs while writing the file
     */
    @Override
    public void close() throws IOException {
        // Open in write mode
        openWrite(false);

        // Write all users
        for (User user : mUsers) {
            StringBuilder line = new StringBuilder();
            line.append(StringUtility.PadRight(user.getName(), ' ', Config.USERNAME_LENGTH));
            line.append(' ');
            line.append(StringUtility.PadRight(UserType.getUserTypeString(user.getUserType()),
                    ' ', Config.USER_TYPE_LENGTH));
            line.append(' ');
            line.append(StringUtility.PadLeft(String.format("%.2f", user.getCredits()), '0', Config.CREDITS_LENGTH));

            writeLine(line.toString());
        }

        // Write file end
        writeLine("END");

        // Close handle
        closeWrite();
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
        for (User user : mUsers) {
            if (user.getName().equals(name)) {
                return user;
            }
        }

        return null;
    }

    /**
     * Adds a user to the list of current users
     * @param user User to add
     */
    public void addUser(User user) {
        mUsers.add(user);
    }

    /**
     * Removes a user from the list of current users
     * @param user User to remove
     */
    public void removeUser(User user) {
        mUsers.remove(user);
    }
}
