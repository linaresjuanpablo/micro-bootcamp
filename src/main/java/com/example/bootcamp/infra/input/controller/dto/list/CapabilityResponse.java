package com.example.bootcamp.infra.input.controller.dto.list;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CapabilityResponse {

    private Long id;
    private String name;
    private List<TechnologyResponse> technologies;
}
