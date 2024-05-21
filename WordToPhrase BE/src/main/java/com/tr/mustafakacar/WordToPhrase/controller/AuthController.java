package com.tr.mustafakacar.WordToPhrase.controller;

import com.tr.mustafakacar.WordToPhrase.entity.UserEntity;
import com.tr.mustafakacar.WordToPhrase.requests.AuthRequest;
import com.tr.mustafakacar.WordToPhrase.responses.AuthResponse;
import com.tr.mustafakacar.WordToPhrase.service.AuthService;
import com.tr.mustafakacar.WordToPhrase.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }

    @PostMapping("/signup")
    public AuthResponse register(@RequestBody UserEntity userEntity) {
        return authService.addUser(userEntity);
    }

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestParam String email) throws MessagingException, UnsupportedEncodingException {
        authService.forgotPassword(email);
    }

    @PostMapping("/{userId}/newWordFrequency")
    public AuthResponse setNewWordFrequency(@PathVariable long userId, @RequestParam int newWordFrequency) {
        return userService.setNewWordFrequency(userId, newWordFrequency);
    }
}
