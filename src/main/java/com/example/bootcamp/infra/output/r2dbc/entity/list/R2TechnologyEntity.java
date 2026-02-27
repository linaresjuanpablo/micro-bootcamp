package com.example.bootcamp.infra.output.r2dbc.entity.list;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("technology")

public class R2TechnologyEntity {

    @Id
    private Long id;

    private String name;
    private Long capabilityId;
}
