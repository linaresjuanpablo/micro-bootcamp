package com.example.bootcamp.domain.model;

import lombok.*;

import java.util.List;


@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor


public class CapabilitySummary {
    private Long id;
    private String name;
    private List<TechnologySummary> technologies;
}
