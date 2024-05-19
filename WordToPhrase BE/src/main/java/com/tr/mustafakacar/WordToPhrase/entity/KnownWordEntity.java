package com.tr.mustafakacar.WordToPhrase.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "known_words")
@Data
public class KnownWordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "word_id")
    private WordEntity word;

    private int correctCount;

    private long lastCorrectDate;

    private long nextToAskDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
