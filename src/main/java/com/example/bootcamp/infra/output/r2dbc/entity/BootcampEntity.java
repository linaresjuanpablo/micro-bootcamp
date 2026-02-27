package com.example.bootcamp.infra.output.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("bootcamp")
@NoArgsConstructor
@AllArgsConstructor

public class BootcampEntity {

    @Id
    private Long id;
    private String name;
    private String description;
    private LocalDate launchdate;
    private Integer duration;
}
