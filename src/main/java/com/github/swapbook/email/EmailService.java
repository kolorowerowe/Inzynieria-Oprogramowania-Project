package com.github.swapbook.email;

import javax.mail.MessagingException;

public interface EmailService {
    void sendMessage(String to, String subject, String text, Boolean isHtml) throws MessagingException;

}
