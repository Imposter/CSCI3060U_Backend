package com.scarz.backend.tests;

import com.scarz.backend.Item;
import junit.framework.TestCase;

public class ItemTest extends TestCase {
    public void testGetName() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals("Potatoes 100kg", item.getName());
    }

    public void testGetSellerUserName() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals("selluser", item.getSellerUserName());
    }

    public void testGetBidderUserName() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals("buyuser", item.getBidderUserName());
    }

    public void testGetDaysToAuction() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals(3, item.getDaysToAuction());
    }

    public void testSetDaysToAuction() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals(3, item.getDaysToAuction());
        item.setDaysToAuction(5);
        assertEquals(5, item.getDaysToAuction());
    }

    public void testGetCurrentBid() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals(300.00, item.getCurrentBid());
    }

    public void testSetBidderUserName() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals("buyuser", item.getBidderUserName());
        item.setBidderUserName("fulluser");
        assertEquals("fulluser", item.getBidderUserName());
    }

    public void testSetCurrentBid() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals(300.00, item.getCurrentBid());
        item.setCurrentBid(350.00);
        assertEquals(350.00, item.getCurrentBid());
    }

    public void testIsNewItem() {
        Item item = new Item("Potatoes 100kg", "selluser", "buyuser", 3, 300.00, true);
        assertEquals(true, item.isNewItem());
    }
}