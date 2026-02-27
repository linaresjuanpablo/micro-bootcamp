package com.example.bootcamp.infra.output.r2dbc.repository;

import com.example.bootcamp.infra.output.r2dbc.entity.BootcampCapabilityEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CapabilityRepository extends ReactiveCrudRepository<BootcampCapabilityEntity, Long> {
    @Query("SELECT capability_name FROM bootcamp_capability WHERE bootcamp_id = :bootcampId")
    Flux<String> findByBootcampId(Long bootcampId);

}
