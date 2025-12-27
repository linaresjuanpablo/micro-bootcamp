package com.example.bootcamp.infra.input.controller.dto;

import com.example.bootcamp.domain.model.CapabilitySummary;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder

public class BootcampResponse {

    private Long id;
    private String name;
    private String description;
    private LocalDate launchdate;
    private Integer duration;
    private List<Long> capabilityIds;
}
