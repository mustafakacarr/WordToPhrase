package com.tr.mustafakacar.WordToPhrase.responses;

import com.tr.mustafakacar.WordToPhrase.entity.UserEntity;
import lombok.Data;

@Data
public class AuthResponse {
private long id;
private String username;
private String email;
private int newWordFrequency;


    public AuthResponse(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.email = userEntity.getEmail();
        this.newWordFrequency = userEntity.getSetting().getNewWordFrequency();
    }
}
