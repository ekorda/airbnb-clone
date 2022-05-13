package com.example.airbnbcloneback.repository;

import com.example.airbnbcloneback.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Long> {
    @Query("select distinct p from Property p join p.currentHistory c where c.leaseEndDate <= :date")
    List<Property> getPropertiesWhoseLeaseEndsBefore(LocalDate date);
}
