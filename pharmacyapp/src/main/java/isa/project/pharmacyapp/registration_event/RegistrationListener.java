package isa.project.pharmacyapp.registration_event;

import isa.project.pharmacyapp.model.User;
import isa.project.pharmacyapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {


    @Autowired
    private IUserService service;

    @Qualifier("messageSource")
    @Autowired
    private MessageSource messages;

    @Autowired
    private EmailService emailService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event){
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String subject = "Registration Confirmation";
        String confirmationUrl
                = event.getAppUrl() + "/auth/registrationConfirm?token=" + token;
//        String message = messages.getMessage("message.regSucc", null, event.getLocale());
        String text = "\r\n" + "http://localhost:8080" + confirmationUrl;

        emailService.sendSimpleMessage(user.getEmail(),subject,text);

    }
}
