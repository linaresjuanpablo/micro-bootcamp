package com.example.bootcamp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TechnologySummary {

    private Long id;
    private String name;
}
