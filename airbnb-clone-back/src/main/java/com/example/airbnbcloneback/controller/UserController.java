package com.example.airbnbcloneback.controller;


import com.example.airbnbcloneback.domain.AppRole;
import com.example.airbnbcloneback.domain.AppUser;
import com.example.airbnbcloneback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody AppUser user){
        return ResponseEntity.created(null).body(userService.saveUser(user));
    }

    @PostMapping("/role")
    public ResponseEntity<?> saveRole(@RequestBody AppRole role){
        return ResponseEntity.created(null).body(userService.saveRole(role));
    }

    @PostMapping("{username}/addrole/{roleName}")
    public ResponseEntity<?> addRoleToUser(@PathVariable String username, @PathVariable String roleName){
        userService.addRoleToUser(username,roleName);
        return ResponseEntity.ok().build();
    }



}
