package com.example.demo.service.converter;

import com.example.demo.controller.dto.LordCreateRequest;
import com.example.demo.controller.dto.LordDto;
import com.example.demo.repository.entity.LordEntity;

import java.util.stream.Collectors;

public class LordConverter {

    public static LordDto convertLordEntityToLordDto(LordEntity lordEntity) {

        return LordDto.builder()
                .id(lordEntity.getId())
                .name(lordEntity.getName())
                .age(lordEntity.getAge())
                .planets(lordEntity.getPlanets()
                        .stream()
                        .map(PlanetConverter::convertPlanetEntityToPlanetDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public static LordEntity convertLordCreateRequestToLordEntity(LordCreateRequest lordCreateRequest) {

        return LordEntity.builder()
                .name(lordCreateRequest.getName())
                .age(lordCreateRequest.getAge())
                .build();
    }
}
