package com.example.fileProcessor.domain.exceptions;

public class FileIsEmptyException extends Exception {

    public FileIsEmptyException(String errMsg) {
        super(errMsg);
    }

}
