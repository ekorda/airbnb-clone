package com.example.airbnbcloneback.repository;

import com.example.airbnbcloneback.domain.Property;
import com.example.airbnbcloneback.domain.PropertyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PropertyHistoryRepo extends JpaRepository<PropertyHistory, Long> {
    @Query("select p from PropertyHistory h join h.property p order by h.leaseStartDate DESC ")
    List<Property> getLastPropertiesRented();
}
