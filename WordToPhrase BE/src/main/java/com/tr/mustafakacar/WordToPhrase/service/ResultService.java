package com.tr.mustafakacar.WordToPhrase.service;

import com.tr.mustafakacar.WordToPhrase.repository.ResultRepository;
import com.tr.mustafakacar.WordToPhrase.responses.ResultResponse;
import org.springframework.stereotype.Service;

@Service
public class ResultService {
private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public ResultResponse getResult(long resultId) {
    return new ResultResponse(resultRepository.findById(resultId).orElseThrow());
    }
}
