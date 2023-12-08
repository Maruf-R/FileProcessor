package com.example.fileProcessor.domain.files.validator;

import com.example.fileProcessor.domain.exceptions.FileIsEmptyException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.InvalidFileNameException;
import org.apache.tomcat.util.http.fileupload.impl.InvalidContentTypeException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Slf4j
public class FileValidator {

    private final String FILE_NAME = "EntryFile.txt";
    private final String FILE_IS_EMPTY = "File is empty";
    private final String INVALID_CONTENT_TYPE = "Invalid content type";

    @SneakyThrows
    public void validate(MultipartFile file) {
        String contentType = file.getContentType();

        if (!file.getOriginalFilename().equals(FILE_NAME)) {
            log.error("Invalid file name expecting a file with name " + FILE_NAME);
            throw new InvalidFileNameException(file.getOriginalFilename(), "Invalid file name expecting a file with name " + FILE_NAME);
        }
        if (file.isEmpty()) {
            log.error(FILE_IS_EMPTY, new FileIsEmptyException(FILE_IS_EMPTY));
            throw new FileIsEmptyException(FILE_IS_EMPTY);
        }
        if (!isSupportedContentType(contentType)) {
            log.error(INVALID_CONTENT_TYPE);
            throw new InvalidContentTypeException(INVALID_CONTENT_TYPE);
        }
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("text/plain");
    }
}
