package com.tr.mustafakacar.WordToPhrase.controller;

import com.tr.mustafakacar.WordToPhrase.entity.UserEntity;
import com.tr.mustafakacar.WordToPhrase.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
private final UserService userService;


    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/signup")
    public UserEntity register(@RequestBody UserEntity userEntity) {
        return userService.addUser(userEntity);
    }
    public String forgotPassword() {
        return "forgotPassword";
    }

}
