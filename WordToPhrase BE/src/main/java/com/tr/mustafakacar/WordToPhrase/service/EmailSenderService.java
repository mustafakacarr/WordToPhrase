package com.tr.mustafakacar.WordToPhrase.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailSenderService {
    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender mailSender;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String receiver, String content) throws MessagingException, UnsupportedEncodingException {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(from, "Mustafa KAÇAR");
            helper.setTo(receiver);

            helper.setSubject("WordToPhrase Forgot Password!");
            helper.setText(content, true);

            mailSender.send(message);
            System.out.println("Mail sent successfully to : " + receiver);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}