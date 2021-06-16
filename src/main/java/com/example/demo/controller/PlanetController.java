package com.example.demo.controller;

import com.example.demo.controller.dto.PlanetCreateRequest;
import com.example.demo.controller.dto.PlanetDto;
import com.example.demo.service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/planets")
@RequiredArgsConstructor
public class PlanetController {

    private final PlanetService planetService;

    @PostMapping
    public void create(@RequestBody @Valid PlanetCreateRequest planetCreateRequest) {
        planetService.save(planetCreateRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        planetService.delete(id);
    }

    @GetMapping
    public List<PlanetDto> getAll() {
        return planetService.get();
    }

}
