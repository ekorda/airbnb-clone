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
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor @Slf4j
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepo propertyRepo;
    private final PropertyHistoryRepo historyRepo;
    private final UserRepo userRepo;
    private final ModelMapper mapper;

    @Override
    public List<Property> getProperties(Map<String, String> filterParams) {
        Integer numOfRooms = null;
        Boolean available = null;
        String location = null;
        if(filterParams.containsKey("occupied")) available = Boolean.parseBoolean(filterParams.get("occupied"));
        if(filterParams.containsKey("num-of-rooms")) numOfRooms = Integer.parseInt(filterParams.get("num-of-rooms"));
        if(filterParams.containsKey("location")) location = filterParams.get("location");

        return propertyRepo.
                findAllByAvailableAndNumberOfRoomsAndLocation(available,numOfRooms,location + "%");
    }


    @Override
    public PropertyDTO addProperty(PropertyDTO propertyDTO) {
        Property property = new Property(null,
                propertyDTO.getNumberOfRooms(),
                propertyDTO.getPrice(),
                propertyDTO.isAvailability(),
                null,
                null,
                propertyDTO.getAddress(),
                null);
        log.info("Adding new property {} ", propertyDTO);
        propertyRepo.save(property);
        return mapper.map(property, PropertyDTO.class);
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
        optionalProperty.ifPresent(property -> {
            property.setAvailable(false);
            propertyRepo.save(property);
        });
        log.info("Unlisting property {} ", optionalProperty.get());
    }

    @Override
    public void listProperty(Long id) {
        Optional<Property> optionalProperty = propertyRepo.findById(id);
        optionalProperty.ifPresent(property -> {
            property.setAvailable(true);
            propertyRepo.save(property);
        });
        log.info("listing property {} ", optionalProperty.get());
    }

    @Override
    public void leaseProperty(LeaseDTO leaseDTO) throws Exception{
        Optional<Property> optionalProperty = propertyRepo.findById(leaseDTO.getPropertyID());
        if(optionalProperty.isEmpty())
            throw new CustomError("Property Does not exist");
        if(leaseDTO.getLeasePaymentAmount()
                < leaseDTO.getLeaseDuration() * optionalProperty.get().getPrice())
            throw new CustomError("Payment not sufficient for lease duration");
        if(!optionalProperty.get().isAvailable())
            throw new CustomError("Property cannot be leased at this time");

        Property property = optionalProperty.get();
        AppUser tenant = userRepo.getById(leaseDTO.getTenantID());
        PropertyHistory history = new PropertyHistory(
                property.getPrice(),
                tenant,
                property,
                LocalDate.now(),
                LocalDate.now().plusMonths(leaseDTO.getLeaseDuration()));
        property.addHistory(history);
        property.setAvailable(false);
        propertyRepo.save(property);
        log.info("Leasing property {} ", optionalProperty.get());

    }

    @Override
    public void deleteProperty(Long id) {
        Optional<Property> property = Optional.of(propertyRepo.getById(id));
        property.ifPresent(value -> value.setDeletedAt(LocalDate.now()));
    }

    @Override
    public void updateProperty(Long id, PropertyDTO propertyDTO) {
        Optional<Property> property = Optional.of(propertyRepo.getById(id));
        property.ifPresent(value -> {
          value.setAvailable(propertyDTO.isAvailability());
          value.setAddress(propertyDTO.getAddress());
          value.setNumberOfRooms(propertyDTO.getNumberOfRooms());
          value.setPrice(propertyDTO.getPrice());
        });
        propertyRepo.save(property.get());
    }

    @Override
    public Double getTotalIncomePerLocation(String city) {
        Optional<Double> optional = Optional.ofNullable(historyRepo.getTotalIncomeForLocation(city));
        return optional.orElse(0.0);
    }
}
