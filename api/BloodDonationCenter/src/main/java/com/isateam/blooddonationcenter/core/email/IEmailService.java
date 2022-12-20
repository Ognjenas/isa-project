package com.isateam.blooddonationcenter.core.email;

public interface IEmailService {
    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}
