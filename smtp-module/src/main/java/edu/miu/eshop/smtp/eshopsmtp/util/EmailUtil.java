package edu.miu.eshop.smtp.eshopsmtp.util;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public interface EmailUtil {

    void sendEmail(String [] toAddress, String subject, String body, File[] attachment);
}
