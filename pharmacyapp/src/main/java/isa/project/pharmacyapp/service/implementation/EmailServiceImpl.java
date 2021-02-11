package isa.project.pharmacyapp.service.implementation;

import isa.project.pharmacyapp.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class EmailServiceImpl implements EmailService {


    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

//    @Resource(name="rabbitTemplate")
//    private RabbitTemplate rabbitTemplate;
//
//    @Value("${mq.topicexchange}")
//    private String exchange;
//
//    @Value("${mq.routekey}")
//    private String routeKey;




    @Async
    @Override
    public void sendSimpleMessage(String to, String subject, String text){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom("http://localhost:8080");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);


//        try{
//            rabbitTemplate.convertAndSend(exchange,routeKey,mailMessage);
//        }
//        catch (Exception e){
//            logger.error("EmailServiceImpl::sendSimpleMessage",e.getMessage());
//        }
        logger.debug(Thread.currentThread().getName());

        javaMailSender.send(mailMessage);


    }

}
