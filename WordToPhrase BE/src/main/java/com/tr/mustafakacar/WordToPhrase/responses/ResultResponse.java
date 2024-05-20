package com.tr.mustafakacar.WordToPhrase.responses;

import com.tr.mustafakacar.WordToPhrase.entity.ResultEntity;
import lombok.Data;

@Data
public class ResultResponse {
    private int correctCount;
    private int wrongCount;

    private long resultDatetime;
    private long examId;
    private long examDatetime;
    private long resultId;

    public ResultResponse(ResultEntity result) {
        this.correctCount = result.getCorrectCount();
        this.wrongCount = result.getWrongCount();
        this.resultDatetime = result.getResultDatetime();
        this.examId = result.getExam().getId();
        this.examDatetime = result.getExam().getExamDatetime();
        this.resultId = result.getId();
    }
}
