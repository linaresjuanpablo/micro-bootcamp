package com.example.bootcamp.infra.output.r2dbc.repository;

import com.example.bootcamp.infra.output.r2dbc.entity.BootcampEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface BootcampRepository extends ReactiveCrudRepository<BootcampEntity, Long> {


}
