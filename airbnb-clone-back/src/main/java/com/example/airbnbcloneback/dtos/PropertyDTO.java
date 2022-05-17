package com.example.airbnbcloneback.dtos;

import com.example.airbnbcloneback.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {
    private Long id;
    private int numberOfRooms;
    private double price;
    private boolean availability;
    private Address address;
    //List<String> photoURLs;
}
