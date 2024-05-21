package com.tr.mustafakacar.WordToPhrase.repository;

import com.tr.mustafakacar.WordToPhrase.entity.KnownWordEntity;
import com.tr.mustafakacar.WordToPhrase.entity.UserEntity;
import com.tr.mustafakacar.WordToPhrase.entity.WordEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KnownWordsRepository extends JpaRepository<KnownWordEntity, Long> {
    List<KnownWordEntity> findByUserId(long userId);

    @Query(value = "SELECT * FROM known_words WHERE next_to_ask_date = :todayAsMilliseconds AND correct_count < 6", nativeQuery = true)
    List<KnownWordEntity> findByNextToAskDate(@Param("todayAsMilliseconds") long todayAsMilliseconds);

    KnownWordEntity findByUserIdAndWordId(long userId, Long wordId);

    @Transactional
    @Modifying
    @Query("DELETE FROM KnownWordEntity k WHERE k.word IN :words AND k.user.id = :userId")
    void deleteAllByWordIn(@Param("words") List<WordEntity> words, @Param("userId") long userId);

}
