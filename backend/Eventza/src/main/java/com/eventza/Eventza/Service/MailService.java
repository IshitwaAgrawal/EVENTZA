package com.eventza.Eventza.Service;

import com.eventza.Eventza.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    JavaMailSender mailSender;

    @Async
    public void sendVerificationEmail(User user){
        String subject = "Please verify your registration.";
        String senderName = "EVENTAZA APP";
        String mailContent = "<p>Dear "+user.getName()+", </p>";
        String site = "http://db5e970c3b25.ngrok.io";
        String verifyUrl = "/verify/"+user.getVerificationToken();
        mailContent += "<p>Please click the link below to verify the registration</p>";
        // <a href="">VERIFY</a>
        mailContent += "<a href=\""+site+verifyUrl+"\">VERIFY</a><br>";
        //mailContent += site+verifyUrl+" , Please access this url!";

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

        try{
            helper.setFrom("${spring.mail.username}",senderName);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(mailContent,true);

            mailSender.send(message);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
