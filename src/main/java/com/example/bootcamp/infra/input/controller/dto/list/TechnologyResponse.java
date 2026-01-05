package com.example.bootcamp.infra.input.controller.dto.list;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder

public class TechnologyResponse {

    private Long id;
    private String name;
}
