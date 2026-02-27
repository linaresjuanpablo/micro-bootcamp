package com.example.bootcamp.domain.model.list;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class CapabilityModel {

    private Long id;
    private String name;
    private List<TechnologyModel> technologies;
}
