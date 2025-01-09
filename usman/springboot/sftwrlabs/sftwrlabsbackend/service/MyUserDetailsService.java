/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.security.core.userdetails.UserDetails
 *  org.springframework.security.core.userdetails.UserDetailsService
 *  org.springframework.security.core.userdetails.UsernameNotFoundException
 *  org.springframework.stereotype.Service
 */
package usman.springboot.sftwrlabs.sftwrlabsbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import usman.springboot.sftwrlabs.sftwrlabsbackend.dao.IUserRepository;
import usman.springboot.sftwrlabs.sftwrlabsbackend.model.UserPrincipal;
import usman.springboot.sftwrlabs.sftwrlabsbackend.model.Users;

@Service
public class MyUserDetailsService
implements UserDetailsService {
    @Autowired
    private IUserRepository repo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.repo.findByUsername(username);
        if (user == null) {
            System.out.println("404 not found");
            throw new UsernameNotFoundException("404 user not found");
        }
        return new UserPrincipal(user);
    }
}
