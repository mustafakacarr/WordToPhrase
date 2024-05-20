package com.tr.mustafakacar.WordToPhrase.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

@Table(name = "settings")
@Data
@Entity
public class SettingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private int newWordFrequency;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "setting")
    private UserEntity user;

}
