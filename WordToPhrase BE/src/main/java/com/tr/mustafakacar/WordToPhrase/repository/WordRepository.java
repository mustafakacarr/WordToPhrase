package com.tr.mustafakacar.WordToPhrase.repository;

import com.tr.mustafakacar.WordToPhrase.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WordRepository extends JpaRepository<WordEntity, Long> {
    @Query(value = "SELECT * FROM words WHERE id NOT IN (:excludedIds) LIMIT :limitSize", nativeQuery = true)
    List<WordEntity> findByIdNotInWithLimit(@Param("excludedIds") List<Long> excludedIds,@Param("limitSize") int limitSize);

    @Query(value = "SELECT * FROM words ORDER BY RANDOM () LIMIT :limitSize", nativeQuery = true)
    List<WordEntity> findWithLimit(@Param("limitSize") int count);
}
