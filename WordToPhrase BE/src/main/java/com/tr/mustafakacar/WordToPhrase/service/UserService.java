package com.tr.mustafakacar.WordToPhrase.service;

import com.tr.mustafakacar.WordToPhrase.entity.UserEntity;
import com.tr.mustafakacar.WordToPhrase.repository.UserRepository;
import com.tr.mustafakacar.WordToPhrase.responses.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponse setNewWordFrequency(long userId, int newWordFrequency) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.getSetting().setNewWordFrequency(newWordFrequency);
        return new AuthResponse(userRepository.save(user));
    }
}
