package com.example.bootcamp.domain.model.delete;

import com.example.bootcamp.domain.model.CapabilitySummary;
import com.example.bootcamp.domain.model.TechnologySummary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor

public class BootcampDeleteModel {

    /*private Long id;
    private String name;
    private String description;
    private LocalDate launchDate;
    private Integer duration;

    private List<CapabilitySummary> capabilities;
    private List<TechnologySummary> technologies;*/

    private Long bootcampId;
    private List<Long> deletedCapabilities;
    private List<Long> deletedTechnologies;
}
