package com.example.airbnbcloneback.domain;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Photo {
    @Id
    long id;
    String url;
}
