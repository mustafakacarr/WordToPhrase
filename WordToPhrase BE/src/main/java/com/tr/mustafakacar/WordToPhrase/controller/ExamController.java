package com.tr.mustafakacar.WordToPhrase.controller;

import com.tr.mustafakacar.WordToPhrase.requests.ExamAnswerRequest;
import com.tr.mustafakacar.WordToPhrase.responses.ExamResponse;
import com.tr.mustafakacar.WordToPhrase.responses.ResultResponse;
import com.tr.mustafakacar.WordToPhrase.service.ExamService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exams")
public class ExamController {
    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/users/{userId}")
    public ExamResponse startExam(@PathVariable long userId) {
        return examService.startExam(userId);
    }

    @PostMapping("/{examId}/finish")
    public ResultResponse finishExam(@RequestBody ExamAnswerRequest examAnswerRequest, @PathVariable long examId) {

        return examService.finishExam(examAnswerRequest, examId);
    }

}


