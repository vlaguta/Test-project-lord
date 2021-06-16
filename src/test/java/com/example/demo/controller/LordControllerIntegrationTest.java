package com.example.demo.controller;

import com.example.demo.repository.LordRepository;
import com.example.demo.repository.PlanetRepository;
import com.example.demo.repository.entity.LordEntity;
import com.example.demo.repository.entity.PlanetEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.example.demo.utils.FileReadUtils.readJsonFileContentAsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LordControllerIntegrationTest {


    private PlanetEntity planetEntity;
    private List<PlanetEntity> planetsEntity;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private LordRepository lordRepository;
    @Autowired
    private PlanetRepository planetRepository;

    @BeforeEach
    public void setUp() {

        planetEntity = PlanetEntity.builder()
                .name("name")
                .build();

        planetRepository.save(planetEntity);

        planetsEntity = List.of(planetEntity);

        final LordEntity lordEntity = lordRepository.save(LordEntity.builder()
                .name("name")
                .age(1)
                .planets(planetsEntity)
                .build());

        planetEntity.setLord(lordEntity);

        planetRepository.save(planetEntity);

        lordRepository.save(LordEntity.builder()
                .name("name")
                .age(1)
                .build());
    }

    @AfterEach
    public void deleteAll() {

        lordRepository.deleteAll();
        planetRepository.deleteAll();
    }

    @Test
    public void getAllLordsSuccessfullyTest() throws Exception {

        mockMvc.perform(get("/lords")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].name").value("name"))
                .andExpect(jsonPath("$[0].age").value(1))
                .andExpect(jsonPath("$[0].planets.[0].name").value(planetsEntity.get(0).getName()));
    }


    @Test
    public void createLordSuccessfullyTest() throws Exception {

        String content = readJsonFileContentAsString("/files/lordController/create-lord-check-request-success.json");
        mockMvc.perform(post("/lords")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk());

        assertEquals(2, lordRepository.findAll().size());
        LordEntity lordEntity = lordRepository.findById(1).get();
        assertEquals("name", lordEntity.getName());
        assertEquals(1, lordEntity.getAge());
    }

    @Test
    public void getTopYoungestLordsSuccessfullyTest() throws Exception {

        this.mockMvc.perform(get("/lords/top")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].name").value("name"))
                .andExpect(jsonPath("$[0].age").value(1))
                .andExpect(jsonPath("$[0].planets.[0].name").value("name"));
    }

    @Test
    public void getSlackerLordsSuccessfullyTest() throws Exception {

        this.mockMvc.perform(get("/lords/slackers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].name").value("name"))
                .andExpect(jsonPath("$[0].age").value(2))
                .andExpect(jsonPath("$[0].planets").isEmpty());
    }
}
