package com.tr.mustafakacar.WordToPhrase.service;

import com.tr.mustafakacar.WordToPhrase.entity.KnownWordEntity;
import com.tr.mustafakacar.WordToPhrase.entity.UserEntity;
import com.tr.mustafakacar.WordToPhrase.entity.WordEntity;
import com.tr.mustafakacar.WordToPhrase.helper.DateActions;
import com.tr.mustafakacar.WordToPhrase.repository.KnownWordsRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnownWordsService {
    private final KnownWordsRepository knownWordsRepository;

    public KnownWordsService(KnownWordsRepository knownWordsRepository) {
        this.knownWordsRepository = knownWordsRepository;
    }

    public KnownWordEntity updateKnownWord(long knownWordId, KnownWordEntity newKnownWord) {
        KnownWordEntity knownWord = knownWordsRepository.findById(knownWordId).orElseThrow(() -> new RuntimeException("Known word not found"));
        knownWord.setNextToAskDate(newKnownWord.getNextToAskDate());
        knownWord.setLastCorrectDate(newKnownWord.getLastCorrectDate());
        knownWord.setCorrectCount(newKnownWord.getCorrectCount());
        return knownWordsRepository.save(knownWord);
    }

    @Transactional
    public void deleteKnownWordsByUserId(List<WordEntity> words, UserEntity user) {
        knownWordsRepository.deleteAllByWordIn(words, user.getId());
    }

    @Transactional
    public void addOrUpdateKnownWordsByUserId(List<WordEntity> words, UserEntity user) {
        for (WordEntity word : words) {
            KnownWordEntity knownWordEntity = knownWordsRepository.findByUserIdAndWordId(user.getId(), word.getId());
            if (knownWordEntity == null) {
                knownWordEntity = new KnownWordEntity();
                knownWordEntity.setUser(user);
                knownWordEntity.setWord(word);
                knownWordEntity.setCorrectCount(1);
                knownWordEntity.setLastCorrectDate(DateActions.getStartOfTodayAsMilliseconds());
                knownWordEntity.setNextToAskDate(DateActions.findNextDateByCorrectCount(1));

            } else {
                knownWordEntity.setLastCorrectDate(DateActions.getStartOfTodayAsMilliseconds());
                knownWordEntity.setNextToAskDate(DateActions.findNextDateByCorrectCount(knownWordEntity.getCorrectCount()));
                knownWordEntity.setCorrectCount(knownWordEntity.getCorrectCount() + 1);

            }
            knownWordsRepository.save(knownWordEntity);

        }
    }
}
