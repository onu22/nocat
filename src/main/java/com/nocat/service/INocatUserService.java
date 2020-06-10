package com.nocat.service;

public interface INocatUserService {
    public Iterable<NocatUser> getUsers();
    public void updateLocation( NocatUser nocatUser);
    NocatUser findById(long id);
}