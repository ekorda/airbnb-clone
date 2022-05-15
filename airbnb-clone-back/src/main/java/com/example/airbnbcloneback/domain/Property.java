package com.example.airbnbcloneback.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Property {

    @Id @GeneratedValue
    Long id;
    int numberOfRooms;
    double price;
    boolean available;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    List<PropertyHistory> history;
    @OneToOne @ToString.Exclude
    PropertyHistory currentHistory;
//    @OneToMany
//    List<Photo> pictures;
    @Embedded
    Address address;
    private LocalDate deletedAt;



    public void addHistory(PropertyHistory propertyHistory){
        if(history == null)
            history = new ArrayList<>();
        history.add(propertyHistory);
        currentHistory = propertyHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Property property = (Property) o;
        return id != null && Objects.equals(id, property.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
