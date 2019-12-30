package com.github.swapbook.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailServiceImpl implements EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender emailSender;

    public void sendMessage(@NonNull String mail_to, String subject, String text, Boolean isHtml) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(mail_to);

//        if subject is null, make it empty
        helper.setSubject(subject != null ? subject : "");

//       same with text. isHtml by default is false
        helper.setText(text != null ? text : "", isHtml != null ? isHtml : false);

        try {
            emailSender.send(message);
            log.info("Sent mail mail_to: {}, with subject: {}", mail_to, subject);
        } catch (MailException e) {
            log.error("Mail not send", e);
        }
    }
}