package com.tr.mustafakacar.WordToPhrase.repository;

import com.tr.mustafakacar.WordToPhrase.entity.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ResultRepository extends JpaRepository<ResultEntity, Long> {
    List<ResultEntity> findByUserId(long resultId);
}
