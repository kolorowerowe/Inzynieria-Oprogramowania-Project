package com.github.swapbook.email;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.internet.MimeMessage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class EmailServiceImplTest {

    @Rule
    public SmtpServerRule smtpServerRule = new SmtpServerRule(2525);

    @Autowired
    private EmailServiceImpl emailService;

    EmailServiceImplTest() throws Throwable {
    }

    @Test
    public void sendSimpleMessage_shouldSendMail() throws Exception {

        // arrange
        String to = "test@domena.pl";
        String title = "Title";
        String content = "Content";

        // act
        emailService.sendSimpleMessage(to, title, content);

        // assert
        MimeMessage[] receivedMessages = smtpServerRule.getMessages();
        assertEquals(1, receivedMessages.length);

        MimeMessage current = receivedMessages[0];

        assertEquals(title, current.getSubject());
        assertEquals(to, current.getAllRecipients()[0].toString());
        assertTrue(String.valueOf(current.getContent()).contains(content));
    }
}