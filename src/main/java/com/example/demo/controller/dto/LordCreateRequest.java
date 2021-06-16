package com.example.demo.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LordCreateRequest {

    @NotBlank
    private String name;
    @Min(value = 0, message = "Age must be greater than 0")
    @Max(value = 4999, message = "age must be less than 5000")
    private int age;
}
