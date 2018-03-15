package com.scarz.backend;

import com.scarz.backend.transactions.BasicTransaction;
import com.scarz.backend.transactions.TransactionType;

public class Main {
    public static void main(String[] args) {
        TransactionFile f = new TransactionFile("./test.txt");
        f.addSerializer(TransactionType.LOGIN, new BasicTransaction.Serializer());
    }
}
