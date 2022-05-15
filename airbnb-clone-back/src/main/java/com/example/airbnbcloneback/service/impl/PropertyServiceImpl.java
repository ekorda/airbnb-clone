package com.example.airbnbcloneback.service.impl;

import com.example.airbnbcloneback.CustomError.CustomError;
import com.example.airbnbcloneback.domain.AppUser;
import com.example.airbnbcloneback.domain.Property;
import com.example.airbnbcloneback.domain.PropertyHistory;
import com.example.airbnbcloneback.dtos.LeaseDTO;
import com.example.airbnbcloneback.dtos.PropertyDTO;
import com.example.airbnbcloneback.repository.PropertyHistoryRepo;
import com.example.airbnbcloneback.repository.PropertyRepo;
import com.example.airbnbcloneback.repository.UserRepo;
import com.example.airbnbcloneback.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepo propertyRepo;
    private final PropertyHistoryRepo historyRepo;
    private final UserRepo userRepo;

    @Override
    public List<Property> getProperties(Map<String, String> filterParams) {
        Integer numOfRooms = null;
        Boolean available = null;
        String location = null;
        if(filterParams.containsKey("occupied")) available = Boolean.parseBoolean(filterParams.get("occupied"));
        if(filterParams.containsKey("num-of-rooms")) numOfRooms = Integer.parseInt(filterParams.get("num-of-rooms"));
        if(filterParams.containsKey("location")) location = filterParams.get("location");

//        System.out.println(numOfRooms);
//        System.out.println(available);
//        System.out.println(location);

        return propertyRepo.
                findAllByAvailableAndNumberOfRoomsAndLocation(available,numOfRooms,location + "%");
    }


    @Override
    public Property addProperty(PropertyDTO propertyDTO) {
        Property property = new Property(null,
                propertyDTO.getNumberOfRooms(),
                propertyDTO.getPrice(),
                propertyDTO.isAvailability(),
                null,
                null,
                propertyDTO.getAddress(),
                null);
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
    }

    @Override
    public void unListProperty(Long id) {
        Optional<Property> optionalProperty = propertyRepo.findById(id);
        optionalProperty.ifPresent(property -> property.setAvailable(false));
    }

    @Override
    public void leaseProperty(LeaseDTO leaseDTO) throws Exception{
        Optional<Property> optionalProperty = propertyRepo.findById(leaseDTO.getPropertyID());
        if(optionalProperty.isEmpty())
            throw new CustomError("Property Does not exist");
        if(leaseDTO.getLeasePaymentAmount()
                < leaseDTO.getLeaseDuration() * optionalProperty.get().getPrice())
            throw new CustomError("Payment not sufficient for lease duration");

        Property property = optionalProperty.get();
        AppUser tenant = userRepo.getById(leaseDTO.getTenantID());
        PropertyHistory history = new PropertyHistory(null,
                property.getPrice(),
                tenant,
                property,
                LocalDate.now(),
                LocalDate.now().plusMonths(leaseDTO.getLeaseDuration()));
        property.addHistory(history);
        property.setAvailable(false);
        propertyRepo.save(property);
    }

    @Override
    public void deleteProperty(Long id) {
        Optional<Property> property = Optional.of(propertyRepo.getById(id));
        property.ifPresent(value -> value.setDeletedAt(LocalDate.now()));
    }
}
