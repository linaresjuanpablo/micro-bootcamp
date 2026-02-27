package com.example.bootcamp.domain.model.delete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CapabilityDeleteSumary {

    private Long id;
    private List<TechnologyDeleteSummary> technologies;
}
