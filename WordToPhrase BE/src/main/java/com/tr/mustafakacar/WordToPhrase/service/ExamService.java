package com.tr.mustafakacar.WordToPhrase.service;

import com.tr.mustafakacar.WordToPhrase.entity.*;
import com.tr.mustafakacar.WordToPhrase.repository.ExamRepository;
import com.tr.mustafakacar.WordToPhrase.repository.KnownWordsRepository;
import com.tr.mustafakacar.WordToPhrase.repository.SettingRepository;
import com.tr.mustafakacar.WordToPhrase.repository.UserRepository;
import com.tr.mustafakacar.WordToPhrase.responses.ExamResponse;
import com.tr.mustafakacar.WordToPhrase.responses.QuestionResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamService {
    private final UserRepository userRepository;
    private final WordService wordService;
    private final SettingRepository settingRepository;
    private final KnownWordsRepository knownWordsRepository;
    private final ExamRepository examRepository;

    public ExamService(UserRepository userRepository, WordService wordService, KnownWordsService knownWordsService, SettingRepository settingRepository, KnownWordsRepository knownWordsRepository,
                       ExamRepository examRepository) {
        this.userRepository = userRepository;
        this.wordService = wordService;
        this.settingRepository = settingRepository;
        this.knownWordsRepository = knownWordsRepository;
        this.examRepository = examRepository;
    }


    public ExamResponse startExam(long userId) {
        LocalDate today = LocalDate.now();
        long startOfTodayAsMilliseconds = today.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
        long nowAsMilliseconds = today.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        SettingEntity setting = settingRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Setting not found"));

        ExamEntity examEntity = new ExamEntity();
        List<KnownWordEntity> knownWords = knownWordsRepository.findByUserId(userId);
        List<WordEntity> wordsToAsk = new ArrayList<>();
        if (knownWords.size() != 0) {
            List<KnownWordEntity> wordsToAskToday = knownWordsRepository.findByNextToAskDate(startOfTodayAsMilliseconds);
            wordsToAsk.addAll(wordsToAskToday.stream().map(knownWord -> knownWord.getWord()).collect(Collectors.toList()));

            List<WordEntity> exceptionWords = knownWords.stream().map(KnownWordEntity::getWord).collect(Collectors.toList());
            List<WordEntity> newWordsToAsk = wordService.getNewWordsForExam(Optional.of(exceptionWords), setting.getNewWordFrequency());
            wordsToAsk.addAll(newWordsToAsk);
        } else {
            List<WordEntity> newWordsToAsk = wordService.getNewWordsForExam(Optional.empty(), setting.getNewWordFrequency());
            wordsToAsk.addAll(newWordsToAsk);
        }
        examEntity.setWords(wordsToAsk);
        examEntity.setExamDatetime(nowAsMilliseconds);
        examEntity.setUser(user);

        ExamEntity savedExam = examRepository.save(examEntity);
        List<QuestionResponse> questions = wordService.getQuestionListByWords(savedExam.getWords());
        return new ExamResponse(savedExam, questions);


    }


}
