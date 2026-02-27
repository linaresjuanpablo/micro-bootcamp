package com.example.bootcamp.infra.output.r2dbc.repository.top;

import com.example.bootcamp.infra.output.r2dbc.entity.BootcampCapabilityEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface BootcampCapabilityRepository extends ReactiveCrudRepository<BootcampCapabilityEntity, Long> {

    @Query("SELECT capability_id FROM bootcamp_capability WHERE bootcamp_id = $1")
    Flux<Long> findCapabilityIdsByBootcampId(Long bootcampId);

}
