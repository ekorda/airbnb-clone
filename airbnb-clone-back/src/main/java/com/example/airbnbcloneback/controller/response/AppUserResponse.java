package com.example.airbnbcloneback.controller.response;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AppUserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String userId;
    Set<String> roles;

    public AppUserResponse(String userName, String userId, Set<String> roles) {
        this.userName = userName;
        this.userId = userId;
        this.roles = roles;
    }


}
