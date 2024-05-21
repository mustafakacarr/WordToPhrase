package com.tr.mustafakacar.WordToPhrase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tr.mustafakacar.WordToPhrase.requests.WordCreateRequest;
import com.tr.mustafakacar.WordToPhrase.responses.WordResponse;
import com.tr.mustafakacar.WordToPhrase.service.WordService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/words")
public class WordController {
private final WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public WordResponse addWord(@RequestPart MultipartFile image, @RequestPart("word") String wordJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        WordCreateRequest word = objectMapper.readValue(wordJson, WordCreateRequest.class);

        return wordService.addWord(image,word);
    }

    @GetMapping
    public List<WordResponse> getAllWords(@RequestParam(value = "userId",required = true) long userId) {
        return wordService.getAllWords(userId);
    }
}
