package com.service;

public interface INocatUserService {
    public Iterable<NocatUser> getUsers();
    public void updateUser( NocatUser nocatUser);
    NocatUser findById(long id);
}