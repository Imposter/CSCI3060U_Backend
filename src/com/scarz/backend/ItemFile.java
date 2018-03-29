package com.scarz.backend;

import com.scarz.backend.utility.StringUtility;
import com.scarz.backend.utility.io.MemoryStream;
import com.scarz.backend.utility.io.StringStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Available items file used to get currently available items for auction
 */
public class ItemFile extends File {
    private List<Item> mItems;

    /**
     * Initializes item file with file path
     * @param name File path
     */
    public ItemFile(String name) {
        super(name);

        mItems = new ArrayList<>();
    }

    /**
     * Opens file and reads the items to memory
     * @throws Exception Thrown when an error occurs while reading the file
     */
    public void open() throws Exception {
        // Open in read mode
        openRead();

        // Read all items
        List<String> lines = readLines();
        for (String line : lines) {
            // End of file item
            if (line.equals("END"))
                break;

            // Create stream from line
            StringStream stream = new StringStream(new MemoryStream(line.getBytes()));

            // Read item name
            String itemName = stream.readString(Config.ITEM_NAME_LENGTH).trim();
            stream.read();

            // Read seller name
            String sellerName = stream.readString(Config.USERNAME_LENGTH).trim();
            stream.read();

            // Read bidder name
            String bidderName = stream.readString(Config.USERNAME_LENGTH).trim();
            stream.read();

            // Read auction days
            int daysToAuction = Integer.parseInt(stream.readString(Config.ITEM_AUCTION_LENGTH));
            stream.read();

            // Read item price
            double currentBid = Double.parseDouble(stream.readString(Config.ITEM_PRICE_LENGTH));

            mItems.add(new Item(itemName, sellerName, bidderName, daysToAuction, currentBid, false));
        }

        // Close file handle
        closeRead();
    }

    /**
     * Writes the updated item list to the file and closes the stream
     * @throws IOException Thrown when an error occurs while writing the file
     */
    @Override
    public void close() throws IOException {
        // Open in write mode
        openWrite(false);

        // Write all items
        for (Item item : mItems) {
            StringBuilder line = new StringBuilder();
            line.append(StringUtility.PadRight(item.getName(), ' ', Config.ITEM_NAME_LENGTH));
            line.append(' ');
            line.append(StringUtility.PadRight(item.getSellerUserName(), ' ', Config.USERNAME_LENGTH));
            line.append(' ');
            line.append(StringUtility.PadRight(item.getBidderUserName(), ' ', Config.USERNAME_LENGTH));
            line.append(' ');
            line.append(StringUtility.PadLeft(Integer.toString(item.getDaysToAuction()),
                    '0', Config.ITEM_AUCTION_LENGTH));
            line.append(' ');
            line.append(StringUtility.PadLeft(String.format("%.2f", item.getCurrentBid()),
                    '0', Config.ITEM_PRICE_LENGTH));

            writeLine(line.toString());
        }

        // Write file end
        writeLine("END");

        // Close handle
        closeWrite();
    }

    /**
     * Gets all of the items that are currently available in the system
     * @return All of the items that are present within the item file
     */
    public List<Item> getItems() {
        return mItems;
    }

    /**
     * Attempts to find an item by the specified name and creator
     * @param seller User which created the item
     * @param name Item name to search for
     * @return Pointer to item found, if any
     */
    public Item getItemByUserAndName(String seller, String name) {
        for (Item item : mItems) {
            if (item.getSellerUserName().equals(seller) && item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }

    /**
     * Adds the item to the list of available items
     * @param item Item to add
     */
    public void addItem(Item item) {
        mItems.add(item);
    }

    /**
     * Removes the item from the list of available items
     * @param item Item to remove
     */
    public void removeItem(Item item) {
        mItems.remove(item);
    }
}
