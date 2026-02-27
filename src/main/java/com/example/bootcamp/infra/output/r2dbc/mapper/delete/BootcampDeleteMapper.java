package com.example.bootcamp.infra.output.r2dbc.mapper.delete;

import com.example.bootcamp.domain.model.delete.CapabilityDeleteSumary;
import com.example.bootcamp.domain.model.delete.TechnologyDeleteSummary;

import java.util.List;
import java.util.stream.Collectors;

public class BootcampDeleteMapper {

    public static CapabilityDeleteSumary toCapabilityDeleteSummay(Long capabilityId){
        return CapabilityDeleteSumary.builder()
                .id(capabilityId)
                .build();
    }

    public static List<TechnologyDeleteSummary> toTechnologyDeleteSummaries(List<Long> technologyIds){
        return technologyIds.stream()
                .map(id -> TechnologyDeleteSummary.builder().id(id).build())
                .collect(Collectors.toList());
    }
}
