package com.tr.mustafakacar.WordToPhrase.service;

import com.tr.mustafakacar.WordToPhrase.entity.SettingEntity;
import com.tr.mustafakacar.WordToPhrase.entity.UserEntity;
import com.tr.mustafakacar.WordToPhrase.repository.UserRepository;
import com.tr.mustafakacar.WordToPhrase.requests.AuthRequest;
import com.tr.mustafakacar.WordToPhrase.responses.AuthResponse;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
public class AuthService {
    private final UserRepository userRepository;

    private final EmailSenderService emailSenderService;

    public AuthService(UserRepository userRepository, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.emailSenderService = emailSenderService;
    }

    public AuthResponse addUser(UserEntity userEntity) {
        SettingEntity settingEntity = new SettingEntity();
        settingEntity.setNewWordFrequency(10);
        userEntity.setSetting(settingEntity);
        return new AuthResponse(userRepository.save(userEntity));
    }

    public AuthResponse login(AuthRequest authRequest) {
        return new AuthResponse(userRepository.findByUsernameAndPassword(authRequest.getUsername(), authRequest.getPassword()).orElseThrow(()-> new RuntimeException("User not found")));
    }

    public void forgotPassword(String email) throws MessagingException, UnsupportedEncodingException {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Random random = new Random();
        String newPassword = String.valueOf(10000 + random.nextInt(90000));
        userEntity.setPassword(newPassword);
        userRepository.save(userEntity);
        emailSenderService.sendEmail(userEntity.getEmail(), "Your new password is: " + newPassword);


    }
}
