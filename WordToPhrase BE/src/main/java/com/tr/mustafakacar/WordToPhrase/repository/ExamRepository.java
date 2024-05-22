package com.tr.mustafakacar.WordToPhrase.repository;

import com.tr.mustafakacar.WordToPhrase.entity.ExamEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
@Transactional
public interface ExamRepository extends JpaRepository<ExamEntity, Long> {
}
