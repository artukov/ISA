package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMessage(String to, String subject, String text){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom("http://localhost:8080");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);


    }

}
