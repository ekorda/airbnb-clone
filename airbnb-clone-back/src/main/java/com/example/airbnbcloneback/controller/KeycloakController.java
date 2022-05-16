package com.example.airbnbcloneback.controller;

import com.example.airbnbcloneback.domain.AppRole;
import com.example.airbnbcloneback.domain.AppUser;
import com.example.airbnbcloneback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/keycloak/users")
@RequiredArgsConstructor
public class KeycloakController {

    private final UserService userService;


    @GetMapping("/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username){
        return ResponseEntity.ok().body(userService.getUser(username));
    }

    @GetMapping("/{username}/verify-password/{password}")
    public ResponseEntity<?> verifyUserPassword(@PathVariable String username , @PathVariable String password){
        return ResponseEntity.ok().body(userService.verifyUserPassword(username , password));
    }





}
