package com.example.bootcamp.domain.model.list;

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

public class BootcampListModel {

    private Long id;
    private String name;
    private String description;
    private LocalDate launchdate;
    private Integer duration;

    private List<CapabilitySummary> capabilities;
}
