package com.example.bootcamp.infra.output.r2dbc.repository.delete;

import com.example.bootcamp.infra.output.r2dbc.entity.delete.BootcampCapabilityEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IR2CapabilityCountRepository extends ReactiveCrudRepository<BootcampCapabilityEntity, Long> {

    @Query("SELECT COUNT(*) FROM bootcamp_capability WHERE capability_id = :capabilityId")
    Mono<Long> countBootcampsByCapability(Long capabilityId);
}
