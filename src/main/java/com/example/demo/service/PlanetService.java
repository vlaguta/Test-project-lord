package com.example.demo.service;

import com.example.demo.controller.dto.PlanetCreateRequest;
import com.example.demo.controller.dto.PlanetDto;

import java.util.List;

public interface PlanetService {
    void save(PlanetCreateRequest planetCreateRequest);

    void delete(int id);

    List<PlanetDto> get();
}
