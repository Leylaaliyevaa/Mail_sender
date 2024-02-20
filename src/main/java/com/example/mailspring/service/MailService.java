package com.example.mailspring.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public void mailSender() throws MessagingException, IOException {
        System.out.println("Hello im here");
//        sendEmail();
        sendEmailWithAttachment();
        log.info("Email sent successfully!");
    }

    @Scheduled(fixedRate = 50000)
    void sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("leylaaliyevaa15@gmail.com");
        msg.setFrom("leylaaliyevaa15@yahoo.com");
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");
        javaMailSender.send(msg);
    }

    @Scheduled(cron = "0 09 19 * * MON")
    void sendEmailWithAttachment() throws MessagingException, IOException {
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("leylaaliyevaa15@gmail.com");
        helper.setFrom("leylaaliyevaa15@yahoo.com");
        helper.setSubject("Testing from Spring Boot");
        helper.setText("<h1>Check attachment for image!</h1>", true);
        helper.addAttachment("my_photo.png", new ClassPathResource("photo.png"));
        javaMailSender.send(msg);
    }

    @Scheduled(fixedDelay = 5000)
    public void printMessage() {
        System.out.println("Scheduled task executed at: " + new Date());
    }
}




