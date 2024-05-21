package com.tr.mustafakacar.WordToPhrase.service;

import com.tr.mustafakacar.WordToPhrase.repository.ResultRepository;
import com.tr.mustafakacar.WordToPhrase.responses.ResultResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {
private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public ResultResponse getResult(long resultId) {
    return new ResultResponse(resultRepository.findById(resultId).orElseThrow());
    }

    public List<ResultResponse> getResults(long resultId) {
    return resultRepository.findByUserId(resultId).stream().map((result)->new ResultResponse(result)).collect(Collectors.toList());
    }
}
