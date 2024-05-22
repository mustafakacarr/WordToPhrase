package com.tr.mustafakacar.WordToPhrase.service;

import com.tr.mustafakacar.WordToPhrase.entity.*;
import com.tr.mustafakacar.WordToPhrase.helper.DateActions;
import com.tr.mustafakacar.WordToPhrase.repository.ExamRepository;
import com.tr.mustafakacar.WordToPhrase.repository.KnownWordsRepository;
import com.tr.mustafakacar.WordToPhrase.repository.ResultRepository;
import com.tr.mustafakacar.WordToPhrase.repository.UserRepository;
import com.tr.mustafakacar.WordToPhrase.requests.ExamAnswerRequest;
import com.tr.mustafakacar.WordToPhrase.responses.ExamResponse;
import com.tr.mustafakacar.WordToPhrase.responses.QuestionResponse;
import com.tr.mustafakacar.WordToPhrase.responses.ResultResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamService {
    private final UserRepository userRepository;
    private final WordService wordService;
    private final KnownWordsRepository knownWordsRepository;
    private final ExamRepository examRepository;
    private final ResultRepository resultRepository;
    private final KnownWordsService knownWordsService;

    public ExamService(UserRepository userRepository, WordService wordService, KnownWordsRepository knownWordsRepository,
                       ExamRepository examRepository, ResultRepository resultRepository, KnownWordsService knownWordsService1) {
        this.userRepository = userRepository;
        this.wordService = wordService;
        this.knownWordsRepository = knownWordsRepository;
        this.examRepository = examRepository;
        this.resultRepository = resultRepository;
        this.knownWordsService = knownWordsService1;
    }

    @Transactional
    public ExamResponse startExam(long userId) {
        LocalDate today = LocalDate.now();
        long startOfTodayAsMilliseconds = today.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
        long nowAsMilliseconds = today.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        SettingEntity setting = user.getSetting();

        ExamEntity examEntity = new ExamEntity();
        List<KnownWordEntity> knownWords = knownWordsRepository.findByUserId(userId);
        List<WordEntity> wordsToAsk = new ArrayList<>();
        if (knownWords.size() != 0) {
            List<KnownWordEntity> knownWordsToAskToday = knownWordsRepository.findByNextToAskDate(startOfTodayAsMilliseconds);
            wordsToAsk.addAll(knownWordsToAskToday.stream().map(knownWord -> knownWord.getWord()).collect(Collectors.toList()));

            List<WordEntity> exceptionWords = knownWords.stream().map(KnownWordEntity::getWord).collect(Collectors.toList());
            List<WordEntity> newWordsToAsk = wordService.getNewWordsForExam(Optional.of(exceptionWords), setting.getNewWordFrequency(), user);
            wordsToAsk.addAll(newWordsToAsk);
        } else {
            List<WordEntity> newWordsToAsk = wordService.getNewWordsForExam(Optional.empty(), setting.getNewWordFrequency(), user);
            wordsToAsk.addAll(newWordsToAsk);
        }

        examEntity.setWords(wordsToAsk);
        examEntity.setExamDatetime(nowAsMilliseconds);
        examEntity.setUser(user);

        ExamEntity savedExam = examRepository.save(examEntity);
        List<QuestionResponse> questions = wordService.getQuestionListByWords(savedExam.getWords());
        return new ExamResponse(savedExam, questions);
    }

    @Transactional
    public ResultResponse finishExam(ExamAnswerRequest examAnswer, long examId) {
        ExamEntity exam = examRepository.findById(examId).orElseThrow(() -> new RuntimeException("Exam not found"));
        Map<Long, String> examAnswers = examAnswer.getAnswers();
        List<WordEntity> wordsInExam = exam.getWords();
        List<WordEntity> newKnownWords = new ArrayList<>();
        List<WordEntity> newUnknownWords = new ArrayList<>();
        for (WordEntity word : wordsInExam) {
            String answer = examAnswers.get(word.getId());
            if (answer != null) {
                if (answer.equals(word.getMeaning()))
                    newKnownWords.add(word);
                else
                    newUnknownWords.add(word);
            } else {
                newUnknownWords.add(word);
            }
        }
        //UPDATE KNOWN WORDS
        knownWordsService.addOrUpdateKnownWordsByUserId(newKnownWords, exam.getUser());
        //DELETE UNKNOWN WORDS
        knownWordsService.deleteKnownWordsByUserId(newUnknownWords, exam.getUser());
        System.out.println("exam finished");
        ResultEntity resultEntity = new ResultEntity();
        resultEntity.setCorrectCount(newKnownWords.size());
        resultEntity.setWrongCount(newUnknownWords.size());
        resultEntity.setResultDatetime(DateActions.getNowAsMilliseconds());
        resultEntity.setExam(exam);
        resultEntity.setUser(exam.getUser());
        return new ResultResponse(resultRepository.save(resultEntity));

    }


}
