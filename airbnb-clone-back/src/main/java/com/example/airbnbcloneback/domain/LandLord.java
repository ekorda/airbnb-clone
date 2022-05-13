package com.example.airbnbcloneback.domain;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class LandLord extends AppUser {

    @OneToMany
    List<Property> properties;

}
