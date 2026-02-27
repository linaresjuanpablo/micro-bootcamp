package com.example.bootcamp.infra.output.r2dbc.repository;

import com.example.bootcamp.infra.output.r2dbc.entity.R2BootcampEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IR2BootcampRepository extends ReactiveCrudRepository<R2BootcampEntity, Long> {

    @Query("INSERT INTO bootcamp_capability (bootcamp_id, capability_id) VALUES (:bootcampId, :capabilityId)")
    Mono<Void> saveBootcampCapability(Long bootcampId, Long capabilityId);



}
