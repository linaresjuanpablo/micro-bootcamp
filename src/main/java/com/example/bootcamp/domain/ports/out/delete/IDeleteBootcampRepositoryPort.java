package com.example.bootcamp.domain.ports.out.delete;

import com.example.bootcamp.domain.model.CapabilitySummary;
import com.example.bootcamp.domain.model.delete.CapabilityDeleteSumary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IDeleteBootcampRepositoryPort {

    Mono<Boolean> existsById(Long id);
    Flux<CapabilityDeleteSumary> findCapabilitiesByBootcampId(Long bootcampId);
    Mono<Void> deleteBootcamp(Long bootcampId);
    Mono<Void> deleteBootcampCapabilityRelations(Long bootcampId);
    Mono<Void> deleteBootcampTechnologyRelations(Long bootcampId);

    Mono<Long> countBootcampsByCapability(Long capabilityId);
   // Mono<Void> deleteCapability(Long capabilityId);


}
