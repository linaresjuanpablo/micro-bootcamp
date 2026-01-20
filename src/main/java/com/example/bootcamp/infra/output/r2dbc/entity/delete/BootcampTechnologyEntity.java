package com.example.bootcamp.infra.output.r2dbc.entity.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("bootcamp_technology")

public class BootcampTechnologyEntity {
    @Id
    private Long id;
    private Long bootcampId;
    private Long technologyId;
}
