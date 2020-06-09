package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NocatUserService implements INocatUserService {

    @Autowired
    private NocatUserRepository nocatUserRepository;

    public Iterable<NocatUser> getUsers() {

        Iterable<NocatUser> users = nocatUserRepository.findAll();
        return  users;
    }

    @Override
    public void updateUser(long id) {
        NocatUser user = nocatUserRepository.findById(id);
        user.setLatLong("48 49");
        nocatUserRepository.save(user);
    }

}
