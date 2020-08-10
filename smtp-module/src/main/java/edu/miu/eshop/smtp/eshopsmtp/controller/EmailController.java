package edu.miu.eshop.smtp.eshopsmtp.controller;

import edu.miu.eshop.smtp.eshopsmtp.entity.Email;
import edu.miu.eshop.smtp.eshopsmtp.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("email")
@RestController
public class EmailController {

    @Autowired
    private EmailUtil emailUtil;

    @PostMapping("/send")
    public ResponseEntity sendMail(@RequestBody Email email) throws IOException {
        String [] to = email.getReceivers(); //"enxbayr@gmail.com"
        String subject = email.getSubject();
        String body = email.getBody();
        String attachmentPaths [] = email.getAttachmentsPath();
        List<File> attachments = new ArrayList<>();

        if(attachmentPaths.length!=0 || attachmentPaths!=null){

            for (String s: attachmentPaths){
                System.out.println(s);
                attachments.add(new File(s));
            }
        }

        emailUtil.sendEmail(to,subject, body, attachments);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Email sent.");
    }
}
