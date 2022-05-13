package com.example.airbnbcloneback.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "history")
public class PropertyHistory {
    @Id
    @GeneratedValue
    private Long id;
//    private double price;
//    @OneToOne
//    private Tenant tenant;
    //@Temporal(TemporalType.DATE)
    @ManyToOne
    private Property property;
    private LocalDate leaseStartDate;
    //@Temporal(TemporalType.DATE)
    private LocalDate leaseEndDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PropertyHistory that = (PropertyHistory) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
