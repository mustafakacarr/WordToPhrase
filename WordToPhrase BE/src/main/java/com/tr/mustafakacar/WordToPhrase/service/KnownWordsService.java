package com.tr.mustafakacar.WordToPhrase.service;

import com.tr.mustafakacar.WordToPhrase.repository.KnownWordsRepository;
import org.springframework.stereotype.Service;

@Service
public class KnownWordsService {
    private final KnownWordsRepository knownWordsRepository;

    public KnownWordsService(KnownWordsRepository knownWordsRepository) {
        this.knownWordsRepository = knownWordsRepository;
    }
}
