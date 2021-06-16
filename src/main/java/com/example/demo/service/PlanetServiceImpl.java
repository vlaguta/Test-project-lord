package com.example.demo.service;

import com.example.demo.controller.dto.PlanetCreateRequest;
import com.example.demo.controller.dto.PlanetDto;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.repository.PlanetRepository;
import com.example.demo.repository.entity.PlanetEntity;
import com.example.demo.service.converter.PlanetConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.service.converter.PlanetConverter.convertPlanetCreateRequestToPlanetEntity;

@Service
@RequiredArgsConstructor
public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository planetRepository;

    @Override
    public void save(PlanetCreateRequest planetCreateRequest) {
        planetRepository.save(convertPlanetCreateRequestToPlanetEntity(planetCreateRequest));
    }

    @Override
    public void delete(int id) {

        PlanetEntity planetEntity = planetRepository
                .findById(id)
                .orElseThrow(() -> new BusinessException("Planet not found"));
        planetRepository.delete(planetEntity);
    }

    @Override
    public List<PlanetDto> get() {

        return (planetRepository.findAll()
                .stream()
                .map(PlanetConverter::convertPlanetEntityToPlanetDto)
                .collect(Collectors.toList()));
    }
}
