package com.example.bootcamp.infra.output.r2dbc.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("bootcamp_capability")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BootcampCapabilityEntity {

    @Id
    private Long id;

    @Column("bootcamp_id")
    private Long bootcampId;

    @Column("capability_name")
    private String capabilityName;

}
