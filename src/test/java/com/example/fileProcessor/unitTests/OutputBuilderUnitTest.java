package com.example.fileProcessor.unitTests;

import com.example.fileProcessor.domain.builders.OutputBuilder;
import com.example.fileProcessor.domain.files.Output;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OutputBuilderUnitTest {

    private OutputBuilder outputBuilder = new OutputBuilder();

    @Test
    void expectedOutput() {
        String inputString = "18148426-89e1-11ee-b9d1-0242ac120002|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2|12.1";
        Output result = outputBuilder.stringToOutput(inputString);

        Assertions.assertEquals(result.getName(), "John Smith");
        Assertions.assertEquals(result.getTransport(), "Rides A Bike");
        Assertions.assertEquals(result.getTopSpeed(), Float.parseFloat("12.1"));
    }

    @Test
    void invalidInputWhereAFloatWasExpected() {
        String inputString = "18148426-89e1-11ee-b9d1-0242ac120002|1X1D14|John Smith|Likes Apricots|Rides A Bike|6.2|twelve";

        Assertions.assertThrows(NumberFormatException.class, () -> {
            outputBuilder.stringToOutput(inputString);
        });
    }

}
