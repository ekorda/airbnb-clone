package com.example.airbnbcloneback.dtos;

import com.example.airbnbcloneback.domain.AppRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String userName;
    private String password;
    private String role;
}
