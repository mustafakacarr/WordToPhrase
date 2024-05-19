package com.tr.mustafakacar.WordToPhrase.responses;

import com.tr.mustafakacar.WordToPhrase.entity.WordEntity;
import lombok.Data;

import java.util.List;
@Data
public class QuestionResponse {
    private long wordId;
    private String word;
    private List<String> options;

    public QuestionResponse(WordEntity wordEntity, List<String> options) {
        this.wordId = wordEntity.getId();
        this.word = wordEntity.getWord();
        this.options = options;
    }
}
