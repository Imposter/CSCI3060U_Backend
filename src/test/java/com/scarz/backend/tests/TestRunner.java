package com.scarz.backend.tests;

import com.scarz.backend.tests.handlers.*;

import com.scarz.backend.tests.transactions.*;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(
                // Transactions
                BasicTransactionTest.class,
                AdvertiseTransactionTest.class,
                BidTransactionTest.class,
                RefundTransactionTest.class,

                // Handlers
                AddCreditHandlerTest.class,
                AdvertiseHandlerTest.class,
                BidHandlerTest.class,
                CreateHandlerTest.class,
                DeleteHandlerTest.class,
                LoginHandlerTest.class,
                LogoutHandlerTest.class,
                RefundHandlerTest.class,

                // Files
                FileTest.class,
                ItemFileTest.class,
                UserFileTest.class,
                TransactionFileTest.class,

                // Models
                ItemTest.class
        );

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        if (result.wasSuccessful()) {
            System.out.println("Tests completed successfully");
        }
    }
}
