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


    @ElementCollection
    @CollectionTable(name = "word_to_score", joinColumns = @JoinColumn(name = "result_id"))
    private HashMap<WordEntity, Integer> wordToScore;

    /*It means how many words user entirely learned
    So It's like that how many word that will not get asked them again because of learned */
    private int learnedWordCount;

    //its total percent of wordToScore
    private int totalScore;

    private long resultDatetime;
}
