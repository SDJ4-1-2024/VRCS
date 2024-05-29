package org.example.util.logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingletonLogTest {

    @Test
    void checkSingletonLog(){
        String logText = "Log has been added";
        SingletonLog.getInstance().addLog(logText);

        String actualResult = String.valueOf(SingletonLog.getInstance().getLogLines().stream().findAny().get());
        String expectedResult = logText;
        Assertions.assertEquals(expectedResult, actualResult);
    }

}