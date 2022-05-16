package com.example.airbnbcloneback.repository;

import com.example.airbnbcloneback.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Long> {
    @Query("select distinct p from Property p join p.currentHistory c where c.leaseEndDate <= :date and p.deletedAt is null ")
    List<Property> getPropertiesWhoseLeaseEndsBefore(LocalDate date);
//    List<Property> findAllByAvailable(boolean availability);
//    List<Property> findAllByNumberOfRoomsEquals(int numOfRooms);
//    @Query(" select p from Property p where p.address.city =:location")
//    List<Property> findAllByLocation(String location);
    @Query("select p from Property  p " +
            "where (p.available =: isAvailable or :isAvailable is null )" +
            "and (p.numberOfRooms =: numberOfRoom or :numberOfRoom is null)" +
            "and (p.address.city like :city or :city is null)" +
            "and p.deletedAt is null")
    List<Property> findAllByAvailableAndNumberOfRoomsAndLocation(
            Boolean isAvailable,
            Integer numberOfRoom,
            String city);

    @Query("select distinct sum (p.price) from Property p join fetch p.history where p.address.city =:city")
    Double getTotalIncomeForLocation(String city);
}
