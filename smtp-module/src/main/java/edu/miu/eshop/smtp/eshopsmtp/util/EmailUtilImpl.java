package edu.miu.eshop.smtp.eshopsmtp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtilImpl implements  EmailUtil{

    @Autowired
    private JavaMailSender sender;

    @Override
    public void sendEmail(String [] toAddress, String subject, String body, File [] attachments){

        MimeMessage mimeMessage = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(body);

            //loop through the attachments a
            for (File file : attachments) {
                FileSystemResource fr = new FileSystemResource(file);
                helper.addAttachment(file.getName(), fr);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(mimeMessage);
    }
}
