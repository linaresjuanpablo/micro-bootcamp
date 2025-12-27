package com.example.bootcamp.infra.output.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("bootcamp")

public class BootcampEntity {


    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("launchdate")
    private LocalDate launchdate;

    @Column("duration")
    private Integer duration;
}
