package com.example.bootcamp.infra.output.r2dbc.entity.list;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;
import java.util.List;

@Data
public class R2BootcampListCapabilityEntity {
    private Long id;
    private String name;
    private String description;
    private LocalDate launchDate;
    private Integer duration;

    @Column("capability_ids")
    private List<Long> capabilityIds;
}
