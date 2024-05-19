package com.tr.mustafakacar.WordToPhrase.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "words")
@Data
public class WordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String word;

    private String meaning;
    @ElementCollection
    @CollectionTable(name = "phrases", joinColumns = @JoinColumn(name = "word_id"))
    private List<String> phrases;

    @OneToOne
    private ImageEntity image;

    @ManyToOne
    private UserEntity wordOwner;
}
