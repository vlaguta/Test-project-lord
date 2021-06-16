package com.example.demo.service;

import com.example.demo.controller.dto.LordCreateRequest;
import com.example.demo.controller.dto.LordDto;

import java.util.List;

public interface LordService {

    void save(LordCreateRequest lordCreateRequest);

    void designatePlanet(int lordId, int planetId);

    List<LordDto> getAllSlackerLords();

    List<LordDto> getTopYoungestLords();

    List<LordDto> getAll();
}
