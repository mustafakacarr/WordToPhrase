package com.tr.mustafakacar.WordToPhrase.requests;

import lombok.Data;

import java.util.Map;

@Data
public class ExamAnswerRequest {
private Map<Long,String> answers;
}
