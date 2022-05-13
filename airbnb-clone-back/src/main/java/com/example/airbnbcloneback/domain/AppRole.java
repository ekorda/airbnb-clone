package com.example.airbnbcloneback.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity @NoArgsConstructor @AllArgsConstructor
@Getter
@Setter
@ToString
public class AppRole {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AppRole appRole = (AppRole) o;
        return id != null && Objects.equals(id, appRole.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
