package com.example.airbnbcloneback.service;

import com.example.airbnbcloneback.domain.Property;
import com.example.airbnbcloneback.dtos.LeaseDTO;
import com.example.airbnbcloneback.dtos.PropertyDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PropertyService {
    List<Property> getProperties(Map<String, String> filterParams);
    Property addProperty(PropertyDTO propertyDTO);
    List<Property> getLastNPropertiesRented(int number);
    List<Property> getPropertiesWhoseLeaseEndsBefore(LocalDate date);
    void unListProperty(Long id);
    void leaseProperty(LeaseDTO leaseDTO) throws Exception;
    void deleteProperty(Long id);
    void updateProperty(Long id, PropertyDTO propertyDTO);
}
