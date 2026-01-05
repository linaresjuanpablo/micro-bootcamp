package com.example.bootcamp.infra.output.r2dbc.entity.list;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.List;

//@Getter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
@Data
@Table("bootcamp")

public class R2BootcampListEntity {

    @Id
    @Column("id")
    private Long id;

   @Column("name")
    private String name;

    @Column("description")
    private String description;

    @Column("launchDate")
    private LocalDate launchDate;

    @Column("duration")
    private Integer duration;

    private List<Long> capabilityIds;
    private Integer capacityCount;
}
