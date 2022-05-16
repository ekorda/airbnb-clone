package com.example.airbnbcloneback.service.impl;

import com.example.airbnbcloneback.controller.response.AppUserResponse;
import com.example.airbnbcloneback.controller.response.VerifyPasswordResponse;
import com.example.airbnbcloneback.domain.AppRole;
import com.example.airbnbcloneback.domain.AppUser;
import com.example.airbnbcloneback.repository.RoleRepo;
import com.example.airbnbcloneback.repository.UserRepo;
import com.example.airbnbcloneback.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving new user {} ", user.getName());
        user.setPassword(user.getPassword());
        return userRepo.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        log.info("Saving new role {} ", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("Adding role {} to user {} ", roleName, userName);
        AppUser user = userRepo.findByUserName(userName);
        AppRole role = roleRepo.findByName(roleName);
        user.addRole(role);
    }

    @Override
    public AppUserResponse getUser(String userName) {
        log.info("Fetching user {} ", userName);
        AppUser appUser = userRepo.findByUserName(userName);

        if (appUser == null) {
            //exception
        }
        AppUserResponse appUserResponse =
                new AppUserResponse(appUser.getUserName(), String.valueOf(appUser.getId()),
                        appUser.getRoles().stream().map(r -> r.getName()).collect(Collectors.toSet()));

        return appUserResponse;
    }

    @Override
    public VerifyPasswordResponse verifyUserPassword(String userName, String password) {
        log.info("verifying user {} ", userName);
        VerifyPasswordResponse rs = new VerifyPasswordResponse();
        AppUser user = userRepo.findByUserName(userName);

        log.info("verifying password {} with {}", user.getPassword() , password);

        rs.setResult(user.getPassword().equals(password));
        return rs;
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }

    @Override
    public void deactivateAccount(Long id) {

    }

    @Override
    public void activateAccount(Long id) {

    }

    @Override
    public void resetPassword() {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = Optional.ofNullable(userRepo.findByUserName(username));
        if (user.isEmpty()) {
            log.error("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        AppUser appUser = user.get();
        Collection<SimpleGrantedAuthority> authorities = appUser
                .getRoles()
                .stream()
                .map(AppRole::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new User(appUser.getUserName(), appUser.getPassword(), authorities);
    }
}
