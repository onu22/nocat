package com.nocat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NocatUserService implements INocatUserService {

    @Autowired
    private NocatUserRepository nocatUserRepository;

    @Override
    public NocatUser findById(long id) {
        NocatUser user = nocatUserRepository.findById(id);
        return  user;
    }

    @Override
    public void updateLocation( NocatUser updatedUser) {
        long id = updatedUser.getId();
        NocatUser user = nocatUserRepository.findById(id);
        user.setLatLong(updatedUser.getLatLong());
        nocatUserRepository.save(user);
    }

    @Override
    public Iterable<NocatUser> getUsers() {
        Iterable<NocatUser> users = nocatUserRepository.findAll();
        return  users;
    }
}
