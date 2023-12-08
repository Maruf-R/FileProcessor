package com.example.fileProcessor.domain.files;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Output {

    String name;
    String transport;
    float topSpeed;

}
