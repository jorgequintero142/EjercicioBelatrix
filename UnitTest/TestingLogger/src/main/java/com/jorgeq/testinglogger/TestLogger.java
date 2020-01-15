package com.jorgeq.testinglogger;

import java.util.Scanner;
import junit.framework.TestCase;
import static junit.framework.TestCase.assertFalse;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

public class TestLogger extends TestCase {

    private final Scanner scanner;

    public TestLogger() {
        scanner = new Scanner(System.in);
    }

    @Test
    public void testMessage() {

        String name = this.getName();
        System.out.println("Test Case Name = " + name);
        System.out.println("Enter Message to log: ");
        String message = scanner.nextLine();
        message = message.trim();
        assertFalse(message.isEmpty());
        assertNotEquals("", message);
    }

    @Test
    public void testDefineMessageType() {
        String name = this.getName();
        System.out.println("Test Case Name = " + name);

        System.out.println("Selected the message type: ");
        System.out.println("1. Warning ");
        System.out.println("2. Message ");
        System.out.println("3. Error ");
        int numero = scanner.nextInt();
        assertTrue(1 <= numero && numero <= 3);
    }

}
