package com.example.bootcamp.infra.output.entity.delete;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("bootcamp")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class BootcampDeleteEntity {

    @Id
    private Long id;
    private String name;
    private String description;
}
