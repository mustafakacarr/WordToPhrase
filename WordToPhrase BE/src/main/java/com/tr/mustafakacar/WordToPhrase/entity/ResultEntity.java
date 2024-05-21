package com.tr.mustafakacar.WordToPhrase.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashMap;

@Entity
@Data
@Table(name = "results")
public class ResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int correctCount;
    private int wrongCount;

    private long resultDatetime;

    @OneToOne
    @JoinColumn(name = "exam_id")
    private ExamEntity exam;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
