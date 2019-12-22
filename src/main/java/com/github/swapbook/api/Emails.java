package com.github.swapbook.api;

import com.github.swapbook.email.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Emails {

    @Autowired
    EmailServiceImpl emailService;

    @GetMapping("/api/emails/test")
    public void sendTestMail() {
        emailService.sendSimpleMessage("dominos55555@gmail.com", "TEST SUBJECT", "Ciało");
    }


}
