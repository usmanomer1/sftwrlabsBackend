/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package usman.springboot.sftwrlabs.sftwrlabsbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usman.springboot.sftwrlabs.sftwrlabsbackend.dao.IUserRepository;
import usman.springboot.sftwrlabs.sftwrlabsbackend.model.Users;
import usman.springboot.sftwrlabs.sftwrlabsbackend.service.IUserService;

@Service
public class UserService
implements IUserService {
    @Autowired
    private IUserRepository repo;

    @Override
    public Users saveTheUser(Users user) {
        return (Users)this.repo.save(user);
    }
}
