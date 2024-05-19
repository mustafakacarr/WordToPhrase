package com.tr.mustafakacar.WordToPhrase.repository;

import com.tr.mustafakacar.WordToPhrase.entity.KnownWordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KnownWordsRepository extends JpaRepository<KnownWordEntity, Long> {
    List<KnownWordEntity> findByUserId(long userId);

    List<KnownWordEntity> findByNextToAskDate(long todayAsMilliseconds);
}
