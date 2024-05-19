package com.tr.mustafakacar.WordToPhrase.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "settings")
@Data
@Entity
public class SettingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private int newWordFrequency=10;

}
