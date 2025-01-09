/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.mail.MessagingException
 *  jakarta.mail.internet.MimeMessage
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.core.io.ClassPathResource
 *  org.springframework.mail.javamail.JavaMailSender
 *  org.springframework.mail.javamail.MimeMessageHelper
 *  org.springframework.stereotype.Service
 */
package usman.springboot.sftwrlabs.sftwrlabsbackend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import usman.springboot.sftwrlabs.sftwrlabsbackend.service.IEmailService;

@Service
public class EmailService
implements IEmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmailWithTemplate(String to, String subject, String body) throws MessagingException {
        MimeMessage message = this.mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("uuk11@outlook.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        this.mailSender.send(message);
    }

    @Override
    public String generatePromotionalEmailContent(String templatePath, String placeholder, String replacement) {
        String template = this.loadEmailTemplate(templatePath);
        return template.replace(placeholder, replacement);
    }

    public String loadEmailTemplate(String templatePath) {
        String string;
        BufferedReader reader = new BufferedReader(new InputStreamReader(new ClassPathResource("templates/" + templatePath).getInputStream(), StandardCharsets.UTF_8));
        try {
            string = reader.lines().collect(Collectors.joining("\n"));
        }
        catch (Throwable throwable) {
            try {
                try {
                    reader.close();
                }
                catch (Throwable throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
            catch (IOException e) {
                throw new RuntimeException("Failed to read email template", e);
            }
        }
        reader.close();
        return string;
    }

    @Override
    public String generateWelcomeEmailContent(String firstName) {
        String template = this.loadEmailTemplate("email-template.html");
        return template.replace("{{firstName}}", firstName);
    }
}
