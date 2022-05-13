package com.example.airbnbcloneback.service.impl;

import com.example.airbnbcloneback.domain.Property;
import com.example.airbnbcloneback.repository.PropertyHistoryRepo;
import com.example.airbnbcloneback.repository.PropertyRepo;
import com.example.airbnbcloneback.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepo propertyRepo;
    private final PropertyHistoryRepo historyRepo;

    @Override
    public List<Property> getProperties() {
        //return propertyRepo.findLastFewRented(2);
        //return propertyRepo.findLastFewRented();
        return null;
    }

    @Override
    public Property addProperty(Property property) {
        return propertyRepo.save(property);
    }

    @Override
    public List<Property> getLastNPropertiesRented(int number) {
        return historyRepo
                .getLastPropertiesRented()
                .stream()
                .limit(number)
                .collect(Collectors.toList());
    }

    @Override
    public List<Property> getPropertiesWhoseLeaseEndsBefore(LocalDate date) {
        return propertyRepo.getPropertiesWhoseLeaseEndsBefore(date);
        //return null;
    }
}
