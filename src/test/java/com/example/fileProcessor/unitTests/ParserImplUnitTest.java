package com.example.fileProcessor.unitTests;

import com.example.fileProcessor.domain.adapters.ParserImpl;
import com.example.fileProcessor.domain.builders.OutputBuilder;
import com.example.fileProcessor.domain.files.Output;
import com.example.fileProcessor.domain.ports.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

@SpringBootTest
public class ParserImplUnitTest {

    private OutputBuilder outputBuilder = new OutputBuilder();
    private Parser parser = new ParserImpl(outputBuilder);

    @Test
    void parsesCorrectly() {
        byte[] correctInputArray = ("18148426-89e1-11ee-b9d1-0242ac120002|1X1D14|John Smith|Likes Apricots|" +
                "Rides A Bike|6.2|12.1 \\n 3ce2d17b-e66a-4c1e-bca3-40eb1c9222c7|2X2D24|Mike Smith|" +
                "Likes Grape|Drives an SUV|35.0|95.5 \\n 1afb6f5d-a7c2-4311-a92d-974f3180ff5e|3X3D35|" +
                "Jenny Walters|Likes Avocados|Rides A Scooter|8.5|15.3").getBytes();

        MockMultipartFile multipartFile = new MockMultipartFile("EntryFile", correctInputArray);
        List<Output> result = parser.parse(multipartFile);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(result.get(0).getName(), "John Smith");
        Assertions.assertEquals(result.get(2).getName(), "Jenny Walters");
    }

    @Test
    void incorrectInputThrowsException() {
        byte[] incorrectInputArray = ("18148426").getBytes();Parser parser = new ParserImpl(outputBuilder);
        MockMultipartFile multipartFile = new MockMultipartFile("EntryFile", incorrectInputArray);

        Assertions.assertThrows(Exception.class, () -> {
            parser.parse(multipartFile);
        });
    }

}
