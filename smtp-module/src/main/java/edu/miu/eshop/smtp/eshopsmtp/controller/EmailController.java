package edu.miu.eshop.smtp.eshopsmtp.controller;

import edu.miu.eshop.smtp.eshopsmtp.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;


@RequestMapping("email")
@RestController
public class EmailController {

    @Autowired
    private EmailUtil emailUtil;

    @PostMapping("/send")
    public void sendMail() throws IOException {
        String [] to = {"springmukera@gmail.com"}; //"enxbayr@gmail.com"
        String subject = "Test Message!";
        String body = "This is a test message from smtp module.";

        File attachment1  = new File ("src/main/resources/attachments/testAttachment.txt");
     // File attachment1 = new ClassPathResource("attachments/testAttachment.txt").getFile();
       // File attachment1 = ResourceUtils.getFile("classpath:attachments/testAttachment.txt");
        File[] attachments = new File[] { attachment1 };

        emailUtil.sendEmail(to,subject, body, attachments);
    }
}
