package com.service;

import java.util.ArrayList;
import java.util.List;

import com.model.NocatUser;
import com.model.NocatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NocatServiceImp implements INocatService {

    @Autowired
    private NocatUserRepository nocatUserRepository;

    public List<String> getAllUsers() {
        List<String> result = new ArrayList<String>();
        List<NocatUser> players = nocatUserRepository.findAll();
        for (NocatUser player : players) {
            result.add(player.getUsername());
        }
        return result;
    }

}
