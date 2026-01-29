package com.example.bootcamp.infra.output.r2dbc.repository;

import com.example.bootcamp.infra.output.r2dbc.entity.BootcampEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface BootcampRepository extends ReactiveCrudRepository<BootcampEntity, Long> {
}
