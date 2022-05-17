package com.example.airbnbcloneback.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {
    @Id
    Long id;
    String url;
}
