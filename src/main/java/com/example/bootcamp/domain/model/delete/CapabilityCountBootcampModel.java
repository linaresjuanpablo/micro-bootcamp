package com.example.bootcamp.domain.model.delete;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder

public class CapabilityCountBootcampModel {

    private Long id;
    private String name;
    private String description;

    private List<Long> bootcampIds;
    private List<Long> technologyIds;

}
