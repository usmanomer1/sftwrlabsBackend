/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.mail.MessagingException
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.scheduling.annotation.Scheduled
 *  org.springframework.stereotype.Component
 */
package usman.springboot.sftwrlabs.sftwrlabsbackend.utils;

import jakarta.mail.MessagingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import usman.springboot.sftwrlabs.sftwrlabsbackend.model.Client;
import usman.springboot.sftwrlabs.sftwrlabsbackend.model.PromotionalContent;
import usman.springboot.sftwrlabs.sftwrlabsbackend.service.IClientService;
import usman.springboot.sftwrlabs.sftwrlabsbackend.service.IEmailService;
import usman.springboot.sftwrlabs.sftwrlabsbackend.service.IPromotionalContentService;

@Component
public class EmailScheduler {
    @Autowired
    private IClientService contactService;
    @Autowired
    private IPromotionalContentService promotionalContentService;
    @Autowired
    private IEmailService emailService;

    @Scheduled(cron="0 0 9 * * MON")
    public void sendWeeklyPromotionalEmails() {
        List<Client> clients = this.contactService.findAll();
        PromotionalContent content = this.promotionalContentService.getLatestContent();
        if (content != null) {
            for (Client client : clients) {
                try {
                    this.emailService.sendEmailWithTemplate(client.getEmail(), content.getSubject(), content.getContent());
                }
                catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
