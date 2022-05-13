package com.example.airbnbcloneback.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    String state;
    String city;
    String line1;
    String line2;
    String zipcode;
}
