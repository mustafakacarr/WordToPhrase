package com.tr.mustafakacar.WordToPhrase.controller;

import com.tr.mustafakacar.WordToPhrase.entity.ExamEntity;
import com.tr.mustafakacar.WordToPhrase.responses.ExamResponse;
import com.tr.mustafakacar.WordToPhrase.service.ExamService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exams")
public class ExamController {
    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    public ExamResponse startExam(@RequestBody long userId) {
        return examService.startExam(userId);
    }

}
