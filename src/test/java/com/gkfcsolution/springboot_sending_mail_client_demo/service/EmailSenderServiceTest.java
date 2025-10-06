package com.gkfcsolution.springboot_sending_mail_client_demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Created on 2025 at 14:13
 * File: JUnit5 Test Class.java.java
 * Project: springboot_sending_mail_Client_demo
 *
 * @author Frank GUEKENG
 * @date 06/10/2025
 * @time 14:13
 */
class EmailSenderServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailSenderService emailSenderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSendSimpleEmailSuccessfully() {
        // given
        String to = "test@example.com";
        String subject = "Test Subject";
        String body = "Hello world!";

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);

        // when
        emailSenderService.sendSimpleEmail(to, body, subject);

        // then
        verify(mailSender, times(1)).send(captor.capture());
        SimpleMailMessage sentMessage = captor.getValue();

        assertEquals(to, sentMessage.getTo()[0]);
        assertEquals(subject, sentMessage.getSubject());
        assertEquals(body, sentMessage.getText());
        assertEquals("gkfcspringboot@gmail.com", sentMessage.getFrom());
    }

    @Test
    void shouldHandleMailSendExceptionGracefully() {
        // given
        String to = "test@example.com";
        String subject = "Test Failure";
        String body = "This will fail";

        doThrow(new RuntimeException("SMTP error")).when(mailSender).send(any(SimpleMailMessage.class));

        // when & then (no exception should propagate)
        assertDoesNotThrow(() -> emailSenderService.sendSimpleEmail(to, body, subject));
    }
}