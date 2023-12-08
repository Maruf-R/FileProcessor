package com.example.fileProcessor.domain.ports;

import com.example.fileProcessor.domain.files.Output;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Parser {

    List<Output> parse(MultipartFile inputFile);

}
