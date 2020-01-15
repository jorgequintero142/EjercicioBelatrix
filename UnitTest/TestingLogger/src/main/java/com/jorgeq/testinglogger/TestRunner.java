package com.jorgeq.testinglogger;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args) {
        Result result1 = JUnitCore.runClasses(TestLogger.class);

        for (Failure failure : result1.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("Successful result 1: " + result1.wasSuccessful());

        Result result2 = JUnitCore.runClasses(TestJunitLevel.class);

        for (Failure failure : result2.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println("Successful result2: " + result2.wasSuccessful());
    }
}
