package com.example.bootcamp.infra.output.repository;

import com.example.bootcamp.infra.output.entity.BootcampEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IBootcampRepository extends ReactiveCrudRepository<BootcampEntity, Long> {
}
