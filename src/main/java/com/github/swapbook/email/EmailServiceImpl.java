package com.github.swapbook.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String mail_to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail_to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
        log.info("Sent mail mail_to: {}, with subject: {}", mail_to, subject);
    }

}