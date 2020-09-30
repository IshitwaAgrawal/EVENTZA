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
    public void sendMail(User user,String subject,String senderName,String mailContent){
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

    public void sentContactMail(String name, String email,String userMessage){

        String mailContent = "<p>Dear Eventaza support team</p><br>";
        mailContent += "<p>User-name: " + name + " </p>";
        mailContent += "<p>User-email: " + email + " </p><br>";
        mailContent += "<p>" + userMessage + "</p>";
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try{
            helper.setFrom("${spring.mail.username}", name);
            helper.setTo("eventaza076@gmail.com");
            helper.setSubject("Client message");
            helper.setText(mailContent, true);
            mailSender.send(message);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

}
