package com.tr.mustafakacar.WordToPhrase.repository;

import com.tr.mustafakacar.WordToPhrase.entity.WordEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Transactional
public interface WordRepository extends JpaRepository<WordEntity, Long> {
    @Query(value = "SELECT * FROM words WHERE id NOT IN (:excludedIds) AND word_owner_id= :userId LIMIT :limitSize", nativeQuery = true)
    List<WordEntity> findByIdNotInWithLimit(
            @Param("userId") long userId, @Param("excludedIds") List<Long> excludedIds, @Param("limitSize") int limitSize);

    @Query(value = "SELECT * FROM words WHERE word_owner_id= :userId  ORDER BY RANDOM () LIMIT :limitSize", nativeQuery = true)
    List<WordEntity> findWithLimit(@Param("userId") long userId, @Param("limitSize") int count);

    List<WordEntity> findByWordOwnerId(long userId);
}
