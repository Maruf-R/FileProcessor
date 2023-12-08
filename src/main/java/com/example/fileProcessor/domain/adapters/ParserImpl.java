package com.example.fileProcessor.domain.adapters;

import com.example.fileProcessor.domain.builders.OutputBuilder;
import com.example.fileProcessor.domain.files.Output;
import com.example.fileProcessor.domain.ports.Parser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ParserImpl implements Parser {

    private final OutputBuilder outputBuilder;

    @Override
    public List<Output> parse(MultipartFile inputFile) {
        String[] inputFileToStringArray = convertMultipartFileToString(inputFile).split("\\\\n");
        List<Output> outputs = new ArrayList<>();

        for (int i=0; i<inputFileToStringArray.length; i++) {
            outputs.add(outputBuilder.stringToOutput(inputFileToStringArray[i]));
        }

        return outputs;
    }

    @SneakyThrows
    private String convertMultipartFileToString(MultipartFile inputFile) {
        byte[] bytes = inputFile.getBytes();
        return new String(bytes);
    }


}
