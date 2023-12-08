package com.example.fileProcessor.application.controllers;

import com.example.fileProcessor.domain.files.Output;
import com.example.fileProcessor.domain.files.validator.FileValidator;
import com.example.fileProcessor.domain.ports.Parser;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private Parser parserImpl;
    @Autowired
    private FileValidator fileValidator;
    @Value("${files.validation}")
    private boolean validate;

    @SneakyThrows
    @PostMapping(value = "/parse", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Output> parse(@RequestParam("file") MultipartFile inputFile) {
        if (validate) {
            fileValidator.validate(inputFile);
        }

        return parserImpl.parse(inputFile);
    }

    @PostMapping(value = "/test", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> test(@RequestParam("file") MultipartFile inputFile) {
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
