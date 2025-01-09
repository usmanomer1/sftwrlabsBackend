/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.web.bind.annotation.CrossOrigin
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package usman.springboot.sftwrlabs.sftwrlabsbackend.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usman.springboot.sftwrlabs.sftwrlabsbackend.model.Client;
import usman.springboot.sftwrlabs.sftwrlabsbackend.service.IClientService;
import usman.springboot.sftwrlabs.sftwrlabsbackend.service.IEmailService;
import usman.springboot.sftwrlabs.sftwrlabsbackend.service.ISlackService;

@RestController
@RequestMapping(value={"/api/contacts"})
@CrossOrigin(origins={"https://sftwrlabs.com", "https://www.sftwrlabs.com", "https://sftwrlabs.netlify.app"})
public class ClientController {
    @Autowired
    private ISlackService slackService;
    @Autowired
    private IClientService clientService;
    @Autowired
    private IEmailService emailService;

    @PostMapping(value={"/create"})
    public Client createClient(@RequestBody Client client) {
        Client savedClient = this.clientService.saveClient(client);
        String message = "*New client created:*\n*First Name:* " + client.getFirstName() + "\n*Last Name:* " + client.getLastName() + "\n*Email:* " + client.getEmail() + "\n*Phone:* " + client.getPhone() + "\n*Region:* " + client.getRegion() + "\n*Project Details:* " + client.getProjectDetails() + "\n";
        List<Map<String, Object>> blocks = this.slackService.buildSlackMessageBlocks(message);
        this.slackService.sendMessageToSlackWithBlocks(message, blocks);
        return savedClient;
    }
}
