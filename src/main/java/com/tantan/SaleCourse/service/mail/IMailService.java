package com.tantan.SaleCourse.service.mail;

import org.springframework.scheduling.annotation.Async;

public interface IMailService {
    @Async
    public void sendMail(String to, String subject, String body);
}
