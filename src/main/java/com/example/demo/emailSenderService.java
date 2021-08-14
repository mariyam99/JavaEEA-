package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class emailSenderService {

    private MailSender mailSender;

    public void setMailSender(MailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    public void sendEmail(String from, String to, String subject, String message)
    {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        mailSender.send(simpleMailMessage);
    }

}
