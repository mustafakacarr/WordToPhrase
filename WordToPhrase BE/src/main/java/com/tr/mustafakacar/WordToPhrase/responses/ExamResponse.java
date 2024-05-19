package com.tr.mustafakacar.WordToPhrase.responses;

import com.tr.mustafakacar.WordToPhrase.entity.ExamEntity;
import lombok.Data;

import java.util.List;
@Data
public class ExamResponse {
    private final List<QuestionResponse> questions;
    private final long examDatetime;
    private final long examId;


    public ExamResponse(ExamEntity examEntity, List<QuestionResponse> questions) {
        this.questions = questions;
        this.examDatetime = examEntity.getExamDatetime();
        this.examId = examEntity.getId();
    }
}
