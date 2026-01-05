package com.example.bootcamp.infra.output.r2dbc.entity.list;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("capability")
public class R2CapabilityEntity {

    @Id
    private Long id;

    private String name;
    private Long bootcampId;
}
