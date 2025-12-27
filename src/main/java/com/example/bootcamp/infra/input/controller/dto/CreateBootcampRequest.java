package com.example.bootcamp.infra.input.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter

public class CreateBootcampRequest {

    private String name;
    private String description;
    private LocalDate launchdate;
    private Integer duration;
    private List<Long> capabilityIds;
}
