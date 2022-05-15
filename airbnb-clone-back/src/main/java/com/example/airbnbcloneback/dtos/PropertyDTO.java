package com.example.airbnbcloneback.dtos;

import com.example.airbnbcloneback.domain.Address;
import lombok.Data;
import lombok.Getter;


@Data
public class PropertyDTO {
    private int numberOfRooms;
    private double price;
    private boolean availability;
    private Address address;
}
