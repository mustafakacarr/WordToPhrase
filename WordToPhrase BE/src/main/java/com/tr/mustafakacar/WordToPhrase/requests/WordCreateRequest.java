package com.tr.mustafakacar.WordToPhrase.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class WordCreateRequest {
    private String meaning;
    private String word;
    private List<String> phrases;
    private long ownerId;
}
