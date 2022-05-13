package com.example.airbnbcloneback.service;

import com.example.airbnbcloneback.domain.Property;

import java.time.LocalDate;
import java.util.List;

public interface PropertyService {
    List<Property> getProperties();
    Property addProperty(Property property);
    List<Property> getLastNPropertiesRented(int number);
    List<Property> getPropertiesWhoseLeaseEndsBefore(LocalDate date);
}
