package com.example.bootcamp.domain.model;

import lombok.Data;

import java.util.List;

@Data

public class BootcampSummary {

    private Long id;
    private String name;
    private List<CapabilitySummary> capability;
}
