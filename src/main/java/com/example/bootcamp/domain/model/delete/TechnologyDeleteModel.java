package com.example.bootcamp.domain.model.delete;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TechnologyDeleteModel {

    private Long id;
    private String name;
}
