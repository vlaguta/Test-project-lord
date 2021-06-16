package com.example.demo.controller;

import com.example.demo.controller.dto.LordCreateRequest;
import com.example.demo.controller.dto.LordDto;
import com.example.demo.service.LordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lords")
@RequiredArgsConstructor
public class LordController {

    private final LordService lordService;

    @PostMapping
    public void create(@RequestBody @Valid LordCreateRequest lordCreateRequest) {
        lordService.save(lordCreateRequest);
    }

    @PutMapping("/{lord-id}/planets/{planet-id}")
    public void designatePlanet(@PathVariable("lord-id") int lordId, @PathVariable("planet-id") int PlanetId) {
        lordService.designatePlanet(lordId, PlanetId);
    }

    @GetMapping
    public List<LordDto> getAll() {
        return lordService.getAll();
    }

    @GetMapping("/top")
    public List<LordDto> getTopYoungestLords() {
        return lordService.getTopYoungestLords();
    }

    @GetMapping("/slackers")
    public List<LordDto> getSlackerLords() {
        return lordService.getAllSlackerLords();
    }
}
