package com.service;

import java.util.List;

public interface INocatUserService {
    public Iterable<NocatUser> getUsers();
    public void updateUser(long id);
}