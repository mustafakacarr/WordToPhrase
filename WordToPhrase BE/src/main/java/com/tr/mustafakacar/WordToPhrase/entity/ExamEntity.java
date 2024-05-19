package com.tr.mustafakacar.WordToPhrase.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Entity
@Table(name = "exams")
@Data
public class ExamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToMany
    @CollectionTable(name = "exam_words", joinColumns = @JoinColumn(name = "exam_id"))
    private List<WordEntity> words;

    private long examDatetime;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
