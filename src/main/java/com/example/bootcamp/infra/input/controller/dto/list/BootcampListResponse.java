package com.example.bootcamp.infra.input.controller.dto.list;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder

public class BootcampListResponse {

    private Long id;
    private String name;
    private String description;
    private LocalDate lauchdate;
    private Integer duration;
    private List<CapabilityResponse> capabilities;
}
