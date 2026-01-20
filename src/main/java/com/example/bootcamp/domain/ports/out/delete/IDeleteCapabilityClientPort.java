package com.example.bootcamp.domain.ports.out.delete;

import com.example.bootcamp.domain.model.delete.TechnologyDeleteSummary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IDeleteCapabilityClientPort {

    Mono<Long> countBootcampByCapability(Long capabilityId);
    Mono<Void> deleteCapability(Long capabilityId);
    Flux<TechnologyDeleteSummary> findTechnologiesByCapabilityId(Long capabilityId);
}
