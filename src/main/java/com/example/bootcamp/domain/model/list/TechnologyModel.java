package com.example.bootcamp.domain.model.list;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class TechnologyModel {

    private Long id;
    private String name;
}
