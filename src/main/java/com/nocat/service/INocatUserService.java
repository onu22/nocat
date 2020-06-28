package com.nocat.service;

import com.nocat.NocatUser;

public interface INocatUserService {
    public Iterable<NocatUser> getUsers();
    public void updateLocation( NocatUser nocatUser);
    NocatUser findById(long id);
}