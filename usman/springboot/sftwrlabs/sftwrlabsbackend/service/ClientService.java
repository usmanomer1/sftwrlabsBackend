/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jakarta.mail.MessagingException
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package usman.springboot.sftwrlabs.sftwrlabsbackend.service;

import jakarta.mail.MessagingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usman.springboot.sftwrlabs.sftwrlabsbackend.dao.IClientRepo;
import usman.springboot.sftwrlabs.sftwrlabsbackend.model.Client;
import usman.springboot.sftwrlabs.sftwrlabsbackend.service.IClientService;
import usman.springboot.sftwrlabs.sftwrlabsbackend.service.IEmailService;

@Service
public class ClientService
implements IClientService {
    @Autowired
    private IClientRepo repo;
    @Autowired
    private IEmailService service;

    @Override
    public Client saveClient(Client client) {
        Client savedClient = (Client)this.repo.save(client);
        try {
            String welcomeEmailContent = this.service.generateWelcomeEmailContent(client.getFirstName());
            this.service.sendEmailWithTemplate(client.getEmail(), "Welcome to SFTWR LABS - Empowering Your Business Starts Here ", welcomeEmailContent);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
        return savedClient;
    }

    @Override
    public List<Client> findAll() {
        return this.repo.findAll();
    }
}
