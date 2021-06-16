package com.example.demo.repository;

import com.example.demo.repository.entity.PlanetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<PlanetEntity, Integer> {

}
