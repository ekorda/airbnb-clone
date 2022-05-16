package com.example.airbnbcloneback.service;

import com.example.airbnbcloneback.controller.response.AppUserResponse;
import com.example.airbnbcloneback.controller.response.VerifyPasswordResponse;
import com.example.airbnbcloneback.domain.AppRole;
import com.example.airbnbcloneback.domain.AppUser;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser user);
    AppRole saveRole(AppRole role);
    void addRoleToUser(String userName, String roleName);
    AppUserResponse getUser(String userName);
    List<AppUser> getUsers();
    VerifyPasswordResponse verifyUserPassword(String userName, String password);
    void deactivateAccount(Long id);
    void activateAccount(Long id);
    void resetPassword();
}
