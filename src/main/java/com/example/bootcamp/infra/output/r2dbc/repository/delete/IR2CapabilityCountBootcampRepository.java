package com.example.bootcamp.infra.output.r2dbc.repository.delete;

import com.example.bootcamp.infra.output.r2dbc.entity.delete.BootcampCapabilityEntity;
import com.example.bootcamp.infra.output.r2dbc.entity.delete.R2CapabilityCountBootcampEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface IR2CapabilityCountBootcampRepository extends ReactiveCrudRepository<BootcampCapabilityEntity, Long> {

    Mono<Long> countByCapabilityId(Long capabilityId);
}
