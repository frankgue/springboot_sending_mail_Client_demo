package com.gkfcsolution.springboot_sending_mail_client_demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Created on 2025 at 11:54
 * File: null.java
 * Project: springboot_sending_mail_Client_demo
 *
 * @author Frank GUEKENG
 * @date 06/10/2025
 * @time 11:54
 */
@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Envoie un e-mail simple (texte brut)
     *
     * @param toEmail destinataire
     * @param body    contenu du message
     * @param subject objet du mail
     */
    public void sendSimpleEmail(String toEmail, String body, String subject) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("gkfcspringboot@gmail.com");
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject(subject);

            mailSender.send(message);

            System.out.println("✅ Email envoyé avec succès à " + toEmail);
        } catch (Exception e) {
            System.err.println("❌ Échec de l'envoi de l'email : " + e.getMessage());
        }
    }
}
