package com.example.demo.service;

import com.example.demo.controller.dto.LordCreateRequest;
import com.example.demo.controller.dto.LordDto;
import com.example.demo.repository.entity.LordEntity;
import com.example.demo.repository.entity.PlanetEntity;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.repository.LordRepository;
import com.example.demo.repository.PlanetRepository;
import com.example.demo.service.converter.LordConverter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LordServiceImpl implements LordService {

    private final LordRepository lordRepository;
    private final PlanetRepository planetRepository;

    @Override
    public void save(LordCreateRequest lordCreateRequest) {

        lordRepository.save(LordConverter.convertLordCreateRequestToLordEntity(lordCreateRequest));
    }

    @Override
    @Transactional
    public void designatePlanet(int lordId, int planetId) {

        LordEntity lordEntity = lordRepository
                .findById(lordId)
                .orElseThrow(() -> new BusinessException("Lord not found"));

        PlanetEntity planetEntity = planetRepository
                .findById(planetId)
                .orElseThrow(() -> new BusinessException("Planet not found"));

        planetEntity.setLord(lordEntity);
        lordEntity.addPlanets(planetEntity);
    }

    @Override
    public List<LordDto> getAllSlackerLords() {

        return lordRepository.findAll()
                .stream()
                .filter(lordEntity -> CollectionUtils.isEmpty(lordEntity.getPlanets()))
                .map(LordConverter::convertLordEntityToLordDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LordDto> getTopYoungestLords() {

        return lordRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(LordEntity::getAge))
                .limit(10)
                .map(LordConverter::convertLordEntityToLordDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LordDto> getAll() {

        return lordRepository.findAll()
                .stream()
                .map(LordConverter::convertLordEntityToLordDto)
                .collect(Collectors.toList());
    }
}
