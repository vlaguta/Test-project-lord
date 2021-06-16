package com.example.demo.service.converter;

import com.example.demo.controller.dto.PlanetCreateRequest;
import com.example.demo.controller.dto.PlanetDto;
import com.example.demo.repository.entity.PlanetEntity;

public class PlanetConverter {

    public static PlanetDto convertPlanetEntityToPlanetDto(PlanetEntity planetEntity) {

        return PlanetDto.builder()
                .id(planetEntity.getId())
                .name(planetEntity.getName())
                .build();
    }

    public static PlanetEntity convertPlanetCreateRequestToPlanetEntity(PlanetCreateRequest planetCreateRequest){

        return PlanetEntity.builder()
                .name(planetCreateRequest.getName())
                .build();
    }
}
