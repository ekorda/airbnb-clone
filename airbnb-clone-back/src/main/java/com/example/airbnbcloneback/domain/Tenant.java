package com.example.airbnbcloneback.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Tenant extends AppUser {

    @OneToOne
    Property property;

}
