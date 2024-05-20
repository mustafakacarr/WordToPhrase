package com.tr.mustafakacar.WordToPhrase.responses;

import com.tr.mustafakacar.WordToPhrase.entity.ImageEntity;
import com.tr.mustafakacar.WordToPhrase.entity.WordEntity;
import lombok.Data;

import java.util.List;

@Data
public class WordResponse {
    private long wordId;
    private String word;
    private List<String> phrases;
    private String meaning;
    private ImageEntity image;

    public WordResponse(WordEntity word) {
        this.wordId = word.getId();
        this.word = word.getWord();
        this.phrases = word.getPhrases();
        this.meaning = word.getMeaning();
        this.image = word.getImage();

    }
}
