package com.tr.mustafakacar.WordToPhrase.controller;

import com.tr.mustafakacar.WordToPhrase.requests.WordCreateRequest;
import com.tr.mustafakacar.WordToPhrase.responses.WordResponse;
import com.tr.mustafakacar.WordToPhrase.service.WordService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/words")
public class WordController {
private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @PostMapping
    public WordResponse addWord(@RequestBody WordCreateRequest wordCreateRequest) throws IOException {
        return wordService.addWord(null,wordCreateRequest);
    }

    @GetMapping
    public List<WordResponse> getAllWords(@RequestParam(value = "userId",required = true) long userId) {
        return wordService.getAllWords(userId);
    }
}
