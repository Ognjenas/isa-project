package com.isateam.blooddonationcenter.core.email;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayDeque;
import java.util.Queue;

@RequiredArgsConstructor
@Service
public class EmailService implements IEmailService {

    private final JavaMailSender javaMailSender;

    private static Queue<EmailDetails> queue = new ArrayDeque<>();

    @Value("${spring.mail.username}")
    private String sender;

    public void sendSimpleMail(EmailDetails details) {
        queue.add(details);
    }

    @Scheduled(fixedDelay = 5000)
    private void sendFromQueue() {
        if (queue.isEmpty()) {
            return;
        }
        System.out.println("alo");

        while(!queue.isEmpty()) {
            System.out.println("alo");
            var details = queue.poll();
            if(details.getAttachment() != null) {
                sendWithAttachment(details);
            } else {
                sendWithoutAttachment(details);
            }
        }
    }

    private void sendWithoutAttachment(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getBody());
            mailMessage.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);
            System.out.println("Mail Sent Successfully...");
        }
        catch (Exception e) {
            throw new RuntimeException("Error while Sending Mail");
        }
    }

    private void sendWithAttachment(EmailDetails details) {
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {

            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getBody());
            mimeMessageHelper.setSubject(details.getSubject());
            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));
            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            javaMailSender.send(mimeMessage);
            System.out.println("Mail Sent Successfully...");
        }
        catch (MessagingException e) {
            throw new RuntimeException("Error while Sending Mail");
        }
    }
}
