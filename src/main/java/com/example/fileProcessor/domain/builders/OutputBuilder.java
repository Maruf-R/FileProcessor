package com.example.fileProcessor.domain.builders;

import com.example.fileProcessor.domain.files.Output;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OutputBuilder {

    public Output stringToOutput(String input) {
        String[] inputArray = parseString(input);

        try {
            return Output.builder()
                    .name(inputArray[2])
                    .transport(inputArray[4])
                    .topSpeed(Float.parseFloat(inputArray[6]))
                    .build();
        } catch (IllegalArgumentException e) {
            log.error("Some of the file content are invalid");
            throw e;
        }
    }

    private String[] parseString(String input) {
        String[] stringArray = input.split("\\|");
        return stringArray;
    }

}
