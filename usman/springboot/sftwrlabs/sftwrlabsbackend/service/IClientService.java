/*
 * Decompiled with CFR 0.152.
 */
package usman.springboot.sftwrlabs.sftwrlabsbackend.service;

import java.util.List;
import usman.springboot.sftwrlabs.sftwrlabsbackend.model.Client;

public interface IClientService {
    public Client saveClient(Client var1);

    public List<Client> findAll();
}
