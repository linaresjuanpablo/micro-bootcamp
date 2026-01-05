package com.example.bootcamp.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor



public class BootcampModel {

    private Long id;
    private String name;
    private String description;
    private LocalDate launchDate;
    private Integer duration;
    private List<Long> capabilityIds;


}
