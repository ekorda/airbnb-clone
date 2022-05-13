package com.example.airbnbcloneback.service;

import com.example.airbnbcloneback.domain.AppRole;
import com.example.airbnbcloneback.domain.AppUser;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    AppRole saveRole(AppRole role);
    void addRoleToUser(String userName, String roleName);
    AppUser getUser(String userName);
    List<AppUser> getUsers();
}
