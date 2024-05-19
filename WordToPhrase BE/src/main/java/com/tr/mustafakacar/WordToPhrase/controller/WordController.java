package com.tr.mustafakacar.WordToPhrase.controller;

import com.tr.mustafakacar.WordToPhrase.entity.WordEntity;
import com.tr.mustafakacar.WordToPhrase.requests.WordCreateRequest;
import com.tr.mustafakacar.WordToPhrase.service.WordService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/words")
public class WordController {
private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @PostMapping
    public WordEntity addWord( @RequestBody WordCreateRequest wordCreateRequest) throws IOException {
        return wordService.addWord(null,wordCreateRequest);
    }

}
