package com.tr.mustafakacar.WordToPhrase.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {


    @PostMapping("/login")
    public String login() {
        return "login";
    }
    public String register() {
        return "register";
    }
    public String forgotPassword() {
        return "forgotPassword";
    }

}
