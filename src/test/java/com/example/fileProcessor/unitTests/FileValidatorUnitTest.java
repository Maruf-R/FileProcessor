package com.example.fileProcessor.unitTests;

import com.example.fileProcessor.domain.exceptions.FileIsEmptyException;
import com.example.fileProcessor.domain.files.validator.FileValidator;
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.apache.tomcat.util.http.fileupload.impl.InvalidContentTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest
public class FileValidatorUnitTest {

    private FileValidator fileValidator = new FileValidator();

    @Test
    void validFile() {
        MockMultipartFile multipartFile = new MockMultipartFile("EntryFile", "EntryFile.txt", "text/plain", "content".getBytes());
        fileValidator.validate(multipartFile);
    }

    @Test
    void invalidEmptyFileThrowsException() {
        MockMultipartFile multipartFile = new MockMultipartFile("EntryFile", "EntryFile.txt", "text/plain", "".getBytes());
        Assertions.assertThrows(FileIsEmptyException.class, () -> {
            fileValidator.validate(multipartFile);
        });
    }

    @Test
    void invalidFileNameThrowsException() {
        MockMultipartFile multipartFile = new MockMultipartFile("EntryFile", "File.txt", "text/plain", "content".getBytes());
        Assertions.assertThrows(InvalidFileNameException.class, () -> {
            fileValidator.validate(multipartFile);
        });
    }

    @Test
    void invalidFileContentTypeThrowsException() {
        MockMultipartFile multipartFile = new MockMultipartFile("EntryFile", "EntryFile.txt", "text/xml", "content".getBytes());
        Assertions.assertThrows(InvalidContentTypeException.class, () -> {
            fileValidator.validate(multipartFile);
        });
    }

}
