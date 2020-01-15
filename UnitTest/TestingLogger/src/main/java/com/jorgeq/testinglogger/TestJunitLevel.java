package com.jorgeq.testinglogger;

import java.util.logging.Level;
import static junit.framework.TestCase.assertNotNull;
import org.junit.Test;

public class TestJunitLevel {

    enum MessageTypeEnum {
        MESSAGE, ERROR, WARNING
    }

    @Test
    public void homologateLevels() {
        Level level = null;
        MessageTypeEnum messageType = MessageTypeEnum.ERROR;
        //MessageTypeEnum messageType = null;

        System.out.println("Test Case Name = homologateLevels");
        switch (messageType) {
            case MESSAGE:
                level = Level.INFO;
                break;
            case ERROR:
                level = Level.SEVERE;
                break;
            case WARNING:
                level = Level.WARNING;
                break;
        }
        assertNotNull(level);
    }
}
