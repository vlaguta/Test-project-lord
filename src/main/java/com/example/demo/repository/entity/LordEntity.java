package com.example.demo.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "lord")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lord")
    private List<PlanetEntity> planets = new ArrayList<>();

    public void addPlanets(PlanetEntity planet) {
        if (planets == null) {
            planets = new ArrayList<>();
        }
        this.planets.add(planet);
    }
}
