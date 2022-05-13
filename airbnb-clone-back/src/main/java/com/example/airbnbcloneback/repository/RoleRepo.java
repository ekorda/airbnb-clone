package com.example.airbnbcloneback.repository;

import com.example.airbnbcloneback.domain.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<AppRole, Long> {
    AppRole findByName(String name);
}
