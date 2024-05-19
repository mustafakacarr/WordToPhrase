package com.tr.mustafakacar.WordToPhrase.repository;

import com.tr.mustafakacar.WordToPhrase.entity.ExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<ExamEntity, Long> {
}
