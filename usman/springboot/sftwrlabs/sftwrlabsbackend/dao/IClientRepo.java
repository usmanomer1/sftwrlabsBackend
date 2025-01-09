/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.data.jpa.repository.JpaRepository
 */
package usman.springboot.sftwrlabs.sftwrlabsbackend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import usman.springboot.sftwrlabs.sftwrlabsbackend.model.Client;

public interface IClientRepo
extends JpaRepository<Client, Long> {
}
