package com.example.bootcamp.infra.output.r2dbc.repository;

import com.example.bootcamp.infra.output.r2dbc.entity.R2BootcampEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IR2BootcampRepository extends ReactiveCrudRepository<R2BootcampEntity, Long> {

    //Flux<R2BootcampEntity>
}
