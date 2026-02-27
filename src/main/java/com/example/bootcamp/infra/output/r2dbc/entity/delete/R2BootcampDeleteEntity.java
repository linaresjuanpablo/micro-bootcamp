package com.example.bootcamp.infra.output.r2dbc.entity.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("bootcamp")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class R2BootcampDeleteEntity {

    @Id
    private Long id;
    @Column("name")
    private String name;
    @Column("description")
    private String description;
}
