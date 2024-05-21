package com.tr.mustafakacar.WordToPhrase.service;

import com.tr.mustafakacar.WordToPhrase.entity.ImageEntity;
import com.tr.mustafakacar.WordToPhrase.entity.UserEntity;
import com.tr.mustafakacar.WordToPhrase.entity.WordEntity;
import com.tr.mustafakacar.WordToPhrase.repository.UserRepository;
import com.tr.mustafakacar.WordToPhrase.repository.WordRepository;
import com.tr.mustafakacar.WordToPhrase.requests.WordCreateRequest;
import com.tr.mustafakacar.WordToPhrase.responses.QuestionResponse;
import com.tr.mustafakacar.WordToPhrase.responses.WordResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class WordService {
    private final WordRepository wordRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;

    public WordService(WordRepository wordRepository, UserRepository userRepository, ImageService imageService) {
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    public WordResponse addWord(MultipartFile file, WordCreateRequest wordCreateRequest) throws IOException {
        ImageEntity image = imageService.saveImage(file);
        UserEntity user = userRepository.findById(wordCreateRequest.getOwnerId()).orElseThrow(() -> new RuntimeException("User not found"));
        WordEntity wordEntity = new WordEntity();
        wordEntity.setMeaning(wordCreateRequest.getMeaning());
        wordEntity.setWord(wordCreateRequest.getWord());
        wordEntity.setPhrases(wordCreateRequest.getPhrases());
        wordEntity.setWordOwner(user);
        wordEntity.setImage(image);
        return new WordResponse(wordRepository.save(wordEntity));
    }

    public List<WordEntity> getNewWordsForExam(Optional<List<WordEntity>> excludedWords, int count) {
        List<WordEntity> exceptOf = excludedWords.orElseGet(() -> List.of());
        List<Long> exceptionIds = exceptOf.stream().map(WordEntity::getId).collect(Collectors.toList());

        if (exceptionIds.isEmpty())
            return wordRepository.findWithLimit(count);
        else
            return wordRepository.findByIdNotInWithLimit(exceptionIds, count);
    }

    public QuestionResponse getQuestionByWord(WordEntity mainWord) {

        List<WordEntity> allWords = wordRepository.findAll();
        Collections.shuffle(allWords);

        List<String> options = allWords.stream()
                .filter(w -> !w.getId().equals(mainWord.getId()))
                .map(WordEntity::getMeaning)
                .limit(3)
                .collect(Collectors.toList());

        options.add(mainWord.getMeaning());
        Collections.shuffle(options);

        return new QuestionResponse(mainWord, options);
    }

    public List<QuestionResponse> getQuestionListByWords(List<WordEntity> words) {
        return words.stream().map(this::getQuestionByWord).collect(Collectors.toList());
    }

    public List<WordResponse> getAllWords(long userId) {
        return wordRepository.findByWordOwnerId(userId).stream().map((word) -> new WordResponse(word)).collect(Collectors.toList());
    }
}
