package com.gkfcsolution.springboot_sending_mail_client_demo;

import com.gkfcsolution.springboot_sending_mail_client_demo.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SpringbootSendingMailClientDemoApplication {
@Autowired
    private EmailSenderService emailSenderService;
    public static void main(String[] args) {
        SpringApplication.run(SpringbootSendingMailClientDemoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail(){
        emailSenderService.sendSimpleEmail("frankguekeng11@Gmail.com", "Hello Frank From SpringBoot Sender mail body message", "Hello this is the famous subject");
    }

}
