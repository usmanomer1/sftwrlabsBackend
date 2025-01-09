/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.mail.MessagingException
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
 *  org.springframework.web.bind.annotation.GetMapping
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RestController
 */
package usman.springboot.sftwrlabs.sftwrlabsbackend.controller;

import jakarta.mail.MessagingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import usman.springboot.sftwrlabs.sftwrlabsbackend.model.Client;
import usman.springboot.sftwrlabs.sftwrlabsbackend.model.PromotionalContent;
import usman.springboot.sftwrlabs.sftwrlabsbackend.model.Users;
import usman.springboot.sftwrlabs.sftwrlabsbackend.service.IClientService;
import usman.springboot.sftwrlabs.sftwrlabsbackend.service.IEmailService;
import usman.springboot.sftwrlabs.sftwrlabsbackend.service.IPromotionalContentService;
import usman.springboot.sftwrlabs.sftwrlabsbackend.service.UserService;

@RestController
public class UserController {
    @Autowired
    private IClientService clientService;
    @Autowired
    private IPromotionalContentService promotionalContentService;
    @Autowired
    private IEmailService emailService;
    @Autowired
    private IClientService contactService;
    @Autowired
    private UserService service;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping(value={"register"})
    public Users register(@RequestBody Users user) {
        user.setPassword(this.encoder.encode((CharSequence)user.getPassword()));
        System.out.println(user.getPassword());
        return this.service.saveTheUser(user);
    }

    @GetMapping(value={"/all"})
    public List<Client> getAllContacts() {
        return this.clientService.findAll();
    }

    @PostMapping(value={"/send-promotions"})
    public void sendPromotionalEmails() {
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
